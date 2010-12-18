/*
Twidor: the twiddler typing tutor.
Copyright (C) 2005	James Fusia

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
USA.
 */
/**
 * CCG: Twidor- The Twiddler Tutor!
 * <pre>
 * TwiddlerPanel.java, the class that defines how the Twiddler face looks.
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 * 		0.2	06 June 2003
 * 			Should be nearly ready.
 * 		0.1	29 May 2003
 * 			Created class TwiddlerPanel
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
public class TwiddlerPanel extends JPanel implements TwidorConstants {
	/**
	 * internal variables
	 */
	private KeyMap myKeyMap;
	private boolean thumbOrientation;
	private boolean fingerOrientation;
	private boolean showThumbMap;
	private boolean showFingerMap;
	private boolean showTwiddler;
	private Vector panelList;

	/**
	 * default constructor
	 */
	public TwiddlerPanel (KeyMap newMap, boolean thumb, boolean finger) {
		if (bDEBUG) System.out.println("TwiddlerPanel: creating panel");

		setKeyMap(newMap);
		setThumbOrientation(thumb);
		setFingerOrientation(finger);
		setThumbKeysVisible(true);
		setFingerKeysVisible(true);
		setTwiddlerVisible(true);
		setBackground(twiddlerBackground);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		initPanels();
		/* general doodlings with the JPanel */
		setLayout(new GridLayout(5, 1));
		reOrient();

		if (bDEBUG) System.out.println("TwiddlerPanel: panel created");
	}// end TwiddlerPanel ()

	/**
	 * accessor for thumb orientation
	 * @return boolean the current thumb orientation
	 */
	public boolean getThumbOrientation () {
		return thumbOrientation;
	}// end getThumbOrientation ()

	/**
	 * accessor for finger orientation
	 * @return boolean the current finger orientation
	 */
	public boolean getFingerOrientation () {
		return fingerOrientation;
	}// end setFingerOrientation ()

	/**
	 * accessor for thumb keys
	 * @return boolean the current key visability
	 */
	public boolean getThumbKeysVisible () {
		return showThumbMap;
	}// end getThumbKeysVisible ()

	/**
	 * accessor for finger keys
	 * @return boolean the current key visability
	 */
	public boolean getFingerKeysVisible () {
		return showFingerMap;
	}// end getFingerKeysVisible ()

	/**
	 * accessor for the Twiddler itself
	 * @return boolean the current visibility
	 */
	public boolean getTwiddlerVisible () {
		return showTwiddler;
	}// end getVisible ()

	/**
	 * accessor for the current keymap
	 * @return KeyMap the current keymap
	 */
	public KeyMap getKeyMap () {
		return myKeyMap;
	}// end getKeyMap ()

	/**
	 * accessor for the JPanels we contain
	 * @return Vector the vector of panels we contain
	 */
	public Vector getPanels () {
		return panelList;
	}// end getPanels ()

	/**
	 * modifier for thumb orientation
	 * @param boolean the desired thumb orientation
	 */
	public void setThumbOrientation (boolean newOrientation) {
		thumbOrientation = newOrientation;
	}// end setThumbOrientation (boolean)

	/**
	 * modifier for finger orientation
	 * @param boolean the desired finger orientation
	 */
	public void setFingerOrientation (boolean newOrientation) {
		fingerOrientation = newOrientation;
	}// end setFingerOrientation (boolean)

	/**
	 * modifier for thumb key visibility
	 * @param boolean the new status
	 */
	public void setThumbKeysVisible (boolean status) {
		showThumbMap = status;
	}// end setThumbKeysVisible (boolean)

	/**
	 * modifier for finger key visiblity
	 * @param boolean the new status
	 */
	public void setFingerKeysVisible (boolean status) {
		showFingerMap = status;
	}// end setFingerKeysVisible (boolean)

	/**
	 * modifier for twiddler visibility
	 * @param boolean the new status
	 */
	public void setTwiddlerVisible (boolean status) {
		showTwiddler = status;
	}// end setVisible (boolean)

	/**
	 * modifier for the current keymap
	 * @param KeyMap the desired KeyMap
	 */
	public void setKeyMap (KeyMap newMap) {
		myKeyMap = newMap;
	}// end setKeyMap (KeyMap)

	/**
	 * initializer for the panel list
	 */
	private void initPanels () {
		panelList = new Vector();
	}// end initPanels ()

	/**
	 * highlights the keys for a given character
	 * @param String the character to highlight
	 */
	public void highlight (String key) {
		KeyElement keyPress = getKeyMap().getKey(key);
		if (keyPress != null) {
			highlight(keyPress);
		}
		else {
			if (bDEBUG) System.out.println("TwiddlerPanel: Can't highlight " + key);
		}
	}// end highlight (String)

	/**
	 * highlights the keys for the given KeyElement
	 * @param KeyElement the key to highlight
	 */
	public void highlight (KeyElement key) {
		Color hColor;
		if (key == null) {
			if (bDEBUG) System.out.println("TwiddlerPanel: Can't highlight key.");
			return;
		}
		try {
			if (key.getLetter().length() > 1) {
				hColor = mccHighlight;
			} else {
				hColor = buttonHighlight;
			}
			for (int finger = 0; finger < 4; finger++) {
				for (int button = 0; button < 3; button++) {
					if (key.getButton(finger * FINGER_OFFSET + button)) {
						((TwiddlerSubPanel)getPanels().elementAt(finger +
								1)).highlight(button, hColor);
					}
				}
			}
			for (int thumb = 0; thumb < 4; thumb++) {
				if (key.getButton(thumb + THUMB_OFFSET)) {
					((TwiddlerSubPanel)getPanels().elementAt(0)).highlight(thumb,
							hColor);
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			if (bDEBUG) System.out.println("TwiddlerPanel: array oob");
		}
	}// end highlight (KeyElement)

	/**
	 * clears all possibly highlighted keys
	 */
	public void clear () {
		for (int i = 0; i < getPanels().size(); i++) {
			((TwiddlerSubPanel)getPanels().elementAt(i)).clear();
		}
	}// end clear ()

	/**
	 * called whenever you want to do a redraw of the Twiddler display;
	 * i.e. anytime you modify the Finger/Thumb Orientation or KeyMap.
	 */
	public void reOrient () {
		if (bDEBUG) System.out.println("TwiddlerPanel: rearranging twiddler layout");
		setVisible(false);
		removeAll();
		setMinimumSize(new Dimension(twiddlerX, windowY));
		getPanels().clear();
		getPanels().addElement(new ThumbPanel(getThumbOrientation(), getKeyMap(),
					getThumbKeysVisible()));
		for (int i = 0; i < 4; i++) {
			getPanels().addElement(new FingerPanel(i, getFingerOrientation(),
						getKeyMap(), getFingerKeysVisible()));
		}
		for (int i = 0; i < getPanels().size(); i++) {
			add((JPanel)getPanels().elementAt(i));
		}
		if (getTwiddlerVisible()) {
			setVisible(true);
		}
		if (bDEBUG) System.out.println("TwiddlerPanel: rearrangement complete");
	}// end reOrient ()

}// end class TwiddlerPanel
