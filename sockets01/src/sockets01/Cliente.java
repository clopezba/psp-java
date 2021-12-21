package sockets01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		//-----EJECUTAR SIEMPRE ANTES EL SERVIDOR----
		
		// Realizar la conexión indicando IP del Servidor y su puerto de escucha
		Socket socket = new Socket("localhost", 5252);

		// Establecer flujo de entrada
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		System.out.println("El servidor me dice: " + entrada.readUTF());

		// Establecer flujo de salida
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		Thread.sleep(3000);
		salida.writeUTF("Soy el cliente!!");
	}
}
