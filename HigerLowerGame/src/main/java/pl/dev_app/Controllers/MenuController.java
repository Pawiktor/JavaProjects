package pl.dev_app.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private Button startGameButton;
	
	@FXML
	private Button scoresButton;
	
	@FXML
	private Button exitButton;
	
	private MainController mainController;

	@FXML
	public void showScores() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ScoreScreen.fxml"));
		BorderPane borderPane = null;
		try {
			borderPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainController.setScreen(borderPane);
		ScoreScreenController scoreScreenController = loader.getController();
		scoreScreenController.setMainController(mainController);
	}
	
	@FXML
	public void startGame() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameScreen.fxml"));
		BorderPane borderPane = null;
		try {
			borderPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(borderPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Lower/Higher");
		stage.setResizable(false);
		closeMenu();
		stage.show();

		GameController gameController = loader.getController();
		gameController.setGameStage(stage);
		gameController.setGameStageAtGameClass();
	}

	@FXML
	public void exitApp() {
		System.exit(0);
	}
	
	private void closeMenu() {
		Stage mainStage = mainController.getMainStage();
		mainStage.close();
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
