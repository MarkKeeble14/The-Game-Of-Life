module game {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.desktop;

	opens World to javafx.fxml;
	exports World;
}