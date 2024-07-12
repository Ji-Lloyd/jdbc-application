import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestGround {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String userID = scan.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scan.nextLine();

        System.out.print("Enter middle name: ");
        String middleName = scan.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scan.nextLine();

        scan.close();

        
        final String DATABASEURL = "jdbc:mysql://localhost:3306/userDB";
        final String dbUsername = "root";
        final String dbPassword = "root";

        final String insertUserStatement = "INSERT INTO users (userID, firstName, middleName, lastName) VALUES (?,?,?,?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection sqlConnection = DriverManager.getConnection(DATABASEURL, dbUsername, dbPassword);

           
            PreparedStatement inserStatement= sqlConnection.prepareStatement(insertUserStatement);

            inserStatement.setInt(1, Integer.parseInt(userID));
            inserStatement.setString(2, firstName);
            inserStatement.setString(3, middleName);
            inserStatement.setString(4, lastName);

            inserStatement.executeUpdate();

            Statement readStatement = sqlConnection.createStatement();


            ResultSet resultSet =  readStatement.executeQuery("SELECT * FROM users");

            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " : " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }

            sqlConnection.close();
        }catch(Exception exception){
            exception.printStackTrace();
        }


    }
}
