package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RecibirDoc {
	public static void main(String[] args)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		ServerSocket servSock = new ServerSocket(5533);
		Socket socket = servSock.accept();
		System.out.println("Conectado desde: " + socket.getInetAddress());
		HiloConexion hilo = new HiloConexion(socket);
		hilo.start();
		servSock.close();

	}
}
