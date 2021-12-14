
public class Mensaje {

	String mensaje;
	String origen;
	
	
	public Mensaje(String mensaje, String origen) {
		super();
		this.mensaje = mensaje;
		this.origen = origen;
	}


	@Override
	public String toString() {
		return "[" + origen + "]: " + mensaje;
	}
	
	
	
}
