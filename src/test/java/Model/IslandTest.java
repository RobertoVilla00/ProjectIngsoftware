package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IslandTest {

    @Test
    public void Island(){
        Island i = new Island();
        assertEquals(0,i.CountStudents(Color.YELLOW));
        assertEquals(0,i.CountStudents(Color.GREEN));
        assertEquals(0,i.CountStudents(Color.PINK));
        assertEquals(0,i.CountStudents(Color.RED));
        assertEquals(0,i.CountStudents(Color.BLUE));
        assertEquals(0,i.CountTowers());
        //assertEquals(false,i.GetNoEntryTile);
    }

    @Test
    public void AddStudent() {
        Island i = new Island();
        i.AddStudent(Color.GREEN);

        assertEquals(1,i.CountStudents (Color.GREEN));
        assertEquals(0,i.CountStudents (Color.RED));
        assertEquals(0,i.CountStudents (Color.BLUE));
        assertEquals(0,i.CountStudents (Color.PINK));
        assertEquals(0,i.CountStudents (Color.YELLOW));

        i.AddStudent(Color.RED);

        assertEquals(1,i.CountStudents (Color.GREEN));
        assertEquals(1,i.CountStudents (Color.RED));
        assertEquals(0,i.CountStudents (Color.BLUE));
        assertEquals(0,i.CountStudents (Color.PINK));
        assertEquals(0,i.CountStudents (Color.YELLOW));

        i.AddStudent(Color.BLUE);

        assertEquals(1,i.CountStudents (Color.GREEN));
        assertEquals(1,i.CountStudents (Color.RED));
        assertEquals(1,i.CountStudents (Color.BLUE));
        assertEquals(0,i.CountStudents (Color.PINK));
        assertEquals(0,i.CountStudents (Color.YELLOW));

        i.AddStudent(Color.PINK);

        assertEquals(1,i.CountStudents (Color.GREEN));
        assertEquals(1,i.CountStudents (Color.RED));
        assertEquals(1,i.CountStudents (Color.BLUE));
        assertEquals(1,i.CountStudents (Color.PINK));
        assertEquals(0,i.CountStudents (Color.YELLOW));

        i.AddStudent(Color.YELLOW);

        assertEquals(1,i.CountStudents (Color.GREEN));
        assertEquals(1,i.CountStudents (Color.RED));
        assertEquals(1,i.CountStudents (Color.BLUE));
        assertEquals(1,i.CountStudents (Color.PINK));
        assertEquals(1,i.CountStudents (Color.YELLOW));

        }
    @Test
    public void BuildTower(){
       Island i =new Island();
       i.BuildTower(TowerColor.BLACK);
       //assertEquals(TowerColor.BLACK, i.getTowerColor());
       assertEquals(1,i.CountTowers());
       i.BuildTower(TowerColor.BLACK);
       assertEquals(1,i.CountTowers());

    }
    @Test
    public void IncreaseTower(){
        Island i =new Island();
        i.IncreaseTower();
        assertEquals(1,i.CountTowers());
        i.IncreaseTower();
        assertEquals(2,i.CountTowers());
    }
    @Test
    public void CountTowers(){
        Island i =new Island();
        assertEquals(0,i.CountTowers());
        i.IncreaseTower();
        assertEquals(1,i.CountTowers());

    }
    @Test
    public void CountStudents(){
        Island i =new Island();
        i.AddStudent(Color.YELLOW);
        i.AddStudent(Color.GREEN);
        i.AddStudent(Color.PINK);
        i.AddStudent(Color.RED);
        i.AddStudent(Color.BLUE);

        assertEquals(1,i.CountStudents (Color.GREEN));
        assertEquals(1,i.CountStudents (Color.RED));
        assertEquals(1,i.CountStudents (Color.BLUE));
        assertEquals(1,i.CountStudents (Color.PINK));
        assertEquals(1,i.CountStudents (Color.YELLOW));
    }
    @Test
    public void setNoEntryTile(){
        Island i =new Island();
        i.setNoEntryTile();
        //assertEquals(true,i.GetNoEntryTile);
    }
    }


