
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */
public class ErrorConstants {
    
	private final int INVALID_SNAC = 0x01;
	private final int RATE_TO_HOST = 0x02;
	private final int RATE_TO_CLIENT = 0x03;
	private final int NOT_LOGGED_ON = 0x04;
	private final int SERVICE_UNAVAILABLE = 0x05;
	private final int SERVICE_NOT_DEFINED = 0x06;
	private final int OBSOLETE_SNAC = 0x07;
	private final int NOT_SUPPORTED_BY_HOST = 0x08;
	private final int NOT_SUPPORTED_BY_CLIENT = 0x09;
	private final int REFUSED_BY_CLIENT = 0x0A;
        private final int REPLY_TOO_BIG = 0x0B;
	private final int RESPONSES_LOST = 0x0C;
	private final int REQUEST_DENIED = 0x0D;
	private final int BUSTED_SNAC_PAYLOAD = 0x0E;
	private final int INSUFFICIENT_RIGHTS = 0x0F;
        private final int RECIPIENT_BLOCKED = 0x010;
        private final int SENDER_TO_EVIL = 0x011;
        private final int RECEIVER_TOOEVIL = 0x012;
        private final int USER_UNAVAILBLE = 0x013;
        private final int NO_MATCH = 0x014;
        private final int LIST_OVERFLOW = 0x015;
	private final int REQUEST_AMBIGUOUS = 0x016;
	private final int SERVER_QUEUE_FULL = 0x017;
	private final int NOT_WHILE_ON_AOL = 0x018;
        
        private int code;
        
        public ErrorConstants(int code){
            this.code = code;
        }
        
        public String toString(){
            switch(code){
                case INVALID_SNAC:
                    return "Invalid SNAC header.";
                case RATE_TO_HOST:
                    return "Server rate limit exceeded.";         
                case RATE_TO_CLIENT:
                    return "Client rate limit exceeded.";        
                case NOT_LOGGED_ON:
                    return "Recipient is not logged in.";        
                case SERVICE_UNAVAILABLE:
                    return "Requested service unavailable.";    
                case SERVICE_NOT_DEFINED:
                    return "Requested service not defined.";   
                case OBSOLETE_SNAC:
                    return "You sent obsolete SNAC.";  
                case NOT_SUPPORTED_BY_HOST:
                    return "Not supported by server.";    
                case NOT_SUPPORTED_BY_CLIENT:
                    return "Not supported by client.";    
                case REFUSED_BY_CLIENT:
                    return "Refused by client.";       
                case REPLY_TOO_BIG:
                    return "Reply too big.";  
                case RESPONSES_LOST:
                    return "Responses lost.";        
                case REQUEST_DENIED:
                    return "Request denied.";    
                case BUSTED_SNAC_PAYLOAD:
                    return "Incorrect SNAC format.";          
                case INSUFFICIENT_RIGHTS:
                    return "Insufficient rights.";       
                case RECIPIENT_BLOCKED:
                    return "In local permit/deny (recipient blocked).";    
                case SENDER_TO_EVIL:
                    return "Sender too evil.";           
                case RECEIVER_TOOEVIL:
                    return "Receiver too evil.";    
                case USER_UNAVAILBLE:
                    return "User temporarily unavailable.";  
                case NO_MATCH:
                    return "No match.";     
                case LIST_OVERFLOW:
                    return "List overflow.";      
                case REQUEST_AMBIGUOUS:
                    return "Request ambiguous.";     
                case SERVER_QUEUE_FULL:
                    return "Server queue full."; 
                case NOT_WHILE_ON_AOL:
                    return "Not while on AOL.";                     
            }
            return "Unknown error (" + code + ")";
        }
        
        public int getCode(){
            return code;
        }
}
