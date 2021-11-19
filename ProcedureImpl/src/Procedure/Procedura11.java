package Procedure;

import java.sql.*;
import java.util.Scanner;

public class Procedura11 {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        CallableStatement myCall = null;
        ResultSet myRS = null;

        try {

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/adventureworks", "student", "student");

            while(true) {

                myCall = myConn.prepareCall("{call Procedura11(?)}");

                System.out.println("Uneti jezik:");

                Scanner input = new Scanner(System.in);

                String lang = input.nextLine();

                if(lang.equals("exit")) break;

                myCall.setString(1, lang);
                myCall.execute();

                myRS = myCall.getResultSet();

                if (!myRS.next()) {
                    System.out.println("Ne postoji Uputstvo na ovom jeziku!");
                }

                while (myRS.next()) {
                    /// Name, NumberOfOrders, AverageNumberOfArticles, TotalSales
                    System.out.println(myRS.getString("ProductID") + " "
                            + myRS.getString("Name") + " "
                            + myRS.getString("ListPrice"));
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
