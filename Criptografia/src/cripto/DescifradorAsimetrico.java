package cripto;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DescifradorAsimetrico {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//Cargamos clave ya creada en el otro programa CifradoAsimetrico.java
		FileInputStream fichero = new FileInputStream("./Publica.dat");
		
		//Obtengo el número de bytes del fichero
		int nBytes = fichero.available();
		
		//Creo un array en que quepa justa la clave leída
		byte[] claveBytes = new byte[nBytes];
		
		//Meto el contenido del fichero en la clave
		fichero.read(claveBytes);
		fichero.close();
		
		//Leemos el fichero encriptado (reutilizamos objetos)
		fichero = new FileInputStream("./TextoCifrado.dat");
		nBytes = fichero.available();
		byte[] textoCifrado = new byte[nBytes];
		
		fichero.read(textoCifrado);
		fichero.close();
				
		//Creamos claves para desncriptar
		KeyFactory fabricaClaves = KeyFactory.getInstance("RSA");
		KeySpec tipoClave = new X509EncodedKeySpec(claveBytes);//Tipo de clave que se generó cuando la guardamos
		
		PublicKey clavePublica = fabricaClaves.generatePublic(tipoClave);
		
		//Desencriptar
		Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		rsa.init(Cipher.DECRYPT_MODE, clavePublica);
		
		byte[] textoEnBytes = rsa.doFinal(textoCifrado);
		String textoDescifrado = new String(textoEnBytes);
		System.out.println(textoDescifrado);
	}

}
