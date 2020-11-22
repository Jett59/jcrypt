package app.cleancode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class JcryptTest {
	@Test
	public void generateKey_small() {
		generateKeyWithSize(256);
	}
	@Test
	public void generateKey_large() {
		generateKeyWithSize(4096);
	}
	public void generateKeyWithSize(int keySize) {
		byte[] key1 = Jcrypt.generateKey(keySize);
		assertEquals(keySize, key1.length);
		byte[] key2 = Jcrypt.generateKey(keySize);
		assertEquals(keySize, key2.length);
		assertFalse(Arrays.equals(key1, key2), "Keys are the same! This is detrimental to the encryption system");
	}
@Test
public void testEncrypt_smallKeyAndData() {
	String testString = "This will prove that the system works!";
	byte[] key = Jcrypt.generateKey(32);
	byte[] encryptedData = Jcrypt.encryptBytes(testString.getBytes(), key);
	System.out.println("Encrypted data: "+new String(encryptedData));
	byte[] decryptedData = Jcrypt.encryptBytes(encryptedData, key);
	String decryptedString = new String(decryptedData);
	System.out.println("decrypted: "+decryptedString);
	assertEquals(testString, decryptedString);
}
@Test
public void encryptBytes_largeData() {
	byte[] key = Jcrypt.generateKey(32);
	String text = "test".repeat(256);
	byte[] encryptedData = Jcrypt.encryptBytes(text.getBytes(), key);
	String decryptedText = new String(Jcrypt.encryptBytes(encryptedData, key));
	assertEquals(text, decryptedText);
}
}
