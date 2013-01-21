
package ru.oscar.icq.core.api.listener;

/**
 * @author Kornackiy Alexsandr
 */
public interface ListenerConnection {
    
    public void successfulConnected();
    
    public void failureConnection(String message);
    
    public void breakConnection(String message);
    
    public void errorNotification(int family, int subTupe, int errorCode);
    
}
