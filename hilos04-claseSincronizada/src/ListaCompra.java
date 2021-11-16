import java.util.ArrayList;


public class ListaCompra {
	ArrayList<String> lista;

	public ListaCompra() {
		this.lista = new ArrayList<String>();
	}
	
	public synchronized void anyadirProducto(String producto) {
		this.lista.add(producto);
		this.notify(); //La propia clase notifica que ha añadido un producto a quien esté esperando
	}
	public synchronized void extraerProducto(String producto) {
		boolean resultado = this.lista.remove(producto);
		if(!resultado)
			System.out.println("[ListaCompra]: Advertencia, producto " + producto + " no encontrado en la lista.");
	}

	@Override
	public synchronized String toString() {
		return "ListaCompra [lista=" + lista + "]";
	}
	
	
	
}
