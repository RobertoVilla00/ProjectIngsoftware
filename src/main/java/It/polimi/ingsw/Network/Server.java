package It.polimi.ingsw.Network;

import It.polimi.ingsw.Controller.GameController;
import It.polimi.ingsw.Controller.RoundController;
import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.Message;
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
    private RoundController roundController;

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
        if (connections.size() == 0 || connections.size() < numberOfPlayer) {
            AddConnection(c);
            VirtualView virtualView=new VirtualView(c);
            List<Connection> keys = new ArrayList<>(waitConnection.keySet());
            boolean UsedName = false;
            String name;
            name = c.Read().substring(4);
            System.out.println("ho letto "+name);
            do {
                if (UsedName) {
                    c.AsyncSend("Error! this name is already used ");
                    name = c.Read();
                    System.out.println("ho letto "+name);
                }
                UsedName = false;
                for (Connection k : keys) {
                    if (waitConnection.get(k).equalsIgnoreCase(name)) {
                        UsedName = true;
                    }
                }
            } while (UsedName);
            c.AsyncSend("valid name");
            waitConnection.put(c, name);
            if (waitConnection.size() == 1) {
                keys = new ArrayList<>(waitConnection.keySet());
                Connection c1 = keys.get(0);
                c1.AsyncSend("Choose the number of player: 2 or 3 ?");
                while (!(numberOfPlayer == 2 || numberOfPlayer == 3)) {
                    try {
                        String s = c1.Read();
                        int Number = Integer.parseInt(s);

                        if (Number == 2 || Number == 3) {
                            numberOfPlayer = Number;
                            c1.AsyncSend("Valid Number");
                        } else {
                            c1.AsyncSend("Error! choose 2 or 3");
                        }
                    } catch (NumberFormatException e) {
                        c1.AsyncSend((String) "Error! choose 2 or 3");
                    }
                }
            }
        //    if() come ottengo attributo cli del client
        }
        else{
            AddConnection(c);
            c.AsyncSend("too many players!");
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