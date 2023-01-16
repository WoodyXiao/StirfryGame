package object;

import java.awt.Graphics2D;

import processing.core.PVector;

/*//interface of pan drawing. 
*/
public interface PanDrawingInterface {
	public Pan getBasePan();

	public void drawPan(Graphics2D g2);

	public PVector getLocation();
}
