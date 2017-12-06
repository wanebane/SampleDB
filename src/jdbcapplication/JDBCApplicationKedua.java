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
public class JDBCApplicationKedua {

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
            result = stat.executeQuery("SELECT Product.DESCRIPTION as description,Purchase.QUANTITY as qty, Product.PURCHASE_COST as cost "
                    + "FROM PRODUCT Product "
                    + "JOIN PURCHASE_ORDER Purchase "
                    + "ON Product.PRODUCT_ID = Purchase.PRODUCT_ID");
            double totalOrder = 0.0;
            while (result.next()) {
                String namaProduk = result.getString("description");
                int qty = result.getInt("qty");
                double cost = result.getDouble("cost");
                double total = qty * cost;
                totalOrder += total;
                String fmt = String.format("Rp. %,.2f", total);
                System.out.println("Nama Produk : " + namaProduk +"\t "+qty+" pcs "+cost+" "+fmt);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCApplicationKedua.class.getName()).log(Level.SEVERE, "Drivers not found", ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCApplicationKedua.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                result.close();
                stat.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCApplicationKedua.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
