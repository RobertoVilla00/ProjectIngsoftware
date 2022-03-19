package Model;

import java.util.ArrayList;

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

		bag = new Bag();
		Clouds = new ArrayList<Cloud>();
		Table = new ArrayList<Island>();
		Teachers = new ArrayList<Teacher>();
		CharacterDeck = new CharacterCardDeck();

		for(int i=0; i<3; i++){										//initialize CharacterCardOnTable
			CharacterCardOnTable[i] = CharacterDeck.SelectCard();
		}

		for (int i = 0; i < 12; i++) {                              //initialize Table (create islands)
			Table.add(new Island());
		}
		for (int i = 1; i < 12; i++) {								//fill Table at start
			if(i !=6) MoveStudentsBagToIsland(i);
		}

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

	public void MoveStudentsBagToIsland(int index){
		Color StudentColor = bag.FillIsland();
		Table.get(index).AddStudent(StudentColor);
	}

	public void MoveStudentsFromEntrance(){
		String Decision;
		int StudentIndex;
		int IslandIndex;
		Color StudentColor;

		for(int i=0; i<NumberOfPlayers; i++){
			for(int j=0; j<3; j++) {
				//player chooses where to move student, gives index        ?????
				Decision = "DiningRoom"; //example
				if(Decision.equals("DiningRoom")){
					//player gives StudentIndex                                  ??????
					StudentIndex = 2;  //example
					Players[i].getPlayersSchool().MoveStudentToDiningRoom(StudentIndex);
				}
				if(Decision.equals("Island")) {
					//player gives StudentIndex and IslandIndex				?????
					StudentIndex = 2;  //example
					IslandIndex = 3; 	//example
					StudentColor = Players[i].getPlayersSchool().MoveStudentToIsland(StudentIndex);
					Table.get(IslandIndex).AddStudent(StudentColor);
				}
			}

		}
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
		for(int i=0;i<NumberOfPlayers; i++){
			int CardIndex = i;    					//example, how to get card index ????
			Players[i].PlayAssistantCard(CardIndex);
		}
		//sorting method missing here
	}

	public void ActionPhase() {

	}



	public void AddCharacterCard() {

	}


}
