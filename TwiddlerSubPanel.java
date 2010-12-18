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
 * TwiddlerSubPanel.java, An interface to provide common functionality between
 * TwiddlerPanel elements.
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 * 		0.1	05 June 2003
 * 		Created interface TwiddlerSubPanel
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
public interface TwiddlerSubPanel {

	/**
	 * a function for clearing all of the buttons
	 */
	public void clear ();

	/**
	 * a function for highlighting only a certain button
	 * @param int the button to highlight
	 * @param Color the color to hightlight it to
	 */
	public void highlight (int which, java.awt.Color hColor);

}// end interface TwiddlerSubPanel
