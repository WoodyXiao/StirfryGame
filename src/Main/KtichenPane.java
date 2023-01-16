package Main;

import Veg.Veg;
import object.Box;
import object.Button;
import object.Fire;
import object.Oil;
import object.Pan;
import object.PanBasicDecorator;
import object.PanCookingAnimation;
import object.PanDrawingInterface;
import object.PanOil;
import object.PanOilBeef;
import object.PanOilBeefVeg;
import object.PanOilBeefVegNoodles;
import object.PanOilBeefVegNoodlesSauce;
import object.PanOilChicken;
import object.PanOilChickenVeg;
import object.PanOilChickenVegNoodles;
import object.PanOilChickenVegNoodlesSauce;
import object.PanOilPork;
import object.PanOilPorkVeg;
import object.PanOilPorkVegNoodles;
import object.PanOilPorkVegNoodlesSauce;
import object.Sign;
import object.SoyaSauce;
import object.Star;
import Customer.customer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import processing.core.PVector;
import tree.GeneratedTree;
import Meat.Meat;
import Meat.Beef;
import Meat.Chicken;
import Meat.Pork;
import Noodles.Noodles;
import Util.ImageLoader;
import Util.Util;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import sound.MinimHelper;

public class KtichenPane extends JPanel implements ActionListener {

	// for background image
	// for all of the kitchen stuff setting,
	// packing, pan, oil bottle, sauce bottle,
	// open/close sign, tree, day and night sky.
	// vegetable, meat, nooldes.
	private Timer timer;
	public static BufferedImage background;
	private GeneratedTree tree;
	private Pan pan;
	private customer customer;
	private Oil oil;
	private SoyaSauce soy;
	private Box box;
	public static Button button;
	private Sign sign;
	private static ArrayList<Veg> vegs = new ArrayList<Veg>();
	private static ArrayList<Meat> meats = new ArrayList<Meat>();
	private static ArrayList<Noodles> noodles = new ArrayList<Noodles>();
	private Fire fire;
	private ArrayList<Box> boxs = new ArrayList<Box>();
	// for the setting of pan decorators.
	public static PanDrawingInterface panPainter, panWithOil, panWithBeef, panWithChicken, panWithPork, panWithBeefVeg,
			panWithChickenVeg, panWithPorkVeg, panWithBeefVegNoodles, panWithChickenVegNoodles, panWithPorkVegNoodles,
			panWithChickenVegNoodlesSauce, panWithPorkVegNoodlesSauce, panWithBeefVegNoodlesSauce, panAnimation;
	private boolean isAnimation = false;
	private int customerTimer = 0;

	// Introduction page and ending page setting, and
	// button of play and replay.
	private EndPage end;
	private IntroPage intro;
	private gameButton gButton, gButton2, gButton3;

	// for the customer dialogue setting.
	private ArrayList<Text> texts = new ArrayList<Text>();
	private Text text, text1;
	private String dialogue = "Hi, Could I get a ";
	private String currentOrder = "";

	// for the instruction text for first time cooking
	private String ins = "";
	private String ins1 = "Hi, now you own an stir-fry resturant,Open the store first";
	private String ins2 = "Congradulation, now you have a order, light the stove first!";
	private String ins3 = "Ok, so now drage a oil bottle and pour on the pan";
	private String ins4 = "Good!, now take one of the meat that customer order to the pan!";
	private String ins5 = "Take a veg to the pan~";
	private String ins6 = "Then take a noodles and put it on the pan~";
	private String ins7 = "Look good, pour some sauce on it!";
	private String ins8 = "Yum Yum!, its amlost done,try press space key to cook more";
	private String ins8_1 = "press space again, then turn the stove off and pick a package box to pack the stir-fry! and pass to the customer!";
	private String ins9 = "Good job!, now you finished the instruction, enjoy you stir-fry trip!!";
	private Text text2;

	// star and moon and sun
	private ArrayList<Star> stars = new ArrayList<Star>();

	// for the Controlling panel setting.
	private static ControlPanel cPanel;
	public static boolean isCPanel = false;
	// sound setting.
	private static AudioPlayer cookSound, cookSound1, ligthingStoveSound, customerSound, successSound, failSound,
			backgroundMusic, openDoorSound, introMusic, endSound;

