import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class ClienteSimple {

	public static void main(String[] args) throws URISyntaxException {
		//HttpRequest --> Petición (GET, POST, PUT, DELETE, HEAD, OPTIONS)
		//HttpResponse --> Respuesta
		
		String apikey = "?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDAxMDFAaWVzc2llcnJhZGVndWFyYS5jb20iLCJqdGkiOiJjNjIwNzA4NS1i"
				+ "ZDY0LTRhMGQtOTkyYy0zNjhjY2IzZmJlM2UiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTY0NDIyNjU0MywidXNlcklkIjoiYzYyMDcw"
				+ "ODUtYmQ2NC00YTBkLTk5MmMtMzY4Y2NiM2ZiZTNlIiwicm9sZSI6IiJ9.0wH1ms5xLAXn4tvGN0lgtKOgaZSczYLEe0IBHBeIJPs";
		String idema = "9491X";
		String servidor = "https://opendata.aemet.es/opendata";
		String cadenaPeticion = "/api/observacion/convencional/datos/estacion/";
		
		HttpRequest peticion = HttpRequest.newBuilder()
								.uri(new URI(servidor+cadenaPeticion+idema+apikey))
								.GET()
								.build();
		

	}

}
