package It.polimi.ingsw.Network;

import It.polimi.ingsw.View.Cli.Cli;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main of the client app
 */

public class ClientApp {

    public static void main(String[] args) {
        try{
            Scanner s=new Scanner(System.in);
            System.out.println("Please type the ip address: ");
            String ip=s.nextLine();
            System.out.println("Please type the number port :");
            int p=Integer.parseInt(s.nextLine());

            boolean useCli=false;
            System.out.println("Insert cli or gui");
            String par=s.nextLine();
            System.out.println(par);
            if(par.equalsIgnoreCase("cli")){
                useCli=true;
            }
            Client client=new Client(ip, p);
            if(useCli){
                Cli cli = new Cli();
            }
            client.run();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
