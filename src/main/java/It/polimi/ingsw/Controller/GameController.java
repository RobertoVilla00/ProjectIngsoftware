package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;
import It.polimi.ingsw.Observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;

/**
 * The controller of the main methods of the game.
 */
public class GameController {

	private Match match;
	private CharacterCardController characterCardController;
	private int activePlayer;
	private int ranking[][];
	/*private View view;*/

	/**
	 * Default constructor.
	 */
	public GameController() {
	}

	/**
	 * It creates a new match receiving the number of players and the game mode.
	 * In case of expert mode, it creates the Character card controller.
	 *
	 * @param startMessage: the message which contains the number of players and the game mode.
	 * @throws InvalidInputException: the number of players can only be 2 or 3 and game mode can only be 0 or 1.
	 */
	public void InitializeGame(StartMessage startMessage) throws InvalidInputException {
		match = new Match(startMessage.getNumberOfPlayers(), startMessage.getGameMode());
		if (startMessage.getNumberOfPlayers() < 2 || startMessage.getNumberOfPlayers() > 3) {
			throw new InvalidInputException("Player Numbers can only be 2 or 3");
		} else {
			if (startMessage.getGameMode() < 0 || startMessage.getGameMode() > 1) {
				throw new InvalidInputException("Game mode can only be 0 or 1");
			} else {
				if (match.isExpertMode()) {
					characterCardController = new CharacterCardController(this);
				}
			}
		}
	}

	/**
	 * It carries out the necessary checks to understand if an island should be unified or not.
	 *
	 * @param IslandIndex: the index of the island that must be checked for a possible unification.
	 */
	public void CheckIslandMerge(int IslandIndex) {

		if (match.getTable().get(IslandIndex).isEmpty()) return;     //if island has no towers end check

		TowerColor CurrentColor = match.getTable().get(IslandIndex).getTowersColor();      //tower color of the island
		int PreviousIslandIndex, NextIslandIndex;


		if (IslandIndex == 0) { //if island is first element of the list
			PreviousIslandIndex = match.getTable().size() - 1; //previous island is last element of the list
			NextIslandIndex = IslandIndex + 1;
		} else if (IslandIndex == match.getTable().size() - 1) { //if island is last element of the list
			PreviousIslandIndex = IslandIndex - 1;
			NextIslandIndex = 0; //next island is first element of the list
		} else {
			PreviousIslandIndex = IslandIndex - 1;
			NextIslandIndex = IslandIndex + 1;
		}

		//Previous island check
		if (match.getTable().get(PreviousIslandIndex).SameTowerColor(CurrentColor)) {
			match.MergeIslands(IslandIndex, PreviousIslandIndex);

			//eventually update indexes
			if (IslandIndex != 0 && IslandIndex != match.getTable().size()) { //if island is not first or last
				IslandIndex--;
				NextIslandIndex--;
			} else if (IslandIndex == match.getTable().size()) { //if island is last
				IslandIndex--;
			}
		}
		//Next island check
		if (match.getTable().get(NextIslandIndex).SameTowerColor(CurrentColor)) {
			match.MergeIslands(IslandIndex, NextIslandIndex);
		}

		//number of islands = 3 -> endgame
		if (match.getTable().size() <= 3) EndGame();
	}

	/**
	 * It checks if the student who moved the student takes possession of the professor.
	 *
	 * @param color:                a color of a teacher.
	 * @param playerPlacingStudent: the player who places a student.
	 */
	public void CheckTeacherControl(Color color, Player playerPlacingStudent) {
		Teacher ColorTeacher = match.getTeacherByColor(color);
		int highestNumberOfStudents = ColorTeacher.getHighestNumberOfStudents();
		if (playerPlacingStudent.getPlayersSchool().getStudentNumber(color) > highestNumberOfStudents) {
			ColorTeacher.IncreaseHighestNumberOfStudents();
			ColorTeacher.ChangeController(playerPlacingStudent);
		}
	}

