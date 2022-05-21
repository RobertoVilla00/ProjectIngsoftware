package It.polimi.ingsw.Network;

import It.polimi.ingsw.Controller.GameController;
import It.polimi.ingsw.Controller.RoundController;
import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.View.VirtualView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class Server  {
    private int numberOfPlayer=0;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private List<Connection> connections = new ArrayList<Connection>();
    private Map<Connection, String> waitConnection = new HashMap<>();
    private Map<Connection, Connection> playConnection = new HashMap<>();
    private RoundController controller;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
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


    public synchronized void Lobby(Connection c) throws IOException {
        System.out.println("nella lobby");
        if (connections.size() == 0 || connections.size() < numberOfPlayer) {
            AddConnection(c);
            PlayerIdMessage playerIdMessage=new PlayerIdMessage(connections.size()-1);
            c.AsyncSend(playerIdMessage);
            VirtualView virtualView=new VirtualView(c);
            List<Connection> keys = new ArrayList<>(waitConnection.keySet());
            boolean UsedName = false;
            String name;
            NicknameMessage msg = (NicknameMessage)c.ReadMessage();
            name = msg.getNickname();
            System.out.println("ho letto "+name);
            do {
                if (UsedName) {
                    ErrorMessage errorMessage=new ErrorMessage("The name is already taken!");
                    c.AsyncSend(errorMessage);
                    c.AsyncSend(msg);
                    NicknameMessage nicknameMessage = (NicknameMessage)c.ReadMessage();
                    name = nicknameMessage.getNickname();
                    System.out.println("ho letto "+name);
                }
                UsedName = false;
                for (Connection k : keys) {
                    if (waitConnection.get(k).equalsIgnoreCase(name)) {
                        UsedName = true;
                    }
                }
            } while (UsedName);
            waitConnection.put(c, name);
            if (waitConnection.size() == 1) {
                keys = new ArrayList<>(waitConnection.keySet());
                Connection c1 = keys.get(0);
                PlayersMessage playersMessage = new PlayersMessage();
                c1.AsyncSend(playersMessage);
                controller = new RoundController();
                StartMessage startMessage = (StartMessage)c.ReadMessage();
                numberOfPlayer=startMessage.getNumberOfPlayers();
                try{
                    controller.MessageHandler(startMessage);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            controller.getGameController().getMatch().addObserver(virtualView);
            if (waitConnection.size() == numberOfPlayer){
                controller.getGameController().getMatch().CreateMessage();
            }
        }


        else{
            AddConnection(c);
            DeregisterConnection(c);
        }

    }

    public void run() {
        int connections = 0;
        //System.out.println("Server Listening on port: " + Port);
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
            controller.MessageHandler(message);
        } catch (NoActivePlayerException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (WrongMessageException e) {
            e.printStackTrace();
        }
    }
}