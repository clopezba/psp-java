import java.util.ArrayList;

public class Principal {
	public static Thread insertarProducto(String prod, ArrayList<String> lista) {
		Thread h = new Thread(new HiloEscritor(prod, lista));
		h.start();
		return h;
	}
	public static void main(String[] args) {
		ArrayList<String> listaCompra = new ArrayList<String>();
		ArrayList<Thread> escritores = new ArrayList<Thread>();
		escritores.add(insertarProducto("Manzanas", listaCompra));
		escritores.add(insertarProducto("Peras", listaCompra));
		escritores.add(insertarProducto("Patatas", listaCompra));
		escritores.add(insertarProducto("Naranjas", listaCompra));
		escritores.add(insertarProducto("Zanahorias", listaCompra));
		for (Thread hilo : escritores) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/*synchronized (listaCompra) {
			System.out.println(listaCompra);
		}*/
		new Thread(new HiloLector(listaCompra)).start();
		
		
	}

}
