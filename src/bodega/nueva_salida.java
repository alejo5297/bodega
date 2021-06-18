/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jagh_
 */
public class nueva_salida extends javax.swing.JFrame {
    Bodega bd = new Bodega();
    public String numpedido,depto,cant,producto,fecha,codproduct;
    public String nombre = bd.nombre;
    public String id = bd.id;
    
    public nueva_salida() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
        this.numpedido = bd.numpedido;
        this.depto = bd.depto;
        this.cant = bd.cant;
        this.producto = bd.producto;
        this.codproduct=bd.codproduct;
        this.txtnumpedido.setText(numpedido);
        this.txtproducto.setText(producto);
        this.txtdepto.setText(depto);
        this.txtcantidad.setText(cant);
        obtenertipo();
    }
    
    Conexion cn = new Conexion();
    
    public void actualizar(){
    Connection conexion = cn.conector();
    String sql = "UPDATE `bodega`.`pedidos`"
            + "SET `ESTADO` = 'ENTREGADO' WHERE (NO = '"+id+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();   
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
    }
    public void fecha(){
        try {
        Date  fecha=date.getDate();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String fecha2 =f.format(fecha);
        this.fecha = fecha2;
    } catch (Exception e) {
    }
    }
    public void guardar(){
        fecha();
    actualizar();
    Connection conexion = cn.conector();
    String codigo = this.codproduct;
    String unidad = tipotxt.getText();
    String user = nombre;
    String date = this.fecha;
    String depto = this.depto;
    String nopedido = this.numpedido;
    String cant = this.cant;
    String sql;
    if(unidad.equals("UNIDADES")){
        sql = "INSERT INTO `bodega`.`salidas` (`CODIGO`, `USUARIO`, `DEPTO`, `FECHA_EGRESO`, `NO_PEDIDO`"
            + ", `CANT_UNIT`, `CANT_LBS`, `MOTIVO`) VALUES ("+codigo+", '"
            +user+"', '"+depto+"', '"+date+"', "+nopedido+", "+cant+", 0, 'Despacho de producto.');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salida registrada");
            this.setVisible(false);       
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }
    }
    if(unidad.equals("LIBRAS")){
        sql = "INSERT INTO `bodega`.`salidas` (`CODIGO`, `USUARIO`, `DEPTO`, `FECHA_EGRESO`, `NO_PEDIDO`"
            + ", `CANT_UNIT`, `CANT_LBS`, `MOTIVO`) VALUES ("+codigo+", '"
            +user+"', '"+depto+"', '"+date+"', "+nopedido+", 0, "+cant+", 'Despacho de producto.');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salida registrada");
            this.setVisible(false);       
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }finally{
            cn.cierraConexion();
        }
    }
    }
    public void obtenertipo(){
        Connection conexion = cn.conector();
        String sql = "select producto.TIPO_UNIDADES from producto where (producto.CODIGO = "+id+");";
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
              String tipo = result.getString(1);
              tipotxt.setText(tipo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(solicitar_producto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcantidad = new rscomponentshade.RSTextFieldShade();
        rSButtonShade1 = new rscomponentshade.RSButtonShade();
        txtnumpedido = new javax.swing.JLabel();
        txtproducto = new javax.swing.JLabel();
        txtdepto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipotxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Despacho de Producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 129, 135));
        jLabel1.setText("Departamento:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 129, 135));
        jLabel2.setText("Producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Fecha de salida:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 137, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 129, 135));
        jLabel4.setText("Número de pedido:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 129, 135));
        jLabel6.setText("Cantidad:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        txtcantidad.setPlaceholder("Cantidad");
        jPanel1.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 89, -1));

        rSButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salida (1).png"))); // NOI18N
        rSButtonShade1.setText("Despachar");
        rSButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        txtnumpedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtnumpedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 211, 22));

        txtproducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 279, 22));

        txtdepto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtdepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 246, 22));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Salida de Producto");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        tipotxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(tipotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 110, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade1ActionPerformed
        guardar();
    }//GEN-LAST:event_rSButtonShade1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(nueva_salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nueva_salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nueva_salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nueva_salida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nueva_salida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private rscomponentshade.RSButtonShade rSButtonShade1;
    private javax.swing.JLabel tipotxt;
    public rscomponentshade.RSTextFieldShade txtcantidad;
    public javax.swing.JLabel txtdepto;
    public javax.swing.JLabel txtnumpedido;
    public javax.swing.JLabel txtproducto;
    // End of variables declaration//GEN-END:variables
}
