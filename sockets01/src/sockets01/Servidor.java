package sockets01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		//----SIEMPRE LANZAR ANTES DEL SERVIDOR----
		ServerSocket servSock = new ServerSocket(5252);
		Socket socket = servSock.accept(); //Queda esperando a que alguien llame
		System.out.println(socket.getInetAddress()); //Aparece la dirección del router, no del pc
		
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		salida.writeUTF("Hola cliente soy el servidor");
		DataInputStream entrada = new DataInputStream(socket.getInputStream()); //Lo que devuelve el cliente
		System.out.println("El cliente me dice: " + entrada.readUTF());
		
	}

}
