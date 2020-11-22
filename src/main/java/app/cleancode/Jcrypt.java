package app.cleancode;

public class Jcrypt {
public static void main(String[] args) {
	byte[] key = generateKey(2048);
	byte[] encryptedBytes = encryptBytes("Hello, World, ".repeat(256).getBytes(), key);
	System.out.println("Encrypted data: "+new String(encryptedBytes));
	byte[] decryptedData = encryptBytes(encryptedBytes, key);
	System.out.println("Decrypted: "+new String(decryptedData));
}
public static byte[] generateKey(int length) {
	byte[] key = new byte[length];
	for(int i = 0; i < length; i++) {
		key[i] = (byte)(Math.random()*256-127);
	}
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
