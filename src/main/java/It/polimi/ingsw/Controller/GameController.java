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

    public void CheckIslandDominance(){
        int IslandIndex = match.getMotherNaturePosition();
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

        //TO BE CONTINUED

        //eventually merge islands and update indexes

        //number of islands = 3 -> endgame
    }




    @Override
    public void update(Observable o, Object arg){
    }
}


