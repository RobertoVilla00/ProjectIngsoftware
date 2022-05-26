package It.polimi.ingsw.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterCardTest {
	@Test
	public void getIdCharacterCard() {
		Match match = new Match(3, 1);
		CharacterCard characterCard = new CharacterCard(3, 5, match);
		int id = characterCard.getIdCharacterCard();
		assertEquals(3, id);
	}

	@Test
	public void getCardCost() {
		Match match = new Match(3, 1);
		CharacterCard characterCard = new CharacterCard(3, 5, match);
		int cost = characterCard.getCardCost();
		assertEquals(5, cost);
	}

	@Test
	public void IncreaseCardCost() {
		Match match = new Match(3, 1);
		CharacterCard characterCard = new CharacterCard(1, 2, match);
		characterCard.IncreaseCardCost();
		int cost = characterCard.getCardCost();
		assertEquals(3, cost);
	}

	@Test
	public void getMatch() {
		Match match = new Match(3, 1);
		CharacterCard characterCard = new CharacterCard(3, 5, match);
		Match foundMatch = characterCard.getMatch();
		assertEquals(match, foundMatch);
	}
}
