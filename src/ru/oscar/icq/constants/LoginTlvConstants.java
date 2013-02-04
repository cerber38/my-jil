
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public interface LoginTlvConstants {
    
    /* TLV.Type(0x01) - screen name (uin) */
    public static final int TLV_TYPE_SCREEN_NAME = 0x01;
    /* TLV.Type(0x02) - roasted password */
    public static final int TLV_TYPE_ROASTED_PASSWORD = 0x02;    
    /* TLV.Type(0x03) - client id string */
    public static final int TLV_TYPE_CLIENT_ID_STRING = 0x03;  
    /* TLV.Type(0x05) - reconnect here */
    public static final int TLV_TYPE_RECONNECT_HERE = 0x05; 
    /* TLV.Type(0x06) - authorization cookie */
    public static final int TLV_TYPE_AUTHORIZATION_COOKIE = 0x06;  
    /* TLV.Type(0x08) - error subcode */
    public static final int TLV_TYPE_ERROR_SUBCODE = 0x08;  
    /* TLV.Type(0x16) - client id */
    public static final int TLV_TYPE_CLIENT_ID = 0x16;  
    /* TLV.Type(0x17) - client major version */
    public static final int TLV_TYPE_CLIENT_MAJOR_VERSION = 0x17;  
    /* TLV.Type(0x18) - client minor version */
    public static final int TLV_TYPE_CLIENT_MINOR_VERSION = 0x18;  
    /* TLV.Type(0x19) - client lesser version */
    public static final int TLV_TYPE_CLIENT_LESSER_VERSION = 0x19;  
    /* TLV.Type(0x1A) - client build number */
    public static final int TLV_TYPE_CLIENT_BUILD_NUMBER = 0x1A;  
    /* TLV.Type(0x14) - distribution number */
    public static final int TLV_TYPE_CLIENT_DISTRIBUTION_NUMBER = 0x14;  
    /* TLV.Type(0x0F) - client language */
    public static final int TLV_TYPE_CLIENT_LANGUAGE = 0x0F;  
    /* TLV.Type(0x0E) - client country */
    public static final int TLV_TYPE_CLIENT_ID_COUNTRY = 0x0E;          
    /* TLV.Type(0x25) - password md5 hash */
    public static final int TLV_TYPE_PASSWORD_MD5_HASH = 0x25;
    
    public static final int TLV_TYPE_UNKNOWN0_MD5_AUTH = 0x4B; 
    public static final int TLV_TYPE_UNKNOWN1_MD5_AUTH = 0x5A;    
}
