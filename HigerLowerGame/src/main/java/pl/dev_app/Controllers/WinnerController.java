
package pl.dev_app.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.dev_app.GameSettings.DataBase;
import pl.dev_app.Main.Main;

public class WinnerController {
	
	@FXML
	private TextField nameTextField;
	
	private Stage gameStage;
	private Stage currentStage;
	
	private int moves = 0;
	
	DataBase db = new DataBase();
	
	@FXML
	public void saveButton() {
		db.insertWinners(nameTextField.getText(), moves);
		db.closeDb();
		Main main = new Main();
		try {
			main.start(new Stage());
			gameStage.close();
			currentStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public void setGameStage(Stage gameStage) {
		this.gameStage = gameStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}
	
	
}