	/**
	 * It calculates the influence of the island and in case there is no towers it builds the tower.
	 *
	 * @param IslandIndex: the index of an island.
	 */
	public void CheckIslandInfluence(int IslandIndex) {
		if (match.getTable().get(IslandIndex).GetNoEntryTile()) {
			match.getTable().get(IslandIndex).ResetNoEntryTile();
			Card5 card5 = (Card5) match.getCharacterCardById(5);
			card5.AddNoEntryTile();
		} else {
			int InfluencePoints[] = new int[match.getNumberOfPlayers()];
			for (int i = 0; i < match.getNumberOfPlayers(); i++) {
				InfluencePoints[i] = 0;
				if (match.getPlayerById(i).getAdditionalPoints()) {
					InfluencePoints[i] = 2;
					match.getPlayerById(i).setAdditionalPoints(false);
				}
			}
			int numberOfStudents;
			Player TeacherController;
			int ControllingPlayerId;
			for (Color color : Color.values()) {
				numberOfStudents = match.getTable().get(IslandIndex).CountStudents(color);//count the number of students for each color
				if (match.getTeacherByColor(color).getHighestNumberOfStudents() != 0) {
					TeacherController = match.getTeacherByColor(color).getControllingPlayer();
					ControllingPlayerId = TeacherController.getPlayerId();
					InfluencePoints[ControllingPlayerId] += numberOfStudents;
				}
			}
			int numberOfTowers = match.getTable().get(IslandIndex).CountTowers();
			if (numberOfTowers != 0 && !match.getPlaysCard6()) {
				Player TowerController;
				TowerColor towerColor = match.getTable().get(IslandIndex).getTowersColor();
				TowerController = match.getPlayerByTowerColor(towerColor);
				ControllingPlayerId = TowerController.getPlayerId();
				InfluencePoints[ControllingPlayerId] += numberOfTowers;
			}
			match.setPlaysCard6(false);
			int maximum = 0;
			int idMax = 0;
			boolean isTied = true;
			for (int i = 0; i < match.getNumberOfPlayers(); i++) {
				if (InfluencePoints[i] > maximum) {
					maximum = InfluencePoints[i];
					isTied = false;
					idMax = i;
				} else if (InfluencePoints[i] == maximum) {
					isTied = true;
				}
			}
			if (!isTied) {                //if the count is tied no one build the tower
				TowerColor BuiltTowerColor = match.getPlayerById(idMax).getPlayerColor();         //find color of the tower to build
				match.getTable().get(IslandIndex).BuildTower(BuiltTowerColor);
				match.CountNumberOfTowers();
				checkEndGame();
				CheckIslandMerge(IslandIndex);
			}
		}
	}

	/**
	 * It checks if there are conditions for the end of the game.
	 */
	public void checkEndGame() {
		for (Player p : match.getPlayers()) {
			if (match.getNumberOfPlayers() == 2) {
				if (p.getTowersPlaced() == 8) {
					EndGame();
				}
			}
			if (match.getNumberOfPlayers() == 3) {
				if (p.getTowersPlaced() == 6) {
					EndGame();
				}
			}
		}
	}

