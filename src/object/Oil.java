package object;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import Main.KtichenPane;
import Util.ImageLoader;
import processing.core.PVector;

/*here is the Oil class that representing the oil bottle in the game, 
users could dragging it and pour oil dropping in the pan. */

public class Oil {
	// location
	protected PVector location;
	protected BufferedImage img;
	protected boolean isPull = false;
	protected ArrayList<oilDrop> drops = new ArrayList<oilDrop>();
	protected boolean isDropHitPan = false;

	public Oil(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/oil.png");

	}

	public void drawOil(Graphics2D g2) {
		if (!isPull) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -80 / 2, -314 / 2, 70, 304, null);
			g2.setTransform(tr);
		} else {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.rotate(-90);
			g2.drawImage(img, -80 / 2, -314 / 2, 70, 304, null);
			g2.setTransform(tr);
		}

	}

	// checking if the mouse hit in the oil or not.
	public boolean checkOilHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (80 / 2) && Math.abs(location.y - e.getY()) < (314 / 2));

	}

	// set the location of oil following with the mouse movement
	public void setLocation(PVector newLoc) {
		location = newLoc;
	}

	// when mouse released, oil location will be reset at the original location.
	public void originalLoaction(PVector oLoc) {
		location = oLoc;
	}

	// checking if the oil close to the pan enough or not.
	public boolean checkOilHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 200);
	}

	// set the boolean value of deciding if need pull oil or no.
	public void setisPull(boolean value) {
		isPull = value;
	}

	public void drop() {
		drops.add(new oilDrop(location.x - 140, location.y + 100, 0, 4));
	}

	public void drawDrop(Graphics2D g2, Pan p) {
		for (int i = 0; i < drops.size(); i++) {
			oilDrop d = drops.get(i);
			d.drawOilDrop(g2);
			d.move();
			if (d.hitPan(p)) {
				d.setisAlive(false);
				drops.remove(d);
				if (KtichenPane.button.getBoolean()) {
					isDropHitPan = true;
				}
			}
		}
	}

	public PVector getLocation() {
		return location;
	}

	public boolean getisDropHitPan() {
		return isDropHitPan;
	}

	public void setIsDropHitPan(boolean value) {
		isDropHitPan = value;
	}
}