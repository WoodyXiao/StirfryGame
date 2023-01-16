package object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*here is the sauce drop class, it create when soysauce bottle pouring toward pan. */

public class sauceDrop {
	protected PVector location, vel;
	protected BufferedImage img;

	protected boolean isAlive = true;

	public sauceDrop(double x, double y, double sx, double sy) {
		location = new PVector((float) x, (float) y);
		vel = new PVector((float) sx, (float) sy);
		img = ImageLoader.loadImage("assets/sauceDrop.png");
	}

	public void drawSauceDrop(Graphics2D g2) {
		if (isAlive) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -20 / 2, -37 / 2, 20, 37, null);
			g2.setTransform(tr);
		}
	}

	public void move() {
		location.add(vel);
	}

	public boolean hitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 200 && Math.abs(location.y - p.getLocation().y) < 80);
	}

	public void setisAlive(boolean value) {
		isAlive = value;
	}
}
