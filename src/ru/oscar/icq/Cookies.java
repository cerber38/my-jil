
package ru.oscar.icq;

import java.util.HashMap;

/**
 * @author Kornackiy Alexsandrр
 */
public class Cookies {   

    private HashMap<Integer, Object> cookieHash;
    
    public Cookies(){
        cookieHash = new HashMap<Integer, Object>();
    }    
    
    public void set(int key, Object o){
        cookieHash.put(key, o);
    }
    
    public Object get(int key){
        return cookieHash.get(key);
    }
    
    public boolean isKey(int key){
        return cookieHash.containsKey(key);    
    }
    
    public void remove(int key){
        cookieHash.remove(key);
    }
    
    public void clear(){
        cookieHash.clear();
    }
    } 
    
    