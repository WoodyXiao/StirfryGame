package object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import Util.ImageLoader;
import processing.core.PVector;

/*here is the class of SoyaSauce, representing the soysuace bottle in the game, 
players could dragging it and pouring sauce towards pan.*/

public class SoyaSauce {

	// location
	protected PVector location;
	protected double x, y;
	protected BufferedImage img;
	protected boolean isPull = false;
	protected ArrayList<sauceDrop> drops = new ArrayList<sauceDrop>();
	protected boolean isDropHitPan = false;

	public SoyaSauce(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/sauce.png");

	}

	public void drawSauce(Graphics2D g2) {
		if (!isPull) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -100 / 2, -152 / 2, 80, 132, null);
			g2.setTransform(tr);
		} else {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.rotate(-90);
			g2.drawImage(img, -100 / 2, -152 / 2, 80, 132, null);
			g2.setTransform(tr);
		}

	}

	// method of checking the sauce bottle hitting with mouse or not.
	public boolean checkSauceHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (100 / 2) && Math.abs(location.y - e.getY()) < 152 / 2);
	}

	public void setLocation(PVector newLoc) {
		location = newLoc;
	}

	public void originalLocation(PVector oLoc) {
		location = oLoc;
	}

	// when sauce move close enough to the pan, the sauce bottle would rotated and
	// act like pouring status.
	public boolean checkSauceHitPan(Pan p) {
		return (Math.abs(location.x - p.getLocation().x) < 200);
	}

	// method of setting the boolean of setisPull is true or not.
	public void setisPull(boolean value) {
		isPull = value;
	}

	// method of drop, when call it, it would create sauce drops class.
	public void drop() {
		drops.add(new sauceDrop(location.x - 55, location.y + 80, 0, 3));
	}

	// method of drawing sauce suace drop.
	public void drawDrop(Graphics2D g2, Pan p) {
		for (int i = 0; i < drops.size(); i++) {
			sauceDrop s = drops.get(i);
			s.drawSauceDrop(g2);
			s.move();
			if (s.hitPan(p)) {
				s.setisAlive(false);
				drops.remove(s);
				isDropHitPan = true;
			}
		}
	}

	// method of checking if sauce drop hitting pan or not.
	public boolean getisDropHitPan() {
		return isDropHitPan;
	}

	public void setIsDropHitPan(boolean value) {
		isDropHitPan = value;
	}

}
