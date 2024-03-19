import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {
    //test connection
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //String url = "jdbc:postgresql://localhost:5432/students";
        String baseUrl = "jdbc:postgresql://localhost:5432/";
        System.out.println("Enter database name: ");
        String dbName = scanner.nextLine();

        // Construct the full URL
        String url = baseUrl + dbName;

        System.out.println("Enter username: ");
        String user = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        try {
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                if (conn != null) {
                    System.out.println("Connected to PostgreSQL successfully!");
                } else {
                    System.out.println("Failed to establish connection.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection connect() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        // The base part of the URL, without the database name
        String baseUrl = "jdbc:postgresql://localhost:5432/";
        System.out.println("Enter database name: ");
        String dbName = scanner.nextLine();

        // Construct the full URL
        String url = baseUrl + dbName;
        //String url = "jdbc:postgresql://localhost:5432/students";
        System.out.println("Enter username('postgres'): ");
        String user = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
