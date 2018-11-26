package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.user.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersDAO {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://ec2-54-246-85-234.eu-west-1.compute.amazonaws.com:5432/d38fg66uk8rvv9?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

    //  Database credentials
    private static final String USER = "ztacdohjspyclv";
    private static final String PASS = "3d0cc2c4883e31c3a81df63bae6235dd3812609b831da382a3737b20df5e5aad";

    private Connection connection;

    public UsersDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private ResultSet executeQuery(String query) {

        Statement stmt;
        try {
            stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    private void executeUpdate(String query) {

        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.setAutoCommit(true);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

    }

    public List<User> getAll() {

        ResultSet rs = executeQuery("SELECT * from VS_USERS");
        List<User> result = new ArrayList<>();

        if (rs != null) {
            try {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String lastName = rs.getString("last_name");
                    Integer age = rs.getInt("age");

                    result.add(new User(id, age, name, lastName));
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    public Optional<User> getById(Long id) {

        ResultSet rs = executeQuery("SELECT * from VS_USERS WHERE id = " + id);

        if (rs != null) {
            try {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String lastName = rs.getString("last_name");
                    Integer age = rs.getInt("age");
                    rs.close();

                    return Optional.of(new User(id, age, name, lastName));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty(); // не совсем правильное решение, так как можно подумать, что таск не существует, если просто нет соединения

    }

    public void insert(User user) {

        String name = user.getName();
        String lastName = user.getLastName();
        Integer age = user.getAge();

        String query = "INSERT INTO VS_USERS (name, last_name, age) " +
                "VALUES('" +
                name + "', '" +
                lastName + "', " +
                age + ");";

        executeUpdate(query);

    }

    public void update(Long userId, User newUser) {

        String query = "UPDATE VS_USERS " +
                "SET name = '" + newUser.getName() + "', " +
                "last_name = '" + newUser.getLastName() + "', " +
                "age = " + newUser.getAge() +
                "WHERE id = "+ userId +";";

        executeUpdate(query);

    }

    public void delete(Long userId) {

        String query = "DELETE FROM VS_USERS " +
                "WHERE id = "+ userId +";";

        executeUpdate(query);

    }

}
