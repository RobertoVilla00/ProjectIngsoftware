package Model;

public class Teacher {

	private Color TeacherColor;

	private int HighestNumberOfStudents;

	private TowerColor ControllingPlayer;

	public Teacher(Color teacherColor) {
		TeacherColor = teacherColor;
		HighestNumberOfStudents = 0;
	}

	public int getHighestNumberOfStudents() {   //gives the attribute to Match
		return this.HighestNumberOfStudents;
	}

	public void ChangeController(TowerColor NewControllingPlayer) { //changes the attribute if requested by Match
		ControllingPlayer = NewControllingPlayer;
	}

}
