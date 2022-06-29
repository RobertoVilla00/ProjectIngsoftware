package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;

/**
 * The controller class for the Character Card.
 */
public class CharacterCardController {

	private GameController Game;

	/**
	 * Constructor of the class. It sets the game controller.
	 *
	 * @param gController: the game controller.
	 */
	public CharacterCardController(GameController gController) {
		this.Game = gController;
	}

	/**
	 * It is used to play Character Card with id 1 which allows you to move students from card to an island.
	 *
	 * @param message: contains the index of a student and the index of an island.
	 * @throws InvalidInputException:   in case the player don't have enough money to play the card.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard1(Card1Message message) throws InvalidInputException, NoActivePlayerException {                     //students from card to Island

		if (Game.getMatch().isCharacterCardOnTable(1)) {
			Cards1and10 card1 = (Cards1and10) Game.getMatch().getCharacterCardById(1);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card1.getCardCost()) {
				if (message.getStudentOnCardIndex() >= card1.getNumberOfStudents()) {
					throw new InvalidInputException("Given Student Index is not Available, please give another one");
				} else {
					if (message.getIslandIndex() >= Game.getMatch().getTable().size()) {
						throw new InvalidInputException("Given Island Index is not Available, please give another one");
					} else {
						Color StudentColor = card1.RemoveStudent(message.getStudentOnCardIndex());
						Game.getMatch().getTable().get(message.getIslandIndex()).AddStudent(StudentColor);
						card1.AddStudent();
						Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card1.getCardCost());
						Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
						card1.IncreaseCardCost();
						Game.getMatch().CreateMessage();
					}
				}
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 3 which allows you to count the influence as if mother nature had finished her movement there.
	 *
	 * @param message: contains the index of an island.
	 * @throws InvalidInputException:   in case of the index of the island is invalid, or the player don't have enough money to play the card
	 *                                  or the selected card is not on table.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard3(Card3and5Message message) throws InvalidInputException, NoActivePlayerException {              //count the influence on an island

		if (Game.getMatch().isCharacterCardOnTable(3)) {
			CharacterCard card3 = Game.getMatch().getCharacterCardById(3);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card3.getCardCost()) {
				if (message.getIslandIndex() >= Game.getMatch().getTable().size()) {
					throw new InvalidInputException("Given Island Index is not Available, please give another one");
				} else {
					Game.CheckIslandInfluence(message.getIslandIndex());
					Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card3.getCardCost());
					Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
					card3.IncreaseCardCost();
					Game.getMatch().CreateMessage();
				}
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 4 which allows you to increase the number of steps that mother nature can perform of 2.
	 *
	 * @throws InvalidInputException:   in case of the player don't have enough money to play the card or the selected card is not on table.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard4() throws InvalidInputException, NoActivePlayerException {               //increase maximum mother nature movement
		if (Game.getMatch().isCharacterCardOnTable(4)) {
			CharacterCard card4 = Game.getMatch().getCharacterCardById(4);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card4.getCardCost()) {
				Game.getMatch().getPlayerById(Game.getActivePlayer()).IncreaseMovements(2);
				Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card4.getCardCost());
				Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
				card4.IncreaseCardCost();
				Game.getMatch().CreateMessage();
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 5 which allows you to place a prohibition cards on an island chosen by you.
	 *
	 * @param message: contains an island index.
	 * @throws InvalidInputException:   in case of the index of the island is invalid, or the player don't have enough money to play the card
	 *                                  or the selected card is not on table.
	 * @throws NoEntryTilesException:   in case there are no prohibition cards on the card.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard5(Card3and5Message message) throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {        //no entry tiles
		if (Game.getMatch().isCharacterCardOnTable(5)) {
			Card5 card = (Card5) Game.getMatch().getCharacterCardById(5);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card.getCardCost()) {
				if (card.getNoEntryTilesOnCard() == 0) throw new NoEntryTilesException();
				else {
					if (message.getIslandIndex() >= Game.getMatch().getTable().size()) {
						throw new InvalidInputException("Given Island Index is not Available, please give another one");
					} else {
						card.RemoveNoEntryTile();
						Game.getMatch().getTable().get(message.getIslandIndex()).setNoEntryTile();
						Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card.getCardCost());
						Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
						card.IncreaseCardCost();
						Game.getMatch().CreateMessage();
					}
				}
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 6 which allows you to calculate the influence on an island or a group of islands
	 * without counting the towers.
	 *
	 * @throws InvalidInputException:   in case of the player don't have enough money to play the card
	 *                                  or the selected card is not on table.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard6() throws InvalidInputException, NoActivePlayerException {               //no towers counted in influence
		if (Game.getMatch().isCharacterCardOnTable(6)) {
			CharacterCard card6 = Game.getMatch().getCharacterCardById(6);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card6.getCardCost()) {
				if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card6.getCardCost()) {
					Game.getMatch().setPlaysCard6(true);
					Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card6.getCardCost());
					Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
					card6.IncreaseCardCost();
					Game.getMatch().CreateMessage();
				}
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 9 which allows you to have 2 bonus points in the calculation of the influence.
	 *
	 * @throws InvalidInputException:   in case of the player don't have enough money to play the card
	 *                                  or the selected card is not on table.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard9() throws InvalidInputException, NoActivePlayerException {                   //2 bonus points
		if (Game.getMatch().isCharacterCardOnTable(9)) {
			CharacterCard card9 = Game.getMatch().getCharacterCardById(9);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card9.getCardCost()) {
				Game.getMatch().getPlayerById(Game.getActivePlayer()).setAdditionalPoints(true);
				Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card9.getCardCost());
				Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
				card9.IncreaseCardCost();
				Game.getMatch().CreateMessage();
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 10 which allows you move a student to the dining room.
	 *
	 * @param message: contains the index of  student.
	 * @throws InvalidInputException:   in case of the index of the student is invalid, or the player don't have enough money to play the card
	 *                                  or the selected card is not on table or the dining room is full.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard10(Card10Message message) throws InvalidInputException, NoActivePlayerException {             //move student to dining room
		if (Game.getMatch().isCharacterCardOnTable(10)) {
			Cards1and10 card = (Cards1and10) Game.getMatch().getCharacterCardById(10);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card.getCardCost()) {
				if (message.getStudentIndex() >= card.getNumberOfStudents()) {
					throw new InvalidInputException("Given Student Index is not Available, please give another one");
				} else {
					Color StudentColor = card.GetStudentColor(message.getStudentIndex());
					if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getPlayersSchool().getStudentNumber(StudentColor) == 10) {
						throw new InvalidInputException("Dining Room is full, you cannot play this Card");
					} else {
						card.RemoveStudent(message.getStudentIndex());
						Game.getMatch().getPlayerById(Game.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(StudentColor);
						Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card.getCardCost());
						card.AddStudent();
						Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
						Game.CheckTeacherControl(StudentColor, Game.getMatch().getPlayerById(Game.getActivePlayer()));
						card.IncreaseCardCost();
						Game.getMatch().CreateMessage();
					}
				}
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}

	/**
	 * It is used to play Character Card with id 12 which allows you to remove 3 students of this color from the dining room of each student.
	 *
	 * @param message: contains the color of a student.
	 * @throws InvalidInputException:   in case of the player don't have enough money to play the card
	 *                                  or the selected card is not on table.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void PlayCard12(Card12Message message) throws InvalidInputException, NoActivePlayerException {         //remove 3 students from every school
		if (Game.getMatch().isCharacterCardOnTable(12)) {
			CharacterCard card12 = Game.getMatch().getCharacterCardById(12);
			if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card12.getCardCost()) {
				for (Player p : Game.getMatch().getPlayers()) {
					for (int i = 0; i < 3; i++) {
						if (p.getPlayersSchool().getStudentNumber(message.getStudentColor()) > 0) {
							p.getPlayersSchool().RemoveStudentFromDiningRoom(message.getStudentColor());
							Game.getMatch().getBag().AddStudent(message.getStudentColor());
						}
					}
				}
				Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card12.getCardCost());
				Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
				card12.IncreaseCardCost();
				Game.getMatch().CreateMessage();
			} else throw new InvalidInputException("You do not have enough coins to play this card");
		} else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
	}
}
