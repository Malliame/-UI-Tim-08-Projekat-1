package Procedure;

import java.sql.*;

public class Procedura4 {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        CallableStatement myCall = null;
        ResultSet myRS = null;

        try {

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project01", "student", "student");

            myCall = myConn.prepareCall("{call Procedura4(?)}");

            myCall.setString(1, "A Bike Store");
            myCall.execute();

            myRS = myCall.getResultSet();

            while(myRS.next()){
                /// Name, NumberOfOrders, AverageNumberOfArticles, TotalSales
                System.out.println("Store " + myRS.getString("Name") + " had " + myRS.getInt("NumberOfOrders") + " orders with average number of saled items per order " +
                        myRS.getFloat("AverageNumberOfArticles") + " and total sale " + myRS.getFloat("TotalSales"));
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