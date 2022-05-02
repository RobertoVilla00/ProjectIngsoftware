package It.polimi.ingsw.Network;


import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Message.MessageContent;
import It.polimi.ingsw.Observer.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class Connection extends Observable implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    //private Scanner in;
    //private PrintWriter out;
    private Server server;
    //private String Nickname;
    private boolean active=true;


    public Connection(Socket socket, Server server){
        this.socket=socket;
        this.server=server;
    }

    public synchronized void SendMessage(Object message){
        try{
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

    public synchronized boolean isActive(){
        return active;
    }

    public void AsyncSend(final Object message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SendMessage(message);
            }
        }).start();
    }

    public synchronized void closeConnection(){
        SendMessage("Connection closed from the server side");
        try{
            socket.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        active=false;
    }

    public void Close(){
        closeConnection();
        System.out.println("Deregistering client.. ");
        server.DeregisterConnection(this);
        System.out.println("Done!");
    }

    public void run(){
        Scanner in;
        String Nickname;
        try{
            in=new Scanner(socket.getInputStream());
            out= new ObjectOutputStream(socket.getOutputStream());
            SendMessage("Welcome in Eryantis!! What's your nickname?");
            Nickname=in.nextLine();
            server.Lobby(this, Nickname);
            while (isActive()){
                String readIn= in.nextLine();
                System.out.println(readIn.toUpperCase());
                notifyObserver(readIn);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }finally {
            Close();
        }
    }

}
