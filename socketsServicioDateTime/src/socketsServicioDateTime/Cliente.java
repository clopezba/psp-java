package socketsServicioDateTime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 5454);
		ObjectInputStream fec = new ObjectInputStream(socket.getInputStream());
		System.out.println("Fecha de conexión: " + fec.readObject());
		fec.close();
		socket.close();

	}

}
