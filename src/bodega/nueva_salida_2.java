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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jagh_
 */
public class nueva_salida_2 extends javax.swing.JFrame {
    Bodega bd = new Bodega();
    public String numpedido,producto,fecha,codproduct,categoria;
    public String nombre = bd.nombre;
    public String id;
    
    public nueva_salida_2() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenartabla();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
    }
    
       Conexion cn = new Conexion();
    
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`producto`;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Código");
      model.addColumn("Descripción");
      model.addColumn("Categoría");
      table.setModel(model);
      String[] dato = new String[3];
     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(nueva_salida_2.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
  }
    public void obtenerdatos(){
        Bodega b = new Bodega();
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        this.id=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        this.producto = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        this.categoria = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
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
    public void buscar(){
      String buscar = busqueda2.getText();
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`producto` WHERE producto.DESCRIPCION like '%"+buscar+"%';";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Código");
      model.addColumn("Descripción");
      model.addColumn("Categoría");
      table.setModel(model);
      String[] dato = new String[3];
     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(nueva_salida_2.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
    }
    public void obtenertipo(){
        Connection conexion = cn.conector();
        String sql = "select producto.TIPO_UNIDADES from producto where (producto.CODIGO = "+codproduct+");";
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
    public void guardar(){
        fecha();
        String validar = tipotxt.getText();
        String validar2 = (String)combodepto.getSelectedItem();
        if((validar2!=" ")&& (txtcantidad.getText().length()!=0)&& (txtnumpedido.getText().length()!=0)){
            if(validar.equals("LIBRAS")||validar.equals("UNIDADES")){
                    Connection conexion = cn.conector();
                    String codigo = this.codproduct;
                    String unidad = tipotxt.getText();
                    String user = nombre;
                    String date = this.fecha;
                    String depto = (String)combodepto.getSelectedItem();
                    String nopedido = txtnumpedido.getText();
                    String cant = txtcantidad.getText();
                    String motivo = motivotxt.getText();
                    String sql;
                    if(unidad.equals("UNIDADES")){
                        sql = "INSERT INTO `bodega`.`salidas` (`CODIGO`, `USUARIO`, `DEPTO`, `FECHA_EGRESO`, `NO_PEDIDO`"
                            + ", `CANT_UNIT`, `CANT_LBS`, `MOTIVO`) VALUES ("+codigo+", '"
                            +user+"', '"+depto+"', '"+date+"', "+nopedido+", "+cant+",0, '"+motivo+"');";
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
                            +user+"', '"+depto+"', '"+date+"', "+nopedido+", 0, "+cant+", '"+motivo+"');";
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
            }else{
                JOptionPane.showMessageDialog(null, "Eliga si salieron unidades o libras");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Verifique los campos");
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
        txtproducto = new javax.swing.JLabel();
        txtnumpedido = new rscomponentshade.RSTextFieldShade();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        combodepto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        busqueda2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tipotxt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        motivotxt = new rscomponentshade.RSTextFieldShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Salida de Producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 129, 135));
        jLabel1.setText("Departamento:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 270, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 129, 135));
        jLabel2.setText("Producto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 210, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Fecha de salida:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 328, -1, -1));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 330, 137, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 129, 135));
        jLabel4.setText("Número de pedido:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 152, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 129, 135));
        jLabel6.setText("Motivo de salida:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        txtcantidad.setPlaceholder("Cantidad");
        jPanel1.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(682, 381, 89, -1));

        rSButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.png"))); // NOI18N
        rSButtonShade1.setText("Registrar");
        rSButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, -1, -1));

        txtproducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 210, 310, 22));

        txtnumpedido.setPlaceholder("# de pedido");
        jPanel1.add(txtnumpedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(761, 142, 124, -1));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setColorBackgoundHead(new java.awt.Color(26, 129, 135));
        table.setColorFilasForeground1(new java.awt.Color(26, 129, 135));
        table.setColorFilasForeground2(new java.awt.Color(26, 129, 135));
        table.setColorSelBackgound(new java.awt.Color(26, 129, 135));
        table.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table.setRowHeight(25);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 549, 500));

        combodepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "BODEGA", "CAMARERIA", "COCINA", "LAVANDERIA", "MANTENIMIENTO", "PANADERIA", "RESTAURANTE" }));
        jPanel1.add(combodepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 274, 187, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Salida de Producto");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, -1, -1));

        busqueda2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busqueda2KeyPressed(evt);
            }
        });
        jPanel1.add(busqueda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 330, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        tipotxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(tipotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, 90, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(26, 129, 135));
        jLabel8.setText("Unidades:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 391, -1, -1));

        motivotxt.setPlaceholder("Motivo");
        jPanel1.add(motivotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 440, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade1ActionPerformed
        guardar();
    }//GEN-LAST:event_rSButtonShade1ActionPerformed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        
    }//GEN-LAST:event_tableKeyPressed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
    DefaultTableModel tm = (DefaultTableModel) table.getModel();
        this.codproduct = String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        this.producto = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        this.categoria = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
        txtproducto.setText(this.producto);
        obtenertipo();        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void busqueda2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busqueda2KeyPressed
        buscar();
    }//GEN-LAST:event_busqueda2KeyPressed

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
            java.util.logging.Logger.getLogger(nueva_salida_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nueva_salida_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nueva_salida_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nueva_salida_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nueva_salida_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda2;
    private javax.swing.JComboBox<String> combodepto;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSTextFieldShade motivotxt;
    private rscomponentshade.RSButtonShade rSButtonShade1;
    private rojerusan.RSTableMetro table;
    private javax.swing.JLabel tipotxt;
    public rscomponentshade.RSTextFieldShade txtcantidad;
    private rscomponentshade.RSTextFieldShade txtnumpedido;
    public javax.swing.JLabel txtproducto;
    // End of variables declaration//GEN-END:variables
}
