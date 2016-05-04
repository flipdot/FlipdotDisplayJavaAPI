package org.flipdot.display.api;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

class FlipdotDisplayUtil {
	public static final int FILL_BLACK = 0;
	public static final int FILL_WHITE = 1;
	public static final int FILL_GREY1 = 2;
	public static final int FILL_GREY2 = 3;
	public static final int FILL_LOGO1 = 4;
	public static final int FILL_LOGO2 = 5;
	FlipdotDisplay display;
	
	public FlipdotDisplayUtil(FlipdotDisplay display) {
		this.display = display;
	}

	public void fill(int state) {
		Graphics2D g = display.createGraphics();
		switch (state) {
		case 0:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, display.width, display.height);
			break;
		case 1:
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, display.width, display.height);
			break;
		case 2:
		case 3:
			for (int x = 0; x < display.width; x++) {
				for (int y = 0; y < display.height; y++) {
					g.setColor(((x + y + state) % 2 == 0) ? Color.WHITE : Color.BLACK);
					g.fillRect(x, y, 1, 1);
				}
			}
			break;
		case 4:
		case 5:
			drawFlipdot(display, state == 5);

		default:
			break;
		}
	}

	public static void drawFlipdot(FlipdotDisplay display, boolean flipGrey) {
		new FlipdotDisplayUtil(display).fill(flipGrey ? FlipdotDisplayUtil.FILL_GREY1 : FlipdotDisplayUtil.FILL_GREY2);
		Graphics2D g = display.createGraphics();
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
