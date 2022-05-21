package It.polimi.ingsw.Model;

import It.polimi.ingsw.Controller.GamePhase;
import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Observer.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the main class of the game, which contains the main methods.
 */
public class Match extends Observable implements Serializable {
	private static final long serialVersionUID=1L;

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

	private int ActivePlayerId;

	private GamePhase gamePhase;

	private int ExpectedCardMessage;

	private boolean PlaysCard6;

	/**
	 * Constructor of the class Match. This method manages the start of the game, takes care of filling the bag with the students,
	 * filling the Entrance of the school for each player, preparing the table of islands, clouds, schools,
	 * initializing the players, the professors, the Character Card.
	 * Handles the case in which there are 2 or 3 players or you choose to play with the variant for experts.
	 * @param numberOfPlayers: the number of players (2 or 3) in the game.
	 * @param GameMode: 1 for the variant for experts, 0 for classic game.
	 */
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

		for (int i = 0; i < 12; i++) {                              //initialize Table (create islands)
			Table.add(new Island());
		}
		for (int i = 1; i < 12; i++) {								//fill Table at start
			if(i !=6) MoveStudentsBagToIsland(i);
		}
		bag = new Bag(24);


		if(GameMode == 1) {
			this.InitializeCharacterCardOnTable();
		}

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

	/**
	 * Return the bag containing the students.
	 * @return the bag containing the students.
	 */
	public Bag getBag(){
		return this.bag;
	}


	/**
	 * Add the student extracted from the bag to the island at the given index.
	 * @param index: the index of an island in the table.
	 */
	public void MoveStudentsBagToIsland(int index){
		Color StudentColor = bag.FillIsland();
		Table.get(index).AddStudent(StudentColor);
	}

	/**
	 * If the number of players is 2 it fills the cloud at the given index with 3 students,
	 * if the number of players is 3 it fills it with 4 students.
	 * @param index: the index of a cloud.
	 */
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

	/**
	 * Return the player at the given index in the array Players.
	 * @param PlayerId: the Id of a player.
	 * @return the player at the given Id.
	 */
	public Player getPlayerById(int PlayerId){
		return this.Players[PlayerId];
	}

	/**
	 * It fills 2 clouds if there are two players, 3 clouds if there are three players invoking the method MoveStudentsBagToCloud.
	 */
	public void PlanningPhase() {
		for(int i=0; i<NumberOfPlayers; i++) {
			MoveStudentsBagToCloud(i);
		}
	}

	/**
	 * Its sets the attribute gamePhase.
	 * @param gamePhase: it is a phase of the game.
	 */
	public void setGamePhase(GamePhase gamePhase){
		this.gamePhase = gamePhase;
	}

	/**
	 * Return the phase of the current match.
	 * @return the phase of the current match.
	 */
	public GamePhase getGamePhase(){
		return this.gamePhase;
	}

	/**
	 * It sets the attribute ExpectedCardMessage.
	 * @param id: the Id of a card.
	 */
	public void setExpectedCardMessage(int id){
		this.ExpectedCardMessage=id;
	}

	/**
	 * It used when a player want to play an Assistant Card invoking the method PlayAssistantCard in the class Player.
	 * @param CardIndex: the index of an Assistant Card.
	 * @param PlayerIndex: the index of the player in the array Players who play the Assistant Card.
	 */
	public void PlayAssistantCard(int CardIndex,int PlayerIndex){
		Players[PlayerIndex].PlayAssistantCard(CardIndex);
	}

	/**
	 * It used when you need to unify two islands after a player builds two towers of the same color on two adjacent islands.
	 * It unify the two islands, moves all students, towers, mother nature to a single island
	 * and removes from the ArrayList Table the island that has been unified.
	 * @param IslandIndex: the index of an island in the ArrayList table.
	 * @param IslandToMergeIndex: the index of an island in the ArrayList table.
	 */
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

