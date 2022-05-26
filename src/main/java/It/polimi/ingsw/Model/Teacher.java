package It.polimi.ingsw.Model;


import java.io.Serializable;

/**
 * This class represents the teacher.
 */
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private Color TeacherColor;

	private int HighestNumberOfStudents;

	private Player ControllingPlayer;

	/**
	 * Constructor of the class Teacher.
	 *
	 * @param teacherColor: the color of the teacher.
	 */
	public Teacher(Color teacherColor) {
		TeacherColor = teacherColor;
		HighestNumberOfStudents = 0;
	}

	/**
	 * Return the color of the player's towers controlled by the teacher.
	 *
	 * @return the color of the player's towers controlled by the teacher.
	 */
	public TowerColor getControllingPlayerColor() {
		return this.ControllingPlayer.getPlayerColor();
	}

	/**
	 * Return the number of students controlled by the teacher.
	 *
	 * @return the number of students controlled by the teacher.
	 */
	public int getHighestNumberOfStudents() {   //gives the attribute to Match
		return this.HighestNumberOfStudents;
	}

	/**
	 * Increase the number of students controlled by the teacher by one.
	 */
	public void IncreaseHighestNumberOfStudents() {
		HighestNumberOfStudents++;
	}

	/**
	 * Change the player who controls the teacher.
	 *
	 * @param NewControllingPlayer: the player who controls the teacher.
	 */
	public void ChangeController(Player NewControllingPlayer) { //changes the attribute if requested by Match
		ControllingPlayer = NewControllingPlayer;
	}

	/**
	 * Return the color of the teacher.
	 *
	 * @return the color of the teacher.
	 */
	public Color getTeacherColor() {
		return this.TeacherColor;
	}

	/**
	 * Return the player who controls the teacher.
	 *
	 * @return the player who controls the teacher.
	 */
	public Player getControllingPlayer() {
		return ControllingPlayer;
	}
}
