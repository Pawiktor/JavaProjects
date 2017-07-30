package pl.dev_app.GameSettings;

import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

public class Game {

	private ReadOnlyIntegerWrapper numberPropertyWrapper = new ReadOnlyIntegerWrapper();
	private ReadOnlyIntegerProperty number = numberPropertyWrapper.getReadOnlyProperty();
	private IntegerProperty textFieldProperty = new SimpleIntegerProperty();
	private BooleanProperty lowerLabelProperty = new SimpleBooleanProperty(true);
	private BooleanProperty higherLabelProperty = new SimpleBooleanProperty(true);
	private IntegerProperty numberOfTryProperty = new SimpleIntegerProperty(0);
	private Stage gameStage;
	
	public Game() {
		RandomNumber();
		}

	public void RandomNumber() {
		Random rnd = new Random();
		numberPropertyWrapper.set(rnd.nextInt(100) + 1);
	}

	public Boolean check() {
		numberOfTryProperty.set(getNumberOfTryProperty().get()+1);
		if(getTextFieldProperty().get() == getNumber().get()) {
			Winner win = new Winner(numberOfTryProperty.get(),gameStage);
			return true;
		}
		else if (getTextFieldProperty().get() < getNumber().get()) {
			lowerLabelProperty.set(false);
			higherLabelProperty.set(true);
		}else{
			lowerLabelProperty.set(true);
			higherLabelProperty.set(false);
				
			}
		return false;
		
	}

	public void setGameStage(Stage gameStage) {
		this.gameStage = gameStage;
	}

	public IntegerProperty getNumberOfTryProperty() {
		return numberOfTryProperty;
	}

	public BooleanProperty getLowerLabelProperty() {
		return lowerLabelProperty;
	}

	public void setLowerLabelProperty(BooleanProperty lowerLabelProperty) {
		this.lowerLabelProperty = lowerLabelProperty;
	}

	public BooleanProperty getHigherLabelProperty() {
		return higherLabelProperty;
	}

	public void setHigherLabelProperty(BooleanProperty higherLabelProperty) {
		this.higherLabelProperty = higherLabelProperty;
	}

	public IntegerProperty getTextFieldProperty() {
		return textFieldProperty;
	}

	public void setTextFieldProperty(IntegerProperty textFieldProperty) {
		this.textFieldProperty = textFieldProperty;
	}

	public ReadOnlyIntegerProperty getNumber() {
		return number;
	}

}
