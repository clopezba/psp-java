
public class HiloEsperador implements Runnable {
	private Object compartido;
	
		
	public HiloEsperador(Object compartido) {
		super();
		this.compartido = compartido;
	}

	@Override
	public void run() {
		
		synchronized (compartido) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				System.out.println("Esperando sobre " + compartido);
				compartido.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("["+Thread.currentThread().getName()+ "]" +"He sido notificado");

	}

}
