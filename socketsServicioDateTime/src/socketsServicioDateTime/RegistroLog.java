package socketsServicioDateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import com.google.gson.Gson;

public class RegistroLog {

	private Date fecha;
	private InetAddress ip;
	
	public RegistroLog(Date fecha, InetAddress ip) {
		super();
		this.fecha = fecha;
		this.ip = ip;
	}
	
	public void anyadirRegistro(RegistroLog registro) {
		try {
			FileWriter fw = new FileWriter("RegistroLog.json", true);
			new Gson().toJson(registro, fw);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
