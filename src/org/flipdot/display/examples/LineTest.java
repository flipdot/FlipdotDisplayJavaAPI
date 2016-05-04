package org.flipdot.display.examples;

import java.io.IOException;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

import static java.lang.Math.*;

public class LineTest {
	public static void main(String[] args) throws IOException {
		FlipdotDisplay display = new FlipdotDisplay();
		FlipdotGraphics graphics = display.createGraphics();

		graphics.fillLogo1();
		for (double d = 0; d < 2*PI; d += 0.1) {
			graphics.fillLogo1();
			int x = (int) (10 * sin(d)), y = (int) (10 * cos(d));
			graphics.drawLine(24 + x, 10 + y, 24 - x, 10 - y);
			display.flush();
		}
		graphics.dispose();
		display.flush();
	}
}
