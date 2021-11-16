import java.util.ArrayList;

public class HiloEscritor implements Runnable {
	
	ArrayList<String> lista;
	String producto;
	
	public HiloEscritor(String prod, ArrayList<String> lista) {
		this.producto = prod;
		this.lista = lista;
	}

	@Override
	public void run() {
		synchronized (lista) {
			lista.add(producto);
		}
	}

}
