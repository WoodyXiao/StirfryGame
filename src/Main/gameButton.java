package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import Util.ImageLoader;
import processing.core.PVector;

/*here is for the graphics buttons in the introduction page and end page,
one button is play, another button is reply;
when user click the play button, the boolean of the 
Introduction page will become false, and disappear. */

public class gameButton {
	protected PVector location;
	protected Rectangle2D.Double buttonShape;
	protected Font gameText;
	protected boolean isShowGamePlay = true;
	protected boolean isShowReplay = false;
	private GeneralPath storageButton;
	private Area shape;

	public gameButton(double x, double y) {
		location = new PVector((float) x, (float) y);
		buttonShape = new Rectangle2D.Double(-50 / 2, -30 / 2, 50, 30);
		gameText = new Font("Arial", Font.BOLD, 30);

		// drawing the shape of arrow for storage room.
		int xPoints[] = { 0, 80, 80, 130, 80, 80, 0 };
		int yPoints[] = { 80, 80, 50, 95, 140, 110, 110 };
		storageButton = new GeneralPath();
		storageButton.moveTo(xPoints[0], yPoints[0]);
		for (int index = 1; index < xPoints.length; index++) {
			storageButton.lineTo(xPoints[index], yPoints[index]);
		}
		shape = new Area();
		shape.add(new Area(storageButton));
	}

	// drawing the gameplaying button with the boolean isShowGamePlay.
	// if the boolean of isShowGamePlay is false, it disappear.
	public void drawGamePlay(Graphics2D g2) {
		if (isShowGamePlay) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.setColor(new Color(113, 211, 102));
			g2.fill(buttonShape);
			g2.setColor(new Color(43, 102, 35));
			g2.setFont(gameText);
			g2.setColor(new Color(42, 102, 35));
			g2.drawString("Play", -25, 10);
			g2.setTransform(tr);
		}
	}

	// checking the gamePlay button, get the boolean of this, and set the boolean of
	// this button.
	public boolean checkButtonHit(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (70 / 2) && Math.abs(location.y - e.getY()) < (30 / 2));
	}

	public void setIsGamePlay(boolean value) {
		isShowGamePlay = value;
	}

	public boolean getBoolean() {
		return isShowGamePlay;
	}

	// drawing the game replay button.
	// if the boolean isShowReplay is false, the button will be disappears.
	public void drawGameReplay(Graphics2D g2) {
		if (isShowReplay) {
			AffineTransform tr = g2.getTransform();
			g2.translate(location.x, location.y);
			g2.setColor(new Color(113, 211, 102));
			g2.fill(buttonShape);
			g2.setColor(new Color(43, 102, 35));
			g2.setFont(gameText);
			g2.setColor(new Color(42, 102, 35));
			g2.drawString("Replay", -25, 10);
			g2.setTransform(tr);
		}
	}

	// checking the replay game play button, get the boolean of this, and set the
	// boolean of this button.
	public boolean checkButtonHit1(MouseEvent e) {
		return (Math.abs(location.x - e.getX()) < (70 / 2) && Math.abs(location.y - e.getY()) < (30 / 2));
	}

	public void setIsGameReplay(boolean value) {
		isShowReplay = value;
	}

	public boolean getBoolean1() {
		return isShowReplay;
	}

	// drawing the button of storage room in the game
	public void drawStorage(Graphics2D g2) {
		AffineTransform tr = g2.getTransform();
		g2.translate(location.x, location.y);
		g2.setColor(new Color(255, 0, 0));
		g2.fill(storageButton);
		g2.setTransform(tr);
	}

	// boolean method for checking if mouse hit on the arrow storage room or not.
	public boolean checkStorage(MouseEvent e) {
		return getBoundary().getBounds2D().contains(e.getX(), e.getY());
	}

	// getting the shape of the arrow storage room.
	public Shape getBoundary() {
		AffineTransform tr = new AffineTransform();
		tr.translate(location.x, location.y);
		return tr.createTransformedShape(shape);
	}

}
