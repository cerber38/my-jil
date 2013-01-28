
package ru.oscar.icq.packet.send.meta;

/**
 * Базовые данные
 * @author Kornackiy Alexsandr
 */

public class BasicInformation {
// xx xx	 	word (LE)	 	nickname string length
// xx .. xx 00	 	asciiz str	 	nickname string
// xx xx	 	word (LE)	 	first name string length
// xx .. xx 00	 	asciiz str	 	first name string
// xx xx	 	word (LE)	 	last name string length
// xx .. xx 00	 	asciiz str	 	last name string
// xx xx	 	word (LE)	 	email string length
// xx .. xx 00	 	asciiz str	 	email string
// xx xx	 	word (LE)	 	home city string length
// xx .. xx 00	 	asciiz str	 	home city string
// xx xx	 	word (LE)	 	home state string length
// xx .. xx 00	 	asciiz str	 	home state string
// xx xx	 	word (LE)	 	home phone string length
// xx .. xx 00	 	asciiz str	 	home phone string
// xx xx	 	word (LE)	 	home fax string length
// xx .. xx 00	 	asciiz str	 	home fax string
// xx xx	 	word (LE)	 	home address string length
// xx .. xx 00	 	asciiz str	 	home address string
// xx xx	 	word (LE)	 	cell phone string length
// xx .. xx 00	 	asciiz str	 	cell phone string
// xx xx	 	word (LE)	 	home zip code string length
// xx .. xx 00	 	asciiz str	 	home zip code string
// xx xx	 	word (LE)	 	home country code
// xx	 	char	 	GMT offset
// xx	 	char	 	publish primary email flag    
}
