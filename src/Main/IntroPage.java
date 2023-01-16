package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.KtichenPane;
import Util.ImageLoader;

/*Here is the introduction page before the game starting, 
in this page, there is a button of play, 
when click this button, it will start the game. 
*/

public class IntroPage {
	private Font noodlesInfo, totalInfo;
	private BufferedImage img;
	private boolean isIntro = true;

	public IntroPage() {
		img = ImageLoader.loadImage("assets/Intro.png");
		noodlesInfo = new Font("Arial", Font.BOLD, 40);
		totalInfo = new Font("Arial", Font.BOLD, 60);
	}

	public void gameIntro(Graphics2D g2) {
		if (isIntro) {
			g2.drawImage(img, 0, 0, 1280, 906, null);
		}
	}

	public void setIsIntro(boolean value) {
		isIntro = value;
	}

}
