package app.cleancode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JcryptOutputStream extends OutputStream {
	public static void main(String[] args) throws Exception {
		byte[] key;
		Path keyPath = Paths.get("keyfile");
		if(Files.exists(keyPath)) {
			key = Files.readAllBytes(keyPath);
		}else {
			key = Jcrypt.generateKey(256);
			Files.write(keyPath, key);
		}
		JcryptOutputStream stream = new JcryptOutputStream(new FileOutputStream("outputfile"), key);
		byte[] dataToWrite;
		Path inputPath = Paths.get("inputfile");
		if(Files.exists(inputPath)) {
			dataToWrite = Files.readAllBytes(inputPath);
		}else {
		dataToWrite = "Hello there!".getBytes(StandardCharsets.UTF_8);
		}
		stream.write(dataToWrite);
		stream.close();
	}
private OutputStream stream;
private byte[] key;

public JcryptOutputStream(OutputStream stream, byte[] key) {
	this.stream = stream;
	this.key = key;
}

private int keyBytePos = 0;

	@Override
	public void write(int b) throws IOException {
		byte encryptedByte = Jcrypt.encryptByte((byte)b, key[keyBytePos]);
		int i = ((int)encryptedByte)+128;
		stream.write(i);
		if(keyBytePos+1 < key.length) {
			keyBytePos++;
		}else {
			keyBytePos = 0;
		}
	}
@Override
	public void close() throws IOException {
		super.close();
	}
}
