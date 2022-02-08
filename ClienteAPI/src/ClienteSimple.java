import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.apache.hc.core5.net.URIBuilder;

import com.google.gson.Gson;

public class ClienteSimple {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		// HttpRequest --> Petición (GET, POST, PUT, DELETE, HEAD, OPTIONS)
		// HttpResponse --> Respuesta

		String apikey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDAxMDFAaWVzc2llcnJhZGVndWFyYS5jb20iLCJqdGkiOiJjNjIwNzA4NS1i"
				+ "ZDY0LTRhMGQtOTkyYy0zNjhjY2IzZmJlM2UiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTY0NDIyNjU0MywidXNlcklkIjoiYzYyMDcw"
				+ "ODUtYmQ2NC00YTBkLTk5MmMtMzY4Y2NiM2ZiZTNlIiwicm9sZSI6IiJ9.0wH1ms5xLAXn4tvGN0lgtKOgaZSczYLEe0IBHBeIJPs";
		String idema = "9491X";

		//Construir la dirección URI
		URI direccion = new URIBuilder()
				.setScheme("https") //protocolo
				.setHost("opendata.aemet.es") //servidor
				.setPath("opendata/api/observacion/convencional/datos/estacion/" + idema) //recursos
				.addParameter("api_key", apikey) //parámetro
				.build(); 

		//Construir la petición
		HttpRequest peticion = HttpRequest.newBuilder().uri(direccion).GET().timeout(Duration.ofSeconds(10))
				.version(HttpClient.Version.HTTP_2) // Versión que preferimos, si no se puede pasa a la anterior
				.build();

		// Contruir el cliente
		HttpClient cliente = HttpClient.newBuilder()
				
				.build();
		
		//Solicitar la respuesta enviando la apetición desde el cliente
		HttpResponse<String> respuesta = cliente.send(peticion, BodyHandlers.ofString());

		System.out.println(respuesta.headers());
		System.out.println(respuesta.body());
		
		Gson gson = new Gson();
		RespuestaAEMET respAemet = gson.fromJson(respuesta.body(), RespuestaAEMET.class);
		
		System.out.println(respAemet.descripcion);
		System.out.println(respAemet.datos); //URL con datos
		
		// Pedir datos desde URI de la respuesta
		HttpRequest petic = HttpRequest.newBuilder()
				.uri(respAemet.datos)
				.GET()
				.version(HttpClient.Version.HTTP_2)
				.build();
		
		//Respuesta
		HttpResponse<String> respDatos = cliente.send(petic, BodyHandlers.ofString());
		System.out.println(respDatos.body());
		
		DatosEstacion[] datosAlmudevar = gson.fromJson(respDatos.body(), DatosEstacion[].class);
		System.out.println(datosAlmudevar[0]);
	}

}
