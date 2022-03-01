package cripto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CifradoAsimetrico {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException 
 {
		//Generar claves
		KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
		KeyPair parClaves = generador.generateKeyPair();
		
		//Necesito separar las dos claves
		PublicKey clavePublica = parClaves.getPublic();
		PrivateKey clavePrivada = parClaves.getPrivate();
		
		//Guardar claves en un fichero
		byte[] clavePublicaBytes = clavePublica.getEncoded();
		FileOutputStream fichero = new FileOutputStream("./Publica.dat");
		fichero.write(clavePublicaBytes);
		fichero.close();
		
		String textoClaro = "Hola clase, RSA.";
		
		//Iniciamos el cifrador o codificador
		Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		rsa.init(Cipher.ENCRYPT_MODE, clavePrivada); //Al otro le mandaremos la pública
		
		byte[] textoCifrado = rsa.doFinal(textoClaro.getBytes()); 
		
		FileOutputStream fichero2 = new FileOutputStream("./TextoCifrado.dat");
		fichero2.write(textoCifrado);
		fichero2.close();
	}

}
