package It.polimi.ingsw.Network;

import It.polimi.ingsw.Controller.GameController;
import It.polimi.ingsw.Controller.RoundController;
import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class Server  {
    private static final int Port = 1234;
    private int numberOfPlayer=0;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private List<Connection> connections = new ArrayList<Connection>();
    private Map<Connection, String> waitConnection = new HashMap<>();
    private Map<Connection, Connection> playConnection = new HashMap<>();
    private RoundController roundController;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(Port);
    }

    public synchronized void AddConnection(Connection c) {
        connections.add(c);
    }

    public synchronized void DeregisterConnection(Connection c){
        //connections.remove(c);
        Connection opponent= playConnection.get(c);
        if(opponent!=null) {
            opponent.closeConnection();
        }
            playConnection.remove(c);
            playConnection.remove(opponent);
            /*
            Iterator<String> iterator=waitConnection.keySet().iterator();
            while (iterator.hasNext()){
                if (waitConnection.get(iterator.next()==(c))){
                    iterator.remove();
                }*/
        }


    public synchronized void Lobby(Connection c, String nickname) throws IOException {
        AddConnection(c);
        List<Connection> keys= new ArrayList<>(waitConnection.keySet());
        boolean UsedName= false;
        String name=nickname;
        do {
            if (UsedName) {
                c.AsyncSend("Error! this name is already used ");
                //Scanner in = new Scanner(System.in);
                name = c.Read(); //in.nextLine();
                UsedName = false;
            }
            boolean d = false;
            for (Connection k : keys) {
                if (waitConnection.get(k).equalsIgnoreCase(name)) {
                    d = true;
                }
            }
            if (d) {
                UsedName = true;
            }
        }while (UsedName);
        waitConnection.put(c,name);
        if (connections.size()==1){
            Connection c1=connections.get(0);
            c1.AsyncSend("Choose the number of player: 2 or 3 ?");
            while(!(numberOfPlayer==2 || numberOfPlayer==3)){
                try{
                    //Scanner in = new Scanner(System.in);
                    String s=c1.Read();//in.nextLine();
                    int Number=Integer.parseInt(s);

                    if(Number==2 || Number==3){
                        numberOfPlayer=Number;
                    }else{
                        c1.AsyncSend("Error! choose 2 or 3");
                    }
                }catch (NumberFormatException e){
                    c1.AsyncSend((String)"Error! choose 2 or 3");
                }
            }
        }
    }

    public void run() {
        int connections = 0;
        System.out.println("Server Listening on port: " + Port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Connection number: " + connections);
                connections++;
                Connection connection = new Connection(socket, this);
                executor.submit(connection);
            } catch (IOException e) {
                System.err.println("Connection error");
            }
        }
    }

    public void handleReceivedMessage(Message message) {
        try {
            roundController.MessageHandler(message);
        } catch (NoActivePlayerException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (WrongMessageException e) {
            e.printStackTrace();
        }
    }
}