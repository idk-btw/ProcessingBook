package com.example.processingbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з базою даних
 */
public class DBConnection {
    /**
     * Змінна для підключення до бази даних
     */
    protected String dbHost = "127.0.01";
    /**
     * Змінна для підключення до бази даних
     */
    protected String dbPort = "3306";
    /**
     * Змінна для підключення до бази даних
     */
    protected String dbUser = "root";
    /**
     * Змінна для підключення до бази даних
     */
    protected String dbPass = "";
    /**
     * Змінна для підключення до бази даних
     */
    protected String dbName = "processingbook";
    /**
     * Змінна для підключення до бази даних
     */
    Connection connection;

    /**
     * Підключення до бази даних
     *
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return connection;
    }

    /**
     * Перевірка правильності введених логіна та пароля
     *
     * @param login    Отримує логін користувача з бази даних
     * @param password Отримує пароль користувача з бази даних
     * @return result
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public boolean validateLogin(String login, String password) throws SQLException, ClassNotFoundException {
        boolean result = false;
        DBConnection connectNew = new DBConnection();
        Connection connectDB = connectNew.getDbConnection();

        String verifyLogin = "SELECT * FROM user WHERE login = '" + login + "' and password = '" + password + "';";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);

        if (queryResult.next()) {
            result = true;
        }
        statement.close();
        queryResult.close();
        return result;
    }

    /**
     * Перевірка існування користувача
     *
     * @param login Отримує логін користувача з бази даних
     * @return check
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public boolean userExists(String login) throws SQLException, ClassNotFoundException {
        boolean check = false;

        DBConnection connectNew = new DBConnection();
        Connection connectDB = connectNew.getDbConnection();

        String verifyLogin = "SELECT `login` FROM user WHERE login = '" + login + "';";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);

        if (queryResult.next()) {
            check = true;
        }
        statement.close();
        queryResult.close();
        return check;
    }

    /**
     * Отримання паролю користувача
     *
     * @param login Отримує логін користувача з бази даних
     * @return password
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public String getPass(String login) throws SQLException, ClassNotFoundException {
        DBConnection con = new DBConnection();
        Connection connect = con.getDbConnection();

        String verifyLogin = "SELECT * FROM user WHERE login = '" + login + "';";

        Statement statement = connect.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);
        queryResult.next();

        return queryResult.getString("password");
    }

    /**
     * Функція створення нового користувача
     *
     * @param login    Записує логін для нового користувача у базу данних
     * @param password Записує пароль для нового користувача у базу данних
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public void createNewUser(String login, String password) throws SQLException, ClassNotFoundException {
        try (Statement statement = getDbConnection().createStatement()) {
            String newUser = "INSERT INTO user (login, password) VALUES ('" + login + "','" + password + "')";
            statement.executeUpdate(newUser);
        }
    }

    /**
     * Фукнція видалення користувача
     *
     * @param login Видаляє користувача за логіном
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public void delete(String login) throws SQLException, ClassNotFoundException {
        try (Statement statement = getDbConnection().createStatement()) {
            String delete = "DELETE FROM `user` WHERE `login` = '" + login + "';";
            statement.executeUpdate(delete);
        }
    }

    /**
     * Фукнція зміни пароля користувача
     *
     * @param password Змінна для нового паролю
     * @param login    Змінна для того, щоб у правильного користувача змінювався пароль
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public void update(String password, String login) throws SQLException, ClassNotFoundException {
        try (Statement statement = getDbConnection().createStatement()) {
            String update = "UPDATE `user` SET `password` = '" + password + "' WHERE `login` = '" + login + "';";
            statement.executeUpdate(update);
        }
    }

    /**
     * Коллекція, що отримує назви категорій книги
     *
     * @return functions
     * @throws SQLException           помилка пов'язана з базою даних
     * @throws ClassNotFoundException помилка пошуку якогось класу в classpath
     */
    public List<Function> getFunctions() throws SQLException, ClassNotFoundException {
        List<Function> functions = new ArrayList<>();
        try (Statement statement = getDbConnection().createStatement()) {
            String read = "SELECT * FROM namings, information WHERE namings.id_name = information.id_name";
            ResultSet queryRead = statement.executeQuery(read);
            while (queryRead.next()) {
                Function function = new Function();
                function.setCategory(queryRead.getString("name"));
                function.setCategoryInfo(queryRead.getString("info"));
                functions.add(function);
            }
            statement.close();
            queryRead.close();
        }
        return functions;
    }
}