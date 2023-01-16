package object;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*here is the class of opening/closing sign, when click first time, it 
show open, and customer comes in, 
when click again after at least finished one order, it close and turn to 
the ending page. */

public class Sign {

	// location
	protected PVector location;
	protected BufferedImage img, img2;
	protected boolean isOpen = false;

	public Sign(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/closesign.png");
		img2 = ImageLoader.loadImage("assets/opensign.png");
	}

	public void drawSign(Graphics2D g2) {
		if (!isOpen) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -120 / 2, -62 / 2, 120, 62, null);
			g2.setTransform(tr);
		} else {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img2, -120 / 2, -62 / 2, 120, 62, null);
			g2.setTransform(tr);
		}
	}

	public boolean checkSignHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (80 / 2) && Math.abs(location.y - e.getY()) < (314 / 2));
	}

	public void setisOpen(boolean value) {
		isOpen = value;
	}

	public boolean getBoolean() {
		return isOpen;
	}
}
