/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (13, 2)
 * Запрос параметров SSI
 * @author Kornackiy Alexsandr
 */

public class RequestParametersSsi extends Flap {

    public RequestParametersSsi(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x02, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}