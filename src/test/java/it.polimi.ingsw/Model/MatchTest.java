package it.polimi.ingsw.Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MatchTest {

    @Test
    public void NumberOfPlayers(){
        Match match= new Match(3);
        Player [] players =new Player[3];
        players=match.getPlayers();
        assertNotNull(players[2]);
    }

    @Test
    public void MoveStudentsBagToIsland(){
        Match match = new Match(2);
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
        Match match = new Match(2);
        match.MoveStudentsBagToCloud(1);
        ArrayList<Cloud> clouds=new ArrayList<Cloud>();
        clouds=match.getClouds();
        int blues=clouds.get(1).countStudents(Color.BLUE);
        int reds=clouds.get(1).countStudents(Color.RED);
        int yellows=clouds.get(1).countStudents(Color.YELLOW);
        int pinks=clouds.get(1).countStudents(Color.PINK);
        int greens=clouds.get(1).countStudents(Color.GREEN);
        int students=blues+reds+yellows+pinks+greens;
        assertEquals(3,students);
    }

}
