package hilos01;

public class Tarea extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
		      System.out.println("Soy un hilo y esto es lo que hago");
		      try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    }
		super.run();
	}
	
}
