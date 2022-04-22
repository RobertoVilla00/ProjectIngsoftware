package It.polimi.ingsw.Network;

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
    //private final ObjectInputStream inputStream;
    //private final ObjectOutputStream outputStream;

    public Client(String ip,int port){
        this.ip=ip;
        this.port=port;
    }
    public void run() throws IOException {
        Socket socket=new Socket(ip,port);
        System.out.println("Connection Established");
        ObjectInputStream socketIn= new ObjectInputStream(socket.getInputStream());
        PrintWriter socketOut= new PrintWriter(socket.getOutputStream());
        Scanner stdin=new Scanner(System.in);

        try{
            Thread t0=new Thread();
            Thread t1=new Thread();
            t0.join();
            t1.join();
        }catch (InterruptedException | NoSuchElementException e) {
            System.out.println("Connection closed from the client side");
        }finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
