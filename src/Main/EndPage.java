package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.KtichenPane;
import Util.ImageLoader;

/*Here is for the ending page showing for when
players click the open sign again after finished 
the last orders, and in this page will show the 
summary of the different types order finishing and the total amount. */

public class EndPage {
	private Font noodlesInfo, totalInfo;
	private BufferedImage img;
	private boolean isEnd = true;

	public EndPage() {
		img = ImageLoader.loadImage("assets/end.png");
		noodlesInfo = new Font("Arial", Font.BOLD, 40);
		totalInfo = new Font("Arial", Font.BOLD, 60);
	}

	// End page drawing method, when the
	// boolean value of isEnd is false,
	// the page will not show.
	public void gameEnd(Graphics2D g2) {
		if (isEnd) {
			g2.drawImage(img, 0, 0, 1280, 886, null);
			g2.setFont(noodlesInfo);
			g2.setColor(new Color(202, 76, 80));
			g2.drawString("" + KtichenPane.chickenNoodles, 800, 310);
			g2.drawString("" + KtichenPane.beefNoodles, 800, 470);
			g2.drawString("" + KtichenPane.porkNoodles, 800, 630);
			g2.setFont(totalInfo);
			g2.drawString("" + KtichenPane.totalNoodles, 800, 800);
		}
	}

	// A method that setting the boolean value of isEnd.
	public void setIsEnd(boolean value) {
		isEnd = value;
	}

}
