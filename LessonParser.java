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
 * LessonParser.java, the class to read the lessons.txt file and produce
 * the associated Lesson interface in the menu system, as well as control
 * the flow of the tutoring.
 *
 * Revisions:
 * 			0.1	11 August 2005
 * 			Created class LessonParser
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.1; 11 August 2005
 */
import java.util.*;
import java.io.*;
public class LessonParser implements TwidorConstants {
	/**
	 * internal variables
	 */
	private Vector lessons;

	private LessonParser () {
		lessons = new Vector();
	}

	/**
	 * default constructor
	 */
	public LessonParser (String file) {
		this();
		int count = 1;
		FileReader fReader = null;
		InputStream iStream = null;
		InputStreamReader isReader = null;
		BufferedReader bReader = null;

		if (bDEBUG) System.out.println("LessonParser: reading file " + file);

		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {
			iStream = this.getClass().getResourceAsStream(file);
			if (iStream != null) {
				isReader = new InputStreamReader(iStream);
				bReader = new BufferedReader(isReader);
			} else {
				System.out.println("Could not load Lesson Planner. (" + file + " not found)");
				System.exit(1);
			}
		} catch (Exception e) {
		}
		try {
			while (bReader.ready()) {
				/* read a sentence with readLine() */
				String line = bReader.readLine();
				line.trim();
				if (line.startsWith("#")) {
					/* comment. ignore it. */
				} else {
					String[] parts = line.split("\\s");

					if (parts.length == 4) {
						Lesson nLesson = new Lesson(parts[0]);
						nLesson.setLessonTotal(Integer.parseInt(parts[1]));
						if (parts[2].equalsIgnoreCase("y")) {
							nLesson.setHighlight(true);
						} else {
							nLesson.setHighlight(false);
						}
						if (parts[3].equalsIgnoreCase("y")) {
							nLesson.setHighlightMCC(true);
						} else {
							nLesson.setHighlightMCC(false);
						}
						nLesson.setLessonNumber(count++);
						addLesson(nLesson);
					}
				}
			}
		} catch (IOException e) {
			if (bDEBUG) System.out.println("LessonParser: IO Error while reading");
		} catch (Exception e) {
			if (bDEBUG) System.out.println("LessonParser: General error");
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
	}

	private void addLesson (Lesson newLesson) {
		lessons.addElement(newLesson);
	}

	/**
	 * accessors
	 */
	public int getLessonCount () {
		return lessons.size();
	}

	public Lesson getLesson (int number) {
		return (Lesson)lessons.elementAt(number);
	}

}
