package servidorMultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HiloConexion extends Thread {
	Socket socket;

	//Constructor 
	public HiloConexion(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		String texto = null;
		String hashTexto = "";

		//Recibir texto desde el cliente
		DataInputStream entrada = null;
		try {
			entrada = new DataInputStream(socket.getInputStream());
			texto = entrada.readUTF();

		} catch (IOException e) {
			System.err.println("Error al recibir el mensaje: " + e.getStackTrace());
		}
		
		//Imprimir mensaje por pantalla
		System.out.println("[IP: " + socket.getInetAddress() + "]: " + texto);
		
		//Espera 2 segundos
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.err.println("Error durante la espera: " + e.getStackTrace());
		}
		
		//Generar HASH e imprimir por consola
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(texto.getBytes());
			byte[] hash = md.digest();

			for (byte b : hash) {
				hashTexto += Integer.toHexString(0xFF & b);
			}
			System.out.println(hashTexto);

		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error al generar el hash: " + e.getStackTrace());
		}
		//Enviar HASH al cliente
		DataOutputStream salida = null;
		try {
			salida = new DataOutputStream(socket.getOutputStream());
			salida.writeUTF(hashTexto);
		} catch (IOException e) {
			System.err.println("Error al enviar el hash: " + e.getStackTrace());
		}
		//Cerrar flujos de entrada y salida
		try {
			entrada.close();
			salida.close();
		} catch (IOException e) {
			System.err.println("Error al cerrar los flujos de entrada y salida: " + e.getStackTrace());
		}
	}

}
