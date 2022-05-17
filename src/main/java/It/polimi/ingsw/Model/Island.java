package It.polimi.ingsw.Model;


/**
 * This class represents an island
 */
public class Island {


	private int GreenStudents;

	private int RedStudents;

	private int YellowStudents;

	private int PinkStudents;

	private int BlueStudents;

	private int NumberOfTowers;

	private TowerColor TowersColor;

	private boolean NoEntryTile;

	/**
	 * Constructor of an island
	 */
	public Island() {
		this.YellowStudents=0;
		this.GreenStudents=0;
		this.PinkStudents=0;
		this.RedStudents=0;
		this.BlueStudents=0;
		this.NumberOfTowers=0;
		this.NoEntryTile=false;
	}

	/**
	 * Increment by one the number of student of this color in this island
	 * @param StudentColor: the color of a student
	 */
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

	/**
	 * Return the number of green students in this island
	 * @return the number of green students
	 */
	public int getGreenStudents() {
		return GreenStudents;
	}

	/**
	 * Return the number of red students in this island
	 * @return the number of red Students
	 */
	public int getRedStudents() {
		return RedStudents;
	}

	/**
	 * Return the number of yellow students in this island
	 * @return the number of yellow Students
	 */
	public int getYellowStudents() {
		return YellowStudents;
	}

	/**
	 * Return the number of pink students in this island
	 * @return the number of pink Students
	 */
	public int getPinkStudents() {
		return PinkStudents;
	}

	/**
	 * Return the number of blue students in this island
	 * @return the number of blue Students
	 */
	public int getBlueStudents() {
		return BlueStudents;
	}

	/**
	 * Return the number of towers in this island
	 * @return the number of towers
	 */
	public int getNumberOfTowers() {
		return NumberOfTowers;
	}

	/**
	 * If the number of towers of this color is zero, increment their number by one. Associate this color to the Attibute TowersColor
	 * @param Color: the color of a tower
	 */
	public void BuildTower(TowerColor Color) {
		if (NumberOfTowers == 0) {
			NumberOfTowers++;
		}
		TowersColor= Color;
	}

	public boolean SameTowerColor(TowerColor PlayerColor){
		if(!isEmpty() && TowersColor == PlayerColor){
			return true;
		}
		else return false;
	}

	public void ResetNoEntryTile(){
		this.NoEntryTile = false;
	}

	public boolean isEmpty(){
		if(NumberOfTowers==0) return true;
		else return false;
	}

	public TowerColor getTowersColor(){
		return TowersColor;
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

	public boolean GetNoEntryTile(){return this.NoEntryTile;}


}
