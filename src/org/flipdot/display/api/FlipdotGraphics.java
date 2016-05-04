package org.flipdot.display.api;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class FlipdotGraphics extends Graphics2D {
	private FlipdotDisplay display;
	private Graphics2D big;

	FlipdotGraphics(FlipdotDisplay display, Graphics2D big) {
		this.display = display;
		this.big = big;
	}

	public void fillBlack() {
		new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_BLACK);
	}
	
	public void fillWhite() {
		new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_WHITE);
	}
	
	public void fillGrey1() {
		new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_GREY1);
	}
	
	public void fillGrey2() {
		new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_GREY2);
	}
	
	public void fillLogo1() {
		new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_LOGO1);
	}
	
	public void fillLogo2() {
		new FlipdotDisplayUtil(display).fill(FlipdotDisplayUtil.FILL_LOGO2);
	}
	
	// extended from java.awt.Graphics2D
	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		big.addRenderingHints(hints);
	}

	@Override
	public void clip(Shape s) {
		big.clip(s);
	}

	@Override
	public void draw(Shape s) {
		big.draw(s);
	}

	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		big.drawGlyphVector(g, x, y);
	}

	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
		return big.drawImage(img, xform, obs);
	}

	@Override
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		big.drawImage(img, op, x, y);
	}

	@Override
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		big.drawRenderableImage(img, xform);
	}

	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		big.drawRenderedImage(img, xform);
	}

	@Override
	public void drawString(String str, int x, int y) {
		big.drawString(str, x, y);
	}

	@Override
	public void drawString(String str, float x, float y) {
		big.drawString(str, x, y);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		big.drawString(iterator, x, y);
	}

	@Override
	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		big.drawString(iterator, x, y);
	}

	@Override
	public void fill(Shape s) {
		big.fill(s);
	}

	@Override
	public Color getBackground() {
		return big.getBackground();
	}

	@Override
	public Composite getComposite() {
		return big.getComposite();
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return big.getDeviceConfiguration();
	}

	@Override
	public FontRenderContext getFontRenderContext() {
		return big.getFontRenderContext();
	}

	@Override
	public Paint getPaint() {
		return big.getPaint();
	}

	@Override
	public Object getRenderingHint(Key hintKey) {
		return big.getRenderingHint(hintKey);
	}

	@Override
	public RenderingHints getRenderingHints() {
		return big.getRenderingHints();
	}

	@Override
	public Stroke getStroke() {
		return big.getStroke();
	}

	@Override
	public AffineTransform getTransform() {
		return big.getTransform();
	}

	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return big.hit(rect, s, onStroke);
	}

	@Override
	public void rotate(double theta) {
		big.rotate(theta);
	}

	@Override
	public void rotate(double theta, double x, double y) {
		big.rotate(theta, x, y);
	}

	@Override
	public void scale(double sx, double sy) {
		big.scale(sx, sy);
	}

	@Override
	public void setBackground(Color color) {
		big.setBackground(color);
	}

	@Override
	public void setComposite(Composite comp) {
		big.setComposite(comp);
	}

	@Override
	public void setPaint(Paint paint) {
		big.setPaint(paint);
	}

	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		big.setRenderingHint(hintKey, hintValue);
	}

	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		big.setRenderingHints(hints);
	}

	@Override
	public void setStroke(Stroke s) {
		big.setStroke(s);
	}

	@Override
	public void setTransform(AffineTransform Tx) {
		big.setTransform(Tx);
	}

	@Override
	public void shear(double shx, double shy) {
		big.shear(shx, shy);
	}

	@Override
	public void transform(AffineTransform Tx) {
		big.transform(Tx);
	}

	@Override
	public void translate(int x, int y) {
		big.translate(x, y);
	}

	@Override
	public void translate(double tx, double ty) {
		big.translate(tx, ty);
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
		big.clearRect(x, y, width, height);
	}

	@Override
	public void clipRect(int x, int y, int width, int height) {
		big.clipRect(x, y, width, height);
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		big.copyArea(x, y, width, height, dx, dy);
	}

	@Override
	public Graphics create() {
		return big.create();
	}

	@Override
	public void dispose() {
		big.dispose();
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		big.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		return big.drawImage(img, x, y, observer);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return big.drawImage(img, x, y, bgcolor, observer);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		return big.drawImage(img, x, y, width, height, observer);
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		return big.drawImage(img, x, y, width, height, bgcolor, observer);
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			ImageObserver observer) {
		return big.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
			Color bgcolor, ImageObserver observer) {
		return big.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		if (y1 <= y2) {
			big.drawLine(x1, y1, x2, y2);
		} else {
			big.drawLine(x2, y2, x1, y1);
		}
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		big.drawOval(x, y, width, height);
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		big.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		big.drawPolyline(xPoints, yPoints, nPoints);
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		big.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		big.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
		big.fillOval(x, y, width, height);
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		big.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		big.fillRect(x, y, width, height);
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		big.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	@Override
	public Shape getClip() {
		return big.getClip();
	}

	@Override
	public Rectangle getClipBounds() {
		return big.getClipBounds();
	}

	@Override
	public Color getColor() {
		return big.getColor();
	}

	@Override
	public Font getFont() {
		return big.getFont();
	}

	@Override
	public FontMetrics getFontMetrics(Font f) {
		return big.getFontMetrics(f);
	}

	@Override
	public void setClip(Shape clip) {
		big.setClip(clip);
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		big.setClip(x, y, width, height);
	}

	@Override
	public void setColor(Color c) {
		big.setColor(c);
	}

	@Override
	public void setFont(Font font) {
		big.setFont(font);
	}

	@Override
	public void setPaintMode() {
		big.setPaintMode();
	}

	@Override
	public void setXORMode(Color c1) {
		big.setXORMode(c1);
	}
}
