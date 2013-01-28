
package ru.oscar.core;

import ru.oscar.icq.packet.ParserPackage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.SocketFactory;
import ru.oscar.DataWork;

/**
 * Класс клиент.
 * @author Kornackiy Alexsandr
 */

public class Client implements Runnable {    
    
    public static final String THREAD_NAME = "ClientThread";    
    
    private BlockingQueue<byte[]> queueIncoming; // Входящие
  
    private Socket socketClient;    
    private SocketFactory factory;
    private InputStream in;
    private OutputStream out;
    private Thread runner;
    private InPacket inpacket;
    private PacketHandler handler;// парсер пакетов
    private Connect connect;
    
    private boolean running = true;
    
    private String host;
    
    private int port;
    private final int timeout = 30;
    private long timeSupport = 0;
    private int pingTimeout;
    
    /**
     * Конструктор клиента.
     * Создаст сокет.
     * @param host
     * @param port
     * @param recognizer 
     */
    
    public Client(String host, int port, ParserPackage recognizer, Connect connect){  
        this(host, port, recognizer, null, connect);    
    }
    
    /**
     * Конструктор клиента.
     * Создаст сокет.
     * @param host
     * @param port
     * @param recognizer
     * @param factory 
     */
    
    public Client(String host, int port, PacketHandler handler, SocketFactory factory, Connect connect) {
        queueIncoming = new LinkedBlockingQueue<byte[]>(50);
        this.handler = handler;     
        this.host = host;
        this.port = port;
        this.factory = factory;
        runner = new Thread(this, THREAD_NAME);
        pingTimeout = 30;
        this.connect = connect; 
    }   
    
    /**
     * Подключение
     * @throws IOException 
     */
    
    public synchronized void connect() throws IOException {
    	if (factory == null) {
    		socketClient = new Socket();
                socketClient.connect(new InetSocketAddress(host, port), timeout*1000);
    	} else {
    		socketClient = factory.createSocket(host, port);
    	}
    	out = socketClient.getOutputStream();
    	in = socketClient.getInputStream();
        inpacket = new InPacket(this);
        timeSupport = System.currentTimeMillis();
        runner.start();       
    }   
    
    /**
     * Отключение
     * @throws IOException 
     */
    
    public synchronized void disconnect() throws IOException {
        try {
        	if (socketClient != null){
        		socketClient.shutdownInput();
                }
        }finally {
        	try {
        		if (socketClient != null){
        			socketClient.shutdownOutput();
                        }
        	}finally {
        		try {
        			socketClient.close();
        		}finally {
        			running = false;
        			inpacket.stop();
                                queueIncoming.clear();
        		}
        	}
        }
    }    
    
    /**
     * Вначале читаем 6 байт заголовка, узнаем длину данных,
     * отправляем пакет на дальнейшую обработку.
     */

    @Override
    public void run() {
        byte header[] = new byte[6];
        byte packet[] = null;
        int packetLen = 0;
        boolean waitData = false;
        try {
            while (running) {
            	if (!waitData) {
                    if (in.available() >= 6) {
                        in.read(header, 0, 6);
                        packetLen = DataWork.getWord(header, 4);
                        packet = new byte[packetLen + 6];
                        waitData = true;
                    }
                } else {
                    if (in.available() >= packetLen) {
                        in.read(packet, 6, packetLen);
                        System.arraycopy(header, 0, packet, 0, 6);
                        queueIncoming.put(packet);       
                        waitData = false;
                    }
                }

            	if (in.available() == 0){  
                    Thread.sleep(200);
                    if((System.currentTimeMillis() - timeSupport) >= pingTimeout * 1000){   
                        timeSupport = System.currentTimeMillis();
                        connect.sendKeepAlive();
                }
                }
            }
        }catch (IOException ex) {
            System.err.println("Client run()" + ex.getMessage());
        }catch (InterruptedException ex) {
            System.err.println("Client run()" + ex.getMessage());
        }           
    }

    public BlockingQueue<byte[]> getQueueIncoming(){
        return queueIncoming;
    }
    
    public PacketHandler getHandler(){
        return handler;
    }
    
    /**
     * Отправить пакет
     * @param packet
     * @throws IOException 
     */
    
    public void sendPacket(byte[] packet) throws IOException {          
        timeSupport = System.currentTimeMillis();
        out.write(packet);
        out.flush();
    }    
    
    public byte[] getInetaddress() {
        return socketClient.getLocalAddress().getAddress();
    }    
    
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }
    

    
}
