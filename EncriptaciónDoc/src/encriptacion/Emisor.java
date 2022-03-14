package encriptacion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Emisor {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		//Extraemos los bytes del fichero en un array
		String ruta = "Archivo/webs.txt";
		byte[] bytesArchivo = Files.readAllBytes(Paths.get(ruta));
		
		// ++++++++[[ HASH ]]+++++++++++
		// +++++++++++++++++++++++++++++
		//Generamos el hash en un array de bytes
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(bytesArchivo);
		byte[] hash = md.digest();
		
		//Escribimos el código en hexadecimal
		System.out.println("Hash del archivo original: ");
		for (byte b : hash) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		FileOutputStream ficheroHash = new FileOutputStream("Archivo/hash.dat");
		ficheroHash.write(hash);
		ficheroHash.close();
		
		// +++++++++[[ AES ]]+++++++++++
		// +++++++++++++++++++++++++++++
		String textoGenerador = "clave de AES.....";
		Key key = new SecretKeySpec(textoGenerador.getBytes(), 0, 16, "AES");
		
		//Codificar documento
		Cipher codificadorAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String initVectorString = "encryptionIntVec";
		IvParameterSpec iv = new IvParameterSpec(initVectorString.getBytes());
		codificadorAES.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] archivoEncriptado = codificadorAES.doFinal(bytesArchivo);
		
		FileOutputStream ficheroAES = new FileOutputStream("Archivo/ficheroAES.dat");
		ficheroAES.write(archivoEncriptado);
		ficheroAES.close();
		
		
		// ++++++++[[ RSA ]]++++++++++
		// +++++++++++++++++++++++++++
		//Generamos par de claves 
		KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
		KeyPair parClaves = generador.generateKeyPair();
		
		//Separamos las dos claves
		PublicKey clavePublica = parClaves.getPublic();
		PrivateKey clavePrivada = parClaves.getPrivate();
		
		//Guardamos clave pública en un fichero para que la tenga el receptor
		byte[] clavePublicaBytes = clavePublica.getEncoded();
		FileOutputStream fichero = new FileOutputStream("Archivo/Publica.dat");
		fichero.write(clavePublicaBytes);
		fichero.close();
		
		//Iniciamos el cifrador
		Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		//Encriptamos con la privada, desencripta con la pública
		rsa.init(Cipher.ENCRYPT_MODE, clavePrivada);
		byte[] keyCifrada = rsa.doFinal(key.getEncoded());
		
		//Guardamos clave encriptada
		FileOutputStream fichero2 = new FileOutputStream("Archivo/KeyCifrada.dat");
		fichero2.write(keyCifrada);
		fichero2.close();		
	}

}
