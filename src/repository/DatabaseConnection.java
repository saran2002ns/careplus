package repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private static DatabaseConnection database;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/hospital_management";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
        	System.out.println("Unable to connect to database");
            throw new RuntimeException("Unable to connect to database");
        }
    }

    public static DatabaseConnection getInstance() {
    	 if (database == null) {
    		 database = new DatabaseConnection();
         }
      return database;
    }

    public Connection getConnection() {
        return connection;
    }
}
