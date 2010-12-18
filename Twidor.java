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
 * Twidor.java, the GUI of the program. Get Lots of glue, and a couple rolls
 * of ducttape.
 *
 * Revisions:
 *		0.5	17 July 2003
 *		Completed Tutor
 * 		0.1	22 May 2003
 * 			Created class Twidor
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
public class Twidor extends JFrame implements TwidorConstants {

	/**
	 * internal variables
	 */
	private EventHandler myEventHandler;
	private TwidorMenu myMenuBar;
	private TwiddlerPanel myTwiddler;
	private TypingPanel myTypingPanel;
	private StatsPanel myStatsPanel;
	private InfoPanel myInfoPanel;
	private KeyMap myKeyMap;
	private boolean acceptInput; 
	private boolean showStats;
	private LessonParser myLessonPlan;
	private Lesson currentLesson;
	private String currentSentence;

	/**
	 * Default Constructor.
	 */
	public Twidor () {
		ignoreInput(true);
		showingStats(false);
		/* JFrame Settings */
		setSize(new Dimension(windowX, windowY));
		setTitle(windowTitle);
		setBackground(windowBackground);
		setResizable(windowResizable);

		/* Root Panel Settings */
		Container tempRoot = getContentPane();
		tempRoot.setLayout(new BorderLayout());

		setEventHandler();
		setKeyMap(DEFAULT_KEYMAP);
		setLessonPlan(DEFAULT_LESSON);
		setTwidorMenu();
		setTwiddlerPanel(TWIDDLER_MIRROR, TWIDDLER_MIRROR);
		setTypingPanel();
		setStatsPanel();
		setInfoPanel();
		setTwidorIcon();

		tempRoot.add(getTwiddlerPanel(), BorderLayout.WEST);

		JPanel CenterPane = new JPanel();
		CenterPane.setLayout(new GridLayout(3, 1));
		CenterPane.add(getInfoPanel());
		CenterPane.add(getTypingPanel());
		CenterPane.add(getStatsPanel());
		CenterPane.setBackground(TEXT_BACKGROUND);
		CenterPane.setVisible(true);

		tempRoot.add(CenterPane, BorderLayout.CENTER);
		/* Show it all */
		setVisible(true);
		setLesson("Lesson 1");
		ignoreInput(false);
	}// end Twidor

// EventHandler stuff
	/**
	 * Sets the EventHandler for the System
	 */
	private void setEventHandler() {
		myEventHandler = new EventHandler(this);
		addWindowListener(myEventHandler);
		addKeyListener(myEventHandler);
	}// end setEventHandler ()

	/**
	 * Accessor for the EventHandler
	 * @return EventHandler for the system
	 */
	private EventHandler getEventHandler () {
		return myEventHandler;
	}// end getEventHandler ()

// KeyMap stuff
	/**
	 * Sets the Keymap for the system
	 * @param String the source of the keymap
	 */
	private void setKeyMap (String source) {
		myKeyMap = new KeyMap(source);
		if (myKeyMap.appearsValid()) {
			if (bDEBUG) System.out.println("Keymap Loaded");
		}
		else {
			if (bDEBUG) System.out.println("Keymap Loading Failed");
		}
	}// end setKeymap (String)

	/**
	 * Accessor for the KeyMap
	 * @return KeyMap the current keymap
	 */
	private KeyMap getKeyMap () {
		return myKeyMap;
	}// end getKeymap ()

// TwidorMenu stuff
	/**
	 * Sets the JMenuBar for the options
	 */
	private void setTwidorMenu () {
		myMenuBar = new TwidorMenu(this, getEventHandler(), getLessonPlan().getLessonCount());
		setJMenuBar(myMenuBar);
	}// end setTwidorMenu ()

	/**
	 * Accessor for the TwidorMenu
	 * @return TwidorMenu the system's menubar
	 */
	private TwidorMenu getTwidorMenu () {
		return myMenuBar;
	}// end getTwidorMenu ()

// TwiddlerPanel stuff
	/**
	 * Sets the TwiddlerPanel for the system
	 * @param boolean the Thumb Orientation
	 * @param boolean the Finger Orientation
	 */
	private void setTwiddlerPanel (boolean thumb, boolean finger) {
		if (bDEBUG) System.out.println("Adding TwiddlerPanel");
		myTwiddler = new TwiddlerPanel(getKeyMap(), thumb, finger);
	}// end setTwiddlerPanel (boolean, boolean)

