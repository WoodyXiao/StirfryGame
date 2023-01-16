package object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

/*here is a decorator of pan when the pan havin oil and beef inside. */

public class PanOilBeef extends PanBasicDecorator {
	private BufferedImage img;

	public PanOilBeef(PanDrawingInterface panDecorator) {
		super(panDecorator);
		img = ImageLoader.loadImage("assets/panwbeef.png");
	}

	@Override
	public void drawPan(Graphics2D g2) {
		parentPanDecorator.drawPan(g2);
		AffineTransform tr = g2.getTransform();
		Pan p = getBasePan();
		PVector loc = p.getLocation();
		g2.translate(loc.x, loc.y);
		g2.drawImage(img, -431 / 2, -459 / 2, 451, 479, null);
		g2.setTransform(tr);
	}

	@Override
	public Pan getBasePan() {
		return parentPanDecorator.getBasePan();
	}
}
