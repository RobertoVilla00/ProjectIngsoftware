package It.polimi.ingsw.Observer;

import It.polimi.ingsw.Message.Message;

import java.util.ArrayList;
import java.util.List;


/**
 * Observable class that can be observed by implementing the Observer interface and registering as Listener.
 */
public class Observable {

	private final List<Observer> observers = new ArrayList<>();


	/**
	 * It adds an observer.
	 * @param obs: the observer to be added.
	 */
	public void addObserver(Observer obs) {
		observers.add(obs);
	}


	/**
	 * It notifies all the current observers through the update method.
	 * @param message: the message that is forward to observers.
	 */
	protected void notifyObserver(Message message) {
		for (Observer observer : observers) {
			observer.update(message);
		}
	}
}