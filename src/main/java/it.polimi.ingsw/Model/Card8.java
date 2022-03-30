package it.polimi.ingsw.Model;


public class Card8 extends CharacterCard{

    public Card8(){
        SetCardCost(1);
    }

    public void PlayCard8(Player CardPlayer){
        CardPlayer.IncreaseMovements(2);
        this.IncreaseCardCost();
    }
}
