package tree;

import java.awt.Graphics2D;
import processing.core.PVector;
import tree.*;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import Main.KtichenPane;

/*here is the part of drawing the fractal tree, 
 * and calling the addLeaf method and addLight method. */

public class GeneratedTree {
	private int locx, locy;
	private Branch tree;

	public GeneratedTree(int x, int y, double len, double wid, double ang, int depth) {
		locx = x;
		locy = y;
		tree = new Branch(len, wid, ang, depth);
		tree.addLeaf(0);
		tree.addLight(ang);

	}

	public void drawTree(Graphics2D g2) {
		AffineTransform tr = g2.getTransform();
		g2.translate(locx, locy);
		tree.drawBranch(g2);
		g2.setTransform(tr);
	}

	public void drawTreeWithWind(Graphics2D g2, PVector wind) {

	}

	// method for turning the lights on
	public void turnLightOn() {
		Iterator<Branch> it = Branch.getAllBranchIterator(tree, 1);
		while (it.hasNext()) {
			Branch br = it.next();
			br.turnLightOn();
		}
	}

	// method for turning the lights off.
	public void turnLightOff() {
		Iterator<Branch> it = Branch.getAllBranchIterator(tree, 1);
		while (it.hasNext()) {
			Branch br = it.next();
			br.turnLightOff();
		}
	}

}