	/**
	 * Accessor for the TwiddlerPanel
	 * @return TwiddlerPanel the TwiddlerPanel
	 */
	private TwiddlerPanel getTwiddlerPanel () {
		return myTwiddler;
	}// end void getTwiddlerPanel ()

// TypingPanel stuff
	/**
	 * Sets the TypingPanel up
	 */
	private void setTypingPanel () {
		if (bDEBUG) System.out.println("Adding TypingPanel");
		myTypingPanel = new TypingPanel();
	}// end setTypingPanel ()

	/**
	 * Accessor for the TypingPanel
	 * @return TypingPanel the typing panel
	 */
	private TypingPanel getTypingPanel () {
		return myTypingPanel;
	}// end getTypingPanel ()

// StatsPanel stuff
	/**
	 * Set up the StatsPanel
	 */
	private void setStatsPanel () {
		if (bDEBUG) System.out.println("Adding StatsPanel");
		myStatsPanel = new StatsPanel();
	}// end setStatsPanel ()

	/**
	 * Accessor for the StatsPanel
	 * @return StatsPanel the stats panel
	 */
	private StatsPanel getStatsPanel () {
		return myStatsPanel;
	}// end getStatsPanel ()

// InfoPanel stuff
	/**
	 * Set up the InfoPanel
	 */
	private void setInfoPanel () {
		if (bDEBUG) System.out.println("Adding InfoPanel");
		myInfoPanel = new InfoPanel();
	}// end setInfoPanel

	/**
	 * Accessor for the InfoPanel
	 * @return InfoPanel the panel
	 */
	private InfoPanel getInfoPanel () {
		return myInfoPanel;
	}// end getInfoPanel ()

// Twidor Icon stuff
	/**
	 * Load the ImageIcon from a file. Whee.
	 */
	private void setTwidorIcon () {
		URL location = this.getClass().getResource("icon.gif");
		ImageIcon temp = new ImageIcon(location);
		setIconImage(temp.getImage());
	}// end setTwidorIcon ()

// LessonParser stuff
	/**
	 * Loads the Lesson Parser and parses the lesson file
	 * @param String the source file the lesson parser should use
	 */
	private void setLessonPlan (String source) {
		myLessonPlan = new LessonParser(source);
	}

	/**
	 * Accessor for the Lessons system
	 * @return LessonParser the lessons
	 */
	private LessonParser getLessonPlan () {
		return myLessonPlan;
	}// end getLessons ()

// Lesson stuff
	/**
	 * Sets the current lesson from the set of lessons
	 * @param String the name of the lesson to set
	 */
	private void setLesson (String lesson) {
		LessonParser lpTemp = getLessonPlan();
		Lesson lTemp;
		for (int i = 0; i < lpTemp.getLessonCount(); i++) {
			lTemp = lpTemp.getLesson(i);
			if (lTemp.getLessonName().equals(lesson)) {
				currentLesson = lTemp;
				currentLesson.reloadSentences();
				getStatsPanel().reset();
				nextSentence();
				return;
			}
		}
		if (bDEBUG) System.out.println("Twidor: Unmatched lesson name: " + lesson);
	}// end setLesson (String)

	/**
	 * gets the current lesson
	 * @return Lesson the current lesson
	 */
	private Lesson getLesson () {
		return currentLesson;
	}// end getLesson ()

// Misc stuff

	/**
	 * gets the current sentence
	 * @return String the current sentence
	 */
	public String getSentence () {
		return currentSentence;
	}// end getSentence ()

	/**
	 * updates the current sentence, also checks if we need to
	 * switch Lessons.
	 */
	public void nextSentence () {
		/* next sentence in this lesson */
		currentSentence = getLesson().getSentence();
		getTypingPanel().displaySentence(getSentence());
		getStatsPanel().nextSentence(getSentence());
		doHighlighting();
		getInfoPanel().setTitle(getLesson().getLessonName() + ": Sentence " +
			getLesson().getSentenceNumber());
	}// end nextSentence ()

	/**
	 * updates the current lesson
	 */
	public void nextLesson () {
		int next = getLesson().getLessonNumber() + 1;
		if (next > getLessonPlan().getLessonCount())
			next--;
		getTwidorMenu().makeSelectedLesson("Lesson " + next);
		//setLesson("Lesson " + next);
	}// nextLesson ()

	/**
	 * show the statistics. woo!
	 */
	public void showStats () {
		getTypingPanel().displayMessage("Press any key to continue");
		getStatsPanel().showStats();
		getInfoPanel().setTitle(getLesson().getLessonName() + ": Complete");
	}// showStats ()

	/**
	 * Does the highlighting.
	 */
	public void doHighlighting () {
		getTwiddlerPanel().clear();
		KeyElement match = getKeyMap().getKey(String.valueOf((char)KEY_ENTER));
		int index = getTypingPanel().getCurrent();
		if (index < getSentence().length()) {
			String remainder = getSentence().substring(getTypingPanel().getCurrent());
			match = getKeyMap().matchLargestChunk(remainder);
		}
		getTwiddlerPanel().highlight(match);
	}// end doHighlighting ()

