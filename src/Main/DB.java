package Main;

import java.sql.*;

public class DB
{
    private static Connection con;
    private static Statement statement;

    // Initialize a DB connection.
    public static void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/octodb", "root", Globals.SQLGATE);
            statement = con.createStatement();
        } catch (Exception e) {  }
    }

    public static synchronized void exec(String query) throws SQLException
    {
        statement.execute(query);
    }

    public static synchronized ResultSet get(String query) throws SQLException
    {
        return statement.executeQuery(query);
    }

}


