package ejecutable04;

import java.io.IOException;

public class ejecutableBash {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder constructor = new ProcessBuilder("bash", 
				"/home/alumno/repos/psp-test/bucle.sh");
		Process proceso = constructor.start();
		
		int retorno = proceso.waitFor();
		System.out.println(retorno);
		
		constructor = new ProcessBuilder("bash", 
				"/home/alumno/repos/psp-test/bucle.sh");
		proceso = constructor.start();
		
		int retorno2 = proceso.waitFor();
		System.out.println(retorno2);
	}

}
