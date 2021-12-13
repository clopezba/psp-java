import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		ServerSocket servSock = new ServerSocket(5252);
		Socket socket = servSock.accept(); // Espera la conexión de un cliente

		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		while (true) {
			System.out.println(entrada.readUTF());
		}

	}

}
