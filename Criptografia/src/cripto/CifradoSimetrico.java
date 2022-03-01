package cripto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CifradoSimetrico {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException 
 {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128); //parámetros de la clave - 128bits
		
		//Clave autogenerada
		//Key key = kg.generateKey(); //generamos la clave. Servirá para pasarlo junto con el mensaje para decodificarlo
		
		//Generar una clave basada en un texto. Tiene que tener más de 16 bytes.
		String textoGenerador = "texto de clave....";
		Key key = new SecretKeySpec(textoGenerador.getBytes(), 0, 16, "AES");
		
		String texto = "Este es mi texto secreto";
		
		//++++[[ CODIFICAR ]]+++++
		//ECB no es seguro para paquetes grandes:
		//Cipher codificadorAES = Cipher.getInstance("AES/ECB/PKCS5Padding");
		//codificadorAES.init(Cipher.ENCRYPT_MODE, key); //Decimos modo y la clave
		
		//Para solucionarlo, utilizamos CBC
		Cipher codificadorAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
		String initVectorString = "encryptionIntVec";
		IvParameterSpec iv = new IvParameterSpec(initVectorString.getBytes());
		//Se debe añadir 'iv' si es con CBC
		codificadorAES.init(Cipher.ENCRYPT_MODE, key, iv);
		
		
		
		//Convertirmos String a array de bytes
		byte[] textoBytes = texto.getBytes();
		
		for (byte b : textoBytes) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		//Encriptamos el texto
		byte[] textoEncriptado = codificadorAES.doFinal(textoBytes);
		for (byte b : textoEncriptado) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		
		//+++[[ Descifrar ]]++++
		//Con ECB:
		//Cipher decodificadorAES = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		//Si lo hacemos con CBC:
		Cipher decodificadorAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
		//Se debe añadir 'iv' si es con CBC
		decodificadorAES.init(Cipher.DECRYPT_MODE, key, iv);
		
		byte[] textiDesencriptado = decodificadorAES.doFinal(textoEncriptado);
		
		for (byte b : textiDesencriptado) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		String textoClaro = new String(textiDesencriptado);
		System.out.println(textoClaro);
	}
}
