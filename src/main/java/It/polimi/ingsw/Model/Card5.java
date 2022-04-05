package It.polimi.ingsw.Model;

import java.util.ArrayList;

public class Card5 extends CharacterCard{
    private int NoEntryTilesOnCard;

    public Card5(int idCharacterCard, int cardCost, Match currentMatch){
        super(idCharacterCard,cardCost,currentMatch);
        NoEntryTilesOnCard =4;
    }
    public int getNoEntryTilesOnCard(){
        return this.NoEntryTilesOnCard;
    }

    public void AddNoEntryTile(){
        NoEntryTilesOnCard++;
    }

    public void RemoveNoEntryTile(){
        NoEntryTilesOnCard--;
    }
}
