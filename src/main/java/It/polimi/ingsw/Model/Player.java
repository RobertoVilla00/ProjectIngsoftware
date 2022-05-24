package It.polimi.ingsw.Model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the Player class of the game.
 */
public class Player implements Comparable<Player>, Serializable {

	private static final long serialVersionUID=1L;

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
	 * Constructor of the class player.
	 * @param playerColor: the color of the tower of the player.
	 * @param name: the nickname of the player.
	 * @param playerId: the id of the player.
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
	 * If the player used a CharacterCard, set the attribute PlayedCharacterCard to true.
	 * @param value: boolean parameter that indicates if the player used a CharacterCard.
	 */
	public void setPlayedCharacterCard(boolean value){
		this.PlayedCharacterCard = value;
	}

	/**
	 * Return true if the player used a character card that give additional points, false if not.
	 * @return the boolean attribute AdditionalPoints.
	 */
	public boolean getAdditionalPoints(){
		return this.AdditionalPoints;
	}

	/**
	 * Set true the attribute AdditionalPoints if the player used a character card that give additional points, false if not.
	 * @param value: boolean parameter that indicates if the player used a character card that give additional points.
	 */
	public void setAdditionalPoints(boolean value){
		this.AdditionalPoints = value;
	}

	/**
	 * Return the color of tower of the player.
	 * @return the color of tower of the player.
	 */
	public TowerColor getPlayerColor() {
		return PlayerColor;
	}

	/**
	 * Return the nickname.
	 * @return the nickname of the player.
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Return the school of the player.
	 * @return the school of the player.
	 */
	public School getPlayersSchool() {
		return PlayersSchool;
	}

	/**
	 * Return the number of towers placed by this player.
	 * @return the towers placed.
	 */
	public int getTowersPlaced() {
		return TowersPlaced;
	}

	/**
	 * Return the number of coins owned by this player.
	 * @return the coins of the player.
	 */
	public int getCoins() {
		return Coins;
	}

	/**
	 * Return a number that serves to keep track of who will play first during the next round depending on the value of the card played.
	 * @param OtherPlayer: other player in the game.
	 * @return a number (-1 or 0 or 1).
	 */
	@Override
	public int compareTo(Player OtherPlayer) {
		int x=this.GetPlayedOrderValue();
		int y=OtherPlayer.GetPlayedOrderValue();
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

	/**
	 * Return true if the player used a Character Card, false if not.
	 * @return a boolean that indicates if the player used a Character Card.
	 */
	public boolean HasPlayedCharacterCard(){return PlayedCharacterCard;}

	/**
	 * Return the value of the card played.
	 * @return the order value of the card played.
	 */
	public int GetPlayedOrderValue() {
		return PlayedOrderValue;
	}

	/**
	 * Returns the number of steps that mother nature can at most perform depending on the card played.
	 * @return the number of steps.
	 */
	public int GetPlayedMovements() {
		return PlayedMovements;
	}

	/**
	 * This method it is used to play the assistant card in the index position within the deck.
	 * It saves the value of the card and the number of steps that mother nature can at most make.
	 * Removes the card played from the deck.
	 * @param index: the index of the card to be played.
	 */
	public void PlayAssistantCard(int index) {   // update last played card
		this.PlayedOrderValue=Deck.GetCard(index).getOrderValue();
		this.PlayedMovements=Deck.GetCard(index).getMovement();
		Deck.RemoveCard(index);
	}

	/**
	 * Increase the attribute PlayedMovements by NumberOfMovements.
	 * @param NumberOfMovements: number of steps that mother nature can make.
	 */
	public void IncreaseMovements(int NumberOfMovements){
		this.PlayedMovements=PlayedMovements+NumberOfMovements;
	}

	/**
	 * Increase the number of towers placed by the player.
	 * @param numberOfTowers: number of towers.
	 */
	public void IncreaseTowersPlaced(int numberOfTowers){
		this.TowersPlaced=this.TowersPlaced+numberOfTowers;
	}

	/**
	 * Decrease the number of towers placed by the player.
	 * @param numberOfTowers: number of towers.
	 */
	public void DecreaseTowersPlaced(int numberOfTowers){
		this.TowersPlaced=this.TowersPlaced-numberOfTowers;
	}

	/**
	 * Increase the number of coins owned by the player.
	 * @param numberOfCoins: number of coins.
	 */
	public void AddCoin(int numberOfCoins){
		this.Coins=this.Coins + numberOfCoins;
	}

	/**
	 * Decrease the number of coins owned by the player.
	 * @param numberOfCoins: number of coins.
	 */
	public void RemoveCoins(int numberOfCoins){
		this.Coins=this.Coins - numberOfCoins;
	}

	/**
	 * This method is used to set the IsActive attribute to true to indicate that the player is active during his turn.
	 */
	public void setActive(){
		this.IsActive=true;
	}

	/**
	 * This method is used to set the IsActive attribute to false to indicate that the player is inactive during his turn.
	 */
	public void setInactive(){
		this.IsActive=false;
	}

	/**
	 * Return true if the player is active, false if the player is inactive.
	 * @return a boolean that indicates if the player is active or inactive.
	 */
	public boolean IsActive(){
		return IsActive;
	}

	/**
	 * Return the deck of the player.
	 * @return the deck of Assistant Card of the player.
	 */
	public AssistantCardDeck getDeck(){
		return this.Deck;
	}

	/**
	 * Return the id of the player.
	 * @return the ID of the player.
	 */
	public int getPlayerId(){ return this.PlayerId; }

	/**
	 * Set a number of towers placed.
	 * @param number: the number to set.

	 */
	public void setNumberOfTowers(int number){
		TowersPlaced=number;
	}
}
