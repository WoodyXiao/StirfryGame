package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import Util.Util;
import processing.core.PVector;

public class Star {
	private GeneralPath starPath;
	private PVector location;
	private double scale;
	private double angle;

	public Star(double x, double y, double scale) {
		this.scale = scale;
		location = new PVector((float) x, (float) y);
		int xPoints[] = { 55, 67, 109, 73, 83, 55, 27, 37, 1, 43 };
		int yPoints[] = { 0, 36, 36, 54, 96, 72, 96, 54, 36, 36 };
		angle = Math.toRadians(Util.random(-360, 360));
		starPath = new GeneralPath();

		// set the initial coordinate of the General Path
		starPath.moveTo(xPoints[0], yPoints[0]);

		// create the star--this does not draw the star
		for (int index = 1; index < xPoints.length; index++) {
			starPath.lineTo(xPoints[index], yPoints[index]);
		}

		starPath.closePath(); // close the shape
	}

	public void drawStar(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(location.x, location.y);
		g2.scale(scale, scale);
		g2.rotate(angle);
		g2.setColor(Color.yellow);
		g2.fill(starPath);
		g2.setTransform(transform);
	}
}
