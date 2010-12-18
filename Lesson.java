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
 * Lesson.java, the internal representation of each lesson.
 *
 * Revisions:
 * 			0.1	11 August 2005
 * 			Created class Lesson
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 11 August 2005
 */
import java.util.*;
import java.io.*;
public class Lesson implements TwidorConstants {
	/**
	 * internal variables
	 */
	private String fileName; // where we read our sentences from
	private int lessonNumber; // our lesson number, in order
	private boolean highlightChord; // show highlighting?
	private boolean highlightMCC; // show MCC highlighting?

	private Vector lessonSentences; // array of sentences to show
	private Vector trash;
	private int lessonCount; // number of sentences shown
	private int lessonTotal; // number of sentences to show

	/**
	 * default constructor
	 */
	private Lesson () {
		fileName = "";
		lessonNumber = -1;
		lessonSentences = new Vector();
		trash = new Vector();
		highlightChord = false;
		highlightMCC = false;
		lessonCount = 0;
		lessonTotal = 0;
	}// end Lesson ()

	/**
	 * default constructor
	 * @param String the file to read sentences from
	 */
	public Lesson (String file) {
		this();
		fileName = file;
	}

	/**
	 * Accessors
	 */
	public String getLessonName () {
		return "Lesson " + getLessonNumber();
	}

	public int getLessonNumber () {
		return lessonNumber;
	}

	public boolean isComplete () {
		if (lessonCount >= lessonTotal)
			return true;
		return false;
	}

	public boolean getHighlight () {
		return highlightChord;
	}

	public boolean getHighlightMCC () {
		return highlightMCC;
	}

	public String getSentence () {
		if (lessonSentences.isEmpty()) {
			lessonSentences.addAll(trash);
			trash.clear();
		}
		int victim = (int)(Math.random() * (double)lessonSentences.size());
		String sentence = (String)lessonSentences.elementAt(victim);
		lessonSentences.removeElementAt(victim);
		trash.add(sentence);
		lessonCount++;
		return sentence;
	}

	public int getSentenceNumber () {
		return lessonCount;
	}

	/**
	 * Modifiers
	 */
	public void setLessonNumber (int number) {
		lessonNumber = number;
	}

	public void setLessonTotal (int count) {
		lessonTotal = count;
	}

	public void setHighlight (boolean status) {
		highlightChord = status;
	}

	public void setHighlightMCC (boolean status) {
		highlightMCC = status;
	}

	/**
	 * Function for reading a file of Sentences and parsing them all into
	 * an array.
	 * @param String the name of the file to read
	 */
	private void readFile (String source) {
		FileReader fReader = null;
		InputStream iStream = null;
		InputStreamReader isReader = null;
		BufferedReader bReader = null;

		if (bDEBUG) System.out.println("Lesson: reading file " + source);

		try {
			fReader = new FileReader(source);
			bReader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {
			iStream = this.getClass().getResourceAsStream(source);
			if (iStream != null) {
				isReader = new InputStreamReader(iStream);
				bReader = new BufferedReader(isReader);
			} else {
				System.out.println("Could not load " + getLessonName() + ". (" + source + " not found)");
				System.exit(1);
			}
		} catch (Exception e) {
		}
		try {
			while (bReader.ready()) {
				lessonSentences.addElement(bReader.readLine());
			}
		} catch (IOException e) {
			if (bDEBUG) System.out.println("Lesson: IO Error");
		} catch (Exception e) {
		}

		try {
			bReader.close();
			if (iStream == null) {
				fReader.close();
			} else {
				isReader.close();
				iStream.close();
			}
		} catch (Exception e) {
		}

		if (bDEBUG)
			System.out.println("Lesson: " + lessonSentences.size()
					+ " sentences read");
	}

	public void reloadSentences () {
		lessonSentences.clear();
		trash.clear();
		readFile(fileName);
		lessonCount = 0;
	}

}
