package object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*here is the oil drop class, it would create when oil 
bottle pouring on the pan. 
*/
public class oilDrop {
	protected PVector location, vel;
	protected BufferedImage img;

	protected boolean isAlive = true;

	public oilDrop(double x, double y, double sx, double sy) {
		location = new PVector((float) x, (float) y);
		vel = new PVector((float) sx, (float) sy);
		img = ImageLoader.loadImage("assets/oilDrop.png");
	}

	// when the boolean of isAlive is true, drawing,
	// when the boolean of isAlive is false, disappear.
	public void drawOilDrop(Graphics2D g2) {
		if (isAlive) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -20 / 2, -37 / 2, 20, 37, null);
			g2.setTransform(tr);
		}
	}

	// moving with the y location, acting like dropping.
	public void move() {
		location.add(vel);
	}

	// method checking if hitting pan or no, if hitting pan,
	// boolean of isAlive is turning to false.
	public boolean hitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 200 && Math.abs(location.y - p.getLocation().y) < 80);
	}

	public void setisAlive(boolean value) {
		isAlive = value;
	}
}
