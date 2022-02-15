package cf.iesguara.tutorialspring.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {
	private static final String plantilla = "¡Hola, %s!";
	private final AtomicLong contador = new AtomicLong();
	
	//GetMapping se usa para peticiones HTTP de tipo GET
	@GetMapping("/saluda")
	public Saludo saludo(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre) {
		return new Saludo(contador.incrementAndGet(), String.format(plantilla, nombre));
	}
}
