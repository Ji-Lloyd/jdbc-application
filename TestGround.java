import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestGround {
    public static void main(String[] args) {

        UserDAO userClass = new UserDAO(); ;
        Scanner scan = new Scanner(System.in);

        final String DATABASEURL = "jdbc:mysql://localhost:3306/userDB";
        final String dbUsername = "root";
        final String dbPassword = "root";

        final String insertUserQuery = "INSERT INTO users (userID, firstName, middleName, lastName) VALUES (?,?,?,?)";
        final String retrieveUserQuery = "SELECT * FROM users";
        final String updateUserQuery = "UPDATE users SET firstName = ?, middleName = ?, lastName = ? WHERE userID = ?";
        final String deleteUserQuery = "DELETE FROM users WHERE userID = ?";

        Connection sqlConnection;

        System.out.print("->JDBC Application<- \n1: Insert User \n2: Retrieve All User \n3: Update User \n4: Delete User \n5: Exit System \nChoose: ");
        int clientChoice = scan.nextInt();

        System.out.println("____________________________________");
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(DATABASEURL, dbUsername, dbPassword);

            Statement readStatement = sqlConnection.createStatement();

            switch (clientChoice) {
                case 1:
                    PreparedStatement insertStatement= sqlConnection.prepareStatement(insertUserQuery);

                    System.out.print("Enter ID: ");
                    userClass.setUserID(scan.nextLine());
            
                    System.out.print("Enter first name: ");
                    userClass.setFirstName(scan.nextLine());
            
                    System.out.print("Enter middle name: ");
                    userClass.setMiddleName(scan.nextLine());
            
                    System.out.print("Enter last name: ");
                    userClass.setLastName(scan.nextLine());
        
                    insertStatement.setInt(1, Integer.parseInt(userClass.getUserID()));
                    insertStatement.setString(2, userClass.getFirstName());
                    insertStatement.setString(3, userClass.getMiddleName());
                    insertStatement.setString(4, userClass.getLastName());
        
                    insertStatement.executeUpdate();
                    break;

                case 2:
                    ResultSet resultSet =  readStatement.executeQuery(retrieveUserQuery);
                    while(resultSet.next()){
                        System.out.println(resultSet.getString(1) + " : " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
                    }
                    break;

                case 3: 
                    PreparedStatement updateStatement= sqlConnection.prepareStatement(updateUserQuery);

                    System.out.print("Enter ID: ");
                    userClass.setUserID(scan.nextLine());
            
                    System.out.print("Enter first name: ");
                    userClass.setFirstName(scan.nextLine());
            
                    System.out.print("Enter middle name: ");
                    userClass.setMiddleName(scan.nextLine());
            
                    System.out.print("Enter last name: ");
                    userClass.setLastName(scan.nextLine());

                    updateStatement.setString(1, userClass.getFirstName());
                    updateStatement.setString(2, userClass.getMiddleName());
                    updateStatement.setString(3, userClass.getLastName());
                    updateStatement.setInt(4, Integer.parseInt(userClass.getUserID()));

                    updateStatement.executeUpdate();
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    int deleteInput = scan.nextInt();

                    PreparedStatement deleteStatement = sqlConnection.prepareStatement(deleteUserQuery);
        
                    deleteStatement.setInt(1, deleteInput);
                    deleteStatement.executeUpdate();
        
                    ResultSet updatedResultSet =  readStatement.executeQuery(retrieveUserQuery);
                    System.out.println("Updated User List....");
                    while(updatedResultSet.next()){
                        System.out.println(updatedResultSet.getString(1) + " : " + updatedResultSet.getString(2) + " " + updatedResultSet.getString(3) + " " + updatedResultSet.getString(4));
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
