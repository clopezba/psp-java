package httpConexion;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class DescargaWeb {
	
	public static void main(String[] args) throws IOException {
		String stringURL = "https://wikipedia.org/";
		URL objetoURL = new URL(stringURL); //Creo URL
		
//		URLConnection conexionURL = objetoURL.openConnection(); //Creo conexión
		
		HttpsURLConnection conexionHttps = (HttpsURLConnection) objetoURL.openConnection();  
		
		
		BufferedInputStream lectorURL = new BufferedInputStream(objetoURL.openStream());
		byte[] buffer = new byte[2048]; //ponemos el tamaño que queramos
		
		int bytesLeidos = lectorURL.read(buffer);// -1 si no ha leido bytes
		
		while(bytesLeidos != -1) {
			System.out.println(new String(buffer));
			bytesLeidos = lectorURL.read(buffer);
		}
		System.out.println("El servidor devuelve: " + conexionHttps.getResponseCode());
	}
}
