package It.polimi.ingsw.Message;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Model.Color;

/**
 * The message for Character Card with id 12.
 */
public class Card12Message extends Message {
	private static final long serialVersionUID = 1L;

	private Color StudentColor;

    /**
     * The constructor of the message for Character Card with id 12.
     * @param studentColor: the color of a student.
     */
	public Card12Message(String studentColor) {
		super(MessageContent.CARD12);

		studentColor = studentColor.toUpperCase();
		switch (studentColor) {
			case ("GREEN"):
				this.StudentColor = Color.GREEN;
				break;
			case ("YELLOW"):
				this.StudentColor = Color.YELLOW;
				break;
			case ("RED"):
				this.StudentColor = Color.RED;
				break;
			case ("BLUE"):
				this.StudentColor = Color.BLUE;
				break;
			case ("PINK"):
				this.StudentColor = Color.PINK;
				break;

		}
	}

    /**
     * Return the color of the student.
     * @return the color of the student.
     */
	public Color getStudentColor() {
		return StudentColor;
	}
}
