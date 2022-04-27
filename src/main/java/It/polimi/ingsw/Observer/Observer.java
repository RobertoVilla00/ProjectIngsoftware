package It.polimi.ingsw.Observer;

import It.polimi.ingsw.Message.Message;

public interface Observer<T> {
    void update(Message message);

}