	public KtichenPane(ControlPanel cp) {
		// super();
		cPanel = cp;
		// size of screen
		this.setPreferredSize(new Dimension(1280, 800));
		// background image loading.
		if (timeState == "Night") {
			this.setBackground(new Color(7, 11, 52));
		} else if (timeState == "Day") {
			this.setBackground(new Color(153, 215, 228));

		}

		// for the star setting, and sun, and moon.
		for (int i = 0; i < 20; i++) {
			stars.add(new Star(Util.random(200, 1000), Util.random(20, 350), Util.random(0.1, 0.4)));
		}

		// drawing tree and the background.
		tree = new GeneratedTree(650, 600, 150, 25, 0, 6);
		background = ImageLoader.loadImage("assets/background.png");
		// all of pan decorators.
		pan = new Pan(635, 580);
		panPainter = new PanBasicDecorator(pan);
		panWithOil = new PanOil(pan);
		panWithBeef = new PanOilBeef(pan);
		panWithChicken = new PanOilChicken(pan);
		panWithPork = new PanOilPork(pan);
		panWithBeefVeg = new PanOilBeefVeg(pan);
		panWithChickenVeg = new PanOilChickenVeg(pan);
		panWithPorkVeg = new PanOilPorkVeg(pan);
		panWithBeefVegNoodles = new PanOilBeefVegNoodles(pan);
		panWithChickenVegNoodles = new PanOilChickenVegNoodles(pan);
		panWithPorkVegNoodles = new PanOilPorkVegNoodles(pan);
		panWithChickenVegNoodlesSauce = new PanOilChickenVegNoodlesSauce(pan);
		panWithPorkVegNoodlesSauce = new PanOilPorkVegNoodlesSauce(pan);
		panWithBeefVegNoodlesSauce = new PanOilBeefVegNoodlesSauce(pan);
		panAnimation = new PanCookingAnimation(pan);
		// drawing objects list.
		oil = new Oil(1060, 650);
		soy = new SoyaSauce(1200, 725);
		box = new Box(150, 600);
		boxs.add(box);
		button = new Button(370, 780);
		sign = new Sign(310, 90);
		fire = new Fire();
		customer = new customer(150, 300);
		for (int i = 0; i < 5; i++) {
			vegs.add(new Veg(990 + i * 50, 255));
		}
		for (int i = 0; i < 6; i++) {
			if (i < 2)
				meats.add(new Beef(970 + i * 50, 480));
			else if (i >= 2 && i < 4)
				meats.add(new Chicken(970 + i * 50, 480));
			else
				meats.add(new Pork(970 + i * 50, 480));
		}
		for (int i = 0; i < 4; i++) {
			noodles.add(new Noodles(980 + i * 75, 120));
		}

		end = new EndPage();
		intro = new IntroPage();
		gButton = new gameButton(375, 680);
		gButton2 = new gameButton(1070, 190);
		gButton3 = new gameButton(1150, 490);

		text = new Text(dialogue);
		text1 = new Text(dialogue);
		texts.add(text);
		// for the instruction setting;
		text2 = new Text("");

		MykeyListener mkl = new MykeyListener();
		this.addKeyListener(mkl);
		addMouseMotionListener(new MyMouseMotionAdapter());
		addMouseListener(new MyMouseAdapter());
		setFocusable(true);
		timer = new Timer(30, this);
		timer.start();

		// sound
		Minim minim = new Minim(new MinimHelper());
		cookSound = minim.loadFile("Grilling Sizzle 1.wav");
		cookSound1 = minim.loadFile("Grilling Sizzle 2.wav");
		ligthingStoveSound = minim.loadFile("Lighting Gas Stove.wav");
		customerSound = minim.loadFile("restaurant-bell.mp3");
		successSound = minim.loadFile("short-success-sound-glockenspiel-treasure-video-game.mp3");
		failSound = minim.loadFile("funwithsound__failure-drum-sound-effect-1.mp3");
		backgroundMusic = minim.loadFile("happy-background-music.mp3");
		openDoorSound = minim.loadFile("mativve__electro-open-sound.wav");
		introMusic = minim.loadFile("yummie__game-background-music-loop-short.mp3");
		endSound = minim.loadFile("275104__tuudurt__piglevelwin2.mp3");

		// for the introduction music looping before start the game.
		introMusic.loop();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Only drawing all of kitchen stuff when the state of game
		// is "GamePlaying".
		// star and moon and sun drawing.
		if (gameState == "GamePlaying") {
			// setting the sky color when is day or when is night time;
			if (timeState == "Day") {
				// drawingDay(g2);
			} else if (timeState == "Night") {
				// drawingNight(g2);
				for (Star s : stars) {
					s.drawStar(g2);
				}
			}

			tree.drawTree(g2);
			// for background display
			g2.drawImage(background, 0, 0, 1280, 906, this);
			// when click on the button on the stove, fire show up.
			if (button.getBoolean()) {
				fire.drawFire(g2);
			}
			// draw pan,oil,box,and stove button
			// pan.drawPan(g2);
			panPainter.drawPan(g2);
			// drawing meat and vegetables and noodles.
			for (int i = 0; i < vegs.size(); i++) {
				vegs.get(i).drawVeg(g2);
			}
			for (Meat m : meats) {
				m.drawMeat(g2);
			}
			for (Noodles n : noodles) {
				n.drawNoodles(g2);
			}
			oil.drawOil(g2);
			oil.drawDrop(g2, pan);
			soy.drawSauce(g2);
			soy.drawDrop(g2, pan);
			button.drawButton(g2);
			sign.drawSign(g2);
			// when clicking the sign to open, 10s there is a customer comes in and
			// place for a order.
			if (sign.getBoolean()) {
				customerTimer++;
				if (customerTimer >= 100) {
					customer.setIsDoneOrder(false);
					customer.drawMe(g2);

					for (Text t : texts) {
						t.dialogueDisplay(g2);
						t.wrongOrder(g2);

					}
				}
				text1.correctOrder(g2);
			}

			for (Box b : boxs) {
				b.drawBox(g2);
			}
			cPanel.update(noodles, vegs, meats);

			// for the instruction text display.
			if (text2.getBoolean4())
				text2.instructionDisplay(g2, ins);
		}
		end.gameEnd(g2);
		intro.gameIntro(g2);
		gButton.drawGamePlay(g2);
		gButton2.drawGameReplay(g2);

		// only draw the arrow of storage room when is in the state of game playing.
		if (gameState == "GamePlaying") {
			gButton3.drawStorage(g2);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// calling the updatePanState method.
		updatePanState();
		switch (timeState) {
		case "Day": // when is in the "Day", it calling the DayColor method, which is constantly
					// changing the dark color to the bright color. and also calling the method for
					// restarting the timer.
			DayColor(e);
			TimerReplay(e);
			tree.turnLightOff();
			break;
		case "Night": // when is in the "Night", it calling the NightColor method, which is gradually
						// changing the color of white to the dark. and also calling the method for
						// resume the timer.
			NightColor(e);
			TimerReplay(e);
			tree.turnLightOn();
			break;
		}

		// when customer comes in, play this sound effect.
		if (customerTimer == 100) {
			customerSound.rewind();
			customerSound.play();
		}

		// if oil is closed enough to the pan, then will set the boolean of isPull is
		// true, and oil would be pulled.
		if (oil.checkOilHitPan(pan)) {
			oil.setisPull(true);
			oil.drop();

		} else {
			oil.setisPull(false);
		}
		if (soy.checkSauceHitPan(pan)) {
			soy.setisPull(true);
			soy.drop();
		} else {
			soy.setisPull(false);
		}

		// Setting for the instruction text display on different stage of pan.
		switch (panState) {
		case "EmptyPan":
			ins = ins1;
			if (!text.getBoolean() && totalNoodles == 0) {
				ins = ins2 + "    (It is " + text.getOrder() + " stir fry.)";
			}
			if (button.getBoolean() && totalNoodles == 0 && customerTimer >= 100) {
				ins = ins3;
			}
			break;
		case "PanOil":
			if (totalNoodles == 0)
				ins = ins4 + "    (It is " + text.getOrder() + " stir fry.)";
			break;
		case "PanOilBeef":
			if (totalNoodles == 0)
				ins = ins5;
			break;
		case "PanOilChicken":
			if (totalNoodles == 0)
				ins = ins5;
			break;
		case "PanOilPork":
			if (totalNoodles == 0)
				ins = ins5;
			break;
		case "PanOilBeefVeg":
			if (totalNoodles == 0)
				ins = ins6;
			break;
		case "PanOilChickenVeg":
			if (totalNoodles == 0)
				ins = ins6;
			break;
		case "PanOilPorkVeg":
			if (totalNoodles == 0)
				ins = ins6;
			break;
		case "PanOilBeefVegNoodles":
			if (totalNoodles == 0)
				ins = ins7;
			break;
		case "PanOilChickenVegNoodles":
			if (totalNoodles == 0)
				ins = ins7;
			break;
		case "PanOilPorkVegNoodles":
			if (totalNoodles == 0)
				ins = ins7;
			break;
		case "PanOilBeefVegNoodlesSauce":
			if (totalNoodles == 0)
				ins = ins8;
			break;
		case "PanOilChickenVegNoodlesSauce":
			if (totalNoodles == 0)
				ins = ins8;
			break;
		case "PanOilPorkVegNoodlesSauce":
			if (totalNoodles == 0)
				ins = ins8;
			break;
		case "beefCooking":
			if (totalNoodles == 0)
				ins = ins8_1;
			break;
		case "chickenCooking":
			if (totalNoodles == 0)
				ins = ins8_1;
			break;
		case "porkCooking":
			if (totalNoodles == 0)
				ins = ins8_1;
			break;
		case "Packing":
			if (totalNoodles == 0)
				ins = ins9;
			break;
		}
		// for the updating gameState.
		updateGameState();
		// for the updating timeState controlling.
		updateFSM();
		repaint();

		// for showing the control panel or not.
		if (isCPanel) {
			cPanel.setVisible(true);
		} else if (!isCPanel) {
			cPanel.setVisible(false);
		}

	}

	public static String gameState = "Intro"; // Setting the default state of game is the introduction.

	public void updateGameState() {
		switch (gameState) {
		case "Intro": // when is in the state of "Intro" in the game state, show the introduction
						// page, and show the button of "play"
						// when the boolean of setIsIntro is true, the page of introduction will be
						// drawn,
			intro.setIsIntro(true);
			if (!gButton.getBoolean()) { // when hitting the game button of "Play", the boolean of this button will
											// becoming false, and then disappeared, and the state is changing to the
											// "GamePlaying".
				gameState = "GamePlaying";
				backgroundMusic.rewind(); // the game playing music will be looping when is in the state of
											// "GamePlaying" and will be rewind to very first when is in this state.
				backgroundMusic.loop();
			}
			break;
		case "GamePlaying": // In the state of game "GamePlaying", intro page and end page will set to the
							// false and disappeared, as well as the buttons.

			intro.setIsIntro(false);
			end.setIsEnd(false);
			gButton2.setIsGameReplay(false);
			if (!sign.getBoolean() && customerTimer != 0) { // when hitting the open / close sign again in the timeState
															// of night, it will end the game, and change the gameState
															// to "End".
				gButton2.setIsGameReplay(true);
				gameState = "End";
				backgroundMusic.pause(); // the background music here will pause, and play the ending page music.
				// play the ending music when is in the ending page
				endSound.rewind();
				endSound.play();

				// all of cooking materials would clear when press the open sign again.
				vegs.clear(); // all of the stuffs in the kitchen would be clear.
				meats.clear();
				noodles.clear();
			}
			break;
		case "End": // in the gameState of "End", the ending page will be drawn, and playing the
					// ending music.
			end.setIsEnd(true);
			if (!gButton2.getBoolean1()) { // when hitting the replay button on the ending page, all of the data for the
											// amounts of noodles will be reset. and also, the gameState will turn to
											// the "GamePlaying" again, and play the ingame music agai.
				customerTimer = 0;
				totalNoodles = 0;
				chickenNoodles = 0;
				beefNoodles = 0;
				porkNoodles = 0;
				gameState = "GamePlaying";
				backgroundMusic.rewind();
				backgroundMusic.loop();
				endSound.pause();

				// all of materials reset.when press replay button.
				for (int i = 0; i < 5; i++) {
					vegs.add(new Veg(990 + i * 50, 255));
				}
				for (int i = 0; i < 6; i++) {
					if (i < 2)
						meats.add(new Beef(970 + i * 50, 480));
					else if (i >= 2 && i < 4)
						meats.add(new Chicken(970 + i * 50, 480));
					else
						meats.add(new Pork(970 + i * 50, 480));
				}
				for (int i = 0; i < 4; i++) {
					noodles.add(new Noodles(980 + i * 75, 120));
				}

			}
			break;
		}
		if (totalNoodles >= 1) {
			text2.setBoolean4(false);
		}

	}

	// for the FSM setting, and timer of daytime and night time.
	public static String timeState = "Day";
	public static String priorState = "Day";
	static int dayTimer = -1;
	static int nightTimer = 300;

	// for the state of night time or day time.
	// when the day timer expired, sky would change to the dark.
	// when the night timer expired, sky would change to the blue.
	public void updateFSM() {
		// priorState = timeState;
		switch (timeState) {
		case "Day":
			if (gameState == "GamePlaying")
				nightTimer--;
			// dayTimer = 600;
			if (nightTimer < 0) {
				timeState = "Night";
				dayTimer = 300;
			}
			break;
		case "Night":
			if (gameState == "GamePlaying")
				dayTimer--;
			// nightTimer = 600;
			if (dayTimer < 0) {
				timeState = "Day";
				nightTimer = 300;
			}
			break;
		}
	}

	public static String panState = "EmptyPan";
	public static String priorPanState = "EmptyPan";
	public static int beefNoodles = 0, chickenNoodles = 0, porkNoodles = 0, totalNoodles = 0;

	// FSM for the pan decorator changing
	public void updatePanState() {
		// panState = priorPanState;
		switch (panState) {
		case "EmptyPan": // when the panState is "EmptyPan", the pan will remain empty, and the
							// currentOrder is null.
			currentOrder = "";
			if (button.getBoolean()) {

			}
			// System.out.println("0");
			if (oil.getisDropHitPan() && button.getBoolean()) { // when turn the fire on of the stove, and adding the
																// oil to the pan. the panState of pan will turn to the
																// "PanOil".
				panState = "PanOil";
			}
			break;
		case "PanOil": // when the panState is "PanOil", the panPainter will changing to the
						// panWithOil, which is the decorator of pan that having oil on the pan.
			panPainter = panWithOil;
			for (int i = 0; i < meats.size(); i++) { // if we drag any kind of the meat, chicken, beef, pork to the pan
														// , the panState will turn to that kind meat panState, for
														// example, if adding beef to the pan, the panState will turn to
														// "PanOilBeef". at the same time, the cooking sound effect will
														// play as well.
				Meat m = meats.get(i);
				if (m.checkMeatHitPan(pan) && meats.get(i) instanceof Beef) {
					Beef b = (Beef) meats.get(i);
					meats.remove(b);
					panState = "PanOilBeef";
					cookSound1.rewind();
					cookSound1.play();
				} else if (m.checkMeatHitPan(pan) && m instanceof Chicken) {
					Chicken c = (Chicken) m;
					meats.remove(c);
					panState = "PanOilChicken";
					cookSound1.rewind();
					cookSound1.play();
				} else if (m.checkMeatHitPan(pan) && m instanceof Pork) {
					Pork p = (Pork) m;
					meats.remove(p);
					panState = "PanOilPork";
					cookSound1.rewind();
					cookSound1.play();
				}
			}
			break;
		case "PanOilBeef":
			panPainter = panWithBeef; // when the panState is in "PanOilBeef", decorator of pan will set to the
										// panWithBeef, which is showing the pan with oil and beef.
			for (int i = 0; i < vegs.size(); i++) { // when adding the veg to the pan, the panState will changing to the
													// "PanOilBeefVeg", and the veg will be removed.
				if (vegs.get(i).checkVegHitPan(pan)) {
					vegs.remove(i);
					panState = "PanOilBeefVeg";
					currentOrder = "Beef";
					for (Text t : texts) { // when adding beef to the pan, in here, will adding one to the int variable
											// of beefNoodles for recording.
						if (currentOrder == t.getOrder())
							beefNoodles++;
					}
					cookSound1.rewind(); // playing and rewinding cooking sound effect.
					cookSound1.play();
				}
			}
			break;
		case "PanOilChicken":
			panPainter = panWithChicken; // when the panState is "PanOilChicken", decorator of pan will set to the
											// panWithChicken, which is showing the pan with oil and chicken. when
											// adding veg to it, the panState will changing to the next state, which is
											// "PanOilChickenVeg".
			for (int i = 0; i < vegs.size(); i++) {
				if (vegs.get(i).checkVegHitPan(pan)) {
					vegs.remove(i);
					panState = "PanOilChickenVeg";
					currentOrder = "Chicken";
					for (Text t : texts) {
						if (currentOrder == t.getOrder())
							chickenNoodles++;
					}
					cookSound1.rewind();
					cookSound1.play();
				}
			}
			break;
		case "PanOilPork":
			panPainter = panWithPork; // when the panState is "PanOilPork", decorator of pan will set to the
										// panWithChicken, which is showing the pan with oil and chicken. when adding
										// veg to it, the panState will changing to the next state, which is
										// "PanOilPorkVeg".
			for (int i = 0; i < vegs.size(); i++) {
				if (vegs.get(i).checkVegHitPan(pan)) {
					vegs.remove(i);
					panState = "PanOilPorkVeg";
					currentOrder = "Pork";
					for (Text t : texts) {
						if (currentOrder == t.getOrder())
							porkNoodles++;
					}
					cookSound1.rewind();
					cookSound1.play();
				}
			}
			break;
		case "PanOilBeefVeg": // when is in this state, the panPainter will be changing to the panWithBeefVeg,
								// which will show the veg on the pan as well as the meat and oil.
			panPainter = panWithBeefVeg;
			for (int i = 0; i < noodles.size(); i++) { // when adding the noodles to the pan, it will go to the next
														// state, which is "PanOilBeefVegNoodles".
				if (noodles.get(i).checkNoodlesHitPan(pan)) {
					noodles.remove(i);
					panState = "PanOilBeefVegNoodles";

					cookSound1.rewind();
					cookSound1.play();
				}
			}
			break;
		case "PanOilChickenVeg": // when is in this state, the panPainter will be changing to the
									// panWithChickenVeg, which will show the veg on the pan as well as the meat and
									// oil.
			panPainter = panWithChickenVeg;
			for (int i = 0; i < noodles.size(); i++) {
				if (noodles.get(i).checkNoodlesHitPan(pan)) { // when adding the noodles to the pan, it will go to the
																// next state, which is "PanOilChickenVegNoodles".
					noodles.remove(i);
					panState = "PanOilChickenVegNoodles";

					cookSound1.rewind();
					cookSound1.play();
				}
			}
			break;
		case "PanOilPorkVeg": // when is in this state, the panPainter will be changing to the panWithPorkVeg,
								// which will show the veg on the pan as well as the meat and oil.
			panPainter = panWithPorkVeg;
			for (int i = 0; i < noodles.size(); i++) {
				if (noodles.get(i).checkNoodlesHitPan(pan)) { // when adding the noodles to the pan, it will go to the
																// next state, which is "PanOilPorkVegNoodles".
					noodles.remove(i);
					panState = "PanOilPorkVegNoodles";

					cookSound1.rewind();
					cookSound1.play();
				}
			}
			break;
		case "PanOilBeefVegNoodles": // In this state, the panPainter is panWithBeefVegNoodles, noodles will also on
										// the pan as well,when adding the sauce to the pan, will go to the next state,
										// which is might be
										// "PanOilBeefVegNoodlesSauce","PanOilChickenVegNoodlesSauce","PanOilPorkVegNoodlesSauce"
										// depending on what kind of meat adding before.
			panPainter = panWithBeefVegNoodles;
			if (soy.getisDropHitPan()) {
				panState = "PanOilBeefVegNoodlesSauce";

				cookSound.rewind();
				cookSound.play();
			}
			break;
		case "PanOilChickenVegNoodles": // In this state, the panPainter is panWithBeefVegNoodles, noodles will also on
										// the pan as well,when adding the sauce to the pan, will go to the next state,
										// which is might be
										// "PanOilBeefVegNoodlesSauce","PanOilChickenVegNoodlesSauce","PanOilPorkVegNoodlesSauce"
										// depending on what kind of meat adding before.
			panPainter = panWithChickenVegNoodles;
			if (soy.getisDropHitPan()) {
				panState = "PanOilChickenVegNoodlesSauce";

				cookSound.rewind();
				cookSound.play();
			}
			break;
		case "PanOilPorkVegNoodles": // In this state, the panPainter is panWithBeefVegNoodles, noodles will also on
										// the pan as well,when adding the sauce to the pan, will go to the next state,
										// which is might be
										// "PanOilBeefVegNoodlesSauce","PanOilChickenVegNoodlesSauce","PanOilPorkVegNoodlesSauce"
										// depending on what kind of meat adding before.
			panPainter = panWithPorkVegNoodles;
			if (soy.getisDropHitPan()) {
				panState = "PanOilPorkVegNoodlesSauce";

				cookSound.rewind();
				cookSound.play();
			}
			break;
		case "PanOilBeefVegNoodlesSauce": // In this state, the panPainter will be panWithBeefVegNoodlesSauce or
											// panWithChickenVegNoodlesSauce or panWithPorkVegNoodlesSauce, if pressing
											// the space on the keyboard.
											// will go the next state of "beefCooking", "chickenCooking" or
											// "porkCooking", if dragging a box to the pan and turn the fire off, will
											// go to the next state "Packing".
			pan.setBoolean(false);
			panPainter = panWithBeefVegNoodlesSauce;
			for (Box b : boxs) {
				if (b.checkPackHitPan(pan) && !button.getBoolean()) {
					b.setisClosed(isValid());
					panState = "Packing";
				}
				if (isAnimation) {
					panState = "beefCooking";
				}
			}
			break;
		case "PanOilChickenVegNoodlesSauce": // In this state, the panPainter will be panWithBeefVegNoodlesSauce or
												// panWithChickenVegNoodlesSauce or panWithPorkVegNoodlesSauce, if
												// pressing the space on the keyboard.
												// will go the next state of "beefCooking", "chickenCooking" or
												// "porkCooking",
												// if dragging a box to the pan and turn the fire off, will go to the
												// next state
												// "Packing".
			pan.setBoolean(false);
			panPainter = panWithChickenVegNoodlesSauce;
			for (Box b : boxs) {
				if (b.checkPackHitPan(pan) && !button.getBoolean()) {
					b.setisClosed(isValid());
					panState = "Packing";
				}
				if (isAnimation) {
					panState = "chickenCooking";
				}
			}
			break;
		case "PanOilPorkVegNoodlesSauce": // In this state, the panPainter will be panWithBeefVegNoodlesSauce or
											// panWithChickenVegNoodlesSauce or panWithPorkVegNoodlesSauce, if pressing
											// the space on the keyboard.
											// will go the next state of "beefCooking", "chickenCooking" or
											// "porkCooking",
											// if dragging a box to the pan and turn the fire off, will go to the next
											// state
											// "Packing".
			pan.setBoolean(false);
			panPainter = panWithPorkVegNoodlesSauce;
			for (Box b : boxs) {
				if (b.checkPackHitPan(pan) && !button.getBoolean()) {
					b.setisClosed(isValid());
					panState = "Packing";
				}
				if (isAnimation) {
					panState = "porkCooking";
				}
			}
			break;

		case "beefCooking": // When in this state, it will play the animaiton of cooking stir-fry, the
							// decorator of pan will be panAnimation. when press the space again, the
							// boolean value of isAnimaiton will be false, then will go back to the state of
							// "PanOilBeefVegNoodlesSauce", "PanOilBeefVegNoodlesSauce",
							// "PanOilBeefVegNoodlesSauce"
			pan.setBoolean(true);
			panPainter = panAnimation;
			if (!isAnimation) {
				panState = "PanOilBeefVegNoodlesSauce";
			}
			break;
		case "chickenCooking":
			pan.setBoolean(true);
			panPainter = panAnimation;
			if (!isAnimation) {
				panState = "PanOilBeefVegNoodlesSauce";
			}
			break;
		case "porkCooking":
			pan.setBoolean(true);
			panPainter = panAnimation;
			if (!isAnimation) {
				panState = "PanOilBeefVegNoodlesSauce";
			}
			break;
		case "Packing": // when in this state, the package of box will change the appearance of closed
						// status, and when pass it to the customer, will check the currentOrder if is
						// equal to the customer order.
			panPainter = new PanBasicDecorator(pan);
			for (Box b : boxs) {
				if (b.checkPackHitCustomer(customer)) {
					for (Text t : texts) {
						if (currentOrder == t.getOrder()) { // if the order is match with the getOrder(), then will
															// remove the box, and set the customerTimer to zero, which
															// the customer will be disappeared, and the amount of
															// totalNooles will be adding as well. the text about "Thank
															// you" will be drawn as well. and then will go back to the
															// first panState, which is "EmptyPan". and set the
															// currentOrder is null. also will play the successful sound
															// effect.
							text1.setBoolean3(true);
							boxs.remove(b);
							boxs.add(new Box(150, 600));
							for (int i = 0; i < texts.size(); i++) {
								texts.remove(texts.get(i));
							}
							customerTimer = 0;
							totalNoodles = chickenNoodles + beefNoodles + porkNoodles;
							texts.add(new Text(dialogue));
							customer.setIsDoneOrder(true);
							oil.setIsDropHitPan(false);
							soy.setIsDropHitPan(false);
							panState = "EmptyPan";
							currentOrder = "";

							successSound.rewind();
							successSound.play();

						} else { // if the currentOrder is not matching to the customer order, getOrder(), there
									// is another text about "this is not my order, my order is...." will be drawn,
							for (int i = 0; i < texts.size(); i++) { // at the same time, the box will remove as well,
																		// however, customer will remained here, and the
																		// panState is going back to the "EmptyPan", and
																		// user need to do the order again. also the
																		// currentOrder will be null again, and playing
																		// the fail sound effect.
								texts.get(i).setBoolean2(true);
							}
							boxs.remove(b);
							boxs.add(new Box(150, 600));
							oil.setIsDropHitPan(false);
							soy.setIsDropHitPan(false);
							panState = "EmptyPan";
							currentOrder = "";

							failSound.rewind();
							failSound.play();
						}
					}
				}

			}
			break;

		}

	}

	public void drawingDay(Graphics2D g2) {

		g2.setColor(new Color(110, 150, 255));
		g2.fill(new Rectangle2D.Double(0, 0, 1280, 906));
	}

	public void drawingNight(Graphics2D g2) {
		g2.setColor(new Color(10, 10, 25));
		g2.fill(new Rectangle2D.Double(0, 0, 1280, 906));
	}

	// This is the method of changing color to day sky color animation.
	public void DayColor(ActionEvent e) {
		// set the target color first, and then set the speed of changing.
		// getting the current color of the background.
		final Color targetColor = new Color(153, 215, 228);
		final float changingSpeed = (float) 5;
		final Color currentColor = this.getBackground();
		int r = currentColor.getRed();
		int g = currentColor.getGreen();
		int b = currentColor.getBlue();

		double dr = targetColor.getRed() - r;
		double dg = targetColor.getGreen() - g;
		double db = targetColor.getBlue() - b;

		double norm = Math.sqrt(dr * dr + dg * dg + db * db);

		if (norm < 0.001) {
			((Timer) (e.getSource())).stop();
			return;
		}

		dr /= norm;
		dg /= norm;
		db /= norm;

		dr *= Math.min(changingSpeed, norm);
		dg *= Math.min(changingSpeed, norm);
		db *= Math.min(changingSpeed, norm);

		r += dr;
		g += dg;
		b += db;

		this.setBackground(new Color(r, g, b));
		this.repaint();
	}

	// This is the method of changing color to night sky color animation.
	public void NightColor(ActionEvent e) {
		// set the target color first, and then set the speed of changing.
		// getting the current color of the background.
		final Color targetColor = new Color(7, 11, 52);
		final float changingSpeed = (float) 1.5;
		final Color currentColor = this.getBackground();
		int r = currentColor.getRed();
		int g = currentColor.getGreen();
		int b = currentColor.getBlue();

		double dr = targetColor.getRed() - r;
		double dg = targetColor.getGreen() - g;
		double db = targetColor.getBlue() - b;

		double norm = Math.sqrt(dr * dr + dg * dg + db * db);
		// when is changing to the target color, stop the timer automatically.
		// avoiding do the animation of changing color over again.
		if (norm < 0.001) {
			((Timer) (e.getSource())).stop();
			return;
		}

		dr /= norm;
		dg /= norm;
		db /= norm;

		dr *= Math.min(changingSpeed, norm);
		dg *= Math.min(changingSpeed, norm);
		db *= Math.min(changingSpeed, norm);

		r += dr;
		g += dg;
		b += db;

		this.setBackground(new Color(r, g, b));
		this.repaint();
	}

	// restart the time again when the color changing is done.
	public void TimerReplay(ActionEvent e) {
		((Timer) (e.getSource())).start();
	}

	private class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			// when mouse clicking the switch of the stove and in the gameState
			// "GamePlaying", the button of the stove will be drawn another image of
			// openning status. as well as playing the lighting sound effets.
			if (button.checkButtonHit(e) && !button.getBoolean() && gameState == "GamePlaying") {
				ligthingStoveSound.rewind();
				ligthingStoveSound.play();
				button.setisOn(true);
			} else if (button.checkButtonHit(e) && button.getBoolean()) {
				button.setisOn(false);
				ligthingStoveSound.pause();
			}
			// when clicking the sign placing on the door and in the timeState of "Day", is
			// mean opening the restaurant, and the image of the sign will changing to the
			// opening sign.
			if (sign.checkSignHit(e) && !sign.getBoolean() && timeState == "Day") {
				openDoorSound.rewind();
				openDoorSound.play();
				customer.setIsDoneOrder(false);
				sign.setisOpen(true);
			}

			// when clicking the sign again and in the "Night" as well, it mean closing the
			// Restaurant, and ending the game and go the ending page.
			else if (sign.checkSignHit(e) && sign.getBoolean() && customer.getBoolean() && timeState == "Night") {
				sign.setisOpen(false);
			}

			// here is for the dialogue of customer interation, when click again the text,
			// the text will be disappear.
			for (Text t : texts) {
				if (t.checkTextHit(e) && sign.getBoolean() && !text1.getBoolean3() && customerTimer >= 100) {
					t.setBoolean(false);
				}
				if (t.checkTextHit(e) && panState == "EmptyPan" && sign.getBoolean()) {
					t.setBoolean2(false);
				}
			}
			if (text1.checkTextHit(e) && customerTimer >= 0) {
				text1.setBoolean3(false);
			}
			// when the mouse click on play button and replay button, set the boolean of
			// these are false if they were clicked by mouse
			if (gButton.checkButtonHit(e)) {
				gButton.setIsGamePlay(false);
				introMusic.pause();
			}
			if (gButton2.checkButtonHit(e)) {
				gButton2.setIsGameReplay(false);
			}

			if (gButton3.checkStorage(e) && !isCPanel) {
				isCPanel = true;
				System.out.println("111");
			} else if (gButton3.checkStorage(e) && isCPanel) {
				isCPanel = false;
			}

		}

