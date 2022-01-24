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
		String mensaje = "";

		while (!mensaje.equals("/quit")) {
			mensaje = s.nextLine();
			byte[] msg = mensaje.getBytes();

			// El paquete es el que tiene toda la información de la red y puerto al que se
			// envía
			DatagramPacket packet = new DatagramPacket(msg, msg.length);
			packet.setAddress(mcIPAddress);
			packet.setPort(mcPort);
			udpSocket.send(packet);
			System.out.println("Mensaje enviado a " + mcIPStr + ".");
		}
		System.out.println("Saliendo...");
		udpSocket.close();
	}
}