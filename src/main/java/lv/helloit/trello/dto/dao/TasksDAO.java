package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.task.TaskStatus;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TasksDAO {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://ec2-54-246-85-234.eu-west-1.compute.amazonaws.com:5432/d38fg66uk8rvv9?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

    //  Database credentials
    private static final String USER = "ztacdohjspyclv";
    private static final String PASS = "3d0cc2c4883e31c3a81df63bae6235dd3812609b831da382a3737b20df5e5aad";

    private Connection connection;

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

    public TasksDAO() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }

    public List<Task> getAll() {

        ResultSet rs = executeQuery("SELECT * from VS_TASKS");
        List<Task> result = new ArrayList<>();

        if (rs != null) {
            try {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long assignedUserId = rs.getLong("assigned_user_id");
                    String status = rs.getString("status");

                    result.add(new Task(id, title, description, assignedUserId, TaskStatus.valueOf(status.toUpperCase())));
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    public Optional<Task> getById(Long id) {

        ResultSet rs = executeQuery("SELECT * from VS_TASKS WHERE id = " + id);

        if (rs != null) {
            try {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long assignedUserId = rs.getLong("assigned_user_id");
                    String status = rs.getString("status");
                    rs.close();

                    return Optional.of(new Task(id, title, description, assignedUserId, TaskStatus.valueOf(status.toUpperCase())));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty(); // не совсем правильное решение, так как можно подумать, что таск не существует, если просто нет соединения

    }

    public void insert(Task task) {

        String title = task.getTitle();
        String description = task.getDescription();
        Long assignedUserId = task.getAssignedUserId();
        String status = task.getTaskStatus().toString();

        String query = "INSERT INTO VS_TASKS (title, description, assigned_user_id, status) " +
                "VALUES('" +
                title + "', '" +
                description + "', " +
                assignedUserId + ", '" +
                status + "');";

        executeUpdate(query);

    }

    public void delete(Long taskId) {

        String query = "DELETE FROM VS_TASKS " +
                "WHERE id = "+ taskId +";";

        executeUpdate(query);

    }

    public void update(Long taskId, Task task) {

        String query = "UPDATE VS_TASKS " +
                "SET title = '" + task.getTitle() + "', " +
                "description = '" + task.getDescription() + "', " +
                "assigned_user_id = " + task.getAssignedUserId() +
                "WHERE id = "+ taskId +";";

        executeUpdate(query);

    }

}
