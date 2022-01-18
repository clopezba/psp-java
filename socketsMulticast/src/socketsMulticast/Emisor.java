package socketsMulticast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Emisor {
	public static void main(String[] args) throws Exception {
		int mcPort = 12345;
		String mcIPStr = "230.1.1.1";

		DatagramSocket udpSocket = new DatagramSocket();

		InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
		Scanner s = new Scanner(System.in);
		String mensaje;
		
		while (true) {
			mensaje = s.nextLine();
			byte[] msg = mensaje.getBytes();
			DatagramPacket packet = new DatagramPacket(msg, msg.length);
			packet.setAddress(mcIPAddress);
			packet.setPort(mcPort);
			udpSocket.send(packet);
			System.out.println("Mensaje enviado a " + mcIPStr + ".");
			if (mensaje.equals("/quit")) {
				System.out.println("Saliendo...");
				udpSocket.close();
				break;
			}
			
		}
	}
}