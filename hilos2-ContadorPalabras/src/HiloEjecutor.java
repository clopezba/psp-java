
public class HiloEjecutor extends Thread {

	private String rutaFichero;
	
	//Para poder añadir argumentos a un hilo,  creamos un constructor con el tipo de argumentos que le pasaremos
	public HiloEjecutor(String rutaFichero) {
		super();
		this.rutaFichero = rutaFichero;
	}


	@Override
	public void run() {
		String[] vectorArgs = {rutaFichero};
		ContadorPalabras.main(vectorArgs);
	}
	
}
