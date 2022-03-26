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

	private ArrayList <TowerColor> Teacher;

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

	public void IncreaseMovements(int NumberOfMOvements){
		this.PlayedMovements=PlayedMovements+NumberOfMOvements;
	}

	public void IncreaseTowersPlaced(int numberOfTowers){
		this.TowersPlaced=this.TowersPlaced+numberOfTowers;
	}

	public void DecreaseTowersPlaced(int numberOfTowers){
		this.TowersPlaced=this.TowersPlaced-numberOfTowers;
	}

	public void AddCoin(int numberOfCoins){
		this.Coins=this.Coins + numberOfCoins;
	}
	public void RemoveCoins(int numberOfCoins){
		this.Coins=this.Coins - numberOfCoins;
	}
}