	/**
	 * It used when the player have to move Mother Nature.
	 * It increase the position of Mother Nature by the number received as parameter.
	 * To secure the circularity of the table, when the number indicating the position of Mother Nature is Greater or equal
	 * to the size of table, it decreases the position of Mother Nature by the size of the table.
	 * @param movements: a number indicating the steps that mother nature must take.
	 */
	public void MoveMotherNature(int movements){
		this.MotherNaturePosition=MotherNaturePosition+movements;
		while(MotherNaturePosition>=Table.size()){							//secure the circularity of the table
			MotherNaturePosition=MotherNaturePosition-Table.size();
		}
	}

	/**
	 * It used to move students from the cloud given by parameter to the Entrance of the player given by parameter.
	 * @param PlayerIndex: the index of the player.
	 * @param CloudIndex: the index of the cloud.
	 */
	public void MoveStudentsFromCloudToEntrance(int PlayerIndex, int CloudIndex ){
		Color StudentColor;
		int NumberOfStudents=getClouds().get(CloudIndex).CloudSize();
		for(int i=0;i<NumberOfStudents;i++) {
			StudentColor = getClouds().get(CloudIndex).MoveStudentFromCloud(0);
			Players[PlayerIndex].getPlayersSchool().AddEntranceStudents(StudentColor);
		}
	}

	/**
	 * It initializes the attribute ActivePlayerId at the given id.
	 * @param id: the id of a player.
	 */
	public void setActivePlayerId(int id){
		this.ActivePlayerId = id;
	}

	/**
	 * It used to move students at the given StudentIndex to the Dining Room of the player identified by the PlayerIndex.
	 * If the number of students in the dining room is a multiple of 3 and the game is in the expert mode the player earns a coin.
	 * @param StudentIndex: the index of a student in the Entrance of School.
	 * @param PlayerIndex: the index of a player.
	 */
	public void MoveStudentsFromEntranceToDiningRoom(int StudentIndex, int PlayerIndex){
		Color studentColor= Players[PlayerIndex].getPlayersSchool().GetEntranceStudentColor(StudentIndex);
		Players[PlayerIndex].getPlayersSchool().MoveStudentToDiningRoom(StudentIndex);
		if(Players[PlayerIndex].getPlayersSchool().getStudentNumber(studentColor)%3==0  && ExpertMode){
			Players[PlayerIndex].AddCoin(1);
		}
	}

	/**
	 * Return the attribute ExpectedCardMessage.
	 * @return the id of a card.
	 */
	public int getExpectedCardMessage(){
		return this.ExpectedCardMessage;
	}

	/**
	 * Return the ActivePlayerId.
	 * @return the ActivePlayerId.
	 */
	public int getActivePlayerId(){
		return this.ActivePlayerId;
	}

	/**
	 * Create a showMatchInfoMessage and invoke notifyObserver
	 */
	public void CreateMessage(){
		ShowMatchInfoMessage showMatchInfoMessage= new ShowMatchInfoMessage(this);
		notifyObserver(showMatchInfoMessage);
	}

	/**
	 * It is used to move the student at StudentIndex from the Entrance of the School of the player identified by the PlayerIndex
	 * to the island at IslandIndex.
	 * @param StudentIndex: the index of a student in the Entrance of the School.
	 * @param PlayerIndex: the index of a player in the array Players.
	 * @param IslandIndex: the index of an island in the ArrayList table.
	 */
	public void MoveStudentsFromEntranceToIsland(int StudentIndex, int PlayerIndex, int IslandIndex){
		Color StudentColor = Players[PlayerIndex].getPlayersSchool().MoveStudentToIsland(StudentIndex);
		Table.get(IslandIndex).AddStudent(StudentColor);
	}

	/**
	 * It sorts the order of players in the array Players depending on the value of the Assistant Card played the previous round.
	 */
	public void SortPlayersByOrderValue(){
		Arrays.sort(Players);
	}

