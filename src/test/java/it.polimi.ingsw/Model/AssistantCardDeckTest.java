package it.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssistantCardDeckTest {

    @Test
    public void RemoveCardCount(){
        AssistantCardDeck deck = new AssistantCardDeck();
        deck.RemoveCard(5);
        int cardCount= deck.CardCount();
        assertEquals(9,cardCount);
    }

    @Test
    public void RemoveCardOrderControl(){
        AssistantCardDeck deck =new AssistantCardDeck();
        deck.RemoveCard(1);
        AssistantCard card= deck.GetCard(1);
        int orderValue= card.getOrderValue();
        assertEquals(3,orderValue);
    }


}
