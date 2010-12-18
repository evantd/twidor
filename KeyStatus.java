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
 * KeyStatus.java, Because I can't make the Vector class take boolean
 * elements without giving it a %&*^#*@ object class.
 *
 * Revisions:
 * 		0.5	17 July 2003
 * 		Completed Tutor
 * 		0.2	06 June 2003
 * 			Final. Not that it wasn't, but now it is.
 * 		0.1	27 May 2003
 * 			Created class KeyStatus
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
public class KeyStatus {
	/**
	 * internal variables
	 */
	private boolean status;

	/**
	 * default constructor
	 */
	public KeyStatus () {
		this(false);
	}// end KeyStatus ()

	/**
	 * default constructor
	 * @param boolean the status of this element
	 */
	public KeyStatus (boolean stat) {
		status = stat;
	}// end KeyStatus (boolean)

	/**
	 * default accessor
	 * @return boolean the status of this element
	 */
	public boolean getStatus () {
		return status;
	}// end getStatus ()

	/**
	 * default modifier
	 * @param boolean the new status of this element
	 */
	public void setStatus (boolean stat) {
		status = stat;
	}// end setStatus (boolean)

	/**
	 * default comparator
	 * @param Object the object to compare it to
	 * @return true if they are equal, false otherwise
	 */
	public boolean equal (Object o) {
		if (o instanceof KeyStatus) {
			return (((KeyStatus)o).getStatus() == getStatus());
		}
		return false;
	}// end equal (Object)

}// end class KeyStatus
