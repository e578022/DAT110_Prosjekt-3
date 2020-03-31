package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity) {		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		try {
		
		// we use MD5 with 128 bits digest
		MessageDigest md = MessageDigest.getInstance("MD5");
		// compute the hash of the input 'entity'
		byte[] digest = md.digest(entity.getBytes("utf8"));
		// convert the hash into hex format
		String hashvalue = toHex(digest);
		// convert the hex into BigInteger
		hashint = new BigInteger(hashvalue, 16);
		
	} catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
		
		ex.printStackTrace();
	}
		// return the BigInteger
		
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		
		// get the digest length
		int digestLength = hashint.bitLength();
		
		// compute the number of bits = digest length * 8
		int nrOfBits = digestLength * 8;
		
		// compute the address size = 2 ^ number of bits
		BigInteger size = BigDecimal.valueOf(Math.pow(2, nrOfBits)).toBigInteger();
		
		// return the address size
		
		return size;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		digestlen = hashint.bitLength();
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
