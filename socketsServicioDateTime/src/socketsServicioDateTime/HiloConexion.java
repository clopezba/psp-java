package socketsServicioDateTime;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class HiloConexion extends Thread {
	Socket socket;
	private InetAddress ip;
	private Date fecha;

	// Constructor con socket
	public HiloConexion(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		ip = socket.getInetAddress();
		fecha = new Date();
		ObjectOutputStream oos = null;
		
		RegistroLog reg = new RegistroLog(fecha, ip);
		reg.anyadirRegistro(reg);
//		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(fecha);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
