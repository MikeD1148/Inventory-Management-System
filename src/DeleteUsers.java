import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteUsers {

    public static void main (String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        ViewUsers view = new ViewUsers();

        System.out.println("Which User would you like to delete");
        view.main(args);
        System.out.println("Enter the ID: ");
        int deleteID = keyboard.nextInt();


        DeleteUsers delete = new DeleteUsers();
        delete.deleteRecord(deleteID);



    }

    public void deleteRecord(int User_ID){

        Connection conn = connect();
        String sql = "DELETE FROM Users WHERE User_ID = ?";



        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, User_ID);
            pstmt.executeUpdate();
            System.out.println("A record has been deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }





    private static Connection connect(){

        String fileName = "Stage2Database.db";
        String url = "jdbc:sqlite:" + fileName;
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
