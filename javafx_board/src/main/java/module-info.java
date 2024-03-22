module com.joeun {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.joeun to javafx.fxml;
    exports com.joeun;
}
