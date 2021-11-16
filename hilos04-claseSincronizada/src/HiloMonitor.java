
public class HiloMonitor implements Runnable {

	ListaCompra lista;
	
	
	public HiloMonitor(ListaCompra lista) {
		super();
		this.lista = lista;
	}


	@Override
	public void run() {
		while (true) {
			synchronized (lista) {
				try {
					lista.wait();
					System.out.println(lista);
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
