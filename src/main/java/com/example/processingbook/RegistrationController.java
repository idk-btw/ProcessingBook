package com.example.processingbook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Клас для управління вікном регістрації
 */
public class RegistrationController {
    /**
     * Кнопка для створення користувача та відкриття вікна логіну
     */
    @FXML
    private Button btnCreate;

    /**
     * Кнопка для повернення у вікно логіну
     */
    @FXML
    private Button btnExistingUser;

    /**
     * Текстове поле для вводу нового логіну
     */
    @FXML
    private TextField newLoginField;

    /**
     * Текстове поле для вводу нового паролю
     */
    @FXML
    private PasswordField newPasswordField;

    /**
     * Текстове поле для перевірки правильності вводу нового паролю
     */
    @FXML
    private PasswordField newPasswordConfirmField;

    /**
     * Текстове поле для повідомлення помилки
     */
    @FXML
    public Label labelDoesNotMatch;

    /**
     * Функція для відкриття вікна логіну
     * @throws IOException помилка введення-виведення
     */
    @FXML
    public void openLogin() throws IOException {
        Stage registration = (Stage) btnExistingUser.getScene().getWindow();
        registration.close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.showAndWait();
    }
    /**
     * Функція створення нового користувача та відкриття вікна логіну та перевірок зв'язаних з створенням користувача
     * @throws SQLException помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    @FXML
    public void create() throws SQLException, ClassNotFoundException {
        if (!newLoginField.getText().trim().equals("") && !newPasswordField.getText().trim().equals("")) {
            DBConnection dbConnection = new DBConnection();
            if (newPasswordField.getText().trim().equals(newPasswordConfirmField.getText().trim())) {
                if (!dbConnection.userExists(newLoginField.getText().trim())){
                    dbConnection.createNewUser(newLoginField.getText().trim(), newPasswordField.getText().trim());
                    Stage registration = (Stage) btnCreate.getScene().getWindow();
                    registration.close();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("login.fxml"));

                    try {
                        fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }else {
                    labelDoesNotMatch.setLayoutX(80);
                    labelDoesNotMatch.setText("Користувач з таким ім'ям вже існує");
                    labelDoesNotMatch.setOpacity(0.8);
                }
            } else {
                labelDoesNotMatch.setLayoutX(128);
                labelDoesNotMatch.setText("Паролі не співпадають");
                labelDoesNotMatch.setOpacity(0.8);
            }
        } else {
            labelDoesNotMatch.setLayoutX(120);
            labelDoesNotMatch.setText("Придумайте свій пароль");
            labelDoesNotMatch.setOpacity(0.8);
        }
    }
}