import java.util.ArrayList;

public class Ejecutor {

	public static void main(String[] args) {
		/*
		String[] vectorVacio = {};
		ContadorPalabras.main(vectorVacio);
		
		System.out.println("test"); //comprobar si se hace en paralelo (no)
		
		String[] vectorArgs = {"/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/main.c"};
		ContadorPalabras.main(vectorArgs);
		*/
		
		//Para paralelizar con HiloEejcutor
		/*String rutaFichero = "/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/main.c";
		HiloEjecutor h1 = new HiloEjecutor(rutaFichero);
		
		h1.start();*/
		
		//Varios ficheros paralelizados
		long tInicio= System.nanoTime();
		ArrayList<String> ficheros = new ArrayList<String>();
		ficheros.add("/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/main.c");
		ficheros.add("/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/Makefile");
		ficheros.add("/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/funciones.c");
		ficheros.add("/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/funciones.h");
		ficheros.add("/home/alumno/repos/psp-c/prog12-EjercicioCompleto-Prod-Separado/productor.c");
		
		ArrayList<HiloEjecutor> listaHilos = new ArrayList<HiloEjecutor>();
		for (String rutaFichero : ficheros) {
			listaHilos.add(new HiloEjecutor(rutaFichero));
		}
		System.out.println("Hilos creados tardé " + (System.nanoTime()- tInicio)/1000000 + "ms.");
		for (HiloEjecutor hilo : listaHilos) {
			hilo.start();
			
		}
		System.out.println("Hilos lanzados tardé " + (System.nanoTime()- tInicio)/1000000 + "ms.");
		for (HiloEjecutor hilo : listaHilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Hilos finalizados tardé " + (System.nanoTime()- tInicio)/1000000 + "ms.");
	}

}
