import java.util.ArrayList;

public class ColaMensajes {
	//Buffer con los mensajes que se envían por los clientes
	ArrayList<Mensaje> colaMensajes;

	public ColaMensajes() {
		colaMensajes = new ArrayList<Mensaje>();
	}
	
	//Hay que sincronizar porque puede haber clientes leyendo el mensaje o escribiendo al mismo tiempo
	public synchronized void anyadirMensaje(String mensaje, String origen) {
		colaMensajes.add(new Mensaje(mensaje, origen));
	}
	public synchronized Mensaje obtenerMensaje() {
		Mensaje m = colaMensajes.get(0);
		colaMensajes.remove(0);
		return m; 
	}
	
}
