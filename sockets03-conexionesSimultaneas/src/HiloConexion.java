import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloConexion extends Thread {
	Socket socket;
	ColaMensajes colaMensajes;

	// Constructor using fields
	public HiloConexion(Socket socket, ColaMensajes colaMensajes) {
		super();
		this.socket = socket;
		this.colaMensajes = colaMensajes;
	}

	// Source > Override/Implement methods > run
	@Override
	public void run() {
		System.out.println("Conectado cliente desde: " + socket.getInetAddress());
		System.out.println("Puerto local: " + socket.getLocalPort());
		System.out.println("Puerto remoto: " + socket.getPort());
		System.out.println("IP remota: " + socket.getInetAddress());

		// Establecer flujo de entrada
		DataInputStream entrada = null;
		try {
			entrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println("Error obteniendo el stream del socket");
			e.printStackTrace();
		}
		// Leer mensajes que entran
		String mensaje = null;
		String origMensaje = socket.getInetAddress().toString();

		try {
			mensaje = entrada.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Si el mensaje es 'quit', salimos del flujo y cerramos conexión
		while (!mensaje.equals("quit")) {
			// Creamos mensaje y añadimos a la cola para que se propague
			Mensaje m = new Mensaje(mensaje, origMensaje);
			System.out.println(m);
			colaMensajes.anyadirMensaje(mensaje, origMensaje);

			synchronized (colaMensajes) {
				colaMensajes.notify();
			}
			try {
				mensaje = entrada.readUTF();
			} catch (IOException e) {
				System.err.println("Error en el stream");
				e.printStackTrace();
				mensaje = "quit";
			}
		}
		System.out.println("El cliente quiere salir");
		try {
			entrada.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
