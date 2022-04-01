package It.polimi.ingsw.Model;


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
			break;
			case RED:RedStudents++;
			break;
			case BLUE:BlueStudents++;
			break;
			case PINK:PinkStudents++;
			break;
			case YELLOW:YellowStudents++;
			break;
		}
		Entrance.remove(index);
	}

	public void RemoveStudentFromDiningRoom(Color StudentColor) {
		switch (StudentColor){
			case GREEN:GreenStudents--;
			break;
			case RED:RedStudents--;
			break;
			case BLUE:BlueStudents--;
			break;
			case PINK:PinkStudents--;
			break;
			case YELLOW:YellowStudents--;
			break;
		}
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
			break;
			case PINK:StudentNumber = PinkStudents;
			break;
			case BLUE:StudentNumber = BlueStudents;
			break;
			case RED:StudentNumber = RedStudents;
			break;
			case GREEN:StudentNumber = GreenStudents;
			break;
		}
		return StudentNumber;
	}

	public void AddStudentToDiningRoom(Color StudentColor){
		switch (StudentColor){
			case GREEN:GreenStudents++;
				break;
			case RED:RedStudents++;
				break;
			case BLUE:BlueStudents++;
				break;
			case PINK:PinkStudents++;
				break;
			case YELLOW:YellowStudents++;
				break;
		}
	}

	public Color RemoveStudentFromEntrance(int index){
		Color StudentColor;
		StudentColor=Entrance.get(index);
		Entrance.remove(index);
		return StudentColor;
	}

	public Color GetEntranceStudentColor (int index){
		Color StudentColor;
		StudentColor=Entrance.get(index);
		return StudentColor;
	}
}
