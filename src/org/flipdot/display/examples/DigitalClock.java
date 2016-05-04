package org.flipdot.display.examples;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class DigitalClock extends TimerTask {

	FlipdotDisplay fd;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	FlipdotGraphics g;
	public boolean saveImage = false;
	public Point pos = new Point(3, 14);

	// TMP
	int textPos = 48, direction = -1;

	public static void main(String[] args) throws IOException {
		Timer t = new Timer();
		t.schedule(new DigitalClock(), 1000, 1000);
	}

	public DigitalClock() throws IOException {
		fd = new FlipdotDisplay() {
			@Override
			public void flush() throws IOException {
				if (saveImage) {
					ImageIO.write(bufferedImage, "png", new File("test.png"));
				}
				super.flush();
			}
		};
		g = fd.createGraphics();
		pos = new Point(0, 14);
		g.setFont(g.getFont().deriveFont(9f));
		g.setFont(new Font("Arial", Font.PLAIN, 12));
	}

	@Override
	public void run() {
		pos = new Point(0, 14 - 5);// TMP
		g.setFont(new Font("Tahoma", Font.BOLD, 11));// TMP

		g.fillBlack();
		g.setColor(Color.WHITE);
		g.drawString(sdf.format(new Date()), pos.x, pos.y);

		// TMP
		Font arial = new Font("Arial", Font.PLAIN, 9);
		g.setFont(arial);// TMP
		double width = arial.getStringBounds("flipdot", g.getFontRenderContext()).getWidth() - 1;
		g.drawString("flipdot", textPos, 17);
		textPos += direction;
		if (textPos <= 0)
			direction = 1;
		else if (textPos + width >= 48)
			direction = -1;

		try {
			fd.flush();
		} catch (IOException e) {
			e.printStackTrace();
			cancel();
		}
	}
}