	/**
	 * sets the Input flag (whether we are recording or not)
	 * @param boolean the new status
	 */
	private void ignoreInput (boolean input) {
		acceptInput = input;
	}// end ignoreInput (boolean)

	/**
	 * gets the Input flag
	 * @return boolean whether or not we are recording
	 */
	private boolean ignoreInput () {
		return acceptInput;
	}// end ignoreInput ()

	/**
	 * @param boolean the new status
	 */
	private void showingStats (boolean input) {
		showStats = input;
	}// end showingStats (boolean)

	/**
	 * @return boolean whether or not we go to the next lesson
	 */
	private boolean showingStats () {
		return showStats;
	}// end showingStats ()

	/**
	 * Accessor for the EventHandler to inform the Tutor a key has been
	 * pressed.
	 * @param char the key pressed
	 * @param long the system time when it was pressed
	 */
	public void charTyped (String key, long time) {
		/* FIXME */
		String sentence = getSentence();
		KeyElement typed = getKeyMap().getKey(key);

		//if (ignoreInput()) {
			//if (bDEBUG) System.out.println("Ignoring input for now...");
			//return;
		//}

		if (typed == null) {
			if (bDEBUG) System.out.println("Really big problem with character typed.");
			return;
		}

		if (bDEBUG) System.out.println("Accepting input");

		/* Register the keypress */
		getStatsPanel().charTyped(typed, time);
		getTypingPanel().charTyped(typed);

		/* Determine if we need to change sentences */
		if (getTypingPanel().sentenceComplete()) {
			if (getLesson().isComplete()) {
				if (!showingStats()) {
					showStats();
					showingStats(true);
				} else {
					nextLesson();
					showingStats(false);
				}
			} else {
				nextSentence();
			}
		}
		doHighlighting();
	}// end charTyped (char, long)

	/**
	 * function for effecting menubar based changes
	 * @param String the MenuItem that was selected
	 * @param boolean its new status
	 */
	public void booleanOption (String option, boolean status) {
		/* FIXME */
		if (bDEBUG) System.out.println("Changing " + option + ":" + status);
		getTwidorMenu().itemSelected(option, status);
		if (option.equals(TWIDDLER_SHOW_TEXT)) {
			getTwiddlerPanel().setTwiddlerVisible(status);
			getTwiddlerPanel().reOrient();
			return;
		}
		if (option.equals(TWIDDLER_SHOW_LETTERS_TEXT)) {
			getTwiddlerPanel().setThumbKeysVisible(status);
			getTwiddlerPanel().setFingerKeysVisible(status);
			getTwiddlerPanel().reOrient();
			return;
		}
		if (option.equals(TWIDDLER_MIRROR_TEXT)) {
			getTwiddlerPanel().setThumbOrientation(status);
			getTwiddlerPanel().setFingerOrientation(status);
			getTwiddlerPanel().reOrient();
			return;
		}
		if (option.equals(HIGHLIGHT_ERRORS_TEXT)) {
			getTypingPanel().setHighlightErrors(status);
			return;
		}
		if (option.equals(HIGHLIGHT_HINT_TEXT)) {
			return;
		}
		if (option.equals(HIGHLIGHT_KEYPRESS_TEXT)) {
			return;
		}
		if (bDEBUG) System.out.println("Unhandled option");
	}// end booleanOption (String, boolean)

	/**
	 * function for effecting menubar based changes
	 * @param String the menu option that was selected
	 */
	public void menuOption (String option) {
		if (bDEBUG) System.out.println("Option " + option);
		getTwidorMenu().itemSelected(option, true);
		if (option.equals(QUIT_TEXT)) {
			twidorQuit();
		}
		else if (option.startsWith("Lesson")) {
			setLesson(option);

			/* FIXME */
			//getInfoPanel().setTitle(getSentences().getSource() + ": Sentence " + getSentenceNumber());

			//getTypingPanel().displaySentence(getSentence());
			//getStatsPanel().nextSentence(getSentence());
		}
	}// end menuOption (String)

	/**
	 * function for cleaning up and closing and stuff
	 */
	public void twidorQuit () {
		if (bDEBUG) System.out.println("Exiting Twidor.");
		setVisible(false);
		// insert stats saving stuff here.
		getStatsPanel().save();
		dispose();
		System.exit(0);
	}// end twidorQuit ()

	/**
	 * Good god. The Main.
	 */
	public static void main (String[] argv) {
		Twidor tutor = new Twidor();
	}// end main

}// end class Twidor
