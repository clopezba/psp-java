import java.util.ArrayList;

public class HiloLector implements Runnable {
	private ArrayList<String> lista;
	
	public HiloLector(ArrayList<String> listaCompra) {
		super();
		this.lista = listaCompra;
	}


	
	@Override
	public void run() {
		synchronized (lista) {
			System.out.println(lista);
		}
		
	}

}
