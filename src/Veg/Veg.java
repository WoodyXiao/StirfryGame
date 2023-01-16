package Veg;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import object.Pan;
import processing.core.PVector;

/*here is the Veg class that representing the vegetables, 
having drawVeg method, checkVegHit, checkVegHitPan mothods etc..
*/

public class Veg {

	// location
	protected PVector location;
	protected double x, y;
	protected BufferedImage img;

	public Veg(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/Veg.png");

	}

	public void drawVeg(Graphics2D g2) {

		AffineTransform tr = g2.getTransform();
		g2.translate(location.x, location.y);
		g2.drawImage(img, -120 / 2, -62 / 2, 100, 114, null);
		g2.setTransform(tr);

	}

	// when mouse hit the veg itself.
	public boolean checkVegHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (100 / 2) && Math.abs(location.y - e.getY()) < (114 / 2));
	}

	// seting the new location of the veg.
	public void setLocation(PVector newLoc) {
		location = newLoc;
	}

	// checking the condition if the veg is touching pan obejct or not.
	public boolean checkVegHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 150 && Math.abs(location.y - p.getLocation().y) < 150);
	}

	// set the veg location to the original location.
	public void originalLoaction(PVector oLoc) {
		location = oLoc;
	}
}
