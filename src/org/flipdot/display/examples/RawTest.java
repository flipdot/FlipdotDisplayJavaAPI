package org.flipdot.display.examples;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RawTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		DatagramSocket s = new DatagramSocket();
		s.connect(InetAddress.getByName("192.168.3.36"), 2323);
		byte[] test = new byte[3*3*16];
		for (int i = 0; i < test.length; i++) {
			test[i] = (byte) (85 << ((i/3)%2));
		}
		DatagramPacket p = new DatagramPacket(test, test.length);
		s.send(p);
		s.close();
	}
}
