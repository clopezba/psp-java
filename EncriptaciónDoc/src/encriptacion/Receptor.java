package encriptacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Receptor {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException 
 {
		//++++++++++[[ RSA ]]+++++++++++
		//++++++++++++++++++++++++++++++
		//Cargar clave publica creada en el emisor
		FileInputStream fichero = new FileInputStream("Archivo/Publica.dat");
		//Obtengo el número de bytes del fichero y creo un array con esa longitud
		int nBytes = fichero.available();
		byte[] claveBytes = new byte[nBytes];
		//Meto la clave en ese array
		fichero.read(claveBytes);
		fichero.close();
		
		//Leemos la clave AES cifrada
		fichero = new FileInputStream("Archivo/KeyCifrada.dat");
		nBytes = fichero.available();
		byte[] keyCifrada = new byte[nBytes];
		fichero.read(keyCifrada);
		fichero.close();
		
		//Creamos claves para desencriptar
		KeyFactory fabricaClaves = KeyFactory.getInstance("RSA");
		KeySpec tipoClave = new X509EncodedKeySpec(claveBytes);
		PublicKey clavePublica = fabricaClaves.generatePublic(tipoClave);
		
		//Desencriptar clave AES
		Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		rsa.init(Cipher.DECRYPT_MODE, clavePublica);
		byte[] claveEnBytes = rsa.doFinal(keyCifrada);
		
		
		//++++++++++++[[ AES ]]++++++++++++++
		//+++++++++++++++++++++++++++++++++++
		//Creo la clave para descifrar el archivo
		Key key = new SecretKeySpec(claveEnBytes, 0, 16, "AES");
		Cipher decodificadorAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String initVectorString = "encryptionIntVec";
		IvParameterSpec iv = new IvParameterSpec(initVectorString.getBytes());
		decodificadorAES.init(Cipher.DECRYPT_MODE, key, iv);
		
		//Obtengo el archivo
		FileInputStream archivoEncrip = new FileInputStream("Archivo/ficheroAES.dat");
		nBytes = archivoEncrip.available();
		byte[] archivoCifrado = new byte[nBytes];
		archivoEncrip.read(archivoCifrado);
		archivoEncrip.close();
		
		byte[] archivoDesencriptado = decodificadorAES.doFinal(archivoCifrado);
		
		//++++++++++++[[ HASH ]]++++++++++
		//++++++++++++++++++++++++++++++++
		//Generamos el hash en un array de bytes
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(archivoDesencriptado);
		byte[] hash = md.digest();
		
		System.out.println("Hash del archivo recibido: ");
		for (byte b : hash) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();

		FileOutputStream archivoRecibido = new FileOutputStream("Archivo/websRecibidas.txt");
		archivoRecibido.write(archivoDesencriptado);
		archivoRecibido.close();
		
	}

}
