package It.polimi.ingsw.Model;


public class Card4 extends CharacterCard{                   //put 3 students of a certain color from dining room back into bag
    public Card4(){
        SetCardCost(3);
    }

    public void PlayCard4(Color StudentColor){
        getMatch().ResolveCard4(StudentColor);
        this.IncreaseCardCost();
    }
}
