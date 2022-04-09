package It.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Match {

	private int NumberOfPlayers;

	private boolean ExpertMode;

	private Player[] Players;

	private ArrayList<Island> Table;

	private CharacterCard[] CharacterCardOnTable;

	private CharacterCardDeck CharacterDeck;

	private ArrayList<Cloud> Clouds;

	private	int MotherNaturePosition = 0;

	private Bag bag;

	private ArrayList<Teacher> Teachers;

	private boolean PlaysCard6;

	public Match(int numberOfPlayers, int GameMode) {
		this.NumberOfPlayers = numberOfPlayers;

		if(GameMode == 1) this.ExpertMode = true;
			else this.ExpertMode = false;

		bag = new Bag(2);
		Clouds = new ArrayList<Cloud>();
		Table = new ArrayList<Island>();
		Teachers = new ArrayList<Teacher>();


		Players = new Player[numberOfPlayers];

		CharacterCardOnTable = new CharacterCard[3];

		PlaysCard6 = false;
		if(GameMode == 1) {
			CharacterDeck = new CharacterCardDeck(this);
			CharacterDeck.ShuffleCharacterCardDeck();
			for (int i = 0; i < 3; i++) {                                        //initialize CharacterCardOnTable
				CharacterCardOnTable[i] = CharacterDeck.SelectCard(0);
			}
		}

		for (int i = 0; i < 12; i++) {                              //initialize Table (create islands)
			Table.add(new Island());
		}
		for (int i = 1; i < 12; i++) {								//fill Table at start
			if(i !=6) MoveStudentsBagToIsland(i);
		}
		bag = new Bag(24);

		Teachers.add(new Teacher(Color.BLUE));						//initialize Teachers
		Teachers.add(new Teacher(Color.GREEN));
		Teachers.add(new Teacher(Color.YELLOW));
		Teachers.add(new Teacher(Color.PINK));
		Teachers.add(new Teacher(Color.RED));

		Color studentColor;
		if(numberOfPlayers == 2)
		{
			//getname 															??????
			//temporary name String
			String name1 = "examplePlayer1";					//initialize players
			Players[0] = new Player(TowerColor.WHITE, name1,0);
			for(int i=0;i<7;i++) {
				studentColor=bag.getFirstElement();
				bag.RemoveFirstElement();
				Players[0].getPlayersSchool().AddEntranceStudents(studentColor);
			}
			String name2 = "examplePlayer2";
			Players[1] = new Player(TowerColor.BLACK, name2,1);
			for(int i=0;i<7;i++) {
				studentColor=bag.getFirstElement();
				bag.RemoveFirstElement();
				Players[1].getPlayersSchool().AddEntranceStudents(studentColor);
			}

			for(int j=0; j<2; j++){								//initialize Clouds
				Clouds.add(new Cloud(j));
			}
		}
		else if(numberOfPlayers == 3){
			String name1 = "examplePlayer1";					//initialize players
			Players[0] = new Player(TowerColor.WHITE, name1,0);
			for(int i=0;i<9;i++) {
				studentColor=bag.getFirstElement();
				bag.RemoveFirstElement();
				Players[0].getPlayersSchool().AddEntranceStudents(studentColor);
			}
			String name2 = "examplePlayer2";
			Players[1] = new Player(TowerColor.BLACK, name2,1);
			for(int i=0;i<9;i++) {
				studentColor=bag.getFirstElement();
				bag.RemoveFirstElement();
				Players[1].getPlayersSchool().AddEntranceStudents(studentColor);
			}
			String name3 = "examplePlayer3";
			Players[2] = new Player(TowerColor.GREY, name3,2);
			for(int i=0;i<9;i++) {
				studentColor=bag.getFirstElement();
				bag.RemoveFirstElement();
				Players[2].getPlayersSchool().AddEntranceStudents(studentColor);
			}

			for(int j=0; j<3; j++) {                                //initialize Clouds
				Clouds.add(new Cloud(j));
			}
		}
	}

	public Bag getBag(){
		return this.bag;
	}


	public void MoveStudentsBagToIsland(int index){
		Color StudentColor = bag.FillIsland();
		Table.get(index).AddStudent(StudentColor);
	}

	public void MoveStudentsBagToCloud(int index){
		if(NumberOfPlayers == 2) {
			for (int i = 0; i < 3; i++) {
				Color StudentColor = bag.FillClouds();
				Clouds.get(index).AddStudent(StudentColor);
			}
		}
		else if(NumberOfPlayers == 3){
			for(int i=0; i<4; i++) {
				Color StudentColor = bag.FillClouds();
				Clouds.get(index).AddStudent(StudentColor);
			}
		}
	}

	public Player getPlayerById(int PlayerId){
		return this.Players[PlayerId];
	}

	public void PlanningPhase() {
		for(int i=0; i<NumberOfPlayers; i++) {
			MoveStudentsBagToCloud(i);
		}
	}

	public void PlayAssistantCard(int CardIndex,int PlayerIndex){
		Players[PlayerIndex].PlayAssistantCard(CardIndex);
	}

	public void MergeIslands(int IslandIndex, int IslandToMergeIndex) {
		int StudentNumber;
		int TowersNumber;
		StudentNumber = Table.get(IslandToMergeIndex).CountStudents(Color.GREEN); //update students of the island
		for(int i=0;i<StudentNumber;i++){
			Table.get(IslandIndex).AddStudent(Color.GREEN);
		}
		StudentNumber = Table.get(IslandToMergeIndex).CountStudents(Color.RED);
		for(int i=0;i<StudentNumber;i++){
			Table.get(IslandIndex).AddStudent(Color.RED);
		}
		StudentNumber = Table.get(IslandToMergeIndex).CountStudents(Color.BLUE);
		for(int i=0;i<StudentNumber;i++){
			Table.get(IslandIndex).AddStudent(Color.BLUE);
		}
		StudentNumber = Table.get(IslandToMergeIndex).CountStudents(Color.YELLOW);
		for(int i=0;i<StudentNumber;i++){
			Table.get(IslandIndex).AddStudent(Color.YELLOW);
		}
		StudentNumber = Table.get(IslandToMergeIndex).CountStudents(Color.PINK);
		for(int i=0;i<StudentNumber;i++){
			Table.get(IslandIndex).AddStudent(Color.PINK);
		}

		TowersNumber = Table.get(IslandToMergeIndex).CountTowers();				//update towers of the island
		for(int i=0;i<TowersNumber;i++){
			Table.get(IslandIndex).IncreaseTower();
		}
		Table.remove(IslandToMergeIndex);
		int MaximumIndex;
		if(IslandIndex<IslandToMergeIndex){
			MaximumIndex=IslandToMergeIndex;
		}
		else{
			MaximumIndex=IslandIndex;
		}
		if(this.MotherNaturePosition>=MaximumIndex){
			if(MotherNaturePosition==Table.size() && IslandToMergeIndex== Table.size()){				//the island is already removed so size is reduced by 1
				this.MotherNaturePosition=0;
			}
			else {
				this.MotherNaturePosition--;
			}
		}
		else if(this.MotherNaturePosition==0 && IslandToMergeIndex==0 && IslandIndex==Table.size()){
			this.MotherNaturePosition= Table.size();
		}
	}

	public void MoveMotherNature(int movements){
		this.MotherNaturePosition=MotherNaturePosition+movements;
		if(MotherNaturePosition>=Table.size()){							//secure the circularity of the table
			MotherNaturePosition=MotherNaturePosition-Table.size();
		}
	}

	public void MoveStudentsFromCloudToEntrance(int PlayerIndex, int CloudIndex ){
		Color StudentColor;
		int NumberOfStudents=getClouds().get(CloudIndex).CloudSize();
		for(int i=0;i<NumberOfStudents;i++) {
			StudentColor = getClouds().get(CloudIndex).MoveStudentFromCloud(0);
			Players[PlayerIndex].getPlayersSchool().AddEntranceStudents(StudentColor);
		}
	}

	public void MoveStudentsFromEntranceToDiningRoom(int StudentIndex, int PlayerIndex){
		Players[PlayerIndex].getPlayersSchool().MoveStudentToDiningRoom(StudentIndex);
	}

	public void MoveStudentsFromEntranceToIsland(int StudentIndex, int PlayerIndex, int IslandIndex){
		Color StudentColor = Players[PlayerIndex].getPlayersSchool().MoveStudentToIsland(StudentIndex);
		Table.get(IslandIndex).AddStudent(StudentColor);
	}

	public void SortPlayersByOrderValue(){
		Arrays.sort(Players);
	}

	public int getMotherNaturePosition() {
		return MotherNaturePosition;
	}

	public ArrayList<Island> getTable(){
		return this.Table;
	}

	public Player[] getPlayers(){
		return this.Players;
	}

	public ArrayList<Cloud> getClouds(){
		return this.Clouds;
	}

	public ArrayList<Teacher> getTeachers(){return this.Teachers;}

	public Teacher getTeacherByColor(Color color){
		int index=0;
		for(int i=0;i<5;i++){
			Color TeacherColor=Teachers.get(i).getTeacherColor();
			if (TeacherColor==color){
				index=i;
				break;
			}
		}
		return Teachers.get(index);
	}

	public boolean isCharacterCardOnTable(int CharacterCardId){
		for(int i=0;i<3;i++){
			if(CharacterCardId == CharacterCardOnTable[i].getIdCharacterCard()){    //if id matches
				return true;
			}
		}
		return false;
	}

	public CharacterCard getCharacterCardById(int CharacterCardId){
		int index=0;
		for(int i=0;i<3;i++){
			if(CharacterCardId == CharacterCardOnTable[i].getIdCharacterCard()){    //if id matches
				index = i;
				break;
			}
		}
		return CharacterCardOnTable[index];
	}

	public Player getPlayerByTowerColor(TowerColor towerColor){
		int index=0;
		for(int i=0; i<NumberOfPlayers;i++){
			TowerColor PlayerTowerColor=Players[i].getPlayerColor();
			if(PlayerTowerColor==towerColor){
				index=i;
				break;
			}
		}
		return Players[index];
	}

	public boolean isExpertMode(){
		return ExpertMode;
	}

	public boolean getPlaysCard6(){
		return PlaysCard6;
	}

	public void setPlaysCard6(boolean value){
		PlaysCard6 = value;
	}

	public int getNumberOfPlayers(){
		return this.NumberOfPlayers;
	}

	public CharacterCard[] getCharacterCardsOnTable(){
		return this.CharacterCardOnTable;
	}
}

