package socketsMulticast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receptor {
	public static void main(String[] args) throws Exception {
    int mcPort = 12345;
    String mcIPStr = "230.1.1.1";  // esta es la dirección "especial"
    
    MulticastSocket mcSocket = null;
    InetAddress mcIPAddress = null;
    
    mcIPAddress = InetAddress.getByName(mcIPStr);
    //Como es socket de recepción, hay que darle un puerto
    mcSocket = new MulticastSocket(mcPort);
    System.out.println("Multicast Receiver running at:" + mcSocket.getLocalSocketAddress());
    mcSocket.joinGroup(mcIPAddress);

    DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

    System.out.println("Esperando mensaje multicast en " + mcIPStr+ " ...");
    
    //Se bloquea hasta que reciba un paquete
	
    String msg = "";
	while(!msg.equals("/quit")) {
		mcSocket.receive(packet);
		msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.println("[Multicast  Receiver] Recibido:" + msg);
	}
	System.out.println("Multicast desconectado por emisor");    

    mcSocket.leaveGroup(mcIPAddress); //dejando de escuchar
    mcSocket.close();
  }
}
