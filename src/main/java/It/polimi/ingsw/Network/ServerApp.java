package It.polimi.ingsw.Network;

import java.io.IOException;
import java.util.Scanner;

public class ServerApp {

    public static void main(String[] args) throws InterruptedException {
        Server server;
        try{
            Scanner s=new Scanner(System.in );
            System.out.println("Starting server...");
            Thread.sleep(1000);
            System.out.println("Please type the port number: ");
            int port=Integer.parseInt(s.nextLine());
            server= new Server(port);
            System.out.println("Port Number Accepted");
            server.run();
        }catch (IOException e){
            System.err.println("Impossible to start the server!\n" + e.getMessage());
        }
    }
}
