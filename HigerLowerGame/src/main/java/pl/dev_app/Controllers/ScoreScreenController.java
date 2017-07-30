package pl.dev_app.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.dev_app.GameSettings.DataBase;

public class ScoreScreenController {

	@FXML
	private Label first;
	
	@FXML
	private Label second;
	
	@FXML
	private Label third;
	
	@FXML
	private Label moves1;
	
	@FXML
	private Label moves2;
	
	@FXML
	private Label moves3;
	
	private MainController mainController;
	
	@FXML
	public void initialize() {
		DataBase db = new DataBase();
		String[][] tab =  db.getWinners();
		first.setText(tab[0][0]);
		second.setText(tab[1][0]);
		third.setText(tab[2][0]);
		moves1.setText(tab[0][1]);
		moves2.setText(tab[1][1]);
		moves3.setText(tab[2][1]);
		db.closeDb();
	}
	
	@FXML
	public void goToMenu() {
		mainController.load();
	}
	
	@FXML
	public void dataReset() {
		DataBase db = new DataBase();
		db.deleteAll();
		mainController.load();
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
