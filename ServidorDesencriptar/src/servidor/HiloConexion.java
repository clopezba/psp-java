package servidor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class HiloConexion extends Thread {

	Socket socket;

	public HiloConexion(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {

		// Obtengo arraylist con los datos
		ObjectInputStream datos;
		ArrayList<byte[]> lista = new ArrayList<byte[]>();
		try {
			datos = new ObjectInputStream(socket.getInputStream());
			lista = (ArrayList<byte[]>) datos.readObject();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		byte[] claveEnBytes = null;
		byte[] archivoDesencriptado = null;

		// Obtengo hash original
		byte[] hash = lista.get(0);
		
		System.out.println("Hash original: ");
		String original = "";
		for (byte b : hash) {
			original += Integer.toHexString(0xFF & b);
		}
		System.out.println(original);

		// Obtengo el archivo
		byte[] archivoCifrado = lista.get(1);

		// ++++++++++[[ RSA ]]+++++++++++
		// ++++++++++++++++++++++++++++++
		// Cargar clave publica creada en el emisor
		byte[] claveBytes = lista.get(2);

		// Obtengo clave AES cifrada
		byte[] keyCifrada = lista.get(3);

		// Creamos claves para desencriptar
		KeyFactory fabricaClaves;
		try {
			fabricaClaves = KeyFactory.getInstance("RSA");
			KeySpec tipoClave = new X509EncodedKeySpec(claveBytes);
			PublicKey clavePublica = fabricaClaves.generatePublic(tipoClave);

			// Desencriptar clave AES
			Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsa.init(Cipher.DECRYPT_MODE, clavePublica);
			claveEnBytes = rsa.doFinal(keyCifrada); // <<<<<<<<<<-----------------------<<<<<<<<< KEY DESCIFRADA AES
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		// ++++++++++++[[ AES ]]++++++++++++++
		// +++++++++++++++++++++++++++++++++++
		// Creo la clave para descifrar el archivo
		Key key = new SecretKeySpec(claveEnBytes, 0, 16, "AES");
		Cipher decodificadorAES;
		try {
			decodificadorAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
			String initVectorString = "encryptionIntVec";
			IvParameterSpec iv = new IvParameterSpec(initVectorString.getBytes());
			decodificadorAES.init(Cipher.DECRYPT_MODE, key, iv);

			archivoDesencriptado = decodificadorAES.doFinal(archivoCifrado); // <<<<<<<---<<<<<<< ARCHIVO DESCIFRADO			
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		// ++++++++++++[[ HASH ]]++++++++++
		// ++++++++++++++++++++++++++++++++
		// Generamos el hash en un array de bytes
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(archivoDesencriptado);
			byte[] nuevoHash = md.digest();
			System.out.println("Hash del archivo recibido: ");
			String nuevo = "";
			for (byte b : nuevoHash) {
				nuevo += Integer.toHexString(0xFF & b);
			}
			System.out.println(nuevo);

			if (original.equals(nuevo)) {
				System.out.println("El documento es auténtico, se descargará");
				FileOutputStream archivoRecibido;
				try {
					archivoRecibido = new FileOutputStream("Archivo/webs_copia.txt");
					archivoRecibido.write(archivoDesencriptado);
					archivoRecibido.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("El documento es CORRUPTO, no abrir");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
