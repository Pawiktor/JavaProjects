package pl.dev_app.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.dev_app.Controllers.MainController;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainScreen.fxml"));
		Pane pane = loader.load();
		Scene scene = new Scene(pane,600,400);
		primaryStage.setTitle("Lower/Higher");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		MainController controller = loader.getController();
		controller.setMainStage(primaryStage);
	}
}
