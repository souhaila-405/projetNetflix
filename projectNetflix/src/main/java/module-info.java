module groupna.projectNetflix {
    requires javafx.controls;
    requires javafx.fxml;

    opens groupna.projectNetflix to javafx.fxml;
    exports groupna.projectNetflix;
}
