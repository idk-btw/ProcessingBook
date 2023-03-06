module com.example.processingbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.processingbook to javafx.fxml;
    exports com.example.processingbook;
}