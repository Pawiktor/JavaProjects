package pl.dev_app.Controllers;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import pl.dev_app.GameSettings.Game;
import pl.dev_app.Main.Main;

public class GameController {

	@FXML
	private Label lowerLabel;

	@FXML
	private Label higherLabel;

	@FXML
	private Label numberLabel;

	@FXML
	private TextField numberTextField;

	@FXML
	private Button playButton;
	
	@FXML
	private Label numberOfTry;
	
	private Stage gameStage;
	private Game game = new Game();

	@FXML
	public void initialize() {
		NumberStringConverter conveter = new NumberStringConverter();
		numberTextField.textProperty().bindBidirectional(game.getTextFieldProperty(), conveter);
		numberLabel.textProperty().bind(game.getTextFieldProperty().asString());
		higherLabel.visibleProperty().bindBidirectional(game.getHigherLabelProperty());
		lowerLabel.visibleProperty().bindBidirectional(game.getLowerLabelProperty());
		numberOfTry.textProperty().bind(game.getNumberOfTryProperty().asString());
	}

	@FXML
	public void play() {
		animation(lowerLabel);
		animation(higherLabel);
		if(game.check()) {
			gameStage.close();
		}
	}
	
	@FXML
	public void exit() {
		Main main = new Main();
		try {
			main.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		gameStage.close();
	}

	public void animation(Label label) {
		TranslateTransition right = new TranslateTransition(Duration.millis(100),label);
		right.toXProperty().set(+15);
		TranslateTransition left = new TranslateTransition(Duration.millis(100),label);
		left.toXProperty().set(-15);
		TranslateTransition left2= new TranslateTransition(Duration.millis(100),label);
		left2.toXProperty().set(0);
		
		SequentialTransition trans = new SequentialTransition(right, left, left2);
		trans.play();
	}

	public void setGameStage(Stage gameStage) {
		this.gameStage = gameStage;
	}
	
	public void setGameStageAtGameClass() {
		game.setGameStage(gameStage);
	}
}
