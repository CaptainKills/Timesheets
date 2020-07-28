package timesheets.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import timesheets.logging.Logger;

public class Encryption {
	private static final Logger logger = new Logger(Encryption.class.toString());
	
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

	public static void encrypt(String key, Path inputFile, Path outputFile) {
		logger.info("Encrypting file: " + inputFile.getFileName());
		
		try {
			doCrypto(Cipher.ENCRYPT_MODE, key, inputFile.toFile(), outputFile.toFile());
			inputFile.toFile().delete();
		} catch (EncryptionException | SecurityException e) {
			logger.error("COULD NOT ENCRYPT FILE: " + e);
		}
		
	}

	public static void decrypt(String key, Path inputFile, Path outputFile) {
		logger.info("Decrypting file: " + inputFile.getFileName());
		
		try {
			doCrypto(Cipher.DECRYPT_MODE, key, inputFile.toFile(), outputFile.toFile());
		} catch (EncryptionException e) {
			logger.error("COULD NOT DECRYPT FILE: " + e);
		}
	}

	private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile)
			throws EncryptionException {
		try (FileInputStream inputStream = new FileInputStream(inputFile);
				FileOutputStream outputStream = new FileOutputStream(outputFile);) {
			
			SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);

			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);
			outputStream.write(outputBytes);

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			throw new EncryptionException("Error encrypting/decrypting file", e);
		}
	}
}
