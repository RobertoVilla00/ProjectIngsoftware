package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class CloudTest {

    @Test
    public void getIdCloud(){
        Cloud cloud = new Cloud(1);
        int IdCloud = cloud.getIdCloud();
        assertEquals(IdCloud, 1);
    }

    @Test
    public void MoveStudentsFromCloud(){
        Cloud cloud = new Cloud(1);
        cloud.AddStudent(Color.GREEN);
        Color StudentColor = cloud.MoveStudentsFromCloud(0);
        assertEquals(StudentColor, Color.GREEN);
    }
}
