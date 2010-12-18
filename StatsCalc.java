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
 * Stats.java, what I expect to be the most heavily modified file here.
 * This file is meant to hold the stats for a user/session/whatever. For
 * however long. And automatically do the calculations. Etc.
 *
 * Revisions:
 * 			0.5	17 July 2003
 * 			Completed Tutor
 * 			0.1	22 May 2003
 * 			Created class Stats
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.io.*;
import java.util.Vector;
import java.lang.Math;
public class StatsCalc implements TwidorConstants {

	public static int[] statsMSD (String sentence, String typed) {
		int i, j;
		String a = " " + sentence;
		String b = " " + typed;
		int[][] msd = new int[a.length()][b.length()];
		int[][] mfa = new int[a.length()][b.length()];

		int min, sub, notInA;

		/* initialization */
		for (i = 0; i < a.length(); i++) {
			msd[i][0] = i;
			mfa[i][0] = 0;
		}
		for (j = 0; j < b.length(); j++) {
			msd[0][j] = j;
			mfa[0][j] = j;
		}

		/* calculation */
		for (i = 1; i < a.length(); i++) {
			for (j = 1; j < b.length(); j++) {
				min = msd[i - 1][j] + 1;
				notInA = msd[i][j - 1] + 1;
				sub = msd[i - 1][j - 1];
				if (a.charAt(i) != b.charAt(j)) {
					sub++;
				}
				mfa[i][j] = mfa[i - 1][j];
				if (sub < min) {
					min = sub;
					mfa[i][j] = mfa[i - 1][j - 1];
				}
				if (notInA < min) {
					min = notInA;
					mfa[i][j] = mfa[i][j - 1] + 1;
				}
				msd[i][j] = min;
			}
		}

		/* return value */
		int[] ret = new int[2];
		ret[0] = msd[a.length() - 1][b.length() - 1];
		ret[1] = mfa[a.length() - 1][b.length() - 1];
		return ret;
	}// end statsMSD (String,String)

	public static int statsF (Vector keyPresses) {
		int ret = 0;
		KeyElement temp;
		for (int i = 0; i < keyPresses.size(); i++) {
			temp = ((StatsElement)keyPresses.elementAt(i)).getTyped();
			if (temp.getNumber() == KEY_BACKSPACE || temp.getNumber() == KEY_DELETE) {
				ret++;
			}
		}
		return ret;
	}// end statsF (Vector)

	public static int statsIF (String sentence, String typed, Vector keyPresses) {
		//return (keyPresses.size() - typed.length()) - statsF(keyPresses);
		return statsF(keyPresses) - (typed.length() - sentence.length());
	}// end statsIF (String,String,Vector)

	public static int statsC (String sentence, String typed) {
		int[] msd = statsMSD(sentence, typed);
		int length = Math.min(typed.length(), sentence.length());

		return length - (msd[0] - msd[1]);
	}// end statsC (String,String)

	public static int statsINF (String sentence, String typed) {
		return statsMSD(sentence, typed)[0];
	}// end statsINF (String,String)

	/**
	 * return value is {C, F, IF, INF}
	 */
	public static int[] statsALL (String sentence, String typed, Vector keyPresses) {
		int[] ret = new int[4];
		int[] msd = statsMSD(sentence, typed);

		ret[C] = Math.min(typed.length(), sentence.length()) - (msd[0] - msd[1]);
		ret[F] = statsF(keyPresses);
		ret[IF] = ret[F] - (typed.length() - sentence.length());
		ret[INF] = msd[0];
		return ret;
	}

	public static void main (String[] argv) {
		int c, inf, f, ifix;
		String sentence = "thank you for your help";
		String typed = "shank you for your help";
		Vector keyPresses = new Vector();
		c = statsC(sentence, typed);
		inf = statsINF(sentence, typed);
		f = statsF(keyPresses);
		ifix = statsIF(sentence, typed, keyPresses);

		System.out.println(c + " " + inf + " " + f + " " + ifix);
		System.out.println(((double)(inf + ifix)) / ((double)(c + inf + ifix)));
	}// end main (String)

}// end class StatsCalc
