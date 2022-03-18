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

	private boolean NoEntryTile;

	public Island() {
		this.YellowStudents=0;
		this.GreenStudents=0;
		this.PinkStudents=0;
		this.RedStudents=0;
		this.BlueStudents=0;
		this.NumberOfTowers=0;
		this.NoEntryTile=false;
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

	public void BuildTower(TowerColor Color) {
		if (NumberOfTowers == 0) {
			NumberOfTowers++;
		}
		TowersColor= Color;
	}

	public void IncreasedTower(){
		 NumberOfTowers++;
	}

	public int CountStudents(Color StudentColor) {
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
