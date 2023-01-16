package tree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import Util.Util;

/*here is the branch class for drawing the fractal tree, there are couple methods, 
 * such as drawBranch(), addLeaf(), drawLeaf(), etc...*/

public class Branch {
	public static final double angleLeft = -1.0;
	public static final double angleRight = 1.0;
	public static final double centerCoef = 0.8;
	public static final double sideCoef = 0.5;

	private double length;
	private double width;
	private double angle;
	private int depth;
	private AffineTransform tr;

	private Branch left = null;
	private Branch center = null;
	private Branch right = null;
	private Leaf leaf = null;
	private Light light = null;

	private Rectangle2D.Double shape;
	private Color branchColor;

	public Branch(double len, double wid, double ang, int depth) {
		length = len;
		width = wid;
		angle = ang;
		this.depth = depth;
		shape = new Rectangle2D.Double(-width / 2, -length, width, length);
		branchColor = new Color(85, 52, 43);

		if (depth > 1) {
			if (Util.random(0, 1) > 0.02)
				left = new Branch(length * sideCoef, width * sideCoef,
						angleLeft + Util.random(-angleLeft * 0.2, angleLeft * 0.2), depth - 1);
			if (Util.random(0, 1) > 0.02)
				right = new Branch(length * sideCoef, width * sideCoef,
						angleRight + Util.random(-angleRight * 0.2, angleRight * 0.2), depth - 1);
			if (Util.random(0, 1) > 0.01)
				center = new Branch(length * centerCoef, width * sideCoef, Util.random(-0.1, 0.1), depth - 1);
		}
	}

	public void drawBranch(Graphics2D g2) {
		AffineTransform tr = g2.getTransform();
		g2.rotate(angle);

		g2.setColor(branchColor);
		g2.fill(shape);

		g2.translate(0, -length);
		if (left != null)
			left.drawBranch(g2);
		if (right != null)
			right.drawBranch(g2);
		if (center != null)
			center.drawBranch(g2);
		g2.setTransform(tr);

		this.drawLeaf(g2, angle);
		this.drawLight(g2, angle);
	}

	// getting transformation information of drawing branch.
	public AffineTransform getBranchTransform() {
		return tr;
	}

	// getting the variable of depth.
	public int getDepth() {
		return depth;
	}

	// for the leaf adding method.
	public void addLeaf(double absoluteAngle) {
		absoluteAngle += angle;
		if (depth < 4) {
			if (Math.abs(absoluteAngle) > 0.25) {
				leaf = new Leaf();

			}
		}
		if (left != null)
			left.addLeaf(absoluteAngle);
		if (right != null)
			right.addLeaf(absoluteAngle);
		if (center != null)
			center.addLeaf(absoluteAngle);
	}

	// here is the method for drawing leaf for the tree.
	public void drawLeaf(Graphics2D g, double absoluteAngle) {
		AffineTransform tr = g.getTransform();
		g.rotate(angle);
		if (leaf != null) {
			int offset = (int) (-width);
			int offset1 = (int) (-length / 2);
			if (absoluteAngle > 0) {
				offset = -offset;
				offset1 = -offset1;
			}
			leaf.draw(g, offset, offset1);
		}
		g.setTransform(tr);
	}

	// here is for adding lights method.
	public void addLight(double absoluteAngle) {
		absoluteAngle += angle;
		if (depth < 6) {
			if (Math.abs(absoluteAngle) > 0.25) {
				light = new Light();

			}
		}
		if (left != null)
			left.addLight(absoluteAngle);
		if (right != null)
			right.addLight(absoluteAngle);
		if (center != null)
			center.addLight(absoluteAngle);
	}

	// method for turn the lights on, and the color is random
	public void turnLightOn() {
		if (light != null) {
			light.setColor(194, 76, 246, 200);
		}
	}

	// method for turn the lights on, and the color is random
	public void turnLightOff() {
		if (light != null) {
			light.setColor(255, 255, 255, 200);
		}
	}

	// here is the method for drawing light for the tree.
	public void drawLight(Graphics2D g, double absoluteAngle) {
		AffineTransform tr = g.getTransform();
		g.rotate(angle);
		if (light != null) {
			int offset = (int) (-width);
			int offset1 = (int) (-length / 2);
			if (absoluteAngle > 0) {
				offset = -offset;
				offset1 = -offset1;
			}
			light.draw(g, offset, length);
		}
		g.setTransform(tr);
	}

	// iterator setting for branch that going through all branches when the depth is
	// match the entered depth value.
	public List<Branch> children() {
		List<Branch> children = new ArrayList<Branch>();
		if (left != null)
			children.add(left);
		if (right != null)
			children.add(right);
		if (center != null)
			children.add(center);

		return children;
	}

	public static Iterator<Branch> getAllBranchIterator(Branch root, int depth) {
		return new AllBranchIterator(root, depth);
	}

	private static class AllBranchIterator implements Iterator<Branch> {
		Stack<Branch> stack;
		int requiredDepth;

		private AllBranchIterator(Branch root, int depth) {
			stack = new Stack<Branch>();
			stack.push(root);
			requiredDepth = depth;
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}

		public Branch next() {
			if (!stack.isEmpty()) {
				Branch curr = stack.pop();
				List<Branch> children = curr.children();
				Collections.reverse(children);
				if (curr.getDepth() > requiredDepth) {
					for (Branch b : children) {
						stack.push(b);
					}
				}
				if (curr.getDepth() == requiredDepth)
					return curr;
				else
					return next();
			}
			return null;
		}

	}
}
