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
 * TypingPanel.java, because I figured "Everything else has it's own class,
 * why not this one, too!".
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 * 		0.1	10 June 2003
 * 		Created class TypingPanel
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.lang.String;
public class TypingPanel extends JPanel implements TwidorConstants {

	/**
	 * internal variables
	 */
	Vector Sentence;
	Vector Type;
	int current;
	boolean highlightErrors;
	boolean allowErrors;
	boolean finished;

	/**
	 * default constructor
	 */
	public TypingPanel () {
		Sentence = new Vector();
		Type = new Vector();
		setCurrent(0);
		setEntered(false);
		setHighlightErrors(HIGHLIGHT_ERRORS);
		//setAllowErrors(ALLOW_ERRORS);
		setAllowErrors(true);
		setBackground(TEXT_BACKGROUND);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
		if (bDEBUG) System.out.println("TypingPanel: Created New Panel");
	}// end TypingPanel ()

	/**
	 * Modifier for whether or not we highlight on error
	 * @param boolean the new status of highlighting
	 */
	public void setHighlightErrors (boolean status) {
		highlightErrors = status;
	}// end setHighlightErrors (boolean)

	/**
	 * Modifier for whether or not we allow errors
	 * @param boolean the new status of errors
	 */
	public void setAllowErrors (boolean status) {
		allowErrors = status;
	}// end setAllowErrors (boolean)

	/**
	 * Modifier for the current JLabel in the typing panel
	 * @param int the new value to use as 'current'
	 */
	public void setCurrent (int thing) {
		if (thing <= 0) {
			current = 0;
		}
		else {
			current = thing;
		}
	}// end setCurrent (int)

	/**
	 * Private function to determine some whacky stupid things
	 * @param boolean the new value to set
	 */
	private void setEntered (boolean value) {
		finished = value;
	}// end setEntered (boolean)

	/**
	 * Accessor for whether or not we highlight on error
	 * @return boolean the status of highlighting
	 */
	public boolean getHighlightErrors () {
		return highlightErrors;
	}// end getHighlightErrors ()

	/**
	 * Accessor for whether or not we allow errors
	 * @return boolean the status of errors
	 */
	public boolean getAllowErrors () {
		return allowErrors;
	}// end getAllowErrors ()

	/**
	 * Accessor for the current JLabel in the typing panel
	 * @return int the value of the current JLabel
	 */
	public int getCurrent () {
		return current;
	}// end getCurrent ()

	/**
	 * Accessor for the finished variable
	 * @return boolean the finished variable
	 */
	private boolean getEntered () {
		return finished;
	}// end getEntered ()

	/**
	 * Called when we want to display a message
	 */
	public void displayMessage (String message) {
		setVisible(false);
		removeAll();
		JLabel myMessage = new JLabel(message);
		JPanel temp = new JPanel();
		temp.setAlignmentY(Component.CENTER_ALIGNMENT);
		temp.add(myMessage);
		temp.setBackground(TEXT_BACKGROUND);
		add(temp);
		setBackground(TEXT_BACKGROUND);
		setVisible(true);
	}

	/**
	 * Called when a new sentence is to be displayed
	 * @param String the new sentence to display
	 */
	public void displaySentence (String sentence) {
		setVisible(false);
		JPanel center = new JPanel();
		center.setAlignmentY(Component.CENTER_ALIGNMENT);

		if (bDEBUG) System.out.println("TypingPanel: Displaying new sentence");
		/* Empty everything in this JPanel, and clear all associated variables */
		Sentence.clear();
		Type.clear();
		setCurrent(0);
		setEntered(false);
		removeAll();

		if (sentence == null) {
			if (bDEBUG) System.out.println("TypingPanel: Got sent a null sentence");
			return;
		}
		center.setLayout(new GridLayout(2, sentence.length() + 3));
		center.setBackground(TEXT_BACKGROUND);
		for (int i = 0; i < sentence.length(); i++) {
			JLabel newLetter = letterLabel(String.valueOf(sentence.charAt(i)));
			Sentence.add(newLetter);
			center.add(newLetter);
		}
		/* text alignment. We allow typers to go +/- 3 characters
		 * before hitting enter */
		for (int i = sentence.length(); i < sentence.length() + 3; i++) {
			JLabel newLetter = letterLabel("");
			Sentence.add(newLetter);
			center.add(newLetter);
		}
		for (int i = 0; i < sentence.length() + 3; i++) {
			JLabel newLetter = letterLabel("");
			if (i == 0) {
				newLetter.setText(CURSOR);
				newLetter.setForeground(TEXT_CURSOR);
			}
			Type.add(newLetter);
			center.add(newLetter);
		}
		center.setMaximumSize(center.getPreferredSize());
		add(Box.createVerticalGlue());
		add(center);
		setVisible(true);
		if (bDEBUG) System.out.println("TypingPanel: New sentence displayed");
	}// end displaySentence ()

