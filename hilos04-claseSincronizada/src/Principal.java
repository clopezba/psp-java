
public class Principal {

	public static void main(String[] args) {
		ListaCompra lista =  new ListaCompra();
		new Thread(new HiloMonitor(lista)).start();
		synchronized (lista) {
			lista.notify();
		}
		
		new Thread(new HiloInsertador(lista, "Tomates")).start();
		new Thread(new HiloInsertador(lista, "Patatas")).start();
		new Thread(new HiloInsertador(lista, "Atún")).start();
		new Thread(new HiloInsertador(lista, "Naranjas")).start();
		new Thread(new HiloInsertador(lista, "Pasta")).start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(lista);
	}

}
