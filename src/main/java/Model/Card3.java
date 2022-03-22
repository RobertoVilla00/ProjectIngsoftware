package Model;

public class Card3 extends CharacterCard{                       //Count the influence on an island as if mother nature landed on it
    public Card3(){
        SetCardCost(3);
    }

    public void Playcard3(int index){
        Match.ResolveMotherNature(index);
        this.IncreaseCardCost();
    }

}
