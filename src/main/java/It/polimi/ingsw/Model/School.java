package It.polimi.ingsw.Model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the School of the player.
 */
public class School implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Color> Entrance;

	private int GreenStudents;

	private int RedStudents;

	private int YellowStudents;

	private int PinkStudents;

	private int BlueStudents;

	/**
	 * Constructor of the class School. It initializes the number of students to zero and creates the Entrance of the school as an ArrayList of colors.
	 */
	public School() {
		Entrance = new ArrayList<Color>();
		this.GreenStudents = 0;
		this.RedStudents = 0;
		this.YellowStudents = 0;
		this.PinkStudents = 0;
		this.BlueStudents = 0;
	}

	/**
	 * Add a student to the Entrance of the school of the player.
	 *
	 * @param StudentColor: the color of a student.
	 */
	public void AddEntranceStudents(Color StudentColor) {
		Entrance.add(StudentColor);
	}

	/**
	 * Move a student from the Entrance to Dining Room. It removes the student from the Entrance.
	 * Increase the number of student by one in the Dining Room depending on the color extracted from the Entrance.
	 *
	 * @param index: the index of the student in the Entrance.
	 */
	public void MoveStudentToDiningRoom(int index) {
		Color StudentColor = Entrance.get(index);
		switch (StudentColor) {
			case GREEN:
				GreenStudents++;
				break;
			case RED:
				RedStudents++;
				break;
			case BLUE:
				BlueStudents++;
				break;
			case PINK:
				PinkStudents++;
				break;
			case YELLOW:
				YellowStudents++;
				break;
		}
		Entrance.remove(index);
	}

	/**
	 * Decrease the number of student by one in the Dining Room depending on the StudentColor.
	 *
	 * @param StudentColor: the color of a student.
	 */
	public void RemoveStudentFromDiningRoom(Color StudentColor) {
		switch (StudentColor) {
			case GREEN:
				GreenStudents--;
				break;
			case RED:
				RedStudents--;
				break;
			case BLUE:
				BlueStudents--;
				break;
			case PINK:
				PinkStudents--;
				break;
			case YELLOW:
				YellowStudents--;
				break;
		}
	}

	/**
	 * It extracts the student to the index passed by parameter from the entrance, removes it from the entrance and returns its color.
	 *
	 * @param index: the index of the student in the Entrance.
	 * @return the color of student extracted from the Entrance.
	 */
	public Color MoveStudentToIsland(int index) {
		Color StudentColor = Entrance.get(index);
		Entrance.remove(index);
		return StudentColor;
	}

	/**
	 * Return the number of students in the Dining Room of the color passed by parameter.
	 *
	 * @param StudentColor: the color of a student.
	 * @return the number of students in the Dining Room of the color passed by parameter.
	 */
	public int getStudentNumber(Color StudentColor) {
		int StudentNumber = 0;
		switch (StudentColor) {
			case YELLOW:
				StudentNumber = YellowStudents;
				break;
			case PINK:
				StudentNumber = PinkStudents;
				break;
			case BLUE:
				StudentNumber = BlueStudents;
				break;
			case RED:
				StudentNumber = RedStudents;
				break;
			case GREEN:
				StudentNumber = GreenStudents;
				break;
		}
		return StudentNumber;
	}

	/**
	 * Increase the number of student by one in the Dining Room depending on the color passed by parameter.
	 *
	 * @param StudentColor: the color of a student.
	 */
	public void AddStudentToDiningRoom(Color StudentColor) {
		switch (StudentColor) {
			case GREEN:
				GreenStudents++;
				break;
			case RED:
				RedStudents++;
				break;
			case BLUE:
				BlueStudents++;
				break;
			case PINK:
				PinkStudents++;
				break;
			case YELLOW:
				YellowStudents++;
				break;
		}
	}

	/**
	 * It extracts the student to the index passed by parameter from the entrance, removes it from the entrance and returns its color.
	 *
	 * @param index: the index of the student in the Entrance.
	 * @return the color of student extracted from the Entrance.
	 */
	public Color RemoveStudentFromEntrance(int index) {
		Color StudentColor;
		StudentColor = Entrance.get(index);
		Entrance.remove(index);
		return StudentColor;
	}

	/**
	 * Return the color of student at the position "index" in the Entrance.
	 *
	 * @param index: the index of the student in the Entrance.
	 * @return the color of student extracted from the Entrance.
	 */
	public Color GetEntranceStudentColor(int index) {
		Color StudentColor;
		StudentColor = Entrance.get(index);
		return StudentColor;
	}

	/**
	 * Return the number of students in the Entrance.
	 *
	 * @return the number of students in the Entrance.
	 */
	public int getEntranceStudentsNumber() {
		return this.Entrance.size();
	}
}
