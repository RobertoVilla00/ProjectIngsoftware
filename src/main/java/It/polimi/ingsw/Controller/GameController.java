package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Model.*;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    private Match model;
    /*private View view;*/


    public GameController(Match model/*, View view*/){
        this.model = model;
        /*this.view = view;*/
    }

    public void InitializeGame(){
        //model = new Match();
    }

    @Override
    public void update(Observable o, Object arg){
    }
}


