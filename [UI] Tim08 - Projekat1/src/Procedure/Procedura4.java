package Procedure;

import java.sql.*;
import java.util.Scanner;

public class Procedura4 {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        CallableStatement myCall = null;
        ResultSet myRS = null;
        Scanner sc = new Scanner(System.in);
        String input = "";

        try {

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project01", "student", "student");

            while(true) {
                myCall = myConn.prepareCall("{call Procedura4(?)}");

                System.out.println("Enter store name or exit to quit: ");
                input = sc.nextLine();

                if(input.equals("exit"))
                    break;

                myCall.setString(1, input);
                myCall.execute();

                myRS = myCall.getResultSet();

                while (myRS.next()) {
                    /// Name, NumberOfOrders, AverageNumberOfArticles, TotalSales
                    System.out.println("Store " + myRS.getString("Name") + " had " + myRS.getInt("NumberOfOrders") + " orders with average number of saled items per order " +
                            myRS.getFloat("AverageNumberOfArticles") + " and total sale " + myRS.getFloat("TotalSales"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (myConn != null) {
                myConn.close();
            }

            if (myCall != null) {
                myCall.close();
            }

            if (myRS != null) {
                myRS.close();
            }
        }
    }
}