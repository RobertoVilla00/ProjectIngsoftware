package Model;

public class AssistantCard {

	private int OrderValue;

	private int Movements;

	public AssistantCard(int orderValue, int movements) {
		OrderValue = orderValue;
		Movements = movements;
	}

	public int getOrderValue() {
		return OrderValue;
	}

	public int getMovement() {
		return Movements;
	}

}
