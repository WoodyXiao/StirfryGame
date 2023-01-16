package object;

import java.awt.Graphics2D;

import processing.core.PVector;


/*pan basic decorator*/

public class PanBasicDecorator implements PanDrawingInterface {

	protected PanDrawingInterface parentPanDecorator;

	public PanBasicDecorator(PanDrawingInterface panDecorator) {
		parentPanDecorator = panDecorator;
	}

	public void drawPan(Graphics2D g2) {
		parentPanDecorator.drawPan(g2);
	}

	@Override
	public Pan getBasePan() {
		return parentPanDecorator.getBasePan();
	}
	
	@Override
	public PVector getLocation() {
		return parentPanDecorator.getLocation();
	}
}
