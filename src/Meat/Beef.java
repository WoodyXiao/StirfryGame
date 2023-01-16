package Meat;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import object.Pan;
import processing.core.PVector;

/*beef class that extending from the Meat, using the same methods 
including checkMeatHit, drawMeat, etc.. */

public class Beef extends Meat {
	protected PVector location;
	protected BufferedImage img;

	public Beef(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/beef.png");
	}

	public void drawMeat(Graphics2D g2) {

		AffineTransform tr = g2.getTransform();
		g2.translate(location.x, location.y);
		g2.drawImage(img, -80 / 2, -51 / 2, 80, 51, null);
		g2.setTransform(tr);

	}

	public boolean checkBeefHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (80 / 2) && Math.abs(location.y - e.getY()) < (51 / 2));
	}

	public boolean checkMeatHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (80 / 2) && Math.abs(location.y - e.getY()) < (51 / 2));
	}

	public void setLocation(PVector newLoc) {
		location = newLoc;
	}

	public boolean checkBeefHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 150 && Math.abs(location.y - p.getLocation().y) < 150);
	}

	public boolean checkMeatHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 150 && Math.abs(location.y - p.getLocation().y) < 150);
	}

	public void originalLocation(PVector oLoc) {
		location = oLoc;
	}

}
