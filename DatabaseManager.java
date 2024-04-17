import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            // establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/role_based_db", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method to read data based on role
    public ResultSet readData(String role, String collegeId, String section, String studentId) throws SQLException {
        PreparedStatement statement;
        ResultSet resultSet = null;

        switch (role) {
            case "super_admin":
                statement = connection.prepareStatement("SELECT * FROM students");
                resultSet = statement.executeQuery();
                break;
            case "admin":
                statement = connection.prepareStatement("SELECT * FROM students WHERE college_id = ?");
                statement.setString(1, collegeId);
                resultSet = statement.executeQuery();
                break;
            case "teacher":
                statement = connection.prepareStatement("SELECT * FROM students WHERE section = ?");
                statement.setString(1, section);
                resultSet = statement.executeQuery();
                break;
            case "student":
                statement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
                statement.setString(1, studentId);
                resultSet = statement.executeQuery();
                break;
        }

        return resultSet;
    }

    // method to write data to the database
    public void writeData(String name, String section, String marks) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, section, marks) VALUES (?, ?, ?)");
        statement.setString(1, name);
        statement.setString(2, section);
        statement.setString(3, marks);
        statement.executeUpdate();
    }

    // method to update data in the database
    public void updateData(String id, String name, String section, String marks) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE students SET name = ?, section = ?, marks = ? WHERE id = ?");
        statement.setString(1, name);
        statement.setString(2, section);
        statement.setString(3, marks);
        statement.setString(4, id);
        statement.executeUpdate();
    }

    // method to delete data from the database
    public void deleteData(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
        statement.setString(1, id);
        statement.executeUpdate();
    }

    // close database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
