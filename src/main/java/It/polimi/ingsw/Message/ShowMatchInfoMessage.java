package It.polimi.ingsw.Message;

import It.polimi.ingsw.Controller.GamePhase;
import It.polimi.ingsw.Model.CharacterCard;
import It.polimi.ingsw.Model.*;

import java.util.ArrayList;

/**
 * The message for the info of the match.
 */
public class ShowMatchInfoMessage extends Message{

    private static final long serialVersionUID=1L;
    private CharacterCard[] CharacterCards;
    private ArrayList<Cloud> Clouds;
    private Player[] Players;
    private ArrayList<Island> Table;


    private ArrayList<Teacher> Teachers;

    private GamePhase gamePhase;
    private int ActivePlayerId;
    private int ExpectedCardMessage;
    private int MotherNaturePosition;
    private boolean ExpertMode;


    /**
     * The constructor of the message with the match's infos.
     * @param match: the current match.
     */
    public ShowMatchInfoMessage(Match match) {
        super(MessageContent.SHOWMATCHINFO);
        this.ActivePlayerId=match.getActivePlayerId();
        this.CharacterCards=match.getCharacterCardsOnTable();
        this.ExpectedCardMessage= match.getExpectedCardMessage();
        this.Clouds = match.getClouds();
        this.Table = match.getTable();
        this.gamePhase = match.getGamePhase();
        this.Players = match.getPlayers();
        this.MotherNaturePosition=match.getMotherNaturePosition();
        this.ExpertMode= match.isExpertMode();
        this.Teachers= match.getTeachers();
    }

    /**
     * Return the Arraylist of teachers.
     * @return the ArrayList of teachers.
     */
    public ArrayList<Teacher> getTeachers() {
        return Teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        Teachers = teachers;
    }


    /**
     * Return true if the mode is expert, false if not.
     * @return the mode of the game.
     */
    public boolean isExpertMode() {
        return ExpertMode;
    }

    public void setExpertMode(boolean expertMode) {
        ExpertMode = expertMode;
    }

    /**
     * Return the array of Character Cards.
     * @return the Array of Character Cards.
     */
    public CharacterCard[] getCharacterCards() {
        return CharacterCards;
    }

    /**
     * Return the position of mother nature.
     * @return the position of mother nature.
     */
    public int getMotherNaturePosition() {
        return MotherNaturePosition;
    }

    public void setMotherNaturePosition(int motherNaturePosition) {
        MotherNaturePosition = motherNaturePosition;
    }

    public void setCharacterCards(CharacterCard[] characterCards) {
        CharacterCards = characterCards;
    }

    /**
     * Return the ArrayList of clouds.
     * @return the ArrayList of clouds.
     */
    public ArrayList<Cloud> getClouds() {
        return Clouds;
    }

    public void setClouds(ArrayList<Cloud> clouds) {
        Clouds = clouds;
    }

    /**
     * Return the Array of players.
     * @return the Array of players.
     */
    public Player[] getPlayers() {
        return Players;
    }

    /**
     * It sets the array of players.
     * @param players: the array of the players.
     */
    public void setPlayers(Player[] players) {
        Players = players;
    }

    /**
     * Return the ArrayList of islands.
     * @return the ArrayList of Islands.
     */
    public ArrayList<Island> getTable() {
        return Table;
    }

    public void setTable(ArrayList<Island> table) {
        Table = table;
    }

    /**
     * Return the phase of the game.
     * @return the phase of the game.
     */
    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    /**
     * Return the id of the active player.
     * @return the id of the active player.
     */
    public int getActivePlayerId() {
        return ActivePlayerId;
    }

    public void setActivePlayerId(int activePlayerId) {
        ActivePlayerId = activePlayerId;
    }

    /**
     * Return the number of the expected card.
     * @return the number of the expected card.
     */
    public int getExpectedCardMessage() {
        return ExpectedCardMessage;
    }

    public void setExpectedCardMessage(int expectedCardMessage) {
        ExpectedCardMessage = expectedCardMessage;
    }


}
