
package ru.oscar.icq.util;

public class Dumper {
    
	private Dumper() {}

	public static String dump(byte[] packet, boolean stringTranslation) {
		return dump(packet, stringTranslation, 4, 8);
	}

	public static String dump(byte[] packet, boolean stringTranslation,
			int spacer, int breaker) {
		String hexa = "";
		int i;
		byte[] hexaChar = new byte[breaker];
		byte[] lastLine;

		for (i = 1; i < packet.length + 1; i++) {
			hexa += toUnsignedHex(packet[i - 1]) + " ";

			if (i % spacer == 0) {
				hexa += " ";
				if (i % breaker == 0) {
					if (stringTranslation) {
						System.arraycopy(packet, i - breaker, hexaChar, 0, breaker);
						hexa += stringTranslation(hexaChar);
					}
					hexa += "\n";
				}
			}
		}
		if (stringTranslation) {
			i--;
			lastLine = new byte[i % breaker];
			System.arraycopy(packet, i - (i % breaker), lastLine, 0, i % breaker);
			hexa += align(i % breaker, breaker);
			hexa += stringTranslation(lastLine);
		}

		return hexa;
	}

	private static String toUnsignedHex(byte b) {
		String hex = Integer.toHexString(b & 0xFF);

		if (hex.length() == 1)
			hex = "0" + hex;

		return hex.toUpperCase();
	}

	private static String stringTranslation(byte[] array) {
		String ent = new String(array);
		String res = new String();

		for (int i = 0; i < ent.length(); i++) {
			if (Character.getType(ent.charAt(i)) == Character.CONTROL)
				res += ".";
			else
				res += ent.charAt(i);
		}

		return res;
	}
        
	private static String align(int wrote, int expected) {
		String result = "";
		for (int i = 0; i < expected - wrote; i++) {
			result += "   ";
		}
		result += "  ";

		return result;
	}
        
        public static void println(String message, byte[] packet, boolean stringTranslation,
                        int spacer, int breaker) {
                System.out.println(message + "\n" + dump(packet, stringTranslation, spacer, breaker));
        }        

}
