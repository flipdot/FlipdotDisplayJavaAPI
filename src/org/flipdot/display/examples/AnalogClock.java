package org.flipdot.display.examples;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class AnalogClock extends TimerTask {

	Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
	FlipdotDisplay fd;
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	FlipdotGraphics g;
	Point2D.Double center = new Point2D.Double(23.5, 9.5);
	boolean saveImage = false, startUpAnimation = false;
	int pointerLenHours = 6, pointerLenMins = 8, pointerLenSeconds = 10;

	public static void main(String[] args) throws IOException {
		Timer t = new Timer();
		t.schedule(new AnalogClock(), 0, 1000);
	}

	public AnalogClock() throws IOException {
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
		g.setFont(g.getFont().deriveFont(9f));

		if (startUpAnimation) {
			double h, m, s;
			for (h = 0; h < now.get(Calendar.HOUR) / 12. + now.get(Calendar.MINUTE) / 60. / 12; h += 1. / 60.) {
				drawBackground();
				drawPointer(g, center, h, pointerLenHours);
				sleepAndFlush(100);
			}
			for (m = 0; m < now.get(Calendar.MINUTE) / 60.; m += 1. / 60.) {
				drawBackground();
				drawPointer(g, center, now.get(Calendar.HOUR) / 12. + now.get(Calendar.MINUTE) / 60. / 12,
						pointerLenHours);
				drawPointer(g, center, m, pointerLenMins);
				sleepAndFlush(100);
			}
			now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
			for (s = 0; s < now.get(Calendar.SECOND) / 60.; s += 1. / 60.) {
				drawBackground();
				drawPointer(g, center, now.get(Calendar.HOUR) / 12. + now.get(Calendar.MINUTE) / 60. / 12,
						pointerLenHours);
				drawPointer(g, center, now.get(Calendar.MINUTE) / 60. + now.get(Calendar.SECOND) / 3600.,
						pointerLenMins);
				drawPointer(g, center, s, pointerLenSeconds);
				sleepAndFlush(100);
			}
			drawBackground();
			drawTime(now);
		}
	}

	@Override
	public void run() {
		now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
		drawBackground();
		drawTime(now);
		try {
			fd.flush();
		} catch (IOException e) {
			e.printStackTrace();
			cancel();
			System.exit(0);
		}
	}

	public void drawBackground() {
		g.setColor(Color.BLACK);
		g.fillBlack();// fill background
		g.setColor(Color.WHITE);
		g.fillOval(13, -1, 21, 21);// draw circle
		g.setColor(Color.BLACK);
	}

	public void drawTime(Calendar c) {
		g.setColor(Color.BLACK);
		g.fillRect(23, 9, 2, 2);// draw center

		double hrs = c.get(Calendar.HOUR) / 12.0 + c.get(Calendar.MINUTE) / 60.0 / 12
				+ c.get(Calendar.SECOND) / 3600.0 / 12;
		double mns = c.get(Calendar.MINUTE) / 60.0 + c.get(Calendar.SECOND) / 3600.0;
		double sec = c.get(Calendar.SECOND) / 60.0 + 1. / 120;
		drawPointer(g, center, hrs, pointerLenHours);
		drawPointer(g, center, mns, pointerLenMins);
		drawPointer(g, center, sec, pointerLenSeconds);
	}

	private void drawPointer(Graphics2D g, Point2D.Double pos, double progress, double length) {
		while (progress < 0)
			progress++;
		double alpha = progress * 2 * Math.PI;
		while (alpha * 4 > Math.PI) {
			alpha -= Math.PI / 2;
		}

		double drawX = 0, DrawY = 0;
		for (double x = 0.5, y = 0.5; y < length && x * x + y * y < length * length; x++) {
			y = 0.5 + Math.round(Math.tan(alpha) * x - 0.499999999);
			if (progress * 8 <= 1 || progress * 8 > 7) {
				drawX = y;
				DrawY = -x;
			} else if (progress * 8 <= 3) {
				drawX = x;
				DrawY = y;
			} else if (progress * 8 <= 5) {
				drawX = -y;
				DrawY = x;
			} else if (progress * 8 <= 7) {
				drawX = -x;
				DrawY = -y;
			}
			g.fillRect((int) (pos.x + drawX), (int) (pos.y + DrawY), 1, 1);
		}
	}

	private void sleepAndFlush(int duration) throws IOException {
		try {
			Thread.sleep(100);
			fd.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
