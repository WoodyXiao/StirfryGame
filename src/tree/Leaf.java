package tree;

import java.awt.geom.Ellipse2D;

import Util.Util;

import java.awt.Color;
import java.awt.Graphics2D;

/*here is the leaf class and for the tree displaying*/

public class Leaf {
	public Color leafColor;

	public Leaf() {
		leafColor = new Color((int) 255, (int) 183, (int) 197, (int) Util.random(200, 250));
	}

	public void draw(Graphics2D g2, int offset, double length) {
		g2.setColor(leafColor);
		int leafSize = (int) 10;
		int leafSize1 = (int) 25;

		g2.fill(new Ellipse2D.Double(offset - leafSize / 2, (int) (-length - leafSize / 2), leafSize, leafSize1 - 10));

	}

	public void setColor(int r, int g, int b, int o) {
		leafColor = new Color(r, g, b, 0);
	}
}
