import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Servidor {

	public static final int PUERTO = 5454;
	
	public static void main(String[] args) throws IOException {
		ServerSocket servSock = new ServerSocket(PUERTO);
		Socket socket = servSock.accept(); //Trataremos solo un cliente
		//Creo el objeto que queiro mandar
		Mensaje msj = new Mensaje(
				new Date(), 
				"Hola mundo",
				socket.getLocalAddress()); //InetAddress es la del cliente, quiero poner la mía
		// Obtengo un canal de salida de objetos
		ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
		salida.writeObject(msj);
		
		salida.close();
		socket.close();
		
		
	}

}
