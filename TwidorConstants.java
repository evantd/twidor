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
 * TwidorConstants.java, the file where we make a whole bunch of silly things
 * static. Like Color. And Title. Bleh.
 * How dare you write Easily Maintained code!
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 *		0.1	22 May 2003
 * 		Created interface TwidorConstants
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.Color;
public interface TwidorConstants {

	/**
	 * Debug Variable
	 */
	public static final boolean bDEBUG = false;

	/**
	 * Window Attributes
	 */
	public static final String windowTitle = "Twidor: The Twiddler Tutor!";
	public static final int windowX = 640;
	public static final int windowY = 400;
	public static final int twiddlerX = 120;
	public static final Color windowBackground = Color.white;
	public static final boolean windowResizable = true;

	/**
	 * button attributes
	 */
	public static final int buttonX = (twiddlerX - 30) / 6;
	public static final int buttonY = windowY * 2 / 15; // 2/3 of 1/4 of 4/5 of windowY
	public static final Color buttonBackground = Color.LIGHT_GRAY;
	public static final Color buttonHighlight = Color.yellow;
	public static final Color mccHighlight = Color.green;
	public static final Color twiddlerBackground = Color.GRAY;

	/**
	 * icons
	 */
	public static final String ICON_RED = "red.gif";
	public static final String ICON_BLUE = "blue.gif";
	public static final String ICON_GREEN = "green.gif";
	
	/**
	 * character colors
	 */
	public static final Color TEXT_DEFAULT = Color.BLACK;
	public static final Color TEXT_ERROR = Color.RED;
	public static final Color TEXT_GOOD = Color.GRAY;
	public static final Color TEXT_BLINK = Color.BLUE;
	public static final Color TEXT_BACKGROUND = Color.WHITE;
	public static final Color TEXT_CURSOR = Color.BLUE;
	public static final String CURSOR = "_";

	/**
	 * Files that are searched for by the program when none are specified
	 */
	public static final String DEFAULT_KEYMAP = "keymap.txt";
	public static final String DEFAULT_LESSON = "lessons.txt";
	public static final boolean DEFAULT_THUMB_ORIENTATION = true;
	public static final boolean DEFAULT_FINGER_ORIENTATION = true;

	/**
	 * In-program settings
	 */
	/* Menu stuff: Twidor */
	public static final String QUIT_TEXT = "Quit";

	/* Tutor */
	public static final String HIGHLIGHT_HINT_TEXT = "Highlight Hint";
	public boolean HIGHLIGHT_HINT = true;
	public static final String HIGHLIGHT_KEYPRESS_TEXT = "Highlight Keypress";
	public boolean HIGHLIGHT_KEYPRESS = true;
	public static final String HIGHLIGHT_ERRORS_TEXT = "Highlight Errors";
	public boolean HIGHLIGHT_ERRORS = true;

	/* Twiddler */
	public static final String TWIDDLER_SHOW_TEXT = "Show Twiddler";
	public boolean TWIDDLER_SHOW = true;
	public static final String TWIDDLER_MIRROR_TEXT = "Mirror Twiddler";
	public boolean TWIDDLER_MIRROR = false;
	public static final String TWIDDLER_SHOW_LETTERS_TEXT = "Show Twiddler Letters";
	public boolean TWIDDLER_SHOW_LETTERS = true;

	public static final String MACRO_REGEXP = "\"\\w*\"";

	/**
	 * For consistency when accessing the KeyElement button Vector.
	 */
	public static final int FINGER_OFFSET = 3;
	public static final int INDEX_OFFSET = 0;
	public static final int MIDDLE_OFFSET =  3;
	public static final int RING_OFFSET = 6;
	public static final int PINKY_OFFSET = 9;
	public static final int THUMB_OFFSET = 12;

	public static final int B_NUM = 0;
	public static final int B_ALT = 1;
	public static final int B_CTRL = 2;
	public static final int B_SHIFT = 3;

	public static final int B_LEFT = 0;
	public static final int B_MIDDLE = 1;
	public static final int B_RIGHT = 2;

	/**
	 * And because they might change...
	 */
	public static final int KEY_BACKSPACE = 8;
	public static final int KEY_ENTER = 13;
	public static final int KEY_SPACE = 32;
	public static final int KEY_DELETE = 127;
	/* I'm not sure why this is different from KEY_ENTER, but it makes a
	 * difference */
	public static final int KEY_EOL = 10;

	public static final int C = 0;
	public static final int F = 1;
	public static final int IF = 2;
	public static final int INF = 3;

}// end interface TwidorConstants
