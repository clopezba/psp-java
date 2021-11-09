package hilos01;

public class Programa {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Soy el hilo principal, voy a lanzar la tarea");
		//A través de la superclase
		Tarea t1 = new Tarea();
		t1.start(); //lanzamos el método start aunque hayamos implementado run
		
		//A través de la interfaz necesitamos crear un thread
		Thread t2 = new Thread(new TareaRunnable());
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Este es el final del hilo principal");
		
	}

}
