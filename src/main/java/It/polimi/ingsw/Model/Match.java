package It.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Match {

	private int NumberOfPlayers;

	private Player[] Players;

	private ArrayList<Island> Table;

	private CharacterCard[] CharacterCardOnTable;

	private CharacterCardDeck CharacterDeck;

	private ArrayList<Cloud> Clouds;

	private	int MotherNaturePosition = 0;

	private Bag bag;

	private ArrayList<Teacher> Teachers;

	public Match(int numberOfPlayers) {
		this.NumberOfPlayers = numberOfPlayers;

		bag = new Bag(2);
		Clouds = new ArrayList<Cloud>();
		Table = new ArrayList<Island>();
		Teachers = new ArrayList<Teacher>();
		CharacterDeck = new CharacterCardDeck(this);
		CharacterCardOnTable = new CharacterCard[3];
		Players = new Player[numberOfPlayers];

		for(int i=0; i<3; i++){										//initialize CharacterCardOnTable
			CharacterCardOnTable[i] = CharacterDeck.SelectCard();
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

		if(numberOfPlayers == 2)
		{
			//getname 															??????
			//temporary name String
			String name1 = "examplePlayer1";					//initialize players
			Players[0] = new Player(TowerColor.WHITE, name1);
			String name2 = "examplePlayer2";
			Players[1] = new Player(TowerColor.BLACK, name2);

			for(int j=0; j<2; j++){								//initialize Clouds
				Clouds.add(new Cloud(j));
			}
		}
		else if(numberOfPlayers == 3){
			String name1 = "examplePlayer1";					//initialize players
			Players[0] = new Player(TowerColor.WHITE, name1);
			String name2 = "examplePlayer2";
			Players[1] = new Player(TowerColor.BLACK, name2);
			String name3 = "examplePlayer3";
			Players[2] = new Player(TowerColor.GREY, name3);

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
	}

	public void MoveMotherNature(int movements){
		this.MotherNaturePosition=MotherNaturePosition+movements;
		if(MotherNaturePosition>=Table.size()){							//secure the circularity of the table
			MotherNaturePosition=MotherNaturePosition-Table.size();
		}
		Table.get(MotherNaturePosition).ResolveMotherNature();
	}

	public void GetStudentsFromCloud(int PlayerIndex,int CloudIndex ){
		Color StudentColor;
		int NumberOfStudents=getClouds().get(CloudIndex).CloudSize();
		for(int i=0;i<NumberOfStudents;i++) {
			StudentColor = getClouds().get(CloudIndex).MoveStudentFromCloud(i);
			Players[PlayerIndex].getPlayersSchool().AddEntranceStudents(StudentColor);
		}
	}

	public void MoveStudentsFromEntrance(String Decision,int StudentIndex,int IslandIndex,int PlayerIndex){

		Color StudentColor;

		if(Decision.equals("DiningRoom")){
			Players[PlayerIndex].getPlayersSchool().MoveStudentToDiningRoom(StudentIndex);
		}
		if (Decision.equals(("Island"))){
			StudentColor = Players[PlayerIndex].getPlayersSchool().MoveStudentToIsland(StudentIndex);
			Table.get(IslandIndex).AddStudent(StudentColor);
		}
	}

	public void SortPlayersByOrderValue(){
		Arrays.sort(Players);
	}

	public void ResolveCard4(Color StudentColor) {
		for (Player p : Players) {
			for (int i = 0; i < 3; i++) {
				if (p.getPlayersSchool().getStudentNumber(StudentColor) != 0) {
					p.getPlayersSchool().RemoveStudentFromDiningRoom(StudentColor);
					bag.AddStudent(StudentColor);
				}
			}
		}
	}

	public void ResolveCard5(int index){
		Table.get(index).setNoEntryTile();
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
}

