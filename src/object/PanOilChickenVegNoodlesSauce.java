package object;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Util.ImageLoader;
import processing.core.PVector;

public class PanOilChickenVegNoodlesSauce extends PanBasicDecorator {
	private BufferedImage img;
	private boolean isShow = true;

	public PanOilChickenVegNoodlesSauce(PanDrawingInterface panDecorator) {
		super(panDecorator);
		img = ImageLoader.loadImage("assets/panwchickenwvegwnoodleswsauce.png");
	}

	@Override
	public void drawPan(Graphics2D g2) {
		if (isShow) {
			parentPanDecorator.drawPan(g2);
			AffineTransform tr = g2.getTransform();
			Pan p = getBasePan();
			PVector loc = p.getLocation();
			g2.translate(loc.x, loc.y);
			g2.drawImage(img, -431 / 2, -459 / 2, 451, 479, null);
			g2.setTransform(tr);
		}
	}

	@Override
	public Pan getBasePan() {
		return parentPanDecorator.getBasePan();
	}

	public void setBoolean(boolean value) {
		isShow = value;
	}
}
