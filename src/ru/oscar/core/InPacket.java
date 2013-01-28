
package ru.oscar.core;

/**
 * Класс будет проверять очередь и передавать пакеты на обработку.
 * @author Kornackiy Alexsandr
 */
public class InPacket implements Runnable {
    
    public static final String THREAD_NAME = "IncomingPackage";
    
    private Thread thread;
    private Client client;
    
    private boolean runnable;
    
    public InPacket(Client client) {
        this.client = client;
        runnable = true;
        thread = new Thread(this, THREAD_NAME);
        thread.start();
    }    

    @Override
    public void run() {
        while(runnable) {  
            if(!client.getQueueIncoming().isEmpty()){
                try {
                    
                    client.getHandler().parser(client.getQueueIncoming().take());

                } catch (InterruptedException ex) {
                   // ..
               }
            } else {
                try{                    
                    Thread.sleep(200);
                }catch (InterruptedException ex) {
                    
                }
            }
        }
    }    
    
    public synchronized void stop() {
        runnable = false;
    }    
    
}
