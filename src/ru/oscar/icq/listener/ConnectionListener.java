
package ru.oscar.icq.listener;

/**
 * @author Kornackiy Alexsandr
 */
public interface ConnectionListener {
    
    public void successfulConnected();
    
    public void failureConnection(String message);
    
    public void breakConnection(String message);
    
    public void errorNotification(int family, int subTupe, int errorCode);
    
}
