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

public class Server {
    private static final int Port = 1234;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private List<Connection> connections = new ArrayList<Connection>();
    private Map<String, Connection> waitConnection = new HashMap<>();
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


    public synchronized void Lobby(Connection c, String nickname){
        List<String> keys= new ArrayList<>(waitConnection.keySet());
        for (int i=0;i<keys.size();i++){
            Connection connection=waitConnection.get(keys.get(i));
            connection.AsyncSend("Connected User: "+ keys.get(i));
        }
        waitConnection.put(nickname,c);
        if (waitConnection.size()==1){
            c.AsyncSend("Waiting for another player");
        }

         keys = new ArrayList<>(waitConnection.keySet());

        if(waitConnection.size() ==2){
            Connection c1=waitConnection.get(keys.get(0));
            Connection c2=waitConnection.get(keys.get(1));
            playConnection.put(c1,c2);
            playConnection.put(c2,c1);
            waitConnection.clear();
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
                AddConnection(connection);
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