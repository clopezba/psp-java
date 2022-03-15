package servidorMultihilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		//Crear Socket de servidor
		ServerSocket servSock = new ServerSocket(5533);
		HiloConexion hilo; 
		
		while(true) {
			//Mantener a la espera de conexiones
			Socket socket = servSock.accept();
			//Nuevo hilo por cliente
			hilo = new HiloConexion(socket);
			hilo.start();
		}

	}

}
