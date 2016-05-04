package org.flipdot.display.examples;

import java.awt.Color;
import java.io.IOException;

import org.flipdot.display.api.FlipdotDisplay;
import org.flipdot.display.api.FlipdotGraphics;

public class Sine {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		FlipdotDisplay display = new FlipdotDisplay();
		FlipdotGraphics g = display.createGraphics();
		
		for (int j = 0; j < 500; j++) {
			g.fillBlack();
			g.setColor(Color.WHITE);
			int lasty = 10;
			for (int i = 0; i < 480; i++) {
				int nexty = (int) (10+9*Math.sin((i+j)/1.0) * Math.sin((i+j)/18.0));
				while (lasty != nexty) {
					g.fillRect(i, lasty, 1, 1);
					lasty += (lasty < nexty) ? 1 : -1;
				}
				g.fillRect(i, nexty, 1, 1);
			}
			display.flush();
			Thread.sleep(400);
//			new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_BLACK);
//			display.flush();
//			Thread.sleep(400);
		}
	}
	
}
