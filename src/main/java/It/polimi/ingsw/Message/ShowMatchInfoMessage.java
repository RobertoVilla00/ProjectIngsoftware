package It.polimi.ingsw.Message;

import It.polimi.ingsw.Controller.GamePhase;
import It.polimi.ingsw.Model.CharacterCard;
import It.polimi.ingsw.Model.*;

import java.util.ArrayList;

public class ShowMatchInfoMessage extends Message{

    private static final long serialVersionUID=1L;
    private CharacterCard[] CharacterCards;
    private ArrayList<Cloud> Clouds;
    private Player[] Players;
    private ArrayList<Island> Table;

    private GamePhase gamePhase;
    private int ActivePlayerId;
    private int ExpectedCardMessage;
    private int MotherNaturePosition;


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
    }



    public CharacterCard[] getCharacterCards() {
        return CharacterCards;
    }

    public int getMotherNaturePosition() {
        return MotherNaturePosition;
    }

    public void setMotherNaturePosition(int motherNaturePosition) {
        MotherNaturePosition = motherNaturePosition;
    }

    public void setCharacterCards(CharacterCard[] characterCards) {
        CharacterCards = characterCards;
    }

    public ArrayList<Cloud> getClouds() {
        return Clouds;
    }

    public void setClouds(ArrayList<Cloud> clouds) {
        Clouds = clouds;
    }

    public Player[] getPlayers() {
        return Players;
    }

    public void setPlayers(Player[] players) {
        Players = players;
    }

    public ArrayList<Island> getTable() {
        return Table;
    }

    public void setTable(ArrayList<Island> table) {
        Table = table;
    }

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    public int getActivePlayerId() {
        return ActivePlayerId;
    }

    public void setActivePlayerId(int activePlayerId) {
        ActivePlayerId = activePlayerId;
    }

    public int getExpectedCardMessage() {
        return ExpectedCardMessage;
    }

    public void setExpectedCardMessage(int expectedCardMessage) {
        ExpectedCardMessage = expectedCardMessage;
    }


}
