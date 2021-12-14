import java.io.DataInputStream;
import java.io.IOException;

public class ClienteReceptor implements Runnable {

	DataInputStream entrada;
	
	public ClienteReceptor(DataInputStream entrada) {
		super();
		this.entrada = entrada;
	}

	@Override
	public void run() {
		String msj = null;
		try {
			msj = entrada.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!msj.equals("quit")) {
			try {
				System.out.println(entrada.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