	/**
	 * It moves the student depending on the destination on the island or to the dining room.
	 *
	 * @param moveStudentMessage: contains the id of the active player, the index of student in the Entrance and the destination of the student.
	 * @throws InvalidInputException:   in case of invalid destination, if the dining room is full or if the index of student is invalid.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void MoveStudent(MoveStudentMessage moveStudentMessage) throws InvalidInputException, NoActivePlayerException {
		int activePlayerId = getActivePlayer();
		int StudentIndex = moveStudentMessage.getEntrancePosition();
		int Destination = moveStudentMessage.getDestination();

		if (Destination < -1 || Destination >= match.getTable().size()) {
			throw new InvalidInputException("Invalid Destination");
		} else {
			if (StudentIndex >= 0 && StudentIndex < match.getPlayerById(activePlayerId).getPlayersSchool().getEntranceStudentsNumber()) {
				Color StudentColor = match.getPlayerById(activePlayerId).getPlayersSchool().GetEntranceStudentColor(StudentIndex);
				if (Destination == -1) {                     //if destination is not an island
					if (match.getPlayerById(activePlayerId).getPlayersSchool().getStudentNumber(StudentColor) == 10) {
						throw new InvalidInputException("Dining Room is full, you cannot play this Card");
					} else {
						match.MoveStudentsFromEntranceToDiningRoom(StudentIndex, activePlayerId);
						Player player = match.getPlayerById(activePlayerId);
						CheckTeacherControl(StudentColor, player);
					}
				} else {                                        //if destination is an island
					match.MoveStudentsFromEntranceToIsland(StudentIndex, activePlayerId, Destination);
				}
			} else throw new InvalidInputException("Invalid Student index");
		}
	}

	/**
	 * It moves mother nature depending on the number of steps received as a parameter.
	 *
	 * @param motherNatureMessage: it contains the number of steps mother nature must perform.
	 * @throws InvalidInputException:   in case of a negative number of steps, if the number of steps is zero or if the number of steps is too high.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void MoveMotherNature(MotherNatureMessage motherNatureMessage) throws InvalidInputException, NoActivePlayerException {

		if (motherNatureMessage.getSteps() == 0)
			throw new InvalidInputException("MotherNature has to do at least one step");
		else if (motherNatureMessage.getSteps() < 1) throw new InvalidInputException("You can't put a negative number");
		else if (match.getPlayerById(getActivePlayer()).GetPlayedMovements() >= motherNatureMessage.getSteps()) {
			match.MoveMotherNature(motherNatureMessage.getSteps());
			CheckIslandInfluence(match.getMotherNaturePosition());
		} else
			throw new InvalidInputException("Too many steps, max is " + match.getPlayerById(getActivePlayer()).GetPlayedMovements());
	}

	/**
	 * It invokes the method that moves students from cloud given by parameter to Entrance.
	 *
	 * @param cloudChoiceMessage: contains the index of a cloud.
	 * @throws InvalidInputException:   in case of an invalid cloud index.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void ChooseCloud(CloudChoiceMessage cloudChoiceMessage) throws InvalidInputException, NoActivePlayerException {
		if (cloudChoiceMessage.getCloudIndex() > match.getNumberOfPlayers() - 1 || cloudChoiceMessage.getCloudIndex() < 0) {
			throw new InvalidInputException("invalid Cloud index");
		}
		else if (match.getClouds().get(cloudChoiceMessage.getCloudIndex()).CloudSize()==0){
			throw new InvalidInputException("The cloud is empty");
		}
		else match.MoveStudentsFromCloudToEntrance(getActivePlayer(), cloudChoiceMessage.getCloudIndex());
	}


	/**
	 * This method allows you to play an assistant card.
	 *
	 * @param assistantCardMessage: contains the index of the assistant card to be played.
	 * @throws InvalidInputException:   in case of the card's index is invalid or if the assistant card selected is not playable.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayAssistantCard(AssistantCardMessage assistantCardMessage) throws InvalidInputException, NoActivePlayerException {
		int card = assistantCardMessage.getCardIndex();
		boolean notPlayable = false;
		boolean Played = false;

		if (card >= match.getPlayerById(getActivePlayer()).getDeck().CardCount() || card < 0) {
			throw new InvalidInputException("invalid card index");
		}

		for (Player p : match.getPlayers()) {
			if (p == match.getPlayerById(getActivePlayer())) break;
			else {
				if (match.getPlayerById(getActivePlayer()).getDeck().GetCard(card).getOrderValue() == p.GetPlayedOrderValue()) {
					notPlayable = true;
				}
			}
		}
		if (notPlayable) {
			notPlayable = false;
			for (int i = 0; i < match.getPlayerById(getActivePlayer()).getDeck().CardCount(); i++) {
				Played = false;
				for (Player p : match.getPlayers()) {
					if (p == match.getPlayerById(getActivePlayer())) break;
					else {
						if (match.getPlayerById(getActivePlayer()).getDeck().GetCard(i).getOrderValue() == p.GetPlayedOrderValue()) {
							Played = true;
						}
					}
				}
				if (!Played) {
					notPlayable = true;
					break;
				}
			}
		}
		if (notPlayable) throw new InvalidInputException("Selected Assistant Card is not Playable");
		else {
			match.getPlayerById(getActivePlayer()).PlayAssistantCard(card);
		}

	}

	/**
	 * This method allows you to calculate who is the winner of the game by returning the ids of the winners.
	 *
	 * @return an array list with the ids of the winner(usually one but more in case of a tie).
	 */
	public ArrayList<String> EndGame() {		//return the id of the winner
		ranking = new int[match.getNumberOfPlayers()][3];
		int numberOfTiedPlayers=0;
		for (Player p : match.getPlayers()) {
			ranking[p.getPlayerId()][0] = p.getPlayerId();
			ranking[p.getPlayerId()][1] = p.getTowersPlaced();
			ranking[p.getPlayerId()][2] = match.getTeachersOfPlayer(p);
		}
		int temp[];                                       //sorting of the ranking
		for (int i = 0; i < match.getNumberOfPlayers() - 1; i++) {
			for (int j = 0; j < match.getNumberOfPlayers() - i - 1; j++) {
				if (ranking[j][1] > ranking[j + 1][1]) {
					temp = ranking[j];
					ranking[j] = ranking[j + 1];
					ranking[j + 1] = temp;
				}
			}
		}
		if (ranking[match.getNumberOfPlayers() - 1][1] == ranking[match.getNumberOfPlayers() - 2][1]) {    //check teachers in case of a tie
			if (match.getNumberOfPlayers() == 3 && ranking[1][1] == ranking[0][1]) {                    //case of a 3 way tie
				for (int i = 0; i < match.getNumberOfPlayers() - 1; i++) {
					for (int j = 0; j < match.getNumberOfPlayers() - i - 1; j++) {
						if (ranking[j][2] > ranking[j + 1][2]) {
							temp = ranking[j];
							ranking[j] = ranking[j + 1];
							ranking[j + 1] = temp;
						}
					}
				}
				if(ranking[match.getNumberOfPlayers()-1][2]==ranking[match.getNumberOfPlayers()-2][2]){
					if(ranking[match.getNumberOfPlayers()-3][2]==ranking[match.getNumberOfPlayers()-2][2]){
						numberOfTiedPlayers=3;
					}
					else{
						numberOfTiedPlayers=2;
					}
				}
				else {
					numberOfTiedPlayers=1;
				}
			} else {                                                                                   //case of a 2 way tie
				if (ranking[match.getNumberOfPlayers() - 1][2] < ranking[match.getNumberOfPlayers() - 2][2]) {
					temp = ranking[match.getNumberOfPlayers() - 1];
					ranking[match.getNumberOfPlayers() - 1] = ranking[match.getNumberOfPlayers() - 2];
					ranking[match.getNumberOfPlayers() - 2] = temp;
				}
				if(ranking[match.getNumberOfPlayers()-1][2]==ranking[match.getNumberOfPlayers()-2][2]){
					numberOfTiedPlayers=2;
				}
				else{
					numberOfTiedPlayers=1;
				}
			}
		}
		else{
			numberOfTiedPlayers=1;
		}
		ArrayList<String> tiedPlayers=new ArrayList<String>();
		for (int j=0; j<numberOfTiedPlayers; j++){
			String string=match.getPlayerById(ranking[match.getNumberOfPlayers()-(1+j)][0]).getName();
			tiedPlayers.add(string);
		}
		EndgameMessage endgameMessage=new EndgameMessage(tiedPlayers);
		match.SendEndMessage(endgameMessage);
		return tiedPlayers;
	}