		public void mouseReleased(MouseEvent e) {
			// when release the mouse, those object that are dragging will go back their
			// original location.
			oil.originalLoaction(new PVector(1060, 650));
			soy.originalLocation(new PVector(1200, 725));
			for (int i = 0; i < noodles.size(); i++) {
				noodles.get(i).originalLoaction(new PVector(980 + i * 75, 120));
			}
			for (int i = 0; i < vegs.size(); i++) {
				vegs.get(i).originalLoaction(new PVector(990 + i * 50, 255));
			}

			for (int i = 0; i < meats.size(); i++) {
				Meat m = meats.get(i);
				if (i < 2 && m instanceof Beef) {
					Beef b = (Beef) m;
					b.originalLocation(new PVector(970 + i * 50, 480));
				} else if (i >= 2 && i < 4 && m instanceof Chicken) {
					Chicken c = (Chicken) m;
					c.originalLocation(new PVector(970 + i * 50, 480));
				} else if (i >= 4 && i < 6 && m instanceof Pork) {
					Pork p = (Pork) m;
					p.originalLocation(new PVector(970 + i * 50, 480));
				}

			}
		}
	}

	// here is the interaction of mouse dragging for dragging the oil bottle,
	// noodles, vegetable, or boxes.

	private class MyMouseMotionAdapter extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if (oil.checkOilHit(e)) {
				oil.setLocation(new PVector(e.getX(), e.getY()));
			}
			if (soy.checkSauceHit(e)) {
				soy.setLocation(new PVector(e.getX(), e.getY()));
			}
			for (Box b : boxs) {
				if (b.checkPackHit(e)) {
					b.setLocation(new PVector(e.getX(), e.getY()));
				}
			}
			for (Noodles n : noodles) {
				if (n.checkNoodlesHit(e))
					n.setLocation(new PVector(e.getX(), e.getY()));
			}
			for (Veg v : vegs) {
				if (v.checkVegHit(e))
					v.setLocation(new PVector(e.getX(), e.getY()));
			}
			for (Meat m : meats) {
				if (m.checkMeatHit(e))
					m.setLocation(new PVector(e.getX(), e.getY()));
			}
		}
	}

	// here is the interaction with the keyboard, space bar, when press the space
	// bar, it will changing the value of boolean variable of isAnimation, is
	// controlling the animation for the cooking noodles or not.

	public class MykeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (panState == "PanOilBeefVegNoodlesSauce" || panState == "PanOilChickenVegNoodlesSauce"
					|| panState == "PanOilPorkVegNoodlesSauce" && e.getKeyCode() == KeyEvent.VK_SPACE && !isAnimation) {
				isAnimation = true;
			} else if (panState == "porkCooking" || panState == "chickenCooking"
					|| panState == "beefCooking" && e.getKeyCode() == KeyEvent.VK_SPACE && isAnimation) {
				isAnimation = false;
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Stir-fried express");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ControlPanel cPane = new ControlPanel();
		KtichenPane kitchenPanel = new KtichenPane(cPane);

		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		contentPane.add(kitchenPanel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		contentPane.add(cPane, c);

		// frame.add(kitchenPanel);
		cPane.setVisible(true);
		frame.pack();
		frame.setVisible(true);
	}

	// here is the methods for adding new amount of noodles.
	public static void addNoodles(Double num) {
		for (int i = 0; i < num; i++) {
			noodles.add(new Noodles(980 + i * 75, 120));
		}
	}

	// here is adding new vegetables
	public static void addVeg(Double num) {
		for (int i = 0; i < num; i++) {
			vegs.add(new Veg(990 + i * 50, 255));
		}
	}

	// here is adding the amount of meat and what kind of meats. like, beef,
	// chicken, or pork.
	public static void addMeat(Double num) {
		if (num == 1) {
			for (int i = 0; i < 2; i++) {
				meats.add(new Beef(970 + i * 50, 480));
			}
		} else if (num == 2) {
			for (int i = 2; i < 4; i++) {
				meats.add(new Chicken(970 + i * 50, 480));
			}
		} else if (num == 3) {
			for (int i = 4; i < 6; i++) {
				meats.add(new Pork(970 + i * 50, 480));
			}
		}
	}

	public static void muteBackgroundMusic() {
		backgroundMusic.mute();
	}
	public static void unmuteBackgroundMusic() {
		backgroundMusic.unmute();
	}
	
}
