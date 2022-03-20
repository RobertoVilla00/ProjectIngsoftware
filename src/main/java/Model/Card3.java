package Model;

public class Card3 extends CharacterCard{
    public Card3(){
        SetCardCost(3);
    }

    public void Playcard(int index){
        Match.ResolveMotherNature(index);
        this.IncreaseCardCost();
    }

}
