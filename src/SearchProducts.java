import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchProducts {

    public static void main (String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Search Term: ");
        String searchTerm = keyboard.nextLine();

        SearchProducts productSearch = new SearchProducts();
        productSearch.queryProducts(searchTerm);


    }

    public void queryProducts(String Search){

        Connection conn = connect();

        ArrayList<ArrayList<Object>> data;

        try {

            String sql = null;
            Scanner keyboard = new Scanner(System.in);

            System.out.println("Search in Name or Category?");
            String decision = keyboard.nextLine();

            if (decision.equals("Category")){
                sql = "SELECT Product_ID,Product_Name,Product_Category,Product_Description,Product_Supplier,Product_Price,Product_Quantity,Product_Status,Product_location FROM Products WHERE Product_Category = ?";
            } else if(decision.equals("Name")){
                sql = "SELECT Product_ID,Product_Name,Product_Category,Product_Description,Product_Supplier,Product_Price,Product_Quantity,Product_Status,Product_location FROM Products WHERE Product_Name = ?";
            } else{
                System.out.println("Invalid input");
            }

            PreparedStatement sqlStatement  = conn.prepareStatement(sql);

            sqlStatement.setString(1,Search);

            data = new ArrayList<ArrayList<Object>>();
            ResultSet res = sqlStatement.executeQuery();
            {
                while (res.next()) {

                    int pId = res.getInt("Product_ID");
                    String pName = res.getString("Product_Name");
                    String pCategory = res.getString("Product_Category");
                    String pDescription = res.getString("Product_Description");
                    String pSupplier = res.getString("Product_Supplier");
                    double pPrice = res.getDouble("Product_Price");
                    int pQuantity = res.getInt("Product_Quantity");
                    String pStatus = res.getString("Product_Status");
                    String pLocation = res.getString("Product_Location");

                    ArrayList<Object> rec = new ArrayList<Object>();
                    rec.add(pId);
                    rec.add(pName);
                    rec.add(pCategory);
                    rec.add(pDescription);
                    rec.add(pSupplier);
                    rec.add(pPrice);
                    rec.add(pQuantity);
                    rec.add(pStatus);
                    rec.add(pLocation);


                    data.add(rec);

                }
            }
            printData(data);



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


    public static void printData (ArrayList<ArrayList<Object>> data)
    {
        for (int i=0; i<data.size(); i++)
        {
            for (int j=0; j<data.get(i).size(); j++)
            {
                System.out.print(data.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}