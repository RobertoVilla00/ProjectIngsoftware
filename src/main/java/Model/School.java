package Model;

import java.util.ArrayList;

public class School {

	private ArrayList<Color> Entrance;

	private int GreenStudents;

	private int RedStudents;

	private int YellowStudents;

	private int PinkStudents;

	private int BlueStudents;

	public School() {
		Entrance=new ArrayList<Color>();
		this.GreenStudents=0;
		this.RedStudents=0;
		this.YellowStudents=0;
		this.PinkStudents=0;
		this.BlueStudents=0;
	}

	public void AddEntranceStudents(Color StudentColor) {
		Entrance.add(StudentColor);
	}

	public void MoveStudentToDiningRoom(int index) {
		Color StudentColor=Entrance.get(index);
		switch (StudentColor){
			case GREEN:GreenStudents++;
			case RED:RedStudents++;
			case BLUE:BlueStudents++;
			case PINK:PinkStudents++;
			case YELLOW:YellowStudents++;
		}
		Entrance.remove(index);
	}

	public Color MoveStudentToIsland(int index) {
		Color StudentColor=Entrance.get(index);
		Entrance.remove(index);
		return StudentColor;
	}

	public int getStudentNumber(Color StudentColor) {
		int StudentNumber = 0;
		switch (StudentColor){
			case YELLOW:StudentNumber = YellowStudents;
			case PINK:StudentNumber = PinkStudents;
			case BLUE:StudentNumber = BlueStudents;
			case RED:StudentNumber = RedStudents;
			case GREEN:StudentNumber = GreenStudents;
		}
		return StudentNumber;
	}

}
