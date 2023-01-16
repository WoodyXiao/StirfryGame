package Main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Meat.Meat;
import Noodles.Noodles;
import Veg.Veg;

/*here is for the control panel in the game and its setting, 
in this panel, will included add meat, and add vegetables and add noodles button, 
there are also some information of vegetable, meats, noodles displaying in this panel. 
*/

public class ControlPanel extends JPanel {
	JLabel noodlesAmount;
	// Container right;
	JLabel vegAmout;
	JLabel meatAmount;

	JButton addBeef; // button for adding beef
	JButton addChicken; // button for adding chicken
	JButton addPork; // button for adding pork
	JButton addVeg; // button for adding veg
	JButton addNoodles; // button for adding noodles

	JButton musicMute; // button for mute the music
	JButton musicUnmute; // button for unmute the music

	JPanel infoPane;

	public ControlPanel() {
		super();
		setPreferredSize(new Dimension(200, 800));
		// creating a new BorderLayout, and call
		// setComponnetAttributes method,
		// add right and top containers into the BorderLayout.
		// setLayout(new BorderLayout());
		setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		setComponentAttributes();
		add(infoPane);
		setBorder(BorderFactory.createTitledBorder("Storage Room"));
	}

	private void setComponentAttributes() {
		// Create new JLable of representing noodles amounts,
		// Total meat amount, and vegetable amounts.
		// then adding all of them into the right container
		noodlesAmount = new JLabel("Noodles: ");

		vegAmout = new JLabel("Veg: ");

		meatAmount = new JLabel("Total meat: ");

		infoPane = new JPanel();
		infoPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Setting add noodles, add vegetable, add noodles buttons.
		// and put them into ActionListener with the methods in the
		// KitchenPane.
		addNoodles = new JButton("Add noodles");
		addNoodles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.addNoodles((double) 4);
			}
		});

		addBeef = new JButton("Add Beef");
		addBeef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.addMeat((double) 1);
			}
		});

		addChicken = new JButton("Add Chicken");
		addChicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.addMeat((double) 2);
			}
		});

		addPork = new JButton("Add Pork");
		addPork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.addMeat((double) 3);
			}
		});

		addVeg = new JButton("Add Veg");
		addVeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.addVeg((double) 4);
			}
		});

		musicMute = new JButton("Mute");
		musicMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.muteBackgroundMusic();
			}
		});

		musicUnmute = new JButton("Unmute");
		musicUnmute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KtichenPane.unmuteBackgroundMusic();
			}
		});

		// Put buttons into the top container.
		String[] labelCaptions = { "Noodles: ", "Veg: ", "Total meat: " };
		int numWidgets = labelCaptions.length;
		JLabel[] labels = { noodlesAmount, vegAmout, meatAmount };
		JButton[] buttons = { addNoodles, addVeg, addBeef };
		JButton[] buttons1 = { addBeef, addChicken, addPork };
		JButton[] buttons2 = { addPork, musicMute, musicUnmute };
		//
		for (int i = 0; i < numWidgets; i++) {
			JLabel l = new JLabel(labelCaptions[i], JLabel.LEADING);
			c.fill = GridBagConstraints.WEST;
			c.weightx = 1.0;
			c.gridx = 0;
			c.gridy = i;

			c.insets = new Insets(10, 0, 0, 0); // top padding
			infoPane.add(l, c);

			labels[i].setHorizontalAlignment(SwingConstants.LEADING);

			c.fill = GridBagConstraints.WEST;
			c.weightx = 0.0;
			c.gridx = 1;
			c.gridy = i;

			c.insets = new Insets(10, 0, 0, 0); // top padding
			infoPane.add(labels[i], c);

			buttons[i].setHorizontalAlignment(SwingConstants.CENTER);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.0;
			c.gridx = 0;
			c.gridy = i + 50;

			c.insets = new Insets(10, 0, 0, 0); // top padding
			infoPane.add(buttons[i], c);

			buttons1[i].setHorizontalAlignment(SwingConstants.CENTER);
			buttons[i].setPreferredSize(new Dimension(100, 20));
			buttons1[i].setPreferredSize(new Dimension(100, 20));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.0;
			c.gridx = 0;
			c.gridy = i + 150;

			c.insets = new Insets(10, 0, 0, 0); // top padding
			infoPane.add(buttons1[i], c);

			buttons2[i].setHorizontalAlignment(SwingConstants.CENTER);
			buttons2[i].setPreferredSize(new Dimension(100, 20));
			buttons2[i].setPreferredSize(new Dimension(100, 20));
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.0;
			c.gridx = 0;
			c.gridy = i + 200;

			c.insets = new Insets(10, 0, 0, 0); // top padding
			infoPane.add(buttons2[i], c);

		}

	}

	// Keep updating the information of Vegetables, meats, and noodles.
	public void update(ArrayList<Noodles> ns, ArrayList<Veg> vg, ArrayList<Meat> meat) {
		noodlesAmount.setText(ns.size() + " left");
		vegAmout.setText(vg.size() + " left");
		meatAmount.setText(meat.size() + " left");
	}

}
