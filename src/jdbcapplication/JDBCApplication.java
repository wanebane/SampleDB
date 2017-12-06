/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class JDBCApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet result = null;
        String dburl = "jdbc:derby://localhost:1527/sample";
        String username = "app";
        String password = "app";

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(dburl, username, password);
            stat = conn.createStatement();
            result = stat.executeQuery("Select name as nama, city as kota from customer");
            while (result.next()) {
                String nama = result.getString("nama");
                String kota = result.getString("kota");
                System.out.println("Nama : " + nama + " di " + kota);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCApplication.class.getName()).log(Level.SEVERE, "Drivers not found", ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
