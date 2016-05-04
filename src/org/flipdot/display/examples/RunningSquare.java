package org.flipdot.display.examples;

import java.io.IOException;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class RunningSquare {
	public static void main(String[] args) throws IOException {
		FlipdotDisplay display = new FlipdotDisplay();
		FlipdotGraphics g = display.createGraphics();
		for (int i = 0; i < 48; i++) {
			g.fillBlack();
			g.fillRect(i, 5, 1, 1);
			display.flush();
		}
	}
}
