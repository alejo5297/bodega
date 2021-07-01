/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;

/**
 *
 * @author jagh_
 */
public class Bodega {
    public static String nombre,id,numpedido,usuario,depto,cant,producto,codproduct,fecha;
    public static void main(String[] args) {
    
       Conexion cn= new Conexion();
       cn.conector();
       login ln = new login();
       ln.setVisible(true);
    }
    
}
