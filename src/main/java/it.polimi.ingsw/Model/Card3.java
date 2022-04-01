package it.polimi.ingsw.Model;


public class Card3 extends CharacterCard{                       //Count the influence on an island as if mother nature landed on it
    public Card3(){
        SetCardCost(3);
    }

    public void Playcard3(int index){
        getMatch().getTable().get(index).ResolveMotherNature();
        this.IncreaseCardCost();
    }

}
