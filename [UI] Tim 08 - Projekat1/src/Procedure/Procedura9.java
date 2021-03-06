package Procedure;

import java.sql.*;
import java.util.Scanner;

public class Procedura9 {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        CallableStatement myCall = null;
        ResultSet myRS = null;

        try {

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventureworks", "student", "student");

            while(true) {
                myCall = myConn.prepareCall("{call Procedura9(?)}");

                System.out.println("Enter model name or 'exit' to quit:");

                Scanner sc = new Scanner(System.in);

                String productName = sc.nextLine();

                if(productName.equals("exit")) break;

                //HL Mountain Tire
                myCall.setString(1, productName);
                myCall.execute();

                myRS = myCall.getResultSet();

                while (myRS.next()) {
                    // FirstName, LastName, CountryRegionCode
                    System.out.println(myRS.getString("FirstName") + " " + myRS.getString("LastName") + " from " + myRS.getString("CountryRegionCode"));
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
