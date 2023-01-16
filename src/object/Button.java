package object;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*here is the button of the stove, when clicking on it, 
fire would comes out from the stove. */

public class Button {

	// location
	protected PVector location;
	protected BufferedImage img, img1;
	protected boolean isOn = false;

	public Button(double x, double y) {
		location = new PVector((float) x, (float) y);
		img = ImageLoader.loadImage("assets/buttonOff.png");
		img1 = ImageLoader.loadImage("assets/buttonOn.png");
 
	}
 
	// drawing only the isoN is true.
	public void drawButton(Graphics2D g2) {
		if (!isOn) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img, -70 / 2, -64 / 2, 70, 64, null);
			g2.setTransform(tr);
		} else {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.drawImage(img1, -64 / 2, -70 / 2, 64, 70, null);
			g2.setTransform(tr);
		}

	}

	// checking the button if hiting the mouse clicking.
	public boolean checkButtonHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (70 / 2) && Math.abs(location.y - e.getY()) < (70 / 2));
	}

	// method of setting the boolean value of isOn.
	public void setisOn(boolean value) {
		isOn = value;
	}

	// mothod that geting the isOn boolean value.
	public boolean getBoolean() {
		return isOn;
	}

}
