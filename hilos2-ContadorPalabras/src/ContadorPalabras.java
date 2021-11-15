import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContadorPalabras {

	public static void main(String[] args){
		//Creamos fichero 'texto.txt' -> en temrinal: echo "hola clase ¿cómo estáis?" > texto.txt
		String rutaFichero = "/home/alumno/texto.txt";
		//Si se le pasa un fichero por argumento, leerá ese fichero
		if(args.length > 0) {
			rutaFichero = args[0];
		}
		
		Scanner lector;
		try {
			lector = new Scanner(new File(rutaFichero));
			int contador = 0;
			
			while(lector.hasNext()) {
				lector.next();//Devuelve la palabra que lee, pero no la almacenamos
				contador++;
			}
			System.out.println("Total palabras: " + contador + ".");
			
		} catch (FileNotFoundException e) {
			System.err.println("Fichero " + rutaFichero + " no encontrado.");
			e.printStackTrace();
		}
		

	}

}
