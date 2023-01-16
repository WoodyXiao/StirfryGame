package object;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*Pan class, having method of drawPan, getLocation, getBasePan for decorator. */

public class Pan implements PanDrawingInterface {

	// location
	protected PVector location;
	protected BufferedImage selectedImg;
	protected BufferedImage img;
	protected boolean isHide = false;

	public Pan(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/pan.png");

	}

	public void drawPan(Graphics2D g2) {
		if (!isHide) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			selectedImg = img;
			g2.drawImage(selectedImg, -431 / 2, -459 / 2, 451, 479, null);
			g2.setTransform(tr);
		}
	}

	public boolean checkPanHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (451 / 2) && Math.abs(location.y - e.getY()) < (479 / 2));
	}

	public PVector getLocation() {
		return location;
	}

	// method for getting the base pan for decorators.
	public Pan getBasePan() {
		return this;
	}

	public void prepareG2ForDrawing(Graphics2D g2) {
		g2.translate(location.x, location.y);
	}

	public void setBoolean(boolean value) {
		isHide = value;
	}
}
