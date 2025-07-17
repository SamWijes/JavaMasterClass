module org.example.control {
	requires javafx.controls;
	requires javafx.fxml;


	opens org.example.control to javafx.fxml;
	exports org.example.control;
}