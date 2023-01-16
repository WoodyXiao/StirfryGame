package object;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import static Util.Util.*;
import processing.core.PApplet;

/*here is the fire drawing by Perlin Noise, generative methods. */

public class Fire {
	private float xStart;
	private float xSeed;
	private float ySeed;
	private PApplet pa;

	public Fire() {
		xStart = random(20);
		xSeed = xStart;
		ySeed = random(10);
		pa = new PApplet();
	}

	// drawing the fire with using generative way.
	public void drawFire(Graphics2D g2) {
		float noiseFactor;
		for (int y = 550; y <= 660; y += 4) {
			ySeed += 0.10;
			xSeed = xStart;
			for (int x = 400; x <= 880; x += 1) {
				xSeed += 0.05;
				noiseFactor = pa.noise(xSeed, ySeed);

				AffineTransform at = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor * radians(360));
				float diameter = noiseFactor * 50;
				int orange = (int) (20 + (noiseFactor * 235));
				int red = (int) (180 + (noiseFactor * 75));
				int alph = (int) (150 + (noiseFactor * 105));
				g2.setColor(new Color(red, orange, 0, alph));
				g2.fill(new Rectangle2D.Float(-diameter / 2, -diameter / 1, diameter / 2, diameter / 2));
				g2.setTransform(at);
			}
		}
	}

}
