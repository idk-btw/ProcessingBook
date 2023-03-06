package com.example.processingbook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Клас для управління вікном логіну
 */
public class LoginController {

    /**
     * Кнопка для входу в аккаунт
     */
    @FXML
    private Button btnLogin;

    /**
     * Кнопка для відкриття вікна регістрації
     */
    @FXML
    private Button btnNewUser;

    /**
     * Текстове поле для вводу логіну
     */
    @FXML
    public TextField loginField;

    /**
     * Текстове поле для вводу паролю
     */
    @FXML
    public PasswordField passwordField;

    /**
     * Текстове поле з повідомленням про помилку
     */
    @FXML
    private Label labelMessage;

    /**
     * Статична змінна, що записує в себе логін користувача для подальшого видалення аккаунту або зміни паролю
     */
    public static String loginUser;

    /**
     * Функція, що відкриває вікно регістрації
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void openRegistration() throws IOException{
        Stage login = (Stage)btnNewUser.getScene().getWindow();
        login.close();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("registration.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.resizableProperty().setValue(false);
        stage.setTitle("Processing. Graphic Primitives");
        stage.show();
    }

    /**
     * Фукнція, що відкриває головне вікно,та перевіряє правильність введених пароля та логіна
     * @throws SQLException помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void openBook() throws SQLException, ClassNotFoundException, IOException {
        DBConnection dbConnection = new DBConnection();
        if (!loginField.getText().trim().equals("") && !passwordField.getText().trim().equals("")) {
            if (dbConnection.validateLogin(loginField.getText().trim(), passwordField.getText().trim())) {
                loginUser = loginField.getText().trim();
                Stage login = (Stage)btnLogin.getScene().getWindow();
                login.close();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("book.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.resizableProperty().setValue(false);
                stage.setTitle("Processing. Graphic Primitives");
                stage.show();
            }else{
                labelMessage.setLayoutX(119);
                labelMessage.setText("Логін або пароль не вірні");
                labelMessage.setOpacity(0.6);
            }
        } else {
            labelMessage.setLayoutX(124);
            labelMessage.setText("Введіть логін та пароль");
            labelMessage.setOpacity(0.6);
        }
    }
}