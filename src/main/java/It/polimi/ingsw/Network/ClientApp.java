package It.polimi.ingsw.Network;

import java.io.IOException;

public class ClientApp {

    public static void main(String[] args) {
        Client client=new Client("127.0.0.1", 1234);
        try{
            client.run();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
