package org.example.scenebuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

	@FXML
	private Label label;

	@FXML
	protected void handleAction() {
		label.setText("Ok button pressed");
	}
}