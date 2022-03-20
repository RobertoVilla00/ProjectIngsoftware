package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player implements Comparable<Player> {

	private TowerColor PlayerColor;

	private AssistantCardDeck Deck;

	private String Name;

	private School PlayersSchool;

	private int TowersPlaced;

	private int Coins;

	private int PlayedOrderValue;

	private int PlayedMovements;

	public Player(TowerColor playerColor, String name) {

		PlayerColor = playerColor;
		Name = name;
		PlayersSchool =new School();
		TowersPlaced = 0;
		Coins=1;
		Deck= new AssistantCardDeck();

	}

	public TowerColor getPlayerColor() {
		return PlayerColor;
	}

	public String getName() {
		return Name;
	}

	public School getPlayersSchool() {
		return PlayersSchool;
	}

	public int getTowersPlaced() {
		return TowersPlaced;
	}

	public int getCoins() {
		return Coins;
	}

	@Override
	public int compareTo(Player OtherPlayer) {
		int x=this.GetPlayedOrderValue();
		int y=OtherPlayer.GetPlayedOrderValue();
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

	public int GetPlayedOrderValue() {
		return PlayedOrderValue;
	}

	public int GetPlayedMovements() {
		return PlayedMovements;
	}

	public void PlayAssistantCard(int index) {   // update last played card
		this.PlayedOrderValue=Deck.GetCard(index).getOrderValue();
		this.PlayedMovements=Deck.GetCard(index).getMovement();
	}



}
