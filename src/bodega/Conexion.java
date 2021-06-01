/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author jagh_
 */
public class Conexion {
    public static String user = "admin";
    public static String pass = "Sistemas123*";
    
   private static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/bodega?useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false";
    
    public Connection conector() {
        con=null;
        try{
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado");
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println("Error de conexion" + e);
        }
    return con;
    }
    public void cierraConexion() {
    try {
        con.close();
        System.out.println("Desconectado");
    } catch (SQLException sqle) {
        JOptionPane.showMessageDialog(null, "Error al cerrar conexion", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}


