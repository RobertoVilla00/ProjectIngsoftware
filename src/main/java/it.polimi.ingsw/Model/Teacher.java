package it.polimi.ingsw.Model;


public class Teacher {

	private Color TeacherColor;

	private int HighestNumberOfStudents;

	private Player ControllingPlayer;

	public Teacher(Color teacherColor) {
		TeacherColor = teacherColor;
		HighestNumberOfStudents = 0;
	}

	public TowerColor getControllingPlayerColor(){
		return this.ControllingPlayer.getPlayerColor();
	}

	public int getHighestNumberOfStudents() {   //gives the attribute to Match
		return this.HighestNumberOfStudents;
	}

	public void IncreaseHighestNumberOfStudents(){
		HighestNumberOfStudents++;
	}

	public void ChangeController(Player NewControllingPlayer) { //changes the attribute if requested by Match
		ControllingPlayer = NewControllingPlayer;
	}

}
