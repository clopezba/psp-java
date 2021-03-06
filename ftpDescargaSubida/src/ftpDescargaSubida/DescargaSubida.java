package ftpDescargaSubida;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class DescargaSubida {

	public static void main(String[] args) {
		String servidor = "localhost";
		int puerto = 21;
		String usuario = "uftp";
		String contrasenya = "alumno";
		int contadorFicheros = 0;

		FTPClient cliente = new FTPClient();

		// Conectar
		try {
			cliente.connect(servidor, puerto);
			System.out.println("Conectado");

			// Comprobar login
			if (cliente.login(usuario, contrasenya)) {
				System.out.println("Login correcto!");
			} else {
				System.out.println("Login incorrecto!");
			}
			// Establecer tipo de ficheros que enviará/recibirá
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			// Obtener carpetas y ficheros y descargarlos
			FTPFile[] carpetas = cliente.listDirectories();

			for (FTPFile carpeta : carpetas) {
				System.out.println(carpeta);

				// Cambia al directorio de la carpeta
				cliente.cwd(carpeta.getName());
				FTPFile[] archivos = cliente.listFiles();
				for (FTPFile archivo : archivos) {
					System.out.println(archivo);
					
					// Flujo de salida para guardar los ficheros que descargue
					OutputStream os = new FileOutputStream(new File(archivo.getName()));
					boolean descargado = cliente.retrieveFile(archivo.getName(), os);
					os.close();

					// Cuenta los ficheros descargados
					if (descargado) {
						contadorFicheros++;
					}
				}
				cliente.changeToParentDirectory();
			}
			System.out.println("Se han descargado " + contadorFicheros + " ficheros.");

			// Creación de fichero Log
			String fechaHora = new SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm")
					.format(Calendar.getInstance().getTime());

			FileWriter fichero = new FileWriter("ficheroLog.txt");
			BufferedWriter bw = new BufferedWriter(fichero);
			bw.write("Descarga realizada el " + fechaHora);
			bw.close();
			fichero.close();
			
			// Crear archivo de subida y enviar
			InputStream is = new FileInputStream("ficheroLog.txt");
			boolean terminado = cliente.storeFile("log.txt", is);
			is.close();

			if (terminado) {
				System.out.println("Archivo enviado");
			} else {
				System.out.println("Error en la transmisión");
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			// Cerrar sesión y desconectar
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
