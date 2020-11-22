package app.cleancode;

import java.security.SecureRandom;

public class Jcrypt {
public static byte[] generateKey(int length) {
	SecureRandom rand = new SecureRandom();
	byte[] key = new byte[length];
	rand.nextBytes(key);
	return key;
}
public static byte encryptByte(byte b, byte keyByte) {
	return (byte) (b ^ keyByte);
}
public static byte[] encryptBytes(byte[] bytes, byte[] key) {
	byte[] result = new byte[bytes.length];
	int keyBytePos = 0;
	for(int i = 0; i < bytes.length; i++) {
		result[i] = encryptByte(bytes[i], key[keyBytePos]);
		if(keyBytePos+1 < key.length) {
			keyBytePos++;
		}else {
			keyBytePos = 0;
		}
	}
	return result;
}
}
