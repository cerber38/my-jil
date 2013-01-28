
package ru.oscar.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author Kornackiy Alexsandr
 */
public class Crypto {

  public static byte[] digest(byte[] data, String algorithm){
     try {   
        MessageDigest md = MessageDigest.getInstance(algorithm);  
        return md.digest(data);
    } catch (NoSuchAlgorithmException ex) {
        return null;
    }
  }


}