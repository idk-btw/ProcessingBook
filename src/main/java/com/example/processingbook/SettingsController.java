package com.example.processingbook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Клас для управління вікном налаштуваннь
 */
public class SettingsController {

    /**
     * Кнопка для зміни пароля
     */
    @FXML
    private Button btnChange;

    /**
     * Кнопка для видалення аккаунту
     */
    @FXML
    private Button btnDelete;

    /**
     * Кнопка для повернення у головне вікно
     */
    @FXML
    private Button btnGoBack;

    /**
     * Текстове поле для введення новго паролю
     */
    @FXML
    private PasswordField newPass;

    /**
     * Функція відкриття головного вікна
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void openBook() throws IOException {
        Stage settings = (Stage) btnGoBack.getScene().getWindow();
        settings.close();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("book.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.resizableProperty().setValue(false);
        stage.setTitle("Processing. Graphic Primitives");
        stage.show();
    }

    /**
     * Функція видалення користувача
     * @throws SQLException помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void deleteUser() throws SQLException, ClassNotFoundException, IOException {
        DBConnection db = new DBConnection();
        db.delete(LoginController.loginUser);
        LoginApplication log = new LoginApplication();
        Stage settings = (Stage) btnDelete.getScene().getWindow();
        settings.close();
        log.start(new Stage());
    }

    /**
     * Функція зміни паролю користувача
     * @throws SQLException помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void changeUserPass() throws SQLException, ClassNotFoundException, IOException {
        DBConnection db = new DBConnection();
        db.getPass(LoginController.loginUser);

        db.update(newPass.getText().trim(), LoginController.loginUser);

        LoginApplication log = new LoginApplication();
        Stage settings = (Stage) btnChange.getScene().getWindow();

        settings.close();

        log.start(new Stage());
    }
}
