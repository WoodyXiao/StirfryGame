package object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*here is the decorator of pan for showing the animation of cooking stir fry.*/

public class PanCookingAnimation extends PanBasicDecorator {
	protected BufferedImage img;
	protected BufferedImage[] imgs = new BufferedImage[11]; // group of pan images of animation of cooking.
	protected int counts; // this is for counting the images playing.

	public PanCookingAnimation(PanDrawingInterface panDecorator) {
		super(panDecorator);

		// loading total 11 pieces of images for displaying the animation.
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageLoader.loadImage("assets/Animation" + (i + 1) + ".png");
		}
	}

	@Override
	public void drawPan(Graphics2D g2) {

		parentPanDecorator.drawPan(g2);
		AffineTransform tr = g2.getTransform();
		Pan p = getBasePan();
		PVector loc = p.getLocation();
		g2.translate(loc.x, loc.y);

		// here is the setting for showing the animation, showing each images at each
		// frames.
		if (counts >= 0) {
			counts++;
			counts = (counts + 1) % 110;
			if (counts == imgs.length) {
				counts = 0;
			}
			img = imgs[counts / 10];
			g2.drawImage(imgs[counts / 10], -431 / 2, -459 / 2, 451, 479, null);
		}

		g2.setTransform(tr);

	}

	@Override
	public Pan getBasePan() {
		return parentPanDecorator.getBasePan();
	}

}
