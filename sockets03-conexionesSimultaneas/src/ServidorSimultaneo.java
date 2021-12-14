import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorSimultaneo {

	public static void main(String[] args) throws IOException {

		ArrayList<Socket> clientes = new ArrayList<Socket>();
		ColaMensajes colaMensajes = new ColaMensajes();
		ServerSocket serverSock = new ServerSocket(5454);
		Thread hiloPropagador = new Thread(new HiloPropagador(clientes, colaMensajes));
		
		while (true) {
			Socket sock = serverSock.accept();
			HiloConexion hilo = new HiloConexion(sock);
			clientes.add(sock);
			System.out.println("Cliente conectado!!: " + hilo.getId());
			hilo.start();
		}

	}

}
