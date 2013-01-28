
package ru.oscar.util;

import java.util.Random;

/**
 * @author Kornackiy Alexsandr
 */

public class Util {
    
    private static Random rand = new Random(System.currentTimeMillis());
    
    public static int nextRandInt() {
        return Math.abs(Math.max(Integer.MIN_VALUE + 1, rand.nextInt()));
    }   
    
    

}
