package It.polimi.ingsw.Controller;
import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.*;
public class RoundController {

    private GameController Game;
    private GamePhase gamePhase;
    private Message msg;


    public RoundController(){
        this.Game = new GameController();
    }

    public void MessageHandler(Message msg) throws NoActivePlayerException, InvalidInputException, WrongMessageException {
        switch (msg.getMessageContent()){
            case START:
                if(gamePhase == GamePhase.IDLE){
                    GamePhaseHandler(GamePhase.GAME_INIT);
                }
                else throw new WrongMessageException();
            case ASSISTANTCARD:
                if(gamePhase == GamePhase.ASSISTANT_CARD){
                    GamePhaseHandler(GamePhase.ASSISTANT_CARD);
                }
                else throw new WrongMessageException();
            case MOVESTUDENT:
                if(gamePhase == GamePhase.MOVE_STUDENT){
                    GamePhaseHandler(GamePhase.MOVE_STUDENT);
                }
                else throw new WrongMessageException();
            case MOTHERNATURE:
                if(gamePhase == GamePhase.MOVE_MN){
                    GamePhaseHandler(GamePhase.MOVE_MN);
                }
                else throw new WrongMessageException();
            case CHARACTERCARD:
                if(gamePhase == GamePhase.MOVE_STUDENT || gamePhase == GamePhase.MOVE_MN || gamePhase == GamePhase.CHOOSE_CLOUD){
                    Game.PlayCharacterCard((CharacterCardMessage) msg);
                }//TODO: play cards with parameters
        }
    }

    public void GamePhaseHandler(GamePhase gamePhase) throws InvalidInputException, WrongMessageException, NoActivePlayerException {
        switch (gamePhase) {
            case GAME_INIT:
                try {
                    Game.InitializeGame((StartMessage) msg);
                    setFirstActivePlayer();
                    GamePhaseHandler(GamePhase.FILL_CLOUDS);
                }
                catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            case FILL_CLOUDS:
                Game.FillClouds();
                gamePhase = GamePhase.ASSISTANT_CARD;

            case ASSISTANT_CARD:
                try {
                    Game.PlayAssistantCard((AssistantCardMessage) msg);
                    if (!FinishedPlayers()) {
                        setNextActivePlayer();
                    }
                    else {
                        Game.getMatch().SortPlayersByOrderValue();
                        setFirstActivePlayer();
                        gamePhase = GamePhase.MOVE_STUDENT;
                    }
                }
                catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            case MOVE_STUDENT:
                try {
                    Game.MoveStudent((MoveStudentMessage) msg);
                    gamePhase = GamePhase.MOVE_MN;
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            case MOVE_MN:
                try {
                    Game.MoveMotherNature((MotherNatureMessage) msg);
                    gamePhase = GamePhase.CHOOSE_CLOUD;
                }
                catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            case CHOOSE_CLOUD:
                try {
                    Game.ChooseCloud((CloudChoiceMessage) msg);
                    if (!FinishedPlayers()) {
                        setNextActivePlayer();
                        gamePhase = GamePhase.MOVE_STUDENT;
                    }
                    else GamePhaseHandler(GamePhase.FILL_CLOUDS);
                }
                catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
        }
    }

    public void setFirstActivePlayer(){
        Game.getMatch().getPlayers()[0].setActive();
        for(int i=1;i<Game.getMatch().getNumberOfPlayers();i++){
            Game.getMatch().getPlayers()[i].setInactive();
        }
    }   //first player of the new list becomes active

    public void setNextActivePlayer() throws NoActivePlayerException {
        int PresentActive = Game.getActivePlayerPosition();
        Game.getMatch().getPlayers()[PresentActive].setInactive();
        Game.getMatch().getPlayers()[PresentActive+1].setActive();
    }    //next player becomes active

    public boolean FinishedPlayers() throws NoActivePlayerException {   //return true if active player is last element of players list
        if(Game.getActivePlayerPosition()==Game.getMatch().getNumberOfPlayers()-1) return true;
        else return false;
    }

    public GameController getGameController(){
        return this.Game;
    }
}