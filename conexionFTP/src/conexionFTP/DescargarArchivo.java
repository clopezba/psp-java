package conexionFTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class DescargarArchivo {

	public static void main(String[] args) {

		String servidor = "192.168.102.200";
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
			// FTP admite datos de texto binarios
			cliente.setFileType(FTP.BINARY_FILE_TYPE);
			// Abro el fichero local
			File archivoLocal = new File("FicheroDescargado.txt");
			// Establezco el nombre de destino
			String archivoRemoto = "aaa/descargame.txt";
			
			OutputStream os = new FileOutputStream(archivoLocal);
			System.out.println("Comenzando la descarga...");
			boolean descargado = cliente.retrieveFile(archivoRemoto, os);
			os.close();
			
			if (descargado) {
				System.out.println("Fichero descargado");
			} else {
				System.out.println("¡El fichero no se descargó!");
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
