import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HiloPropagador implements Runnable {
	ArrayList<Socket> clientes;
	ColaMensajes colaMensajes;

	public HiloPropagador(ArrayList<Socket> clientes, ColaMensajes colaMensajes) {
		super();
		this.clientes = clientes;
		this.colaMensajes = colaMensajes;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (colaMensajes) {
				try {
					colaMensajes.wait(); //Desbloquea la cola de mensajes y se queda esperando 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Mensaje m = colaMensajes.obtenerMensaje();
			DataOutputStream salida = null;
			System.out.println("Propagando...");
			for (Socket socket : clientes) {
				try {
					salida = new DataOutputStream(socket.getOutputStream());
					salida.writeUTF(m.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
