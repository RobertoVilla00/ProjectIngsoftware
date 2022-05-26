package It.polimi.ingsw.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This class represents the bag of the game.
 */
public class Bag implements Serializable {
	private static final long serialVersionUid = 1L;
	private ArrayList<Color> Students;


	/**
	 * Constructor of the bag.It fill the bag with a number of each color students indicate by the parameter dimension.
	 *
	 * @param dimension: the dimension of the bag.
	 */
	public Bag(int dimension) {
		Students = new ArrayList<Color>();
		for (int i = 0; i < dimension; i++) {
			Students.add(Color.GREEN);
		}
		for (int i = 0; i < dimension; i++) {
			Students.add(Color.BLUE);
		}
		for (int i = 0; i < dimension; i++) {
			Students.add(Color.YELLOW);
		}
		for (int i = 0; i < dimension; i++) {
			Students.add(Color.PINK);
		}
		for (int i = 0; i < dimension; i++) {
			Students.add(Color.RED);
		}
		this.ShuffleBag();
	}


	/**
	 * Return color of the first student extracted from the bag.
	 *
	 * @return color of the first student extracted from the bag.
	 */
	public Color getFirstElement() {
		return Students.get(0);
	}

	/**
	 * This method remove the first student from the bag.
	 */
	public void RemoveFirstElement() {
		Students.remove(0);
	}

	/**
	 * Remove the first student extracted from the bag and return the color of that student.
	 *
	 * @return color of the first student extracted from the bag.
	 */
	public Color FillClouds() {
		Color StudentColor = getFirstElement();
		RemoveFirstElement();
		return StudentColor;
	}

	/**
	 * Remove the first student extracted from the bag and return the color of that student.
	 *
	 * @return color of the first student extracted from the bag.
	 */
	public Color FillIsland() {
		Color StudentColor = getFirstElement();
		RemoveFirstElement();
		return StudentColor;

	}

	/**
	 * Remove the first student extracted from the bag and return the color of that student.
	 *
	 * @return color of the first student extracted from the bag.
	 */
	public Color FillCard() {
		Color StudentColor = getFirstElement();
		RemoveFirstElement();
		return StudentColor;

	}

	/**
	 * Shuffle the students in the bag.
	 */
	public void ShuffleBag() {
		Collections.shuffle(Students);
	}

	/**
	 * Add a students to the bag and shuffle the students in the bag.
	 *
	 * @param color: the color of a student.
	 */
	public void AddStudent(Color color) {
		Students.add(color);
		this.ShuffleBag();
	}

	/**
	 * Return the number of the students in the bag.
	 *
	 * @return the number of the students in the bag.
	 */
	public int BagSize() {
		return Students.size();
	}
}
