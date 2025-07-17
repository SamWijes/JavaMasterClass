module org.example.layouts {
	requires javafx.controls;
	requires javafx.fxml;


	opens org.example.layouts to javafx.fxml;
	exports org.example.layouts;
}