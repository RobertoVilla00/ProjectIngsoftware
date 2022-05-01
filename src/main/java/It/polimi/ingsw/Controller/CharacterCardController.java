package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;

public class CharacterCardController{

    private GameController Game;

    public CharacterCardController(GameController gController)
    {
        this.Game = gController;
    }

    public void PlayCard1 (Card1Message message) throws InvalidInputException, NoActivePlayerException {                     //students from card to Island

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
                    }
                }
            } else throw new InvalidInputException("You do not have enough coins to play this card");
        } else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

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
                }
            } else throw new InvalidInputException("You do not have enough coins to play this card");
        } else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard4() throws InvalidInputException, NoActivePlayerException {               //increase maximum mother nature movement
        if(Game.getMatch().isCharacterCardOnTable(4)) {
            CharacterCard card4 = Game.getMatch().getCharacterCardById(4);
            if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card4.getCardCost()) {
                Game.getMatch().getPlayerById(Game.getActivePlayer()).IncreaseMovements(2);
                Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card4.getCardCost());
                Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
            }
            else throw new InvalidInputException("You do not have enough coins to play this card");
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard5(Card3and5Message message) throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {        //no entry tiles
        if(Game.getMatch().isCharacterCardOnTable(5)) {
            Card5 card = (Card5) Game.getMatch().getCharacterCardById(5);
            if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card.getCardCost()) {
                if (card.getNoEntryTilesOnCard() == 0) throw new NoEntryTilesException();
                else {
                    if (message.getIslandIndex() >= Game.getMatch().getTable().size()) {
                        throw new InvalidInputException("Given Island Index is not Available, please give another one");
                    }
                    else {
                        card.RemoveNoEntryTile();
                        Game.getMatch().getTable().get(message.getIslandIndex()).setNoEntryTile();
                        Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card.getCardCost());
                        Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
                    }
                }
            }
            else throw new InvalidInputException("You do not have enough coins to play this card");
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard6() throws InvalidInputException, NoActivePlayerException {               //no towers counted in influence
        if(Game.getMatch().isCharacterCardOnTable(6)) {
            CharacterCard card6 = Game.getMatch().getCharacterCardById(6);
            if(Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card6.getCardCost()){
                if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card6.getCardCost()) {
                    Game.getMatch().setPlaysCard6(true);
                    Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card6.getCardCost());
                    Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
                }
            }
            else throw new InvalidInputException("You do not have enough coins to play this card");
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard9() throws InvalidInputException, NoActivePlayerException {                   //2 bonus points
        if(Game.getMatch().isCharacterCardOnTable(9)){
            CharacterCard card9=Game.getMatch().getCharacterCardById(9);
            if(Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card9.getCardCost()) {
                Game.getMatch().getPlayerById(Game.getActivePlayer()).setAdditionalPoints(true);
                Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card9.getCardCost());
                Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
            }
            else throw new InvalidInputException("You do not have enough coins to play this card");
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard10(Card10Message message) throws InvalidInputException, NoActivePlayerException {             //move student to dining room
        if(Game.getMatch().isCharacterCardOnTable(10)){
            Cards1and10 card = (Cards1and10)Game.getMatch().getCharacterCardById(10);
            if(Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card.getCardCost()) {
                if (message.getStudentIndex() >= card.getNumberOfStudents()) {
                    throw new InvalidInputException("Given Student Index is not Available, please give another one");
                } else {
                    Color StudentColor = card.RemoveStudent(message.getStudentIndex());
                    if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getPlayersSchool().getStudentNumber(StudentColor) == 10) {
                        throw new InvalidInputException("Dining Room is full, you cannot play this Card");
                    } else {
                        Game.getMatch().getPlayerById(Game.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(StudentColor);
                        Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card.getCardCost());
                        Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
                    }
                }
            }
            else throw new InvalidInputException("You do not have enough coins to play this card");
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard12(Card12Message message) throws InvalidInputException, NoActivePlayerException {         //remove 3 students from every school
        if(Game.getMatch().isCharacterCardOnTable(12)) {
            CharacterCard card12 = Game.getMatch().getCharacterCardById(12);
            if (Game.getMatch().getPlayerById(Game.getActivePlayer()).getCoins() >= card12.getCardCost()) {
                for (Player p : Game.getMatch().getPlayers()) {
                    for (int i = 0; i < 3; i++) {
                        if (p.getPlayersSchool().getStudentNumber(message.getStudentColor()) > 0) {
                            p.getPlayersSchool().RemoveStudentFromDiningRoom(message.getStudentColor());
                        }
                    }
                }
                Game.getMatch().getPlayerById(Game.getActivePlayer()).RemoveCoins(card12.getCardCost());
                Game.getMatch().getPlayerById(Game.getActivePlayer()).setPlayedCharacterCard(true);
            }
            else throw new InvalidInputException("You do not have enough coins to play this card");
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }
}
