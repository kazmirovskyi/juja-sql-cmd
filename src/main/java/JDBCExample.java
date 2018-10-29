import java.sql.*;

public class JDBCExample {

    public static void main(String[] argv) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres", "1309");

        Statement statement = connection.createStatement();

        // INSERT
        // statement.executeUpdate("INSERT INTO users (first_name, last_name) VALUES ('Stiven', 'Pupkin')");

        // DELETE
        statement.executeUpdate("DELETE FROM users as users WHERE users.id > 10");

        // SELECT
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users as users");
        while (resultSet.next()) {
            System.out.println("id: " + resultSet.getString("id") +
                    "; first_name: " + resultSet.getString("first_name") +
                    "; last_name: " + resultSet.getString("last_name")
            );

            String password = resultSet.getString("password");
            if (password == null || password.equals("")){
                // UPDATE
                statement.executeUpdate("UPDATE users SET password = 1 WHERE users.id > " + resultSet.getString("id") + "");
            }
        }

    }
}
