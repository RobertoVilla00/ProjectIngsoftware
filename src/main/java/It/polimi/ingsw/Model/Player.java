package It.polimi.ingsw.Model;


import java.util.ArrayList;

/**
 * This class represents the Player class of the game
 */
public class Player implements Comparable<Player> {

	private TowerColor PlayerColor;

	private AssistantCardDeck Deck;

	private String Name;

	private School PlayersSchool;

	private int PlayerId;

	private int TowersPlaced;

	private int Coins;

	private int PlayedOrderValue;

	private int PlayedMovements;

	private boolean IsActive;

	private boolean AdditionalPoints;

	private boolean PlayedCharacterCard;

	private ArrayList <TowerColor> Teacher;

	/**
	 * Constructor of the class player
	 * @param playerColor: the color of the tower of the player
	 * @param name: the nickname of the player
	 * @param playerId: the id of the player
	 */
	public Player(TowerColor playerColor, String name,int playerId) {
		PlayerId=playerId;
		PlayerColor = playerColor;
		Name = name;
		PlayersSchool =new School();
		TowersPlaced = 0;
		Coins=1;
		Deck= new AssistantCardDeck();
		IsActive = false;
	}

	/**
	 * If the player used a CharacterCard, set the attribute PlayedCharacterCard to true
	 * @param value: boolean attribute that indicates if the player used a CharacterCard
	 */
	public void setPlayedCharacterCard(boolean value){
		this.PlayedCharacterCard = value;
	}

	/**
	 * Return true if the player used a character card that give additional points
	 * @return the boolean attribute AdditionalPoints
	 */
	public boolean getAdditionalPoints(){
		return this.AdditionalPoints;
	}

	public void setAdditionalPoints(boolean value){
		this.AdditionalPoints = value;
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

	public boolean HasPlayedCharacterCard(){return PlayedCharacterCard;}

	public int GetPlayedOrderValue() {
		return PlayedOrderValue;
	}

	public int GetPlayedMovements() {
		return PlayedMovements;
	}

	public void PlayAssistantCard(int index) {   // update last played card
		this.PlayedOrderValue=Deck.GetCard(index).getOrderValue();
		this.PlayedMovements=Deck.GetCard(index).getMovement();
		Deck.RemoveCard(index); // not sure
	}

	public void IncreaseMovements(int NumberOfMovements){
		this.PlayedMovements=PlayedMovements+NumberOfMovements;
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

	public void setActive(){
		this.IsActive=true;
	}
	public void setInactive(){
		this.IsActive=false;
	}
	public boolean IsActive(){
		return IsActive;
	}
	public AssistantCardDeck getDeck(){
		return this.Deck;
	}

	public int getPlayerId(){ return this.PlayerId; }
}
