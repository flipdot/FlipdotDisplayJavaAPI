package org.flipdot.display.examples;

import java.io.IOException;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class Nicolaus {

	public static void main(String[] args) throws IOException, InterruptedException {
		new Nicolaus();
	}

	FlipdotDisplay display;
	FlipdotGraphics g;

	public Nicolaus() throws IOException, InterruptedException {
		display = new FlipdotDisplay();
		g = display.createGraphics();

		int h = 13;
		for (int i = 0; i < 1; i++) {
			g.fillBlack();
			display.flush();
			animLine(24 - 5, h + 5, 24 + 5, h - 5);
			display.flush();
			animLine(24, h - 10, 24 + 5, h - 5);
			display.flush();
			animLine(24, h - 10, 24 - 5, h - 5);
			display.flush();
			animLine(24 - 5, h - 5, 24 - 5, h + 5);
			display.flush();
			animLine(24 - 5, h + 5, 24 + 5, h + 5);
			display.flush();
			animLine(24 + 5, h + 5, 24 + 5, h - 5);
			display.flush();
			animLine(24 + 5, h - 5, 24 - 5, h - 5);
			display.flush();
			animLine(24 - 5, h - 5, 24 + 5, h + 5);
			display.flush();
			Thread.sleep(8 * 500);
		}
	}

	private void animLine(int x1, int y1, int x2, int y2) throws IOException {
		double len = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		int d = (int) (0.34f * len);
		for (float i = d; i < len - d; i += d) {
			g.drawLine(x1, y1, (int) (.1 + x1 + i * (x2 - x1)), (int) (.1 + y1 + i * (y2 - y1)));
			display.flush();
		}
		g.drawLine(x1, y1, x2, y2);
		display.flush();

	}
}