	/**
	 * Return the position of Mother Nature.
	 * @return the position of Mother Nature.
	 */
	public int getMotherNaturePosition() {
		return MotherNaturePosition;
	}

	/**
	 * Return the ArrayList of islands.
	 * @return the ArrayList of islands.
	 */
	public ArrayList<Island> getTable(){
		return this.Table;
	}

	/**
	 * Return the array of players.
	 * @return the array of players.
	 */
	public Player[] getPlayers(){
		return this.Players;
	}

	/**
	 * Return the ArrayList of clouds.
	 * @return the ArrayList of clouds.
	 */
	public ArrayList<Cloud> getClouds(){
		return this.Clouds;
	}

	/**
	 * Return the ArrayList of teachers.
	 * @return the Arraylist of teachers.
	 */
	public ArrayList<Teacher> getTeachers(){return this.Teachers;}

	/**
	 * Return the teacher who has the color given by parameter.
	 * @param color: the color of a teacher.
	 * @return the teacher who has the color given by parameter.
	 */
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

	/**
	 * Return true if the Character Card identified by the id receives as parameter is on table, false if not.
	 * @param CharacterCardId: the id of a Character Card.
	 * @return a boolean that indicates if the Character Card is on table or not.
	 */
	public boolean isCharacterCardOnTable(int CharacterCardId){
		for(int i=0;i<3;i++){
			if(CharacterCardId == CharacterCardOnTable[i].getIdCharacterCard()){    //if id matches
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the Character Card which has the id equals to the given id.
	 * @param CharacterCardId: the id of a Character Card.
	 * @return the Character Card which has the id equals to the given id.
	 */
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

	/**
	 * Return the player whose tower's color is equals to the given color.
	 * @param towerColor: the color of a tower.
	 * @return the player whose tower's color is equals to the given color.
	 */
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

	/**
	 * Return true in case of variants for experts, false if not.
	 * @return the mode of the game.
	 */
	public boolean isExpertMode(){
		return ExpertMode;
	}

	/**
	 * Return true if the Card 6 is played, false if not.
	 * @return a boolean that indicates if the Card 6 is played or not.
	 */
	public boolean getPlaysCard6(){
		return PlaysCard6;
	}

	/**
	 * It sets the attribute PlaysCard6 to the value given by parameter.
	 * @param value: it is a boolean.
	 */
	public void setPlaysCard6(boolean value){
		PlaysCard6 = value;
	}

	/**
	 * Return the number of players in the current Match.
	 * @return the number of players in the Match.
	 */
	public int getNumberOfPlayers(){
		return this.NumberOfPlayers;
	}

	/**
	 * Return tha array of Character Card on table.
	 * @return the array of Character Card on table.
	 */
	public CharacterCard[] getCharacterCardsOnTable(){
		return this.CharacterCardOnTable;
	}

	/**
	 * It initializes the Array of Character Card with 3 random cards.
	 */
	public void InitializeCharacterCardOnTable() {
		CharacterDeck = new CharacterCardDeck(this);
		CharacterDeck.ShuffleCharacterCardDeck();
		for (int i = 0; i < 3; i++) {
			CharacterCardOnTable[i] = CharacterDeck.SelectCard(0);
			if(CharacterCardOnTable[i].getIdCharacterCard()==1 || CharacterCardOnTable[i].getIdCharacterCard()==10 ){
				Cards1and10 card=(Cards1and10)CharacterCardOnTable[i];
				for(int j=0;j<4;j++){
					card.AddStudent();
				}
			}
		}
	}

	/**
	 * Return the number of teachers controlled by a certain player
	 * @param player the player we want to know how many teachers control
	 * @return the number of teachers controlled by player
	 */
	public int getTeachersOfPlayer(Player player){
		int numberOfTeachers=0;
		for(Teacher t:this.Teachers){
			if(t.getControllingPlayer()==player)
				numberOfTeachers++;
		}
		return numberOfTeachers;
	}
}

