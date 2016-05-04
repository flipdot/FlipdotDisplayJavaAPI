package org.flipdot.display.examples;

import java.io.IOException;
import java.net.DatagramPacket;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class SpeedTest extends FlipdotDisplay implements Runnable {

	public static void main(String[] args) throws IOException {
		new SpeedTest(15).run();
	}

	public SpeedTest(int width) throws IOException {
		super();
		setWidth(width);
	}

	@Override
	public void run() {
		try {
			FlipdotGraphics g = createGraphics();
			for (int i = 0; i < 15; i++) {
				g.fillGrey1();
				flush();
				g.fillGrey2();
				flush();
			}
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected DatagramPacket imageToDatagramPacket() {//sends two more empty bytes than normal
		DatagramPacket dp = super.imageToDatagramPacket();
		byte[] oldData = dp.getData(), newData = new byte[oldData.length+2];
		System.arraycopy(oldData, 0, newData, 0, oldData.length);
		return new DatagramPacket(newData, newData.length);
	}
}
