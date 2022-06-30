package It.polimi.ingsw.View.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The main class of the Gui which implements the method start.
 */
public class fxGui extends Application {


	/**
	 * It used to create the stage of the game and set the main characteristics.
	 * @param stage: it receives the stage of the game.
	 */
	@Override
	public void start(Stage stage){
		try{
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StartScene.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setWidth(1280);
			stage.setHeight(800);
			stage.setResizable(false);
			stage.setTitle("ERIANTYS");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
