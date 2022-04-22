package It.polimi.ingsw.Controller;
import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;

import java.io.NotActiveException;
import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    private Match match;
    private CharacterCardController characterCardController;
    private int activePlayer;
    /*private View view;*/

    public GameController(){
    }

    public void InitializeGame(StartMessage startMessage) throws InvalidInputException {
        match = new Match(startMessage.getNumberOfPlayers(), startMessage.getGameMode());
        if(startMessage.getNumberOfPlayers() < 2 || startMessage.getNumberOfPlayers() > 3){
            throw new InvalidInputException("Player Numbers can only be 2 or 3");
        }
        else {
            if (startMessage.getGameMode() < 0 || startMessage.getGameMode() > 1) {
                throw new InvalidInputException("Gamemode can only be 0 or 1");
            } else {
                if (match.isExpertMode()) {
                    characterCardController = new CharacterCardController(this);
                }
            }
        }
    }

    public void CheckIslandMerge(int IslandIndex){

        if(match.getTable().get(IslandIndex).isEmpty()) return;     //if island has no towers end check

        TowerColor CurrentColor = match.getTable().get(IslandIndex).getTowersColor();      //tower color of the island
        int PreviousIslandIndex, NextIslandIndex;


        if(IslandIndex==0){ //if island is first element of the list
            PreviousIslandIndex = match.getTable().size() - 1; //previous island is last element of the list
            NextIslandIndex = IslandIndex + 1;
        }
        else if(IslandIndex == match.getTable().size() - 1){ //if island is last element of the list
            PreviousIslandIndex = IslandIndex - 1;
            NextIslandIndex = 0; //next island is first element of the list
        }
        else{
            PreviousIslandIndex = IslandIndex - 1;
            NextIslandIndex = IslandIndex + 1;
        }

        //Previous island check
        if(match.getTable().get(PreviousIslandIndex).SameTowerColor(CurrentColor)){
            match.MergeIslands(IslandIndex, PreviousIslandIndex);

            //eventually update indexes
            if(IslandIndex != 0 && IslandIndex !=  match.getTable().size()) { //if island is not first or last
                IslandIndex--;
                NextIslandIndex--;
            }
            else if(IslandIndex == match.getTable().size()) { //if island is last
                IslandIndex--;
            }
        }
        //Next island check
        if(match.getTable().get(NextIslandIndex).SameTowerColor(CurrentColor)){
            match.MergeIslands(IslandIndex, NextIslandIndex);
        }

        //number of islands = 3 -> endgame
        if (match.getTable().size() <= 3) EndGame();
    }

    public void CheckTeacherControl(Color color,Player playerPlacingStudent){
        Teacher ColorTeacher =match.getTeacherByColor(color);
        int highestNumberOfStudents=ColorTeacher.getHighestNumberOfStudents();
        if(playerPlacingStudent.getPlayersSchool().getStudentNumber(color)>highestNumberOfStudents){
            ColorTeacher.IncreaseHighestNumberOfStudents();
            ColorTeacher.ChangeController(playerPlacingStudent);
        }
    }

    public void CheckIslandInfluence(int IslandIndex){
        if(match.getTable().get(IslandIndex).GetNoEntryTile()){
            match.getTable().get(IslandIndex).ResetNoEntryTile();
        }
        else {
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
            match.setPlaysCard6(false);                                         //TO BE DONE AT THE END OF THE TURN TOO!!

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
                TowerColor BuiltTowerColor = match.getPlayers()[idMax].getPlayerColor();         //find color of the tower to build
                match.getTable().get(IslandIndex).BuildTower(BuiltTowerColor);
                CheckIslandMerge(IslandIndex);
            }
        }
    }

    public void MoveStudent(MoveStudentMessage moveStudentMessage) throws InvalidInputException, NoActivePlayerException {
        int activePlayerId = getActivePlayer();
        int StudentIndex = moveStudentMessage.getEntrancePosition();
        int Destination = moveStudentMessage.getDestination();

        if(Destination <-1 || Destination >= match.getTable().size()) {
            throw new InvalidInputException("Invalid Destination");
        }
        else {
            if (StudentIndex >= 0 && StudentIndex < match.getPlayerById(activePlayerId).getPlayersSchool().getEntranceStudentsNumber()) {
                Color StudentColor = match.getPlayerById(activePlayerId).getPlayersSchool().GetEntranceStudentColor(StudentIndex);
                if (Destination == -1) {                     //if destination is not an island
                    if(match.getPlayerById(activePlayerId).getPlayersSchool().getStudentNumber(StudentColor)==10) {
                        throw new InvalidInputException("Dining Room is full, you cannot play this Card");
                    }
                    else match.MoveStudentsFromEntranceToDiningRoom(StudentIndex, activePlayerId);
                }
                else {                                        //if destination is an island
                    match.MoveStudentsFromEntranceToIsland(StudentIndex, activePlayerId, Destination);
                }
            } else throw new InvalidInputException("Invalid Student index");
        }
    }

    public void MoveMotherNature(MotherNatureMessage motherNatureMessage) throws InvalidInputException, NoActivePlayerException {

        if(motherNatureMessage.getSteps() == 0) throw new InvalidInputException("MotherNature has to do at least one step");
        else if(motherNatureMessage.getSteps() < 1) throw new InvalidInputException("You can't put a negative number");
        else if(match.getPlayerById(getActivePlayer()).GetPlayedMovements() >= motherNatureMessage.getSteps()) {
            match.MoveMotherNature(motherNatureMessage.getSteps());
            CheckIslandInfluence(match.getMotherNaturePosition());
        }
        else throw new InvalidInputException("Too many steps, max is "+match.getPlayerById(getActivePlayer()).GetPlayedMovements());
    }

    public void ChooseCloud(CloudChoiceMessage cloudChoiceMessage) throws InvalidInputException, NoActivePlayerException {
        if( cloudChoiceMessage.getCloudIndex() > match.getNumberOfPlayers()-1 || cloudChoiceMessage.getCloudIndex() < 0){
            throw new InvalidInputException("invalid Cloud index");
        }
        else match.MoveStudentsFromCloudToEntrance(getActivePlayer(),cloudChoiceMessage.getCloudIndex());
    }


    public void PlayAssistantCard(AssistantCardMessage assistantCardMessage) throws InvalidInputException, NoActivePlayerException {
        int card = assistantCardMessage.getCardIndex();
        boolean notPlayable = false;
        boolean Played=false;

        if(card >= match.getPlayerById(getActivePlayer()).getDeck().CardCount() || card < 0){
            throw new InvalidInputException("invalid card index");
        }

        for(Player p: match.getPlayers()){
            if(p == match.getPlayerById(getActivePlayer())) break;
            else{
                if(match.getPlayerById(getActivePlayer()).getDeck().GetCard(card).getOrderValue() == p.GetPlayedOrderValue()){
                    notPlayable = true;
                }
            }
        }
        if(notPlayable){
            notPlayable = false;
            for(int i = 0; i<match.getPlayerById(getActivePlayer()).getDeck().CardCount(); i++){
                Played = false;
                for(Player p: match.getPlayers()){
                    if(p == match.getPlayerById(getActivePlayer())) break;
                    else{
                        if(match.getPlayerById(getActivePlayer()).getDeck().GetCard(i).getOrderValue() == p.GetPlayedOrderValue()) {
                            Played = true;
                        }
                    }
                }
                if(!Played){
                    notPlayable = true;
                    break;
                }
            }
        }
        if(notPlayable) throw new InvalidInputException("Selected Assistant Card is not Playable");
        else{
            match.getPlayerById(getActivePlayer()).PlayAssistantCard(card);
        }

    }

    public void EndGame(){}

    public int getActivePlayer() throws NoActivePlayerException{
        int id = 0;
        for(Player p: match.getPlayers()){
            if(p.IsActive()) id = p.getPlayerId();
        }
        throw new NoActivePlayerException();
    }

     public void setActivePlayer(int activePlayer){
         this.activePlayer=activePlayer;
    }

    public Match getMatch(){return this.match;}

    public int getActivePlayerPosition() throws NoActivePlayerException {
        for(int i=0; i< match.getNumberOfPlayers(); i++){
            if(match.getPlayers()[i].IsActive()) return i;
        }
        throw new NoActivePlayerException();
    }

    @Override
    public void update(Observable o, Object arg){
    }


}


