package Customer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*for the customer showing in the game, there are drawMe method, getlocation method
and getlocation method, getboolean, and setboolean method.*/

public class customer {
	protected PVector location;
	protected BufferedImage img;
	protected boolean isDoneOrder = false;

	public customer(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/Customer.png");
	}

	public void drawMe(Graphics2D g2) {
		if (!isDoneOrder) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -250 / 2, -456 / 2, 250, 456, null);
			g2.setTransform(tr);
		}

	}

	public PVector getLocation() {
		return location;
	}

	public boolean getBoolean() {
		return isDoneOrder;
	}

	public void setIsDoneOrder(boolean value) {
		isDoneOrder = value;
	}
}
