package It.polimi.ingsw.Controller;
import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.*;
public class RoundController {

    private GameController Game;
    private boolean ReceivedMessage;
    private Message msg;


    public RoundController(){
        this.Game = new GameController();
    }
    public void ReceiveMessage(Message message){
        this.msg = message;
    }

    public void GamePhaseHandler(GamePhase gamePhase) throws InvalidInputException, WrongMessageException, NoActivePlayerException {
        switch (gamePhase) {
            case GAME_INIT:
                if (ReceivedMessage) {
                    if (msg.getMessageContent() == MessageContent.START) {
                        Game.InitializeGame((StartMessage) msg);
                        GamePhaseHandler(GamePhase.FILL_CLOUDS);                //TODO: TEST THIS PART !!
                    }
                    else throw new WrongMessageException();
                }
            case FILL_CLOUDS:


            case ASSISTANT_CARD:
                if (ReceivedMessage) {
                    if (msg.getMessageContent() == MessageContent.ASSISTANTCARD) {
                        Game.PlayAssistantCard((AssistantCardMessage) msg);
                        if(!FinishedPlayers()){
                            setNextActivePlayer();
                            GamePhaseHandler(GamePhase.ASSISTANT_CARD);
                        }
                        else{
                            Game.getMatch().SortPlayersByOrderValue();
                            setFirstActivePlayer();
                            GamePhaseHandler(GamePhase.MOVE_STUDENT);
                        }
                    }
                    else throw new WrongMessageException();
                }
            case MOVE_STUDENT:
                if (ReceivedMessage) {
                    if (msg.getMessageContent() == MessageContent.MOVESTUDENT) {
                        Game.MoveStudent((MoveStudentMessage)msg);
                        GamePhaseHandler(GamePhase.MOVE_MN);

                    }
                    else if(msg.getMessageContent() == MessageContent.CHARACTERCARD){ //TODO: EXAMPLE, messages need to be changed

                    }
                    else throw new WrongMessageException();
                }
            case MOVE_MN:
                if(ReceivedMessage){
                    if (msg.getMessageContent() == MessageContent.MOTHERNATURE) {
                        Game.MoveMotherNature((MotherNatureMessage)msg);
                        GamePhaseHandler(GamePhase.CHOOSE_CLOUD);

                    }
                    else if(msg.getMessageContent() == MessageContent.CHARACTERCARD){ //TODO: EXAMPLE, messages need to be changed

                    }
                    else throw new WrongMessageException();
                }
            case CHOOSE_CLOUD:
                if (msg.getMessageContent() == MessageContent.CLOUDCHOICE) {
                    Game.ChooseCloud((CloudChoiceMessage) msg);
                    if(!FinishedPlayers()) {
                        setNextActivePlayer();
                        GamePhaseHandler(GamePhase.MOVE_STUDENT);
                    }
                    else GamePhaseHandler(GamePhase.FILL_CLOUDS);

                }
                else if(msg.getMessageContent() == MessageContent.CHARACTERCARD){ //TODO: EXAMPLE, messages need to be changed

                }
                else throw new WrongMessageException();
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