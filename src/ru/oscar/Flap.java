
package ru.oscar;

import ru.oscar.icq.constants.ChannelsConstants;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.icq.constants.TlvConstants;

/**
 * FLAP protocol 
 * @author Kornackiy Alexsandr
 */

public class Flap extends BasicData implements ChannelsConstants, TlvConstants, SsiConstants{
    
    private final int startMarker = 0x2a;    
    
    private int chanel;
    private int sequence;    
    
    private Snac snac;
    
    public Flap(){
        putHeader(DataWork.putByte(startMarker));
        modifyHeader = true;
    }
    
    public Flap(int chanel){    
        this();
        putHeader(DataWork.putByte(chanel));
    }
    
    public Flap(byte[] array, boolean hasSnac){       
        chanel = DataWork.getByte(array, 1);
        sequence = DataWork.getWord(array, 2) > 0xfff ? 0 : DataWork.getWord(array, 2);
        putHeader(DataWork.putByte(chanel));
        putHeader(DataWork.putWord(sequence));
	if (hasSnac){
            snac = new Snac(array, 6);
            putData(new Data(snac.getByteArray()));
        }else{
            byte[] data = new byte[array.length - 6];
            System.arraycopy(array, 6, data, 0, array.length - 6);
            putData(new Data(data));
	}                 
    }
    
    public void setSeq(int sequence){
        if (sequence > 0xfff){
            this.sequence = 0;
        } else {
            this.sequence = sequence;
        }       
        putHeader(DataWork.putWord(this.sequence));
    }   
    
    public void setChanel(int chanel){
        this.chanel = chanel;
        putHeader(DataWork.putByte(this.chanel));
    }

    public void addFlapData(byte[] array, int index, int length){        
        byte[] data = new byte[length];
        System.arraycopy(array, index, data, 0, length);
        addFlapData(new Data(data));
    }    
    
    public void addFlapData(Data data){        
        putData(data);
    }
    
    public void addSnac(Snac snac){
        this.snac = snac;
        addFlapData(new Data(snac.getByteArray()));
    }
    
    public void addTlv(Tlv tlv){
        addFlapData(new Data(tlv.getByteArray()));
    }

    public Snac getSnac(){
        return snac;
    }    
}
