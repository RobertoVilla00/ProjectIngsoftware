package It.polimi.ingsw.Model;


import java.util.ArrayList;

/**
 * This class represents the cloud
 */
public class Cloud {

	private int IdCloud;

	private ArrayList<Color> CloudStudents;

	/**
	 * Constructor of the cloud. It create the cloud with that id
	 * @param Id: the id of the island
	 */
	public Cloud(int Id){
			IdCloud = Id;
			CloudStudents = new ArrayList<Color>();
	}

	/**
	 * Return the id of the cloud
	 * @return the id of the cloud
	 */
	public int getIdCloud(){
		return this.IdCloud;
	}

	/**
	 * Add a students of that color to the cloud
	 * @param StudentColor: the color of a student
	 */
	public void AddStudent(Color StudentColor) {
		CloudStudents.add(StudentColor);
	}

	/**
	 * Remove the student of that index from the cloud and return its color
	 * @param index: the index of the student
	 * @return the color of the student which is removed
	 */
	public Color MoveStudentFromCloud(int index) {
		Color StudentColor = CloudStudents.get(index);
		CloudStudents.remove(index);
		return StudentColor;
	}

	/**
	 * Return the Arraylist of students
	 * @return the ArrayList of students
	 */
	public ArrayList<Color> getCloudStudents(){
		return this.CloudStudents;
	}

	/**
	 * Return the number of students of that color in this cloud
	 * @param color: the color of a student
	 * @return the number of students of that color
	 */
	public int countStudents(Color color){
		int students=0;
		for(Color c:CloudStudents){
			if(c==color){
				students++;
			}
		}
		return students;
	}

	/**
	 * Return the number of students in this cloud
	 * @return the number of students in this cloud
	 */
	public int CloudSize(){
		return this.CloudStudents.size();
	}
}
