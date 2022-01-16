package socketsServicioDateTime;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {
		//Creamos servidor
		ServerSocket serverSock = new ServerSocket(5454);
		
		//Esperamos conexiones
		while(true) {
			Socket sock = serverSock.accept();
			HiloConexion hilo = new HiloConexion(sock);
			System.out.println("Cliente conectado!!: " + sock.getInetAddress() );
			hilo.start();
		}
	}
}
