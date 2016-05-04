package org.flipdot.display.examples;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class FlipdotLogoTest {

	static boolean animation = false;

	public static void main(String[] args) throws IOException, InterruptedException {
		FlipdotDisplay display = new FlipdotDisplay();

		if (!animation) {
			drawFlipdot(display, false);
			display.flush();
		} else {
			boolean flip = true;
			while (true) {
				drawFlipdot(display, flip = !flip);
				display.flush();
				Thread.sleep(400);
			}
		}
	}

	public static void drawFlipdot(FlipdotDisplay display, boolean flipGrey) {
		FlipdotGraphics g = display.createGraphics();
		if (flipGrey) {
			g.fillLogo2();
		} else {
			g.fillLogo1();
		}
		g.setColor(Color.WHITE);
		g.fillOval(2, -1, 21, 21);
		g.setColor(Color.BLACK);
		g.fillOval(24, -1, 21, 21);
		g.setFont(new Font("Andale Mono", Font.PLAIN, 13));
		g.setColor(Color.BLACK);
		g.drawString("f", 5, 15);
		g.drawString("lip", 9, 15);
		g.setColor(Color.WHITE);
		g.drawString("dot", 26, 15);
		g.dispose();
	}
}
