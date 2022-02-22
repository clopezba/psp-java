package cliente;

import java.io.IOException;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class Cliente {
	public static void main(String[] args) throws IOException {
		// Almacén del certificado del servidor
		System.setProperty("javax.net.ssl.keyStore", "/home/alumno/SSL/almacenCliente.p12");
		// contraseña del almacén del certificado del servidor
		System.setProperty("javax.net.ssl.keyStorePassword", "test321");
		// almacén del certificado en que confío. Están en el mismo sitio
		System.setProperty("javax.net.ssl.trustStore", "/home/alumno/SSL/almacenCliente.p12");
		// contraseña del almacén de los certificados en que confío
		System.setProperty("javax.net.ssl.trustStorePassword", "test321");
		
		SSLSocketFactory sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		Socket socket = sslFactory.createSocket("192.168.102.9",5566);
		
		socket.close();
	}
}
