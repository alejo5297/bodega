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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class nueva_entrada extends javax.swing.JFrame {
    Bodega bd = new Bodega();
    public String nombre = bd.nombre;
    
    public nueva_entrada() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenartabla();
        llenarcombo();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
        btnregistrar.setEnabled(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
    String id, desc, cat, fecha, fechavenc;
    Conexion cn = new Conexion();
    
    public void fecha(){
        try {
        Date  fecha=date.getDate();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String fecha2 =f.format(fecha);
        this.fecha = fecha2;
        Date  fechav=date.getDate();
        DateFormat f2=new SimpleDateFormat("yyyy-MM-dd");
        String fecha3 =f.format(fecha);
        this.fechavenc = fecha3;
    } catch (Exception e) {
    }
    }
    public void llenarcombo(){
    Connection conexion = cn.conector();
    String sql = "SELECT NOMBRE FROM proveedores;";
    Statement st;
    combobox.addItem(" ");
    try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
            combobox.addItem(result.getString("NOMBRE"));
   }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
    }
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`producto`;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("CÓDIGO");
      model.addColumn("DESCRIPCION");
      model.addColumn("CATEGORÍA");
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
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
      
     
  }
    public void buscar(){
      String buscar = busqueda.getText();
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`producto` WHERE producto.DESCRIPCION like '%"+buscar+"%';";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("CÓDIGO");
      model.addColumn("DESCRIPCION");
      model.addColumn("CATEGORÍA");
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
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    public void guardar(){
        fecha();
    Connection conexion = cn.conector();
    String codigo = this.id;
    String proveedor = (String)combobox.getSelectedItem();
    String usuario = nombre;
    String date = this.fecha;
    String nofactura = nfactura.getText();
    String noenvio = nenvio.getText();
    String precio = punit.getText();
    String cant = cantunit.getText();
    String validar = (String)combo.getSelectedItem();
    String date2 = this.fechavenc;
    String sql;
    if(validar.equals("UNIDADES")){
      sql = "INSERT INTO `bodega`.`entradas` (`CODIGO`, `PROVEEDOR`, `USUARIO`, `FECHA`, `NO_FACTURA`, `NO_ENVIO`"
            + ", `PRECIO_UNITARIO`, `PRECIO_LBS`, `CANT_UNIT`, `CANT_LBS`, `FECHA_VENCIMIENTO`) VALUES ("+codigo+", '"
            +proveedor+"', '"+usuario+"', '"+date+"', '"+nofactura+"', '"+noenvio+"', "+precio+", 0, "+cant+", 0, '"+date2+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Entrada registrada");
            entradas ent = new entradas();
            ent.llenartabla();
            ent.setVisible(true);
            this.setVisible(false);       
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }  
    }
    if(validar.equals("LIBRAS")){
        sql = "INSERT INTO `bodega`.`entradas` (`CODIGO`, `PROVEEDOR`, `USUARIO`, `FECHA`, `NO_FACTURA`, `NO_ENVIO`"
            + ", `PRECIO_UNITARIO`, `PRECIO_LBS`, `CANT_UNIT`, `CANT_LBS`, `FECHA_VENCIMIENTO`) VALUES ("+codigo+", '"
            +proveedor+"', '"+usuario+"', '"+date+"', '"+nofactura+"', '"+noenvio+"', 0, "+precio+", 0,"+cant+", '"+date2+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Entrada registrada");
            entradas ent = new entradas();
            ent.llenartabla();
            ent.setVisible(true);
            this.setVisible(false);       
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }  finally{
            cn.cierraConexion();
        }
    } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        nfactura = new rscomponentshade.RSTextFieldShade();
        nenvio = new rscomponentshade.RSTextFieldShade();
        punit = new rscomponentshade.RSTextFieldShade();
        cantunit = new rscomponentshade.RSTextFieldShade();
        combobox = new javax.swing.JComboBox<>();
        btnregistrar = new rscomponentshade.RSButtonShade();
        date = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fechavencimiento = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        productxt = new javax.swing.JLabel();
        busqueda = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Entrada de Producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        table.setSelectionBackground(new java.awt.Color(26, 129, 135));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 630, 223));

        nfactura.setPlaceholder("No. de Factura");
        jPanel1.add(nfactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 172, -1));

        nenvio.setPlaceholder("No. de Envío");
        jPanel1.add(nenvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 172, -1));

        punit.setPlaceholder("Precio Unitario");
        jPanel1.add(punit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, -1, -1));

        cantunit.setPlaceholder("Cant");
        jPanel1.add(cantunit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, -1, -1));

        jPanel1.add(combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, 308, -1));

        btnregistrar.setBackground(new java.awt.Color(26, 129, 135));
        btnregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.png"))); // NOI18N
        btnregistrar.setBgHover(new java.awt.Color(26, 129, 135));
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnregistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, 50, -1));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, 174, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 129, 135));
        jLabel2.setText("Fecha:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Fecha de Vencimiento:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 580, -1, -1));
        jPanel1.add(fechavencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 580, 151, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 129, 135));
        jLabel4.setText("Producto Seleccionado:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "UNIDADES", "LIBRAS" }));
        jPanel1.add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, 180, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 129, 135));
        jLabel6.setText("Seleccione tipo de ingreso:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Entrada de Producto");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 129, 135));
        jLabel7.setText("Proveedor:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, -1, -1));

        productxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(productxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 420, 20));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 330, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String codigo=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String descrip = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        String categoria = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
        this.id = codigo;
        this.desc = descrip;
        this.cat = categoria;
        btnregistrar.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
    guardar();
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
    }//GEN-LAST:event_busquedaKeyPressed

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
            java.util.logging.Logger.getLogger(nueva_entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nueva_entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nueva_entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nueva_entrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nueva_entrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rscomponentshade.RSButtonShade btnregistrar;
    private javax.swing.JTextField busqueda;
    private rscomponentshade.RSTextFieldShade cantunit;
    private javax.swing.JComboBox<String> combo;
    public javax.swing.JComboBox<String> combobox;
    private com.toedter.calendar.JDateChooser date;
    private com.toedter.calendar.JDateChooser fechavencimiento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSTextFieldShade nenvio;
    private rscomponentshade.RSTextFieldShade nfactura;
    private javax.swing.JLabel productxt;
    private rscomponentshade.RSTextFieldShade punit;
    private rojerusan.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
