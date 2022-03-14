import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", Servidor.PUERTO);
		
		ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
		Mensaje msjRecibido = (Mensaje) entrada.readObject();
		System.out.println(msjRecibido);
	}

}
