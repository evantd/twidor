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
public class Stats implements TwidorConstants {

	/**
	 * internal variable that contains stats
	 */
	private String sentence;
	private String typed;
	private Vector statArray; 
	private Vector transcribedInput;

	/**
	 * default constructor
	 */
	private Stats () {
		sentence = "";
		typed = "";
		statArray = new Vector();
		transcribedInput = new Vector();
	}// end Stats

	/**
	 * default constructor
	 */
	public Stats (String newSentence) {
		this();
		if (bDEBUG) System.out.println("Stats created");
		setSentence(newSentence);
	}// end Stats (String)

	/**
	 * Accessor to get the sentence for this stat
	 * @return String the sentence we contain
	 */
	public String getSentence () {
		return sentence;
	}// end getSentence ()

	/**
	 * Accessor to get the typed sentence for this stat
	 * @return String the sentence actually typed
	 */
	public String getTyped () {
		return typed;
	}// end getTyped ()

	/**
	 * Accessor to get the vector of keypresses
	 * @return Vector of StatsElement
	 */
	public Vector getKeyPresses () {
		return statArray;
	}// end getKeyPresses ()

	/**
	 * Accessor for the Transcribed Input
	 * @return Vector of StatsElements
	 */
	public Vector getTranscribedInput () {
		return transcribedInput;
	}// end getTranscribedInput ()

	/**
	 * Modifier to set the current sentence
	 * @param String the sentence to set
	 */
	public void setSentence (String line) {
		sentence = line;
	}// end setSentece (String)

	/**
	 * Modifier to set the KeyPress vector
	 * @param Vector the new vector to set
	 */
	public void setKeyPresses (Vector poke) {
		statArray = poke;
	}// end setKeyPresses (Vector)

	/**
	 * Accessor to add a new StatsElement to the Stats
	 * @param KeyElement the key that was pressed
	 * @param long the time it took to press it (in ms)
	 */
	public void addPressed (KeyElement pressed, long time) {
		getKeyPresses().add(new StatsElement(pressed, time));
		if (pressed.getNumber() == KEY_BACKSPACE || pressed.getNumber() == KEY_DELETE) {
			int last = transcribedInput.size() - 1;
			transcribedInput.removeElementAt(last);
			typed = typed.substring(0, typed.length() - 1);
		} else {
			transcribedInput.add(new StatsElement(pressed, time));
			typed += (char)pressed.getNumber();
		}
	}// end addKey (KeyElement, KeyElement, long)

	public double getCPS () {
		long totalTime = 0;
		StatsElement temp1, temp2;
		if (transcribedInput.isEmpty())
			return 0;
		temp1 = (StatsElement)transcribedInput.elementAt(0);
		for (int i = 1; i < transcribedInput.size() - 1; i++) {
			temp2 = (StatsElement)transcribedInput.elementAt(i);
			totalTime += (temp2.getTime() - temp1.getTime());
			temp1 = temp2;
		}
		return (double)((transcribedInput.size() - 2) * 1000) / (double)totalTime;
	}// end getCPS

	public double getAER () {
		double err;
		int[] stats = StatsCalc.statsALL(getSentence(), getTyped(), getKeyPresses());

		err = (double)(stats[INF] + stats[IF]) / (double)(stats[C] + stats[INF] + stats[IF]);
		err = Math.floor(err * 1000) / 10;
		return err;
	}// end getAER

	/**
	 * Function for writing all stats to a file.
	 * @param String the name of the file to output to
	 */
	public void writeStats (String dest) {
		FileWriter fwFile;
		BufferedWriter bwBuffer;
		StatsElement temp;
		if (getKeyPresses().size() <= 0) {
			/* No need to bother saving */
			return;
		}
		try {
			fwFile = new FileWriter(dest);
			bwBuffer = new BufferedWriter(fwFile);
			
			bwBuffer.write(getSentence());
			bwBuffer.newLine();
			for (int i = 0; i < getKeyPresses().size(); i++) {
//				bwBuffer.write(((StatsElement)getKeyPresses().elementAt(i)).toString());
				bwBuffer.write(getKeyPresses().elementAt(i).toString());
				bwBuffer.newLine();
			}
			bwBuffer.close();
			fwFile.close();
		}
		catch (Exception e) {
			if (bDEBUG) System.out.println("Stats: Error writing to file " + dest);
		}
	}// end writeStats (String)

}// end class Stats
