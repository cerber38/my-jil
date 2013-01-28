
package ru.oscar.core;

/**
 * @author Kornackiy Alexsandr
 */

public abstract class PacketHandler {
    
    public abstract boolean authorization(byte[] array);
    
    public abstract void parser(byte[] array);
    
    
    
}
