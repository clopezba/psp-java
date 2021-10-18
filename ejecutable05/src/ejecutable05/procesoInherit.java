package ejecutable05;

import java.io.IOException;

public class procesoInherit {

	public static void main(String[] args) throws IOException {
		ProcessBuilder constructor = new ProcessBuilder("cat", "/proc/timer_list");
		constructor.inheritIO();
		Process proc1 = constructor.start();
	}

}
