package It.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the class of Character Card with Id 1 and 10, which extends the Character Card class.
 */
public class Cards1and10 extends CharacterCard implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Color> StudentsOnCard;

	/**
	 * Constructor of the class Cards1and10. It create an ArrayList of color which is used for the students on these cards.
	 *
	 * @param idCharacterCard: the id of Character Card.
	 * @param cardCost:        the cost of Character Card.
	 * @param currentMatch:    the current match.
	 */
	public Cards1and10(int idCharacterCard, int cardCost, Match currentMatch) {
		super(idCharacterCard, cardCost, currentMatch);
		StudentsOnCard = new ArrayList<Color>();
	}

	/**
	 * Add a student on these cards invoking the method FillCard in the main class Match.
	 */
	public void AddStudent() {
		Color StudentColor = getMatch().getBag().FillCard();
		StudentsOnCard.add(StudentColor);
	}

	/**
	 * Return the number of students on the card.
	 *
	 * @return the number of students on the card.
	 */
	public int getNumberOfStudents() {
		return StudentsOnCard.size();
	}

	/**
	 * It extracts the student to the index passed by parameter from the ArrayList StudentsOnCard,
	 * removes it from the ArrayList and returns its color.
	 *
	 * @param index: the index of a student.
	 * @return the color of the student extracted from the Arraylist StudentsOnCard.
	 */
	public Color RemoveStudent(int index) {
		Color StudentColor = StudentsOnCard.get(index);
		StudentsOnCard.remove(index);
		return StudentColor;
	}

	/**
	 * Return the color of the student to the index passed by parameter extracted from the ArrayList StudentsOnCard.
	 *
	 * @param index: the index of a student.
	 * @return the color of the student to the index from the Arraylist StudentsOnCard.
	 */
	public Color GetStudentColor(int index) {
		Color StudentColor = StudentsOnCard.get(index);
		return StudentColor;
	}
}
