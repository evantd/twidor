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
 * StatsElement.java, a set of statistics for a single sentence inputted.
 *
 * Revisions:
 * 			0.5	17 July 2003
 * 			Completed Tutor
 * 			0.1	29 May 2003
 * 			Created class StatsElement
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
public class StatsElement implements TwidorConstants {

	/**
	 * internal variables
	 */
	KeyElement myKey;
	long time;

	/**
	 * default constructor
	 */
	private StatsElement () {
		this(null, -1);
	}// end StatsElement ()

	/**
	 * default constructor
	 * @param KeyElement what the key is
	 * @param long the time it took to press this key (since the last time)
	 */
	public StatsElement (KeyElement typed, long time) {
		if (bDEBUG) System.out.println("StatsElement: creating");
		setTyped(typed);
		setTime(time);
	}// end StatsElement (KeyElement, KeyElement, long)

	/**
	 * Accessor to find what key this was
	 * @return KeyElement the typed key
	 */
	public KeyElement getTyped () {
		return myKey;
	}// end getTyped ()

	/**
	 * Acessor to find the amount of time this took
	 * @return long the time in milliseconds
	 */
	public long getTime () {
		return time;
	}// end getTime ()

	/**
	 * Modifier to change the Typed Key
	 * @param KeyElement the key that was typed
	 */
	public void setTyped (KeyElement typed) {
		myKey = typed;
	}// end setTyped (KeyElement)

	/**
	 * Modifier to change the time it took to press the key
	 * @param long the time (in milliseconds) since the last keypress
	 */
	public void setTime (long amount) {
		time = amount;
	}// end setTime (long)

	/**
	 * Function for returning a string form of this element
	 * @return String this KeyElement
	 */
	public String toString () {
		String toReturn = "";
		toReturn += (getTime() + "\t");
		toReturn += getTyped().toString();
		return toReturn;
	}// end toString ()

}// end class StatsElement
