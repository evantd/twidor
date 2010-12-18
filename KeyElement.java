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
 * KeyElement.java, a class for making matching keys to letters sooooooo much
 * easier.
 * 
 * Revisions:
 * 		0.5	17 July 2003
 * 		Completed Tutor
 * 		0.2	06 June 2003
 * 		Bugs Stomped. Final Version.
 * 		0.1	23 May 2003
 * 		Created class KeyElement
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 06 July 2003
 */
import java.util.Vector;
public class KeyElement extends java.lang.Object implements TwidorConstants {

	/**
	 * the button map and the letter we represent
	 */
	private String letter;
	private int number;
	private Vector buttons;

	/**
	 * default constructor
	 */
	public KeyElement () {
		buttons = new Vector();
		for (int i = 0; i < 16; i++) {
			buttons.add(i, new KeyStatus());
		}
		setLetter(null);
	}// end KeyElement

	/**
	 * default constructor
	 * @param int the integer value of the element
	 */
	public KeyElement (int thing) {
		this();
		setNumber(thing);
	}// end KeyElement (int)

	/**
	 * default constructor
	 * @param String the letter/macro to set
	 */
	public KeyElement (String thing) {
		this();
		setLetter(thing);
	}// end KeyElement (String)

	/**
	 * default constructor
	 * @param Vector the button list
	 */
	public KeyElement (Vector buttonlist) {
		setLetter(null);
		buttons = buttonlist;
	} // end KeyElement (Vector)

	/**
	 * get the letter or macro this KeyElement holds.
	 * @return String the letter this Element holds
	 */
	public String getLetter () {
		return displayLetter();
	}// end getLetter ()

	/**
	 * set the letter or macro of this KeyElement.
	 * @param String the new letter/macro
	 */
	public void setLetter (String thing) {
		letter = thing;
		number = -1;
	}// end setLetter (string)

	/**
	 * accessor for the numeric value of this key
	 * @return int the numeric value
	 */
	public int getNumber () {
		return number;
	}// end getNumber ()

	/**
	 * modifier for the numeric value of this key
	 * @param int the value to set
	 */
	public void setNumber (int that) {
		number = that;
		letter = null;
	}// end setNumber (int)

	/**
	 * accessor for the value of this key
	 * @return char the character this number matches
	 */
	public String displayLetter () {
		if (getNumber() != -1) {
			return String.valueOf((char)getNumber());
		}
		return letter;
	}// end getChar ()

	/**
	 * get a specific button
	 * @param int the button to request
	 * @return boolean the status of the button
	 */
	public boolean getButton (int button) {
		return ((KeyStatus)getButtons().elementAt(button)).getStatus();
	}// end getButton (int)

	/**
	 * get a whole bunch of buttons
	 * @return Vector the list of buttons
	 */
	public Vector getButtons () {
		return buttons;
	}// end getButtons ()

	/**
	 * set the button status
	 * @param int the button to modify
	 * @param boolean the new status
	 */
	public void setButton (int button, boolean status) {
		setButton(button, new KeyStatus(status));
	}// end setButton (int, boolean)

	/**
	 * set the button status
	 * @param int the button to modify
	 * @param KeyStatus the new status
	 */
	public void setButton (int button, KeyStatus status) {
		getButtons().setElementAt(status, button);
	}// end setButton (int, KeyStatus)

	/**
	 * default comparator
	 * @param Object the object to compare it to
	 * @return boolean true if equals, false otherwise
	 */
	public boolean equals (Object o) {
		if (o instanceof KeyElement) {
			KeyElement temp = (KeyElement)o;

			if ((temp.displayLetter() != null) && (displayLetter() != null)) {
				return temp.displayLetter().equals(displayLetter());
			}
			if ((temp.getButtons() != null) && (getButtons() != null)) {
				for (int i = 0; i < getButtons().size(); i++) {
					if (getButton(i) != temp.getButton(i)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}// end equals (Object)

	/**
	 * Function for returning this KeyElement in an easy-to-debug format
	 * @return String the text version of this KeyElement
	 */
	public String toString () {
		String toReturn = "";
		if (getNumber() != -1) {
			toReturn += (getNumber() + "\t");
		}
		for (int i = 0; i < getButtons().size(); i++) {
			if (getButton(i)) {
				toReturn += "1 ";
			}
			else {
				toReturn += "0 ";
			}
		}
		return toReturn;
	}// end toString ()

}// end class KeyElement
