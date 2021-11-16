
public class HiloInsertador implements Runnable {

	ListaCompra lista;
	String producto;
	
	public HiloInsertador(ListaCompra lista, String producto) {
		super();
		this.lista = lista;
		this.producto = producto;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lista.anyadirProducto(producto); //El método ya está sincornizado en la clase ListaCompra
		
	}

}
