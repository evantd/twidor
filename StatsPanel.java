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
 * StatsPanel.java, the class that defines how stats are rendered to the
 * screen. Stats such as typing speed, corrected error rate, uncorrected error
 * rate, and all those other ones I'm going to have to re-read papers to
 * remember.
 *
 * Revisions:
 * 		0.5	17 July 2003
 * 			Completed Tutor
 * 		0.1	10 June 2003
 * 			Created class StatsPanel
 * </pre>
 * @author <a href="mailto:visyz@cc.gatech.edu">James Fusia</a>
 * @version Version 0.5; 17 July 2003
 */
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.util.Calendar;
import java.lang.Math;
public class StatsPanel extends JPanel implements TwidorConstants {

	/**
	 * internal variables
	 */
	private Vector myStats;
	private JLabel[] AER; /* Average Error Ragte */
	private JLabel[] WPM; /* Words Per Minute */
	private boolean saveStats;

	/**
	 * default constructor
	 */
	public StatsPanel () {
		myStats = new Vector();
		AER = new JLabel[2];
		WPM = new JLabel[2];
		setBackground(TEXT_BACKGROUND);
		setVisible(true);
		//if (STATS_SHOW) {
			showPanel();
		//}
		//setSave(LOG_ENABLE);
		setSave(false);
	}// end StatsPanel ()

	public void reset () {
		setVisible(false);
		myStats.clear();
		WPM[0].setText("0");
		AER[0].setText("0%");
		WPM[1].setText("0");
		AER[1].setText("0%");
		setVisible(true);
	}// end reset ()

	/**
	 * set up the internals of this panel
	 */
	public void showPanel () {
		JLabel temp;
		setVisible(false);
		removeAll();
		setBackground(TEXT_BACKGROUND);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel center = new JPanel();
		center.setAlignmentY(Component.CENTER_ALIGNMENT);
		center.setLayout(new GridLayout(3, 3));
		center.setBackground(TEXT_BACKGROUND);
		/* Row 1: 1 */
		center.add(quickLabel(""));
		/* Row 1: 2 */
		temp = quickLabel("WPM");
		temp.setPreferredSize(new Dimension(120, 24));
		center.add(temp);
		/* Row 1: 3 */
		temp = quickLabel("AER");
		temp.setPreferredSize(new Dimension(120, 24));
		center.add(temp);
		/* Row 2: 1 */
		temp = quickLabel("Last");
		temp.setHorizontalAlignment(SwingConstants.LEADING);
		center.add(temp);
		/* Row 2: 2 */
		WPM[0] = quickLabel("0");
		center.add(WPM[0]);
		/* Row 2: 3 */
		AER[0] = quickLabel("0%");
		center.add(AER[0]);
		/* Row 3: 1 */
		temp = quickLabel("Lesson");
		temp.setHorizontalAlignment(SwingConstants.LEADING);
		center.add(temp);
		/* Row 3: 2 */
		WPM[1] = quickLabel("0");
		center.add(WPM[1]);
		/* Row 3: 3 */
		AER[1] = quickLabel("0%");
		center.add(AER[1]);

		center.setMaximumSize(center.getPreferredSize());

		add(Box.createVerticalGlue());
		add(center);
		add(Box.createVerticalGlue());
		setVisible(true);
	}// end initPanel ()

	/**
	 * Show the end-of-lesson statistics
	 */
	public void showLessonStatistics () {
		hidePanel();
		setVisible(false);
		JPanel center = new JPanel();
		add(center);
		setVisible(true);
	}

	/**
	 * remove the internals of the panel
	 */
	public void hidePanel () {
		setVisible(false);
		removeAll();
		setBackground(TEXT_BACKGROUND);
		setVisible(true);
	}// end hidePanel ()

	/**
	 * Quicky helper function for repetitive tasks
	 */
	private JLabel quickLabel (String label) {
		JLabel temp = new JLabel(label);
		temp.setFont(new Font("Monospaced", Font.PLAIN, 20));
		temp.setForeground(TEXT_DEFAULT);
		temp.setBackground(TEXT_BACKGROUND);
		temp.setHorizontalAlignment(SwingConstants.TRAILING);
		return temp;
	}// end quickLabel ()

	/**
	 * Called when a new Sentence is being displayed
	 */
	public void nextSentence (String sentence) {
		getStats().add(new Stats(sentence));
		if (isVisible()) {
			reLabel();
		}
	}// end nextSentence (String)

	/**
	 * Accessor for the Stats variable
	 * @return Stats the current Stat
	 */
	public Vector getStats () {
		return myStats;
	}// end getStats ()

	/**
	 * Accessor for the Saving variable
	 * @return boolean whether or not we save
	 */
	public boolean getSave () {
		return saveStats;
	}// end getSave ()

	/**
	 * Modifier for the Saving variable
	 * @param boolean the new value
	 */
	public void setSave (boolean newvalue) {
		saveStats = newvalue;
	}// end saveStats (boolean)

	/**
	 * Informs the StatsPanel that a character was typed.
	 * StatsPanel takes the appropriate action and updates the labels.
	 * @param KeyElement the typed Key
	 * @param long the amount of time it took
	 */
	public void charTyped (KeyElement typed, long time) {
		if (typed.getNumber() == KEY_ENTER || typed.getNumber() == KEY_EOL)
			return;

		Stats current = (Stats)getStats().lastElement();
		current.addPressed(typed, time);
	}// end charTyped (KeyElement, KeyElement, long)

	public void showStats () {
		getStats().add(new Stats(""));
		if (isVisible())
			reLabel();
	}

	/**
	 * Accessor that re-draws what goes on the labels.
	 */
	public void reLabel () {
		Stats temp;
		if (bDEBUG) System.out.println("Relabeling.");
		if (getStats().size() < 2) {
			WPM[0].setText("0");
			WPM[1].setText("0");
			AER[0].setText("0%");
			AER[1].setText("0%");
			return;
		}
		temp = (Stats)getStats().elementAt(getStats().size() - 2);
		double lastCPS = temp.getCPS();
		double lastAER = temp.getAER();
		double newCPS = lastCPS;
		double newAER = lastAER;
		for (int i = 0; i < getStats().size() - 2; i++) {
			temp = (Stats)getStats().elementAt(i);
			newCPS += temp.getCPS();
			newAER += temp.getAER();
		}
		newCPS = newCPS / (getStats().size() - 1);
		newAER = Math.floor((newAER / (getStats().size() - 1)) * 10) / 10;
		WPM[0].setText(String.valueOf(Math.floor(lastCPS * 12)));
		AER[0].setText(String.valueOf(lastAER) + "%");
		WPM[1].setText(String.valueOf(Math.floor(newCPS * 12)));
		AER[1].setText(String.valueOf(newAER) + "%");
	}// end reLabel ()

	/**
	 * Function for dumping all data collected to disk
	 */
	public void save () {
		if (bDEBUG) System.out.println("StatsPanel: Saving Stats");
		if (!getSave()) {
			if (bDEBUG) System.out.println("StatsPanel: Not saving");
			return;
		}
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < getStats().size(); i++) {
			((Stats)getStats().elementAt(i)).writeStats(now.get(Calendar.YEAR) + "." +
				now.get(Calendar.MONTH) + "." + now.get(Calendar.DAY_OF_MONTH) + "." +
				now.get(Calendar.HOUR) + "." + now.get(Calendar.MINUTE) + "-" +
				i + ".log");
		}
	}// end save ()

}// end class StatsPanel
