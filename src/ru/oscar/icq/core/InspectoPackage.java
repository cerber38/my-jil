
package ru.oscar.icq.core;

/**
 * Класс будет проверять очередь и передавать пакеты на обработку или на отправку.
 * @author Kornackiy Alexsandr
 */
public class InspectoPackage implements Runnable {
    
    public static final String THREAD_NAME = "InspectoPackage";
    
    private Thread thread;
    private Client client;
    
    private boolean runnable;
    
    public InspectoPackage(Client client) {
        this.client = client;
        runnable = true;
        thread = new Thread(this, THREAD_NAME);
        thread.start();
    }    

    @Override
    public void run() {
        while(runnable) {  
            if(!client.getQueue().isEmpty()){
                try {
                    
                    client.getRecognizer().incoming(client.getQueue().take());

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
