package pl.dev_app.GameSettings;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.dev_app.Controllers.MainController;
import pl.dev_app.Controllers.WinnerController;

public class Winner {

	private Stage gameStage;
	
	public Winner(int moves, Stage gameStage) {
		this.gameStage = gameStage;
		getWinnerScreen(moves);
	}
	
	private void getWinnerScreen(int moves) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WinnerScreen.fxml"));
		VBox vBox = null;
		try {
			vBox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(vBox);
		stage.setTitle("Congratulations");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		
		WinnerController winner = loader.getController();
		winner.setMoves(moves);
		winner.setGameStage(gameStage);
		winner.setCurrentStage(stage);
	}
}
