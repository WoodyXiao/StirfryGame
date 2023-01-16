package Noodles;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Util.ImageLoader;
import object.Pan;
import processing.core.PVector;

/*here is the Noodles class that use in the game, 
ther are some methods, like drawNoodles, checkNoodlesHit with 
the mouse clicking, checkNoodlesHitPan, checking the noodles touching the 
pan obejct, etc...*/

public class Noodles {

	// location
	protected PVector location;
	protected BufferedImage img;

	public Noodles(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/noodles.png");

	}

	public void drawNoodles(Graphics2D g2) {

		AffineTransform tr = g2.getTransform();
		g2.translate(location.x, location.y);
		g2.drawImage(img, -100 / 2, -98 / 2, 100, 98, null);
		g2.setTransform(tr);

	}

	// checking the mouse if hit the noodles itself or not.
	public boolean checkNoodlesHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (100 / 2) && Math.abs(location.y - e.getY()) < (98 / 2));
	}

	// reset the location of the noodles when mouse pressing the noodles.
	public void setLocation(PVector newLoc) {
		location = newLoc;
	}

	// checking the condition that if the noodles is touching pan or not.
	public boolean checkNoodlesHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 150 && Math.abs(location.y - p.getLocation().y) < 150);
	}

	// go back to the original location.
	public void originalLoaction(PVector oLoc) {
		location = oLoc;
	}

}