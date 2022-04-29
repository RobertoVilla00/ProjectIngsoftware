package It.polimi.ingsw.Network;

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

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(Port);
    }

    public synchronized void AddConnection(Connection c) {
        connections.add(c);
    }

    public synchronized void DeregisterConnection(Connection c){
        connections.remove(c);
        Connection opponent= playConnection.get(c);
        if(opponent!=null){
            opponent.closeConnection();
            playConnection.remove(c);
            playConnection.remove(opponent);
        }
    }

    public synchronized void Lobby(Connection c, String nickname){
        waitConnection.put(nickname,c);
        List<String> keys = new ArrayList<>(waitConnection.keySet());
        Connection c1=waitConnection.get(keys.get(0));
        Connection c2=waitConnection.get(keys.get(1));
        playConnection.put(c1,c2);
        playConnection.put(c2,c1);
        waitConnection.clear();
    }

    public void run() {
        int connections = 0;
        System.out.println("Server Listening on port: " + Port);
        try {
            Socket socket = serverSocket.accept();
            System.out.println("Connection number: " + connections);
            connections++;
            Connection connection = new Connection(socket, this);
            AddConnection(connection);
            executor.submit(connection);
        }catch (IOException e){
            System.err.println("Connection error");
        }
    }
}