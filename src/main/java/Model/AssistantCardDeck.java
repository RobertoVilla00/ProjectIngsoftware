package Model;

import java.util.ArrayList;

public class AssistantCardDeck {
	private ArrayList<AssistantCard> Cards;

	public AssistantCardDeck() {
		Cards= new ArrayList<AssistantCard>();
		Cards.add(new AssistantCard(1,1));
		Cards.add(new AssistantCard(2,1));
		Cards.add(new AssistantCard(3,2));
		Cards.add(new AssistantCard(4,2));
		Cards.add(new AssistantCard(5,3));
		Cards.add(new AssistantCard(6,3));
		Cards.add(new AssistantCard(7,4));
		Cards.add(new AssistantCard(8,4));
		Cards.add(new AssistantCard(9,5));
		Cards.add(new AssistantCard(10,5));
	}

	public void RemoveCard(int index) {
		Cards.remove(index);
	}

    public AssistantCard GetCard (int index){
		return Cards.get(index);
	}
}
