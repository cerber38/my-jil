
package ru.oscar.util;

import java.util.Arrays;

/**
 * byte work
 * @author Kornackiy Alexsandr
 */

public class ByteUtil {
    
    public static boolean eguals(byte[] a, byte [] b){
         return Arrays.equals(a, b);
    }    
    
    public static long toLong(byte[] b){
        long l = 0;
        l |= b[0] & 0xFF;
        l <<= 8;
        l |= b[1] & 0xFF;
        l <<= 8;
        if (b.length > 3) {
            l |= b[2] & 0xFF;
            l <<= 8;
            l |= b[3] & 0xFF;
        }
        return l;
    }
}
