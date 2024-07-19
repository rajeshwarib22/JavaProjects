package bhirud.DBSPT;
import java.sql.*;

public class DBConnect {
    
    private static String jdbc_url = "jdbc:mysql://localhost:3306/";
    private static String username = "root";
    private static String password = "root1234";
    
    public static Connection connectToDB(String database) throws ClassNotFoundException, 
            SQLException{
       
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection dbConnect = DriverManager.getConnection(jdbc_url, username, password);
       System.out.println("Connected to the " + database + " database");
       return dbConnect;
    }
    
    private static void switchToDB(Connection conn,String query) throws ClassNotFoundException,
            SQLException{
       Statement state = conn.createStatement();
       state.execute(query);
    }
    
    public static void UseDB(Connection conn, String database) throws SQLException {
      String query = "USE " + database;
      try (Statement state = conn.createStatement()) {
          state.execute(query);
      }
    }

    
    public static ResultSet executeResultsQuery(String query,String database) throws ClassNotFoundException, 
            SQLException{
       Connection conn = connectToDB(database);
       switchToDB(conn,"USE " + database);
       Statement state = conn.createStatement();
       ResultSet rs = state.executeQuery(query);
       return rs;
    }
    
    public static void executeUpdate(String query, String database) throws ClassNotFoundException, SQLException {
    Connection conn = connectToDB(database);
    switchToDB(conn, "USE " + database);
    Statement state = conn.createStatement();
    int rowsAffected = state.executeUpdate(query);
    //System.out.println(rowsAffected + " rows affected.");
    conn.close(); 
}

    
    
}