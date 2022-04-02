package It.polimi.ingsw.Controller;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    private Match match;

    /*private View view;*/

    public void InitializeGame(StartMessage startMessage){
        match = new Match(startMessage.getNumberOfPlayers(), startMessage.getGameMode());
    }

    public void CheckIslandMerge(){
        int IslandIndex = match.getMotherNaturePosition();

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
            if(IslandIndex != 0 && IslandIndex !=  match.getTable().size() - 1) { //if island is not first or last
                IslandIndex--;
                NextIslandIndex--;
            }
            else if(IslandIndex == match.getTable().size() - 1) { //if island is last
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

    public void EndGame(){}

    @Override
    public void update(Observable o, Object arg){
    }


}


