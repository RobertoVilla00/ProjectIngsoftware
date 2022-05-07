package It.polimi.ingsw.Message;
import It.polimi.ingsw.Model.*;

public class showSchoolMessage extends Message{
    private School school;


    public showSchoolMessage(School school) {
        super(MessageContent.SHOWSCHOOL);
        this.school = school;
    }
}