	private JLabel letterLabel (String letter) {
		JLabel newLetter = new JLabel(letter);
		newLetter.setFont(new Font("Monospaced", Font.PLAIN, 20));
		newLetter.setForeground(TEXT_DEFAULT);
		newLetter.setBackground(TEXT_BACKGROUND);
		return newLetter;
	}

	/**
	 * Called when a character was typed
	 * @param KeyElement the character that was typed
	 */
	public void charTyped (KeyElement typed) {
		/* Make sure we're in valid character range */
		if (getCurrent() < 0) {
			setCurrent(0);
		}
		if (getCurrent() >= Type.size()) {
			if (typed.getNumber() == KEY_EOL || typed.getNumber() == KEY_ENTER) {
				if (getAllowErrors()) {
					setEntered(true);
				}
			}
			else if (typed.getNumber() == KEY_BACKSPACE || typed.getNumber() == KEY_DELETE) {
				setCurrent(getCurrent() - 1);
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
				((JLabel)Type.elementAt(getCurrent())).setForeground(TEXT_CURSOR);
				((JLabel)Type.elementAt(getCurrent())).setText(CURSOR);
			}
			else {
				if (bDEBUG) System.out.println("TypingPanel: Not accepting other input.");
			}
			return;
		}

		setVisible(false);
		if (typed.getNumber() == KEY_EOL || typed.getNumber() == KEY_ENTER) {
			/* Enter only matters if we're allowing errors and the sentence is finished */
			if (getCurrent() >= Sentence.size() - 6) {
				setEntered(true);
			}
		}
		else if ((typed.getNumber() == KEY_BACKSPACE) || (typed.getNumber() == KEY_DELETE)) {
			/* Erase */
			if (getCurrent() <= 0) {
				if (bDEBUG) System.out.println("TypingPanel: Can't delete what's not there");
			}
			else {
				((JLabel)Type.elementAt(getCurrent())).setText("");
				((JLabel)Type.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
				setCurrent(getCurrent() - 1);
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
			}
		}
		else {
			/* Treat it like a normal character */
			String toMatch = ((JLabel)Sentence.elementAt(getCurrent())).getText();
			((JLabel)Type.elementAt(getCurrent())).setForeground(TEXT_DEFAULT);
			((JLabel)Type.elementAt(getCurrent())).setText(typed.displayLetter());
			if (typed.displayLetter().equals(toMatch)) {
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_GOOD);
				setCurrent(getCurrent() + 1);
			}
			else {
				((JLabel)Sentence.elementAt(getCurrent())).setForeground(TEXT_ERROR);
				if (getAllowErrors()) {
					setCurrent(getCurrent() + 1);
				}
			}
		}
		if (getCurrent() < Type.size()) {
			((JLabel)Type.elementAt(getCurrent())).setForeground(TEXT_CURSOR);
			((JLabel)Type.elementAt(getCurrent())).setText(CURSOR);
		}
		setVisible(true);
	}// end charTyped (KeyElement)

	/**
	 * Called to check and see if the sentence is complete
	 */
	public boolean sentenceComplete () {
		if (getAllowErrors()) {
			return getEntered();
		}
		return (getCurrent() >= Sentence.size());
	}// end sentenceComplete ()

}// end class TypingPanel
