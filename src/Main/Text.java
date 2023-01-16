package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import Util.Util;

/*this for the customer dialogue when they come in and ask 
for order, and they wil have different response if they get the wrong order or 
currect order. for example, the method of dialogueDisplay is for the daily conversation 
for askig what kind of the stir fry they want, if user make the correct one, it will use the 
method of correctOrder, like saying thankyou! if user making the wrong order to the customer, 
it will use the method of wrongOrder, saying "that is not my order, my order is XX" */

public class Text {
	private Font dialogue, notice, dialogue1;
	private String content;
	private boolean isShow = true;
	private boolean isShow2 = false;
	private boolean isShow3 = false;
	private boolean isShow4 = true;
	private String order;

	// Text setting, and the random way to create a random order of the
	// noodles from customer, three meat all have same rate of generalize.
	public Text(String content) {
		dialogue = new Font("Arial", Font.BOLD, 25);
		dialogue1 = new Font("Arial", Font.ITALIC, 20);
		notice = new Font("Arial", Font.PLAIN, 15);
		this.content = content;
		int i = (int) Util.random(0, 3);
		if (i < 1 && i >= 0) {
			order = "Beef";
		} else if (i >= 1 && i < 2) {
			order = "Chicken";
		} else if (i >= 2 && i <= 3) {
			order = "Pork";
		}
	}

	// method of displaying the text when customer show.
	// if the isShow boolean is false, the text will disppear.
	public void dialogueDisplay(Graphics2D g2) {
		if (isShow) {
			g2.setColor(new Color(255, 255, 255, 200));
			g2.fillRect(30, 740-30, 1220, 106);
			g2.setFont(dialogue);
			g2.setColor(Color.BLACK);
			g2.drawString(content + order + " stir fry ? Thank you so much!", 50, 770-30);
			g2.setColor(Color.darkGray);
			g2.setFont(notice);
			g2.drawString("(Click for next)", 1150, 830-30);

		}
	}

	// method of displaying the text in the condition that when
	// they received a wrong order that different from their expected one.
	// if the boolean of isShow2 is false, text will disappear.
	public void wrongOrder(Graphics2D g2) {
		if (isShow2) {
			g2.setColor(new Color(255, 255, 255, 200));
			g2.fillRect(30, 740-30, 1220, 106);
			g2.setFont(dialogue);
			g2.setColor(Color.BLACK);
			g2.drawString("Sorry, this is not my order, my order is " + order + " stir fry.", 50, 770-30);
			g2.setColor(Color.darkGray);
			g2.setFont(notice);
			g2.drawString("(Click for next)", 1150, 830-30);
		}
	}

	// method of displaying text when the condition is received the correct
	// order, if the boolean of isShow3 is false, the text will disappear.
	public void correctOrder(Graphics2D g2) {
		if (isShow3) {
			g2.setColor(new Color(255, 255, 255, 200));
			g2.fillRect(30, 740-30, 1220, 106);
			g2.setFont(dialogue);
			g2.setColor(Color.BLACK);
			g2.drawString("Thank you so much!!", 50, 770-30);
			g2.setColor(Color.darkGray);
			g2.setFont(notice);
			g2.drawString("(Click for next)", 1150, 830-30);
		}
	}

	public void instructionDisplay(Graphics2D g2, String instuction) {
		if (isShow4) {
			g2.setColor(new Color(255, 255, 255, 200));
			g2.fillRect(70, 190, 1100, 106);

			g2.setFont(dialogue1);
			g2.setColor(Color.BLACK);
			g2.drawString(instuction, 90, 220);
		}
	}

	// set boolean isShow in main
	public void setBoolean(boolean value) {
		isShow = value;
	}

	// set boolean isShow2 in main
	public void setBoolean2(boolean value) {
		isShow2 = value;
	}

	// set boolean isShow3 in main
	public void setBoolean3(boolean value) {
		isShow3 = value;
	}

	public boolean getBoolean() {
		return isShow;
	}

	// method for get the boolean of isShow3.
	public boolean getBoolean3() {
		return isShow3;
	}

	public void setBoolean4(boolean value) {
		isShow4 = value;
	}

	public boolean getBoolean4() {
		return isShow4;
	}

	public boolean checkTextHit(MouseEvent e) {
		return (Math.abs(30 - e.getX()) < 1220 / 1 && Math.abs(790 - e.getY()) < 106 / 1);
	}

	public String getOrder() {
		return order;
	}
}
