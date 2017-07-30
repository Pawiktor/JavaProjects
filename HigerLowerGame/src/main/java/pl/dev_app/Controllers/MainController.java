package pl.dev_app.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private Pane mainPaneScreen;
	
	private Stage mainStage;
	
	@FXML
	void initialize(){
		load();
	}
	
	public void load() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
		BorderPane borderPane = null;
		try {
			borderPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MenuController menuController = loader.getController();
		menuController.setMainController(this);
		
		if(mainPaneScreen!=null)
		setScreen(borderPane);
	}

	public void setScreen(Parent parent) {
		mainPaneScreen.getChildren().clear();
		mainPaneScreen.getChildren().add(parent);
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public Stage getMainStage() {
		return mainStage;
	}
	
}
