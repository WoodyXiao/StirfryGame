package object;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Customer.customer;
import Util.ImageLoader;
import processing.core.PVector;

/*here is for the Box class when the stir fry is finished, 
two drawing one is the box with opening, one is the box with 
closing, changing when the boolean of isClosed changing.*/

public class Box {

	// location
	protected PVector location;
	protected BufferedImage img, img1;
	protected boolean isClosed = false;

	public Box(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/packopen.png");
		img1 = ImageLoader.loadImage("assets/packclose.png");
	}

	// when isClosed is true, the drawing of box is img1,
	// when isClosed is false, the drawing of box is img.
	public void drawBox(Graphics2D g2) {
		if (!isClosed) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -200 / 2, -200 / 2, 200, 200, null);
			g2.setTransform(tr);
		} else {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img1, -200 / 2, -200 / 2, 200, 200, null);
			g2.setTransform(tr);
		}

	}

	// checking the box if hit by the mouse.
	public boolean checkPackHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < 200 / 2 && Math.abs(location.y - e.getY()) < 200 / 2);
	}

	// location of box could change.
	public void setLocation(PVector newLoc) {
		location = newLoc;
	}

	// checking if the box is hiting the pan,
	// if does, the drawing of box will changing as
	// the boolean of isClosed is becoming true.
	public boolean checkPackHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 200 && Math.abs(location.y - p.getLocation().y) < 200);
	}

	// method could set the boolean of isClosed true or false.
	public void setisClosed(boolean value) {
		isClosed = value;
	}

	// checking the box is hitting customers or not.
	public boolean checkPackHitCustomer(customer c) {
		return (Math.abs(location.x - c.getLocation().x) < 200 && Math.abs(location.y - c.getLocation().y) < 200);
	}

}
