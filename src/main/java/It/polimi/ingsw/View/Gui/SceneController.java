package It.polimi.ingsw.View.Gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneController {

	private static Stage stage;


	public static void SwitchToAskName(Event e){
		try {
			FXMLLoader loader =  new FXMLLoader(SceneController.class.getClassLoader().getResource("fxml/askName.fxml"));
			Parent root = loader.load();
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			//String css= this.getClass().getResource("/CSS/Style.css").toExternalForm();
			//scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setWidth(1280);
			stage.setHeight(950);
			stage.setResizable(false);
			stage.setTitle("ERIANTYS");
			stage.show();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static void SwitchToAskPlayers(Event e){
		try {
			FXMLLoader loader =  new FXMLLoader(SceneController.class.getClassLoader().getResource("fxml/askNameAndPlayers.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			//String css= this.getClass().getResource("/CSS/Style.css").toExternalForm();
			//scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setTitle("ERIANTYS");
			stage.show();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
 	public static void changeScene(String fxml){
		try {
			FXMLLoader fxmlLoader=new FXMLLoader(SceneController.class.getClassLoader().getResource(fxml));
			Parent pane= (Parent)fxmlLoader.load();
			if(Objects.equals(fxml, "fxml/boardScene.fxml")){
				fxController.setBoardController(fxmlLoader.getController());
			}
			if(Objects.equals(fxml, "fxml/askName.fxml")){
				fxController.setNicknameController(fxmlLoader.getController());
			}
			stage.getScene().setRoot(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
