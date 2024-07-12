import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestGround {
    public static void main(String[] args) {

        UserDAO userClass = new UserDAO();

        Scanner scan = new Scanner(System.in);

        
        final String DATABASEURL = "jdbc:mysql://localhost:3306/userDB";
        final String dbUsername = "root";
        final String dbPassword = "root";

        final String insertUserStatement = "INSERT INTO users (userID, firstName, middleName, lastName) VALUES (?,?,?,?)";

        Connection sqlConnection;

        System.out.print("1: Insert User \n2: Retrieve All User \n3: Update User \n4: Delete User \n5: Exit System");
        int clientChoice = scan.nextInt();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(DATABASEURL, dbUsername, dbPassword);

            switch (clientChoice) {
                case 1:
                    System.out.print("Enter ID: ");
                    userClass.setUserID(scan.nextLine());
            
                    System.out.print("Enter first name: ");
                    userClass.setFirstName(scan.nextLine());
            
                    System.out.print("Enter middle name: ");
                    userClass.setMiddleName(scan.nextLine());
            
                    System.out.print("Enter last name: ");
                    userClass.setLastName(scan.nextLine());
            
                    PreparedStatement inserStatement= sqlConnection.prepareStatement(insertUserStatement);
        
                    inserStatement.setInt(1, Integer.parseInt(userClass.getUserID()));
                    inserStatement.setString(2, userClass.getFirstName());
                    inserStatement.setString(3, userClass.getMiddleName());
                    inserStatement.setString(4, userClass.getLastName());
        
                    inserStatement.executeUpdate();
                    break;

                case 2:
                    Statement readStatement = sqlConnection.createStatement();


                    ResultSet resultSet =  readStatement.executeQuery("SELECT * FROM users");
        
                    while(resultSet.next()){
                        System.out.println(resultSet.getString(1) + " : " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
                    }
                    break;

                case 5:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Wrong Input. Pick Again...");
                    break;
            }

            sqlConnection.close();
        }catch(Exception exception){
            exception.printStackTrace();
        }finally{
            scan.close();
        }


    }

    
}
