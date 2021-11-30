
public class Principal {

	public static void main(String[] args) throws InterruptedException {
		Object objCompartido = new Object();

		System.out.println("[Principal]: lanzando hilos");
		for (int i = 0; i < 5; i++) {
			new Thread(new HiloEsperador(objCompartido)).start();
		}
		System.out.println("[Principal]: Hilos lanzados, espero 1 segundo");
		Thread.sleep(1000);
		System.out.println("[Principal]: Notificando a todos");

		// objCompartido.notifyAll(); //Notifica a todos los hilos
		// objCompartido.notify(); //Notifica al primero que entre, los demás se
		// quedarán esperando
		for (int i = 0; i < 5; i++) { // Nofica a todos, pero uno a uno
			synchronized (objCompartido) {
				objCompartido.notify();
			}
			//Thread.sleep(500);
		}
		System.out.println("[Principal]: Notificados");
	}

}
