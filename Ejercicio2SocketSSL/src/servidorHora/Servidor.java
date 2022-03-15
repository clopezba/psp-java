package servidorHora;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.net.ssl.SSLServerSocketFactory;

public class Servidor {

	public static void main(String[] args) throws IOException, InterruptedException {
		// Almacén del certificado del servidor
		System.setProperty("javax.net.ssl.keyStore", "Certificados/almacenServidor.p12");
		// contraseña del almacén del certificado del servidor
		System.setProperty("javax.net.ssl.keyStorePassword", "passServidor");
		// almacén del certificado en que confío
		System.setProperty("javax.net.ssl.trustStore", "Certificados/almacenServidor.p12");
		// contraseña del almacén de los certificados en que confío
		System.setProperty("javax.net.ssl.trustStorePassword", "passServidor");
				
		//Crear servidor SSL y esperar al cliente
		SSLServerSocketFactory sslFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket serSock = sslFactory.createServerSocket(5533);
        Socket socket = serSock.accept();
        
        //Fecha actual del sistema
        Date fechaHora = new Date();
        //Espero 3 seg
        Thread.sleep(3000);
        
        //Envío la fecha al cliente
        ObjectOutputStream envioFecha = new ObjectOutputStream(socket.getOutputStream());
        envioFecha.writeObject(fechaHora);
        envioFecha.close();
        
        //Cierro sockets
        socket.close();
        serSock.close();
        
	}

}
