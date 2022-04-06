package It.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class Card5Test {
    @Test
    public void getNoEntryTiles(){
        Match match=new Match(3,1);
        Card5 card5=new Card5(5,2,match);
        int noEntryTiles=card5.getNoEntryTilesOnCard();
        assertEquals(4,noEntryTiles);
    }

    @Test
    public void AddNoEntryTiles(){
        Match match=new Match(3,1);
        Card5 card5=new Card5(5,2,match);
        card5.AddNoEntryTile();
        int noEntryTiles=card5.getNoEntryTilesOnCard();
        assertEquals(5,noEntryTiles);
    }

    @Test
    public void RemoveNoEntryTiles(){
        Match match=new Match(3,1);
        Card5 card5=new Card5(5,2,match);
        card5.RemoveNoEntryTile();
        int noEntryTiles=card5.getNoEntryTilesOnCard();
        assertEquals(3,noEntryTiles);
    }
}
