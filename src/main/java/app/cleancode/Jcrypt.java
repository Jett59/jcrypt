package app.cleancode;

import java.security.SecureRandom;

public class Jcrypt {
public static void main(String[] args) {
	byte[] key = generateKey(2048);
	byte[] encryptedBytes = encryptBytes("Hello, World, ".repeat(256).getBytes(), key);
	System.out.println("Encrypted data: "+new String(encryptedBytes));
	byte[] decryptedData = encryptBytes(encryptedBytes, key);
	System.out.println("Decrypted: "+new String(decryptedData));
}
public static byte[] generateKey(int length) {
	SecureRandom rand = new SecureRandom();
	byte[] key = new byte[length];
	rand.nextBytes(key);
	return key;
}
public static byte[] encryptBytes(byte[] bytes, byte[] key) {
	byte[] result = new byte[bytes.length];
	int keyBytePos = 0;
	for(int i = 0; i < bytes.length; i++) {
		result[i] = (byte) (bytes[i] ^ key[keyBytePos]);
		if(keyBytePos+1 < key.length) {
			keyBytePos++;
		}else {
			keyBytePos = 0;
		}
	}
	return result;
}
}
