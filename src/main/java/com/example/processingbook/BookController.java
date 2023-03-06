package com.example.processingbook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Клас для управління головної сторінки додатку
 */
public class BookController {

    /**
     * Текстове поле для запису в нього розділу книги
     */
    @FXML
    private Label labelHeader;

    /**
     * Текстове поле для інформації з певного розділу книги
     */
    @FXML
    private Label labelInfo;

    /**
     * Поле для створення кнопок з назвами розділів книги
     */
    @FXML
    private VBox scrollHeader;

    /**
     * Кнопка для переходу в налаштування
     */
    @FXML
    private Button btnSettings;

    /**
     * Отримує назви розділів книги та створює для кожного розділу окремі кнопки
     *
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        DBConnection db = new DBConnection();
        List<Function> functions = db.getFunctions();

        for (Function function : functions) {
            Button button = new Button();
            button.setText(function.getCategory());
            button.setOnMouseClicked(mouseEvent -> {
                labelHeader.setText(function.getCategory());
                labelInfo.setText(function.getCategoryInfo());
            });
            button.setMinWidth(200);
            scrollHeader.getChildren().add(button);
        }
    }

    /**
     * Відкриває налаштування
     *
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void openSettings() throws IOException {
        Stage book = (Stage) btnSettings.getScene().getWindow();
        book.close();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("settings.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.resizableProperty().setValue(false);
        stage.setTitle("Processing. Graphic Primitives");
        stage.show();
    }
}