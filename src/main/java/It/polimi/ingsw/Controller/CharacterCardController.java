package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;

public class CharacterCardController{

    private GameController Game;

    public CharacterCardController(GameController gController)
    {
        this.Game = gController;
    }

    public void PlayCard1 (Card1Message message) throws InvalidInputException {
        if(Game.getMatch().isCharacterCardOnTable(1)){
            Cards1and10 card1 = (Cards1and10)Game.getMatch().getCharacterCardById(1);
            if(message.getStudentOnCardIndex() >= card1.getNumberOfStudents()){
                throw new InvalidInputException("Given Student Index is not Available, please give another one");
            }
            else {
                if(message.getIslandIndex() >= Game.getMatch().getTable().size()) {
                    throw new InvalidInputException("Given Island Index is not Available, please give another one");
                }
                else {
                    Color StudentColor = card1.RemoveStudent(message.getStudentOnCardIndex());
                    Game.getMatch().getTable().get(message.getIslandIndex()).AddStudent(StudentColor);
                    card1.AddStudent();
                }
            }
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard3(Card3and5Message message) throws InvalidInputException {
        if(Game.getMatch().isCharacterCardOnTable(3)){
            if(message.getIslandIndex() >= Game.getMatch().getTable().size()) {
                throw new InvalidInputException("Given Island Index is not Available, please give another one");
            }
            else {
                Game.CheckIslandInfluence(message.getIslandIndex());
            }
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard4() throws InvalidInputException{
        if(Game.getMatch().isCharacterCardOnTable(4)){
            Game.getMatch().getPlayerById(Game.ActivePlayer()).IncreaseMovements(2);
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard5(Card3and5Message message) throws InvalidInputException, NoEntryTilesException{
        if(Game.getMatch().isCharacterCardOnTable(5)){
            Card5 card =(Card5)Game.getMatch().getCharacterCardById(5);
            if(card.getNoEntryTilesOnCard() == 0) throw new NoEntryTilesException();
            else{
                if(message.getIslandIndex() >= Game.getMatch().getTable().size()) {
                    throw new InvalidInputException("Given Island Index is not Available, please give another one");
                }
                else {
                    card.RemoveNoEntryTile();
                    Game.getMatch().getTable().get(message.getIslandIndex()).setNoEntryTile();
                }
            }
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard6() throws InvalidInputException{
        if(Game.getMatch().isCharacterCardOnTable(6)){
            Game.getMatch().setPlaysCard6(true);
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard9() throws InvalidInputException{
        if(Game.getMatch().isCharacterCardOnTable(9)){
            Game.getMatch().getPlayerById(Game.ActivePlayer()).setAdditionalPoints(true);
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard10(Card10Message message) throws InvalidInputException{
        if(Game.getMatch().isCharacterCardOnTable(10)){
            Cards1and10 card = (Cards1and10)Game.getMatch().getCharacterCardById(10);
            if(message.getStudentIndex()>= card.getNumberOfStudents()){
                throw new InvalidInputException("Given Student Index is not Available, please give another one");
            }
            else{
                Color StudentColor = card.RemoveStudent(message.getStudentIndex());
                if(Game.getMatch().getPlayerById(Game.ActivePlayer()).getPlayersSchool().getStudentNumber(StudentColor)==10){
                    throw new InvalidInputException("Dining Room is full, you cannot play this Card");
                }
                else {
                    Game.getMatch().getPlayerById(Game.ActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(StudentColor);
                }
            }
        }
        else throw new InvalidInputException("Selected Card is not on Table, please choose another Card");
    }

    public void PlayCard12(Card12Message message) throws InvalidInputException{
        if(Game.getMatch().isCharacterCardOnTable(10)){
            for(Player p: Game.getMatch().getPlayers()){
                for(int i=0; i < 3; i++) {
                    if (p.getPlayersSchool().getStudentNumber(message.getStudentColor()) > 0) {
                        p.getPlayersSchool().RemoveStudentFromDiningRoom(message.getStudentColor());
                    }
                }
            }
        }
        else throw new InvalidInputException("Dining Room is full, you cannot play this Card");
    }
}
