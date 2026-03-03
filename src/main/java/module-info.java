module com.example.testing2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.testing2 to javafx.fxml;
    exports com.example.testing2;
    exports com.example.testing2.Controller;
    opens com.example.testing2.Controller to javafx.fxml;
}