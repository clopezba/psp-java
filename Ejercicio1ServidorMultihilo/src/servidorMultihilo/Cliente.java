package servidorMultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args)
			throws UnknownHostException, IOException, NoSuchAlgorithmException, InterruptedException {
		
		//Crear socket cliente
		Socket socket = new Socket("localhost", 5533);
		
		//Mensaje del cliente
		System.out.println("Escribe el mensaje: ");
		Scanner s = new Scanner(System.in);
		String texto = s.nextLine();
		s.close();
				
		//Generar HASH del texto
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(texto.getBytes());
		byte[] hash = md.digest();

		String hashTexto = "";
		for (byte b : hash) {
			hashTexto += Integer.toHexString(0xFF & b);
		}
		
		//Enviar texto al servidor
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		salida.writeUTF(texto);

		//Recibir HASH calculado por el servidor
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		String hashRecibido = entrada.readUTF();

		//Imprimir datos por consola
		System.out.println("Mensaje: " + texto);
		System.out.println("Hash servidor: " + hashRecibido);
		System.out.println("Hash cliente: " + hashTexto);

		//Comparar HASHES
		if (hashTexto.equals(hashRecibido)) {
			System.out.println("Los hashes coinciden");
		} else {
			System.out.println("Los hashes no coinciden");
		}
		
		//Cerar flujos de entrada y salida y socket
		entrada.close();
		salida.close();
		socket.close();
	}
}
