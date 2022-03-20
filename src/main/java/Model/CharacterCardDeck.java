package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CharacterCardDeck {

	private ArrayList<CharacterCard> Cards;

	public CharacterCardDeck() {
		Cards= new ArrayList<CharacterCard>();
		Cards.add(new CharacterCard(1,0));
		Cards.add(new CharacterCard(2,0));
		Cards.add(new CharacterCard(3,0));
		Cards.add(new CharacterCard(4,0));
		Cards.add(new CharacterCard(5,0));
		Cards.add(new CharacterCard(6,0));
		Cards.add(new CharacterCard(7,0));
		Cards.add(new CharacterCard(8,0));
	}
	public CharacterCard SelectCard() {
		CharacterCard temp=Cards.get(0);
		Cards.remove(0);
		return temp;
	}

	public void ShuffleCharacterCardDeck() {
		Collections.shuffle(Cards);
	}


}
