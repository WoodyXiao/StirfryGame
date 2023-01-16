package tree;

import Util.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import processing.core.PVector;

/*here is the class for light, for displaying in the tree, and also have the method for setColor for the lights, */

public class Light {

	Color lightColor;

	public Light() {
		lightColor = new Color(255, 255, 255, 200);
	}

	public void draw(Graphics2D g2, int offset, double length) {
		g2.setColor(lightColor);
		int lightSize = 12;
		g2.fill(new Ellipse2D.Double(offset - lightSize / 2, (int) (-length - lightSize / 2), lightSize, lightSize));

	}

	public void setColor(int r, int g, int b, int t) {
		lightColor = new Color(r, g, b);
	}

}

