package It.polimi.ingsw.Network;


import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Message.MessageContent;
import It.polimi.ingsw.Observer.Observable;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection extends Observable implements Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    //private Scanner in;
    //private PrintWriter out;
    private Server server;
    //private String Nickname;
    private boolean active=true;
    private Object lock;


    public Connection(Socket socket, Server server) throws IOException {
        this.socket=socket;
        this.server=server;
        this.lock=new Object();

        //in=new ObjectInputStream(socket.getInputStream());
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

    public String Read() throws NoSuchElementException, IOException {
        Scanner read=new Scanner(socket.getInputStream());
        String line=read.nextLine();
        return line;
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
        Scanner scanner;
        String Nickname;
        try{
            scanner=new Scanner(socket.getInputStream());
            out= new ObjectOutputStream(socket.getOutputStream());
            SendMessage("Welcome in Eryantis!! What's your nickname?");
            server.Lobby(this);
            //TODO: inizia gestione regolare view
            while (isActive()){
                handleConnection();
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }finally {
            Close();
        }
    }

    private void handleConnection(){
        try{
            synchronized (lock){
                Message message=(Message)in.readObject();           ///PROBLEMA QUI in non inizializzato

                server.handleReceivedMessage(message);
            }
        }
        catch (ClassNotFoundException | IOException e){
            //todo:handle exceptions
        }
    }
}
