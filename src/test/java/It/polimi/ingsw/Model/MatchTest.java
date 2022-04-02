package It.polimi.ingsw.Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MatchTest {

    @Test
    public void NumberOfPlayers(){
        Match match= new Match(3, 0);
        Player [] players =new Player[3];
        players=match.getPlayers();
        assertNotNull(players[2]);
    }

    @Test
    public void MoveStudentsBagToIsland(){
        Match match = new Match(2, 1);
        match.MoveStudentsBagToIsland(0);
        int Blues=match.getTable().get(0).CountStudents(Color.BLUE);
        int Reds=match.getTable().get(0).CountStudents(Color.RED);
        int Yellows=match.getTable().get(0).CountStudents(Color.YELLOW);
        int Pinks=match.getTable().get(0).CountStudents(Color.PINK);
        int Green=match.getTable().get(0).CountStudents(Color.GREEN);
        int students=Blues+Reds+Yellows+Pinks+Green;
        assertEquals(1,students);
    }

    @Test
    public void MoveStudentBagToCloud(){
        Match match = new Match(2, 1);
        match.MoveStudentsBagToCloud(1);
        int cloudstudents=match.getClouds().get(1).CloudSize();
        assertEquals(3,cloudstudents);
    }

    @Test
    public void MoveStudentBagToCloud3Players(){
        Match match = new Match(3, 1);
        match.MoveStudentsBagToCloud(1);
        int cloudstudents=match.getClouds().get(1).CloudSize();
        assertEquals(4,cloudstudents);
    }

    @Test
    public void NumberOfClouds(){
        Match match = new Match(2, 1);
        int NumberOfClouds=match.getClouds().size();
        assertEquals(2,NumberOfClouds);
    }

    @Test
    public void PlayAssistantCard(){
        Match match = new Match(3, 1);
        match.PlayAssistantCard(0,2);
        int OrderValue=match.getPlayers()[2].GetPlayedOrderValue();
        assertEquals(1,OrderValue);
    }

    @Test
    public void MoveMotherNature(){
        Match match = new Match(3, 1);
        match.MoveMotherNature(5);
        int motherNaturePosition=match.getMotherNaturePosition();
        assertEquals(5,motherNaturePosition);
    }

    @Test
    public void MoveMotherNatureBackToStart(){                          //test the movement back to the start of table
        Match match = new Match(3, 1);
        match.MoveMotherNature(6);
        match.MoveMotherNature(7);
        int motherNaturePosition=match.getMotherNaturePosition();
        assertEquals(1,motherNaturePosition);
    }

    @Test
    public void MergeIslandsStudents(){
        Match match = new Match(3,1);
        boolean hasBlue=false;
        if(match.getTable().get(7).CountStudents(Color.BLUE)==1){
            hasBlue=true;
        }
        match.getTable().get(6).AddStudent(Color.BLUE);
        match.getTable().get(6).AddStudent(Color.BLUE);
        match.getTable().get(7).AddStudent(Color.BLUE);
        match.getTable().get(7).AddStudent(Color.BLUE);
        match.MergeIslands(7,6);
        int BlueStudents=match.getTable().get(6).CountStudents(Color.BLUE);
        if(!hasBlue){
            assertEquals(4,BlueStudents);
        }
        else{
            assertEquals(5,BlueStudents);
        }
    }

    @Test
    public void MergeIslandsTowers(){
        Match match = new Match(3,1);
        match.getTable().get(2).IncreaseTower();
        match.getTable().get(2).IncreaseTower();
        match.getTable().get(3).IncreaseTower();
        match.MergeIslands(2,3);
        int numberOfTowers = match.getTable().get(2).CountTowers();
        assertEquals(3,numberOfTowers);
    }

    @Test
    public void MergeIslandMotherNaturePosition(){
        Match match = new Match(3,1);
        match.MoveMotherNature(7);
        match.MergeIslands(5,6);
        int position=match.getMotherNaturePosition();
        assertEquals(6,position);
    }

    @Test
    public void MergeIslandMotherNaturePositionOnMergedIsland(){
        Match match = new Match(3,1);
        match.MoveMotherNature(6);
        match.MergeIslands(5,6);
        int position=match.getMotherNaturePosition();
        assertEquals(5,position);
    }

    @Test
    public void MergeIslandMotherNaturePositionOnIslandToMerge(){
        Match match = new Match(3,1);
        match.MoveMotherNature(5);
        match.MergeIslands(5,6);
        int position=match.getMotherNaturePosition();
        assertEquals(5,position);
    }

    @Test
    public void MergeIslandToStart(){
        Match match = new Match(3,1);
        match.MoveMotherNature(11);
        match.MergeIslands(11,0);
        int position=match.getMotherNaturePosition();
        assertEquals(10,position);
    }

    @Test
    public void MergeIslandToStart2(){
        Match match = new Match(3,1);
        match.MoveMotherNature(11);
        match.MergeIslands(0,11);
        int position=match.getMotherNaturePosition();
        assertEquals(0,position);
    }

    @Test
    public void MergeIslandToStart3(){
        Match match = new Match(3,1);
        match.MergeIslands(11,0);
        int position=match.getMotherNaturePosition();
        assertEquals(11,position);
    }

    @Test
    public void MergeIslandToStart4(){
        Match match = new Match(3,1);
        match.MergeIslands(0,11);
        int position=match.getMotherNaturePosition();
        assertEquals(0,position);
    }
}
