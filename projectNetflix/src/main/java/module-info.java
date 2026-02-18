module groupna.projectNetflix {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens groupna.projectNetflix to javafx.fxml;
    exports groupna.projectNetflix;
}
