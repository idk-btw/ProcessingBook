package com.example.processingbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Головний клас для запуску програми
 */
public class LoginApplication extends Application {
    /**
     * Запускає роботу програми
     * @param stage
     * @throws IOException помилка введення-виведення
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.resizableProperty().setValue(false);
        stage.setTitle("Processing. Graphic Primitives");
        stage.setScene(scene);
        stage.show();
    }
}