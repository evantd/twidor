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
 * This class is here to automate the Menu switching and flipping
 * and flopping and other fish-like features.
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 * 		0.1	17 June 2003
 * 		Created class TwidorMenu
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class TwidorMenu extends JMenuBar implements TwidorConstants {

	/**
	 * internal variables
	 */
	EventHandler myEventHandler;
	Twidor myTutor;
	Vector Tutor, Twiddler;
	ButtonGroup Lesson;
	Vector lessonButtons;

	/**
	 * default constructor
	 */
	private TwidorMenu () {
		if (bDEBUG) System.out.println("TwidorMenu: New MenuBar Created");
	}// end TwidorMenu ()

	/**
	 * default constructor
	 */
	public TwidorMenu (Twidor tutor, EventHandler events, int lessonCount) {
		this();

		JMenu menu;
		JMenuItem jmItem;
		JCheckBoxMenuItem cbItem;
		JRadioButtonMenuItem rbItem;
		Tutor(tutor);
		Events(events);

		menu = new JMenu("Twidor");
		jmItem = jmenuItem(QUIT_TEXT);
		menu.add(jmItem);
		add(menu);

		Tutor = new Vector();
		menu = new JMenu("Tutor");
		jmItem = jcheckItem(HIGHLIGHT_HINT_TEXT, HIGHLIGHT_HINT);
		Tutor.add(jmItem);
		menu.add(jmItem);
		jmItem = jcheckItem(HIGHLIGHT_KEYPRESS_TEXT, HIGHLIGHT_KEYPRESS);
		Tutor.add(jmItem);
		menu.add(jmItem);
		jmItem = jcheckItem(HIGHLIGHT_ERRORS_TEXT, HIGHLIGHT_ERRORS);
		Tutor.add(jmItem);
		menu.add(jmItem);
		add(menu);

		Twiddler = new Vector();
		menu = new JMenu("Twiddler");
		jmItem = jcheckItem(TWIDDLER_SHOW_TEXT, TWIDDLER_SHOW);
		Twiddler.add(jmItem);
		menu.add(jmItem);
		jmItem = jcheckItem(TWIDDLER_MIRROR_TEXT, TWIDDLER_MIRROR);
		Twiddler.add(jmItem);
		menu.add(jmItem);
		jmItem = jcheckItem(TWIDDLER_SHOW_LETTERS_TEXT, TWIDDLER_SHOW_LETTERS);
		Twiddler.add(jmItem);
		menu.add(jmItem);
		add(menu);

		Lesson = new ButtonGroup();
		lessonButtons = new Vector();
		menu = new JMenu("Lesson");
		rbItem = jradioItem("Lesson 1", true);
		Lesson.add(rbItem);
		lessonButtons.add(rbItem);
		menu.add(rbItem);
		for (int i = 2; i <= lessonCount; i++) {
			rbItem = jradioItem("Lesson " + i, false);
			Lesson.add(rbItem);
			lessonButtons.add(rbItem);
			menu.add(rbItem);
		}
		add(menu);

		if (bDEBUG) System.out.println("TwidorMenu: Finished Creating");
	}// end TwidorMenu (Twidor, EventHandler)

	/**
	 * helper function for creating menu items.
	 * @param String the text of the item
	 * @return JMenuItem the completed item.
	 */
	private JMenuItem jmenuItem (String text) {
		JMenuItem toReturn = new JMenuItem(text);
		toReturn.addActionListener(Events());
		return toReturn;
	}// end jmenuItem (String)

	/**
	 * helper function for creating menu items.
	 * @param String the text of the item
	 * @param boolean the default value of the item
	 * @return JCheckBoxMenuItem the completed item
	 */
	private JCheckBoxMenuItem jcheckItem (String text, boolean status) {
		JCheckBoxMenuItem toReturn = new JCheckBoxMenuItem(text, status);
		toReturn.addItemListener(Events());
		return toReturn;
	}// end jcheckItem (String, boolean)

	/**
	 * helper function for creating radio menu items
	 * @param String the text of the item
	 * @param boolean the default value of the item
	 * @return JRadioButtonMenuItem the completed item
	 */
	private JRadioButtonMenuItem jradioItem (String text, boolean status) {
		JRadioButtonMenuItem toReturn = new JRadioButtonMenuItem(text, status);
		toReturn.setActionCommand(text);
		toReturn.setSelected(status);
		toReturn.addActionListener(Events());
		return toReturn;
	}// end jradioItem (String, boolean)

	/**
	 * Used for ensuring certain elements of the menu can't overlap each other.
	 * @param String the element that just got fiddled with
	 * @param boolean the state it got changed to
	 */
	public void itemSelected (String item, boolean state) {
		if (bDEBUG) System.out.println("TwidorMenu: " + item + " selected " + state);
		if (item.equals(TWIDDLER_SHOW_TEXT)) {
			if (state) {
				((JMenuItem)Twiddler.elementAt(1)).setEnabled(true);
				((JMenuItem)Twiddler.elementAt(2)).setEnabled(true);
			} else {
				((JMenuItem)Twiddler.elementAt(1)).setEnabled(false);
				((JMenuItem)Twiddler.elementAt(2)).setEnabled(false);
			}
		}
	}// end itemSelected (String, boolean)

	public void makeSelectedLesson (String lesson) {
		JRadioButtonMenuItem temp;
		for (int i = 0; i < lessonButtons.size(); i++) {
			temp = (JRadioButtonMenuItem)lessonButtons.elementAt(i);
			if (temp.getActionCommand().equals(lesson)) {
				temp.doClick();
				return;
			}
		}
	}

	/**
	 * Modifier
	 * @param Twidor
	 */
	private void Tutor (Twidor tutor) {
		myTutor = tutor;
	}// end Tutor (Twidor)

	/**
	 * Accessor
	 * @return Twidor
	 */
	public Twidor Tutor () {
		return myTutor;
	}// end Tutor ()

	/**
	 * Modifier
	 * @param EventHandler
	 */
	private void Events (EventHandler events) {
		myEventHandler = events;
	}// end Events (EventHandler)

	/**
	 * Accessor
	 * @return EventHandler
	 */
	public EventHandler Events () {
		return myEventHandler;
	}// end Events ()

}// end class TwidorMenu
