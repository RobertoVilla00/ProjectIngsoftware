package Model;

public class Player {

	private TowerColor PlayerColor;

	private String Name;

	private School PlayersSchool;

	private int TowersPlaced;

	private int Coins;

	private AssistantCard LastPlayedCard;

	public Player(TowerColor playerColor, String name, School playersSchool, int towersPlaced, AssistantCard lastPlayedCard) {
		PlayerColor = playerColor;
		Name = name;
		PlayersSchool = playersSchool;
		TowersPlaced = towersPlaced;
		Coins=0;
		LastPlayedCard = lastPlayedCard;
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

	public AssistantCard getLastPlayedCard() {
		return LastPlayedCard;
	}

	public AssistantCard PlayAssistantCard() {
             return null;
	}

}
