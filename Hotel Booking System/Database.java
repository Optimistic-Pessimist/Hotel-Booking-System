import java.sql.*;

public class Database
{
    public Connection conn;
    public Statement statement;
    public ResultSet resultSet;

    public String url = "jdbc:mysql://localhost/hbs?useSSL=false&serverTimezone=UTC";

    public Database()
    {
        conn = null;
        statement = null;
        resultSet = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            statement = conn.createStatement();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
}