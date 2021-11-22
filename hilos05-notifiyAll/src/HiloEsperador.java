
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
				compartido.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("He sido notificado");

	}

}
