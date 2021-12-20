import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;

public class Mensaje implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8720899680613027272L;
	Date fecha;
	String mensaje;
	InetAddress ip;
	
	public Mensaje(Date fecha, String mensaje, InetAddress ip) {
		super();
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Mensaje [fecha=" + fecha + ", mensaje=" + mensaje + ", ip=" + ip + "]";
	}

	
	
	
	
}
