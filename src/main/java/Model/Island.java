package Model;

public class Island {

	private int IdIsland;

	private int GreenStudents;

	private int RedStudents;

	private int YellowStudents;

	private int PinkStudents;

	private int BlueStudents;

	private int NumberOfTowers;

	private TowerColor TowersColor;

	private boolean HasMotherNature;

	public Island() {
		this.YellowStudents=0;
		this.GreenStudents=0;
		this.PinkStudents=0;
		this.RedStudents=0;
		this.BlueStudents=0;
		this.NumberOfTowers=0;
		this.HasMotherNature=false;
	}

	public void AddStudent(Color StudentColor) {
		switch (StudentColor){
			case GREEN:GreenStudents++;
			case RED:RedStudents++;
			case BLUE:BlueStudents++;
			case PINK:PinkStudents++;
			case YELLOW:YellowStudents++;
		}
	}

	public void BuilldTower() {
		NumberOfTowers++;
	}
	public int CountStudents() {
		return GreenStudents+YellowStudents+PinkStudents+RedStudents+BlueStudents;
	}

}
