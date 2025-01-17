package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.*;

/**
 * This is the class that represents the controller of the Round.
 */
public class RoundController {

	private GameController Game;
	private GamePhase gamePhase;
	private GamePhase previousPhase;
	private int ExpectedCardMsg;
	private Message msg;
	private int MovedStudentCounter;


	/**
	 * The constructor of the Round Controller. It creates the Game Controller and set the initial Game Phase.
	 */
	public RoundController() {
		this.Game = new GameController();
		this.gamePhase = GamePhase.GAME_INIT;
	}

	/**
	 * This method takes care of managing the messages received.
	 * @param msg: the message.
	 * @throws NoActivePlayerException: in case there is no active player.
	 * @throws InvalidInputException: in case a value that the player insert is invalid.
	 * @throws WrongMessageException: in case there is an error in the game.
	 */
	public void MessageHandler(Message msg) throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		this.msg = msg;
		switch (msg.getMessageContent()) {
			case START:
				if (gamePhase == GamePhase.GAME_INIT) {
					GamePhaseHandler(GamePhase.GAME_INIT);
				} else throw new WrongMessageException();
				break;


			case ASSISTANTCARD:
				if (gamePhase == GamePhase.ASSISTANT_CARD) {
					GamePhaseHandler(GamePhase.ASSISTANT_CARD);
				} else throw new WrongMessageException();
				break;

			case MOVESTUDENT:
				if (gamePhase == GamePhase.MOVE_STUDENT) {
					GamePhaseHandler(GamePhase.MOVE_STUDENT);
				} else throw new WrongMessageException();
				break;

			case MOTHERNATURE:
				if (gamePhase == GamePhase.MOVE_MN) {
					GamePhaseHandler(GamePhase.MOVE_MN);
				} else throw new WrongMessageException();
				break;

			case CLOUDCHOICE:
				if (gamePhase == GamePhase.CHOOSE_CLOUD) {
					GamePhaseHandler(GamePhase.CHOOSE_CLOUD);
				} else throw new WrongMessageException();
				break;

			case CHARACTERCARD:
				if (gamePhase == GamePhase.MOVE_STUDENT || gamePhase == GamePhase.MOVE_MN || gamePhase == GamePhase.CHOOSE_CLOUD) {
					try {
						if (Game.getMatch().getPlayerById(Game.getActivePlayer()).HasPlayedCharacterCard()) {
							ErrorMessage errorMessage=new ErrorMessage("you have already played a character card in this round, wait for the next round", Game.getActivePlayer());
							Game.getMatch().sendError(errorMessage);
							Game.getMatch().CreateMessage();
						} else {
							int CardId = Game.PlayCharacterCard((CharacterCardMessage) msg); //plays character card if parameters are not needed
							if (CardId != 0) {                                               //parameters neededExpectedCardMsg = CardId;
								previousPhase = gamePhase;
								gamePhase = GamePhase.CHARACTER_CARD;
								Game.getMatch().setGamePhase(gamePhase);
								setExpectedCardMsg(CardId);
								Game.getMatch().CreateMessage();
							}
						}
					} catch (InvalidInputException e) {
						ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
						Game.getMatch().sendError(errorMessage);
						Game.getMatch().CreateMessage();
					}
				} else throw new WrongMessageException();
				break;

			case CARD1:
				if (gamePhase == GamePhase.CHARACTER_CARD && ExpectedCardMsg == 1) {
					GamePhaseHandler(GamePhase.CHARACTER_CARD);
				} else throw new WrongMessageException();
				break;

			case CARD3AND5:
				if (gamePhase == GamePhase.CHARACTER_CARD && ExpectedCardMsg == 3 || ExpectedCardMsg == 5) {
					GamePhaseHandler(GamePhase.CHARACTER_CARD);
				} else throw new WrongMessageException();
				break;

			case CARD10:
				if (gamePhase == GamePhase.CHARACTER_CARD && ExpectedCardMsg == 10) {
					GamePhaseHandler(GamePhase.CHARACTER_CARD);
				} else throw new WrongMessageException();
				break;

			case CARD12:
				if (gamePhase == GamePhase.CHARACTER_CARD && ExpectedCardMsg == 12) {
					GamePhaseHandler(GamePhase.CHARACTER_CARD);
				} else throw new WrongMessageException();
				break;
		}
	}

	/**
	 * This method takes care of receiving the current game phase and invoking the methods corresponding to that game phase.
	 * @param gamePhase: this is the current phase of the game.
	 * @throws InvalidInputException: in case a value that the player insert is invalid.
	 * @throws WrongMessageException: in case there is an error in the game.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void GamePhaseHandler(GamePhase gamePhase) throws InvalidInputException, WrongMessageException, NoActivePlayerException {
		switch (gamePhase) {
			case GAME_INIT:
				try {
					Game.InitializeGame((StartMessage) msg);
					setFirstActivePlayer();
					GamePhaseHandler(GamePhase.FILL_CLOUDS);
				} catch (InvalidInputException e) {
					ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
					Game.getMatch().sendError(errorMessage);
				} finally {
					break;
				}

			case FILL_CLOUDS:
				Game.FillClouds();
				this.gamePhase = GamePhase.ASSISTANT_CARD;
				Game.getMatch().setGamePhase(this.gamePhase);
				break;

			case ASSISTANT_CARD:
				try {
					Game.PlayAssistantCard((AssistantCardMessage) msg);
					if (!FinishedPlayers()) {
						setNextActivePlayer();
					} else {
						Game.getMatch().SortPlayersByOrderValue();
						setFirstActivePlayer();
						this.gamePhase = GamePhase.MOVE_STUDENT;
						Game.getMatch().setGamePhase(this.gamePhase);
					}
				} catch (InvalidInputException e) {
					ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
					Game.getMatch().sendError(errorMessage);
				} finally {
					Game.getMatch().CreateMessage();
					break;
				}

			case MOVE_STUDENT:
				try {
					Game.MoveStudent((MoveStudentMessage) msg);
					MovedStudentCounter++;
					if (MovedStudentCounter == Game.getMatch().getNumberOfPlayers() + 1 || Game.getMatch().getPlayerById(Game.getActivePlayer()).getPlayersSchool().getEntranceStudentsNumber() == 0) {
						//change phase if player moved max num of students or if the entrance of the school is empty
						this.gamePhase = GamePhase.MOVE_MN;
						Game.getMatch().setGamePhase(this.gamePhase);
						MovedStudentCounter = 0;      //reset counter
					}
				} catch (InvalidInputException e) {
					ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
					Game.getMatch().sendError(errorMessage);
				} finally {
					Game.getMatch().CreateMessage();
					break;
				}

			case MOVE_MN:
				try {
					Game.MoveMotherNature((MotherNatureMessage) msg);
					this.gamePhase = GamePhase.CHOOSE_CLOUD;
					Game.getMatch().setGamePhase(this.gamePhase);
				} catch (InvalidInputException e) {
					ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
					Game.getMatch().sendError(errorMessage);
				} finally {
					Game.getMatch().CreateMessage();
					break;
				}

			case CHOOSE_CLOUD:
				try {
					Game.ChooseCloud((CloudChoiceMessage) msg);
					Game.getMatch().setPlaysCard6(false);
					Game.getMatch().getPlayerById(Game.getActivePlayer()).setAdditionalPoints(false);
					if (!FinishedPlayers()) {
						setNextActivePlayer();
						this.gamePhase = GamePhase.MOVE_STUDENT;
						Game.getMatch().setGamePhase(this.gamePhase);
					} else {
						Game.EndOfRound();
						setFirstActivePlayer();
						GamePhaseHandler(GamePhase.FILL_CLOUDS);
					}
				} catch (InvalidInputException e) {
					ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
					Game.getMatch().sendError(errorMessage);
				} finally {
					Game.getMatch().CreateMessage();
					break;
				}

			case CHARACTER_CARD:
				this.gamePhase = previousPhase;
				Game.getMatch().setGamePhase(this.gamePhase);
				try {
					if (msg.getMessageContent() == MessageContent.CARD1) {
						Game.PlayCharacterCardById(1, msg);
					}
					if (msg.getMessageContent() == MessageContent.CARD3AND5) {
						Game.PlayCharacterCardById(ExpectedCardMsg, msg);
					}
					if (msg.getMessageContent() == MessageContent.CARD10) {
						Game.PlayCharacterCardById(10, msg);
					}
					if (msg.getMessageContent() == MessageContent.CARD12) {
						Game.PlayCharacterCardById(12, msg);
					}
				} catch (InvalidInputException | NoEntryTilesException e) {
					ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), Game.getActivePlayer());
					Game.getMatch().sendError(errorMessage);
					Game.getMatch().CreateMessage();
				}
		}
	}

	/**
	 * It used to set the fist player to active, and the other players to inactive.
	 */
	public void setFirstActivePlayer() {
		Game.getMatch().getPlayers()[0].setActive();
		Game.getMatch().setActivePlayerId(Game.getMatch().getPlayers()[0].getPlayerId());
		for (int i = 1; i < Game.getMatch().getNumberOfPlayers(); i++) {
			Game.getMatch().getPlayers()[i].setInactive();
		}
	}   //first player of the new list becomes active

	/**
	 * It used to set the next player in the array to active.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public void setNextActivePlayer() throws NoActivePlayerException {
		int PresentActive = Game.getActivePlayerPosition();
		Game.getMatch().getPlayers()[PresentActive].setInactive();
		Game.getMatch().getPlayers()[PresentActive + 1].setActive();
		Game.getMatch().setActivePlayerId(Game.getMatch().getPlayers()[PresentActive + 1].getPlayerId()); //write in model who's the active player
	}    //next player becomes active

	/**
	 * Return true if active player is last element of the array of players, otherwise false.
	 * @return true if active player is last element of the array of players.
	 * @throws NoActivePlayerException: in case there is no active player.
	 */
	public boolean FinishedPlayers() throws NoActivePlayerException {   //return true if active player is last element of players list
		if (Game.getActivePlayerPosition() == Game.getMatch().getNumberOfPlayers() - 1) return true;
		else return false;
	}

	/**
	 * Return the instance of the game controller.
	 * @return the instance of the game controller.
	 */
	public GameController getGameController() {
		return this.Game;
	}

	/**
	 * It sets the phase of the game.
	 * @param gamePhase: the game phase of the game.
	 */
	public void setGamePhase(GamePhase gamePhase) {
		this.gamePhase = gamePhase;
	}

	/**
	 * It sets the expected number of the Character Card.
	 * @param expectedCardMsg: the number of the expected Character Card.
	 */
	public void setExpectedCardMsg(int expectedCardMsg) {
		this.ExpectedCardMsg = expectedCardMsg;
		Game.getMatch().setExpectedCardMessage(expectedCardMsg);
	}

	/**
	 * Return the game phase of the game.
	 * @return the game phase of the game.
	 */
	public GamePhase getGamePhase() {
		return this.gamePhase;
	}

	/**
	 * It used to add the nickname of the player to the match.
	 * @param nicknameMessage: the message containing the nickname of the player and his index.
	 */
	public void addNickName(NicknameMessage nicknameMessage){
		Game.getMatch().getPlayerById(nicknameMessage.getPlayerIndex()).setName(nicknameMessage.getNickname());
	}
}