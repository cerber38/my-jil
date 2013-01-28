
package ru.oscar.util;

/**
 * @author Kornackiy Alexsandr
 */

public class StringUtil {
    
	public static byte[] encryptPassword1(String password) {
		byte bytePassword[] = password.getBytes();
		byte pass[] = new byte[bytePassword.length];
		final byte[] xorValues = { (byte) 0xF3, (byte) 0x26, (byte) 0x81,
				(byte) 0xC4, (byte) 0x39, (byte) 0x86, (byte) 0xDB, (byte) 0x92 };

		for (int i = 0, j = 0; i < bytePassword.length; i++, j++) {
			if (j >= xorValues.length) {
				j = 0;
			}
			pass[i] = (byte) ((byte) (bytePassword[i]) ^ (byte) (xorValues[j]));
		}

		return pass;
	}   
        
    public static String ucs2beByteArrayToString(byte[] arr, int off, int len) {
    	// Length check
        if ((off + len > arr.length) || (len % 2 != 0)) {
            return "";
        }

        // Convert from byte array to string
        //StringBuffer sb = new StringBuffer();
        StringBuilder sb = new StringBuilder();

        for (int i = off; i < off+len; i += 2)
        {
        	char ch = (char) ((((int) arr[i]) << 8) & 0x0000FF00 | (((int) arr[i+1])) & 0x000000FF);
            sb.append(ch);
        }

        return sb.toString();
    }       
    
    public static String byteArrayWin1251ToString(byte buf[], int pos, int len) {
    	// Length check
        if (pos + len > buf.length) {
            return "";
        }        
            try {
                return new String(buf, pos, len, "Windows-1251");
            } catch (Exception e) {
            }
        int end = pos + len;
        //StringBuffer stringbuffer = new StringBuffer(len);
        StringBuilder stringbuffer = new StringBuilder();
        for(int i = pos; i < end; ++i) {
            int ch = buf[i] & 0xff;
            switch (ch) {
                case 168:
                    stringbuffer.append('\u0401');
                    break;
                case 184:
                    stringbuffer.append('\u0451');
                    break;
                    
                    /* Ukrainian CP1251 chars section */
                case 165:
                    stringbuffer.append('\u0490');
                    break;
                case 170:
                    stringbuffer.append('\u0404');
                    break;
                case 175:
                    stringbuffer.append('\u0407');
                    break;
                case 178:
                    stringbuffer.append('\u0406');
                    break;
                case 179:
                    stringbuffer.append('\u0456');
                    break;
                case 180:
                    stringbuffer.append('\u0491');
                    break;
                case 186:
                    stringbuffer.append('\u0454');
                    break;
                case 191:
                    stringbuffer.append('\u0457');
                    break;
                    /* end of section */
                    
                default:
                    try {
                        if (ch >= 192 && ch <= 255) {
                            stringbuffer.append((char) ((1040 + ch) - 192));
                        } else {
                            stringbuffer.append((char)ch);
                        }
                    } catch (Exception e) {
                    }
                    break;
            }
        }
        return removeCr(stringbuffer.toString());
    }  
    
	public static byte[] stringToUcs2beByteArray(String s) {
		String str = restoreCrLf(s);
        byte[] ucs2be = new byte[str.length() * 2];
        for (int i = 0; i < str.length(); i++) {
           ucs2be[i*2] = (byte) (((int) str.charAt(i) >> 8) & 0x000000FF);
           ucs2be[i*2+1] = (byte) (((int) str.charAt(i)) & 0x000000FF);
        }

        return ucs2be;
    }    
        
	public static String utf8ByteArrayToString(byte[] arr, int off, int len){          
            // Length check
            if (off + len > arr.length) {
                return "";
            }

            // Remove \0's at the end
            while ((len > 0) && (arr[off + len - 1] == 0x00)){
                len--;
            }

            try{

            return new String(arr, off, len, "UTF-8");
            } catch (Exception e) {
                return null;
            }
	}          
        
	public static String restoreCrLf(String s) {
        //StringBuffer result = new StringBuffer();
            StringBuilder result = new StringBuilder();

        int size = s.length();
        for (int i = 0; i < size; i++) {
            char chr = s.charAt(i);
            if (chr == '\r') continue;
            if (chr == '\n') result.append("\r\n");
            else result.append(chr);
        }
        return result.toString();
    }        
    
    public static String removeCr(String s) {
        if (s.indexOf('\r') < 0) {
            return s;
        }
        if (-1 == s.indexOf('\n')) {
            return s.replace('\r', '\n');
        }
        
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            if ((chr == 0) || (chr == '\r')) continue;
            result.append(chr);
        }
        return result.toString();
    }    
        
        public static String stringOfBytes(byte[] data, int index, int lenght){
            return new String(data, index, lenght);
        }
        
        public static String stringOfBytes(byte[] data){
            return new String(data);
        }        
        
        public static byte[] bytesOfStringUTF8(String data){
            try{
            return data.getBytes("UTF-8");
            } catch (Exception e) {
                return null;
            }
        }  
        
        public static byte[] bytesOfString(String data){
            return data.getBytes();
        }          

	public static String getAddress(String s) {
		int dbpt = s.indexOf(':');
		return s.substring(0, dbpt);
	}

	public static int getPort(String s) {
		int dbpt = s.indexOf(':');
		return Integer.parseInt(s.substring(dbpt + 1, s.length()));
	}
        
        public static boolean isEmpty(String string) {
            return string == null || string.length() < 1;
        }        
    
}
