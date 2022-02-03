package conexionFTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class SubirArchivo {

	public static void main(String[] args) {
		String servidor = "localhost";
		int puerto = 21; // Puerto por defecto para ftp
		String usuario = "uftp";
		String password = "alumno";

		FTPClient cliente = new FTPClient();

		try {
			cliente.connect(servidor, puerto);
			System.out.println("Conectado");
			if (cliente.login(usuario, password)) {
				System.out.println("Login correcto!");
			} else {
				System.out.println("Login incorrecto!");
			}
			//FTP admite datos de texto binarios
			cliente.setFileType(FTP.BINARY_FILE_TYPE);
			//Abro el fichero local
			File archivoLocal = new File("Cristina.txt");
			//Establezco el nombre de destino
			String archivoRemoto = "FicheroCristina.txt";
			InputStream is = new FileInputStream(archivoLocal);
			System.out.println("Comenzando la subida...");
			boolean terminado = cliente.storeFile(archivoRemoto, is);
			is.close(); //StoreFile no cierra el Stream, lo hacemos manual
			if (terminado) {
				System.out.println("Archivo enviado");
			} else {
				System.out.println("Error en la transmisión");
			}

		} catch (SocketException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			if (cliente.isConnected()) {
				try {
					cliente.logout();
					cliente.disconnect();
					System.out.println("Desconectado");
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				}

			}
		}

	}

}
