package servidorHora;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.net.ssl.SSLSocketFactory;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		 //Almacén del certificado del servidor
        System.setProperty("javax.net.ssl.keyStore", "Certificados/almacenCliente.p12");
        //Contraseña del almacén del certificado del servidor
        System.setProperty("javax.net.ssl.keyStorePassword", "passCliente");
        //Almacén del certificado en que confío. Están en el mismo sitio
        System.setProperty("javax.net.ssl.trustStore", "Certificados/almacenCliente.p12");
        //Contraseña del almacén de los certificados en que confío
        System.setProperty("javax.net.ssl.trustStorePassword", "passCliente");
        
        //Creo socket SSL del cliente
        SSLSocketFactory sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket socket = sslFactory.createSocket("localhost", 5533);
        
        //Recibo fecha del sistema con 3 seg de retraso
        ObjectInputStream recibirFecha = new ObjectInputStream(socket.getInputStream());
        Date fechaServidor = (Date) recibirFecha.readObject();
        recibirFecha.close();
        
        //Imprimo fecha recibida y fecha actual
        Date fechaCliente = new Date();
        System.out.println("Fecha Servidor: " + fechaServidor);
        System.out.println("Fecha Cliente: " + fechaCliente);
        
        //Cierro socket
        socket.close();

	}

}