	/**
	 * Return the id of the active player.
	 *
	 * @return the id of the active player.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public int getActivePlayer() throws NoActivePlayerException {
		int id = 0;
		for (Player p : match.getPlayers()) {
			if (p.IsActive()) return id = p.getPlayerId();
		}
		throw new NoActivePlayerException();
	}


	/**
	 * Return the current match.
	 *
	 * @return the current match.
	 */
	public Match getMatch() {
		return this.match;
	}

	/**
	 * Return the position of the active player.
	 *
	 * @return the position of the active player in the array of players.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public int getActivePlayerPosition() throws NoActivePlayerException {
		for (int i = 0; i < match.getNumberOfPlayers(); i++) {
			if (match.getPlayers()[i].IsActive()) return i;
		}
		throw new NoActivePlayerException();
	}

	/**
	 * It is used to fill the clouds.
	 */
	public void FillClouds() {
		for (int i = 0; i < getMatch().getNumberOfPlayers(); i++) {
			if (match.getBag().BagSize() != 0) {
				match.MoveStudentsBagToCloud(i);
			}
		}
	}

	/**
	 * It is used to play a character Card. It returns a number depending on the id of the card.
	 *
	 * @param cardMessage: contains the index of the Character Card to be played.
	 * @return a number. 0 if the Id of the card is 4,6 or 9, otherwise it returns the id of the card.
	 * @throws InvalidInputException:   in case the index of the Character Card is invalid.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public int PlayCharacterCard(CharacterCardMessage cardMessage) throws InvalidInputException, NoActivePlayerException {
		if (cardMessage.getCardIndex() < 0 || cardMessage.getCardIndex() > 2) {
			throw new InvalidInputException("Invalid Card Index");
		} else {
			int CardId = match.getCharacterCardsOnTable()[cardMessage.getCardIndex()].getIdCharacterCard();
			switch (CardId) {
				case 4:
					characterCardController.PlayCard4();
					return 0;
				case 6:
					characterCardController.PlayCard6();
					return 0;
				case 9:
					characterCardController.PlayCard9();
					return 0;
				default:
					return CardId;
			}
		}
	}


	/**
	 * It executes all the actions needed at the end of every round.
	 */
	public void EndOfRound() {       //executes all the actions needed at the end of every round
		//match.setPlaysCard6(false); ??

		for (Player p : match.getPlayers()) {
			p.setPlayedCharacterCard(false);
		}
		if (match.getBag().BagSize() == 0) {
			EndGame();
		}
		for (Player p : match.getPlayers()) {
			if (p.getDeck().CardCount() == 0) {
				EndGame();
			}
		}

	}

	/**
	 * It allows you to play a Character Card depending on the id of the card.
	 *
	 * @param id:  the id of a Character Card.
	 * @param msg: the message to forward.
	 * @throws NoActivePlayerException: in case there is no active player.
	 * @throws InvalidInputException:   in case the index of the card is invalid.
	 * @throws NoEntryTilesException:   in case there are no prohibition cards.
	 */
	public void PlayCharacterCardById(int id, Message msg) throws NoActivePlayerException, InvalidInputException, NoEntryTilesException {
		if (id == 1) {
			characterCardController.PlayCard1((Card1Message) msg);
		}
		if (id == 3) {
			characterCardController.PlayCard3((Card3and5Message) msg);
		}
		if (id == 5) {
			characterCardController.PlayCard5((Card3and5Message) msg);
		}
		if (id == 10) {
			characterCardController.PlayCard10((Card10Message) msg);
		}
		if (id == 12) {
			characterCardController.PlayCard12((Card12Message) msg);
		}
	}
}


