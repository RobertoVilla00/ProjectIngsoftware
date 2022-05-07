package It.polimi.ingsw.Network;

import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Model.Match;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    private String ip;
    private int port;
    private Socket socket;
    private boolean active = true;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public Client(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream=new ObjectOutputStream(socket.getOutputStream());

    }

    public synchronized boolean isActive() {
        return active;
    }

    public synchronized void setActive(boolean active) {
        this.active = active;
    }

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if (inputObject instanceof String) {
                            System.out.println((String) inputObject);
                        } else if (inputObject instanceof Match) {
                            //((Match)inputObject).print();
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                } catch (Exception e) {
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    public Thread asyncWriteToSocket(final Scanner stdin, final PrintWriter socketOut) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        String inputline = stdin.nextLine();
                        socketOut.println(inputline);
                        socketOut.flush();
                    }
                } catch (Exception e) {
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    public void sendMessage(Message message){
        try {
            outputStream.writeObject(message);
            outputStream.flush();
            outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException {
        System.out.println("Connection Established");
        //ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);
        try {
            Thread t0 = asyncReadFromSocket(inputStream);
            Thread t1 = asyncWriteToSocket(stdin, socketOut);
            t1.join();
            t0.join();
        } catch (InterruptedException | NoSuchElementException e) {
            System.out.println("Connection closed from the client side");
        } finally {
            stdin.close();
            inputStream.close();
            socketOut.close();
            socket.close();
        }
    }
}

