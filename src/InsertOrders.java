import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOrders {

    public static void main (String [] args)
    {
        Scanner keyboard = new Scanner(System.in);

        Connection conn = connect();

        try {


            String sql = "INSERT INTO Orders (User_ID,Order_Date,Order_Status,Extra_Comments) VALUES(?,?,?,?)";

            System.out.println("User ID: ");
            String oUser = keyboard.nextLine();
            System.out.println("Date: ");
            String oDate = keyboard.nextLine();
            System.out.println("Status: ");
            String oStatus = keyboard.nextLine();
            System.out.println("Extra Comments: ");
            String extraComments = keyboard.nextLine();



            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, oUser);
            pstmt.setString(2, oDate);
            pstmt.setString(3, oStatus);
            pstmt.setString(4, extraComments);




            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }






    private static Connection connect(){

        String fileName = "Stage2Database.db";
        String url = "jdbc:sqlite:" + fileName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
