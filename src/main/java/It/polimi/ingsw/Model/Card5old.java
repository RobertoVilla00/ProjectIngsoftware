package It.polimi.ingsw.Model;


public class Card5old extends CharacterCard{                       //Put no entry tiles on island
    private int NumberOfNoEntryTiles;

    /*public Card5old(){
        SetCardCost(2);
        this.NumberOfNoEntryTiles=4;
    }*/

    public void PlayCard5(int index){
        //getMatch().ResolveCard5(index);
        this.IncreaseCardCost();
    }

}

