package It.polimi.ingsw.Network;

import java.io.IOException;
import java.io.PrintWriter;

public class ClientApp {

    public static void main(String[] args) {
        try{
            Client client=new Client("127.0.0.1", 1234);
            client.run();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
