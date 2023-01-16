package Meat;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import object.Pan;
import processing.core.PVector;

/*here is for the abrast class of Meat, it included thoese 
abstract methods. */

public abstract class Meat {

	abstract public void drawMeat(Graphics2D g2);

	abstract public boolean checkMeatHit(MouseEvent e);

	abstract public boolean checkMeatHitPan(Pan p);

	abstract public void setLocation(PVector newLoc);

	abstract public void originalLocation(PVector oLoc);
}
