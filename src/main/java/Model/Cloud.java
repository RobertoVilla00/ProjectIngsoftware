package Model;

import java.util.ArrayList;

public class Cloud {

	private int IdCloud;

	private ArrayList<Color> CloudStudents;

	public Cloud(int Id){
			IdCloud = Id;
			CloudStudents = new ArrayList<Color>();
	}

	public int getIdCloud(){
		return this.IdCloud;
	}

	public void AddStudent(Color StudentColor) {
		CloudStudents.add(StudentColor);
	}

	public Color MoveStudentsFromCloud(int index) {
		Color StudentColor = CloudStudents.get(index);
		CloudStudents.remove(index);
		return StudentColor;
	}

}
