package It.polimi.ingsw.Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharacterCardDeckTest {
    @Test
    public void CharacterCardDeckInitialization(){
        Match match= new Match(3,1);
        CharacterCardDeck characterCardDeck= new CharacterCardDeck(match);
        CharacterCard card=characterCardDeck.SelectCard(2);
        int Cost=card.getCardCost();
        assertEquals(1,Cost);
    }

    @Test
    public void ShuffleCharacterCardDeck(){
        Match match= new Match(3,1);
        CharacterCardDeck characterCardDeck= new CharacterCardDeck(match);
        characterCardDeck.ShuffleCharacterCardDeck();
        CharacterCard card=characterCardDeck.SelectCard(7);
        assertNotNull(card);
    }
}
