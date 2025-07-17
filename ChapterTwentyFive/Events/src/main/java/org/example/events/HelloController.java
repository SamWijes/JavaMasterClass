package org.example.events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
	@FXML  //we tell java to associate with fxml
	private TextField nameField;
	@FXML
	private Button helloButton;
	@FXML
	private Button byeButton;
	@FXML
	private CheckBox ourCheckBox;
	@FXML
	private Label ourLabel;

	@FXML
	public void initialize() {
		helloButton.setDisable(true);
		byeButton.setDisable(true);
	}


//	@FXML
//	public void onButtonClick() {
//		System.out.println("Hello, " + nameField.getText());
//	}

	@FXML
	public void onButtonClick(ActionEvent e) {
		if (e.getSource().equals(helloButton)) {
			System.out.println("Hello, "+nameField.getText());
		} else if (e.getSource().equals(byeButton)) {
			System.out.println("Bye, "+nameField.getText());
		}
		Runnable task=new Runnable() {
			@Override
			public void run() {
				try {
					String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background thread";
					System.out.println("Im going to sleep on the: "+s);
					Thread.sleep(10000);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background thread";
							System.out.println("Im Updating the label in the : "+s);
							ourLabel.setText("We did something!");
						}
					});
				} catch (InterruptedException event) {

				}
			}
		};

		new Thread(task).start();

		if (ourCheckBox.isSelected()) {
			nameField.clear();
			helloButton.setDisable(true);
			byeButton.setDisable(true);
		}
	}

	@FXML
	public void handleKeyReleased() {
		String text=nameField.getText();
		boolean disableButtons=text.isEmpty() || text.trim().isEmpty();
		helloButton.setDisable(disableButtons);
		byeButton.setDisable(disableButtons);
	}

	@FXML
	private void handleChange() {
		System.out.println("The Checkbox is "+(ourCheckBox.isSelected()? "Checked":"not Checked"));
	}
}