package org.flipdot.display.api;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

public class FlipdotDisplay {
	protected InetAddress address;
	protected int port, width = 3 * 16, height = 20, bytesPerCol = 1 + (height - 1) / 8/* =3 */;
	protected DatagramSocket socket;
	protected BufferedImage bufferedImage;
	protected boolean mirrorX = true, mirrorY = false;

	public FlipdotDisplay(InetAddress address, int port) throws IOException {
		this.address = address;
		this.port = port;
		connect();
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
	}

	public FlipdotDisplay() throws IOException {
		this(InetAddress.getByName("192.168.3.36"), 2323);
	}

	private void connect() throws SocketException {
		if (socket != null) {
			try {
				socket.close();
			} catch (RuntimeException e) {
			}
		}
		socket = new DatagramSocket();
		socket.connect(address, port);
	}

	public void setAddressAndPort(InetAddress address, int port) throws SocketException {
		this.address = address;
		this.port = port;
		connect();
	}

	public void setAddress(InetAddress address) throws SocketException {
		this.address = address;
		connect();
	}

	public FlipdotGraphics createGraphics() {
		return new FlipdotGraphics(this, bufferedImage.createGraphics());
	}

	public void flush() throws IOException {
		socket.send(imageToDatagramPacket());
	}

	public void showAnimation(List<BufferedImage> images) throws IOException {
		showAnimation(images, 400);
	}

	public void showAnimation(List<BufferedImage> images, int timeBetw) throws IOException {
		FlipdotGraphics g = createGraphics();
		for (BufferedImage im : images) {
			g.drawImage(im, 0, 0, null);
			flush();
			try {
				Thread.sleep(timeBetw);
			} catch (InterruptedException e) {
			}
		}
		g.dispose();
	}

	protected DatagramPacket imageToDatagramPacket() {
		WritableRaster r = bufferedImage.getRaster();
		byte[] data = new byte[bytesPerCol * width], buf = new byte[1];
		for (int x = 0; x < r.getWidth(); x++) {
			for (int y = 0; y < r.getHeight(); y++) {
				data[bytesPerCol * x + y / 8] += ((byte[]) r.getDataElements(mirrorX ? width - 1 - x : x,
						mirrorY ? height - 1 - y : y, buf))[0] << (7 - (y % 8));
			}
		}
		return new DatagramPacket(data, data.length);
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
	}

	public int getHeight() {
		return height;
	}
}
