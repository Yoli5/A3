import java.sql.*;
import java.util.Scanner;

public class StudentManagement {

    // Method to display all students
    public static void getAllStudents(Connection conn) throws SQLException {
        //SQL query for this function
        String query = "SELECT * FROM students";
        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            //iterates each row in result set, and print
            while (rs.next()) {
                System.out.println(rs.getInt("student_id") + ", "
                        + rs.getString("first_name") + ", "
                        + rs.getString("last_name") + ", "
                        + rs.getString("email") + ", "
                        + rs.getDate("enrollment_date"));
            }
        }
    }

    // Method to add a new student
    public static void addStudent(Connection conn, String first_name, String last_name, String email, Date enrollment_date)
            throws SQLException {
        //SQL insert Query
        String query = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            //setting values
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, enrollment_date);

            //executed, returns the number of rows affected by the execution
            int addedRows = statement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("Student added successfully!");
            }
        }
    }

    // Method to update a student's email
    public static void updateStudentEmail(Connection conn, int student_id, String new_email) throws SQLException {
        //SQL update Query
        String query = "UPDATE students SET email = ? WHERE student_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, new_email);
            statement.setInt(2, student_id);

            //executed, returns the number of rows affected by the execution
            int updateRows = statement.executeUpdate();
            if (updateRows > 0) {
                System.out.println("Email updated successfully!");
            }
        }
    }

    // Method to delete a student
    public static void deleteStudent(Connection conn, int student_id) throws SQLException {
        //SQL delete Query
        String query = "DELETE FROM students WHERE student_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, student_id);

            //executed, returns the number of rows affected by the execution
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Student deleted successfully!");
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        try (Connection conn = DBConnection.connect()) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Choose an operation:");
            System.out.println("1. Display all students");
            System.out.println("2. Add a new student");
            System.out.println("3. Update a student's email");
            System.out.println("4. Delete a student");
            System.out.println("5. Exit");

            while (true) {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        getAllStudents(conn);//case1: just call the function getALLStudents(conn)
                        break;
                    case 2:
                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter enrollment date (YYYY-MM-DD): ");
                        Date enrollmentDate = Date.valueOf(scanner.nextLine());
                        //store the user input, then call the function addStudent
                        addStudent(conn, firstName, lastName, email, enrollmentDate);
                        break;
                    case 3:
                        System.out.print("Enter student ID to update email: ");
                        int studentIdUpdate = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        //store the user input, then call the function updateStudentEmail
                        updateStudentEmail(conn, studentIdUpdate, newEmail);
                        break;
                    case 4:
                        System.out.print("Enter student ID to delete: ");
                        int studentIdDelete = scanner.nextInt();
                        //store the user input, then call the function deleteStudent
                        deleteStudent(conn, studentIdDelete);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return; //terminate this method
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }
}