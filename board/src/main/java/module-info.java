module com.joeun {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.joeun to javafx.base, javafx.graphics, javafx.fxml;
    opens com.joeun.Controller to javafx.fxml;
    opens com.joeun.DTO to javafx.base;
    
    // exports com.joeun;
}
