package it.polimi.ingsw.Model;


public class Island {

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

	public void BuildTower(TowerColor Color) {
		if (NumberOfTowers == 0) {
			NumberOfTowers++;
		}
		TowersColor= Color;
	}

	public void IncreaseTower(){
		 NumberOfTowers++;
	}

	public int CountTowers(){
		return NumberOfTowers;
	}

	public int CountStudents(Color StudentColor) {
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

	public void setNoEntryTile(){
		this.NoEntryTile=true;
	}
}
