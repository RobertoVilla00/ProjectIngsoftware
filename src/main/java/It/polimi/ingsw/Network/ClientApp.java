package It.polimi.ingsw.Network;

import It.polimi.ingsw.View.Cli.Cli;
import It.polimi.ingsw.View.Gui.Gui;
import It.polimi.ingsw.View.Gui.fxGui;
import javafx.application.Application;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main of the client app
 */

public class ClientApp {

	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			System.out.println("Please type the ip address: ");
			String ip = s.nextLine();
			System.out.println("Please type the number port :");
			int p = Integer.parseInt(s.nextLine());

			boolean useCli = false;
			boolean useGui = false;
			System.out.println("Insert cli or gui");
			String par = s.nextLine();
			System.out.println(par);
			if (par.equalsIgnoreCase("cli")) {
				useCli = true;
			}
			else if (par.equalsIgnoreCase("gui")) {
				useGui = true;
			}
			Client client = new Client(ip, p,useGui);
			if (useCli) {
				Cli cli = new Cli();
				client.addObserver(cli);
				cli.addObserver(client);
			}
			if(useGui){
				Gui gui = new Gui();
				client.addObserver(gui);
				gui.addObserver(client);
				client.run();
				Application.launch(fxGui.class);//todo: mettere questo in un posto giusto
			}
			client.run();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
