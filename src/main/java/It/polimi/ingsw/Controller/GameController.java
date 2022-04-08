package It.polimi.ingsw.Controller;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    private Match match;
    private CharacterCardController characterCardController;
    /*private View view;*/

    public GameController(){
    }

    public void InitializeGame(StartMessage startMessage){
        match = new Match(startMessage.getNumberOfPlayers(), startMessage.getGameMode());
        if(match.isExpertMode()){
            characterCardController = new CharacterCardController(this);
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

    public void MoveStudent(MoveStudentMessage moveStudentMessage){
        int activePlayerId = ActivePlayer();
        int StudentIndex = moveStudentMessage.getEntrancePosition();
        int Destination = moveStudentMessage.getDestination();

        if(Destination == - 1) {                     //if destination is not an island
            match.MoveStudentsFromEntranceToDiningRoom(StudentIndex, activePlayerId);
        }
        else{                                        //if destination is an island
            match.MoveStudentsFromEntranceToIsland(StudentIndex, activePlayerId, Destination);
        }
    }

    public void MoveMotherNature(MotherNatureMessage motherNatureMessage){
        //check if its possible + additional steps
        match.MoveMotherNature(motherNatureMessage.getSteps());
    }

    public void ChooseCloud(CloudChoiceMessage cloudChoiceMessage){
        match.MoveStudentsFromCloudToEntrance(ActivePlayer(),cloudChoiceMessage.getCloudIndex());
    }


    public void PlayAssistantCard(Message assistantCardMessage){
        //check if it's playable then calls model
    }

    public void EndGame(){}

    public int ActivePlayer(){
        return this.match.getPlayerById(2).getPlayerId(); //EXAMPLE!!! IsActive boolean in Model??
    }



    public Match getMatch(){return this.match;}

    @Override
    public void update(Observable o, Object arg){
    }


}


