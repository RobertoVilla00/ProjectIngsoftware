package It.polimi.ingsw.Observer;

import It.polimi.ingsw.Message.Message;

import java.util.ArrayList;
import java.util.List;


public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();


    public void addObserver(Observer<T> obs) {
        observers.add(obs);
    }


    public void removeObserver(Observer<T> obs) {

        observers.remove(obs);
    }


    protected void notifyObserver(T message) {
        for (Observer<T> observer : observers) {
            observer.update((Message) message);
        }
    }
}
