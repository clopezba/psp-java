package hilos01;

public class TareaRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 15; i++) {
		      System.out.println("Soy un hilo runnable y esto es lo que hago");
		      try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
