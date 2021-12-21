package sockets01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {
		// Crear socket servidor indicando puerto de escucha
		ServerSocket servSock = new ServerSocket(5252);
		
		// Queda esperando a que alguien llame
		Socket socket = servSock.accept();
		System.out.println(socket.getInetAddress()); // Aparece la dirección del router, no del pc
		
		// Establecer flujo de salida
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		salida.writeUTF("Hola cliente soy el servidor");
		
		// Establecer flujo de entrada
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		System.out.println("El cliente me dice: " + entrada.readUTF());
	}
}
