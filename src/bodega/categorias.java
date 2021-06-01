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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jagh_
 */
public class categorias extends javax.swing.JFrame {

    /**
     * Creates new form categorias
     */
    public categorias() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenartabla();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
        editar.setVisible(false);
        eliminar.setVisible(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
    }
        String id;
        Conexion cn = new Conexion();
        
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`cat_producto` ORDER BY cat_producto.NOMBRE;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("CÓDIGO");
      model.addColumn("DESCRIPCION");
      table.setModel(model);
      String[] dato = new String[2];
     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
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
  }
    public void editar(){
        Connection conexion = cn.conector();
        String cat = txtcat.getText();
        
        String sql = "UPDATE `bodega`.`cat_producto` SET `NOMBRE` = '"+cat+"' WHERE (`CODIGO` = '"+this.id+"');";

        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoría actualizada");
            llenartabla();
            editar.setVisible(false);
            txtcat.setText(null);
            nuevo_producto product = new nuevo_producto();
            this.setVisible(false);
            product.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }finally{
            cn.cierraConexion();
        }
        guardar.setVisible(true);
        editar.setVisible(false);
        eliminar.setVisible(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
    }
    public void eliminar(){
        Connection conexion = cn.conector();
        String sql = "DELETE FROM `bodega`.`cat_producto` WHERE (`CODIGO` = '"+this.id+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoría eliminada");
            llenartabla();
            txtcat.setText(null);
            nuevo_producto product = new nuevo_producto();
            this.setVisible(false);
            product.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);

        }finally{
            cn.cierraConexion();
        }
        guardar.setVisible(true);
        editar.setVisible(false);
        eliminar.setVisible(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
    }
    public void guardar(){
        if(txtcat.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Ingrese una categoría válida");
        }
        else{
            Connection conexion = cn.conector();
            String cat = txtcat.getText();
            String sql = "INSERT INTO `bodega`.`cat_producto` (`NOMBRE`) VALUES ('"+cat+"');";
            try {
                PreparedStatement st = conexion.prepareStatement(sql);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Categoría registrada");
                nuevo_producto product = new nuevo_producto();
                this.setVisible(false);
                product.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Verifique los datos");
            }
            finally{
            cn.cierraConexion();
        }
        }
        llenartabla();
        txtcat.setText(null);

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        editar = new rscomponentshade.RSButtonShade();
        guardar = new rscomponentshade.RSButtonShade();
        eliminar = new rscomponentshade.RSButtonShade();
        txtcat = new rscomponentshade.RSTextFieldShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Categoría de Productos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

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
        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setFuenteFilas(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setFuenteHead(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setRowHeight(25);
        table.setSelectionBackground(new java.awt.Color(26, 129, 135));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 460, 250));

        editar.setBackground(new java.awt.Color(26, 129, 135));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editar.png"))); // NOI18N
        editar.setBgHover(new java.awt.Color(26, 129, 135));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 49, -1));

        guardar.setBackground(new java.awt.Color(26, 129, 135));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.png"))); // NOI18N
        guardar.setBgHover(new java.awt.Color(26, 129, 135));
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 49, -1));

        eliminar.setBackground(new java.awt.Color(26, 129, 135));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eliminar.png"))); // NOI18N
        eliminar.setBgHover(new java.awt.Color(26, 129, 135));
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 50, -1));

        txtcat.setPlaceholder("Nueva categoría");
        jPanel1.add(txtcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 300, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
       editar();
    }//GEN-LAST:event_editarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_guardarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int input = JOptionPane.showConfirmDialog(null,
            "¿Quiere eliminar la categoría seleccionada?", "Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            eliminar();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        this.editar.setVisible(true);
        this.eliminar.setVisible(true);
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String codigo=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String descrip = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        txtcat.setText(descrip);
        this.id = codigo;
    }//GEN-LAST:event_tableMouseClicked

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
            java.util.logging.Logger.getLogger(categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new categorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rscomponentshade.RSButtonShade editar;
    private rscomponentshade.RSButtonShade eliminar;
    private rscomponentshade.RSButtonShade guardar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro table;
    private rscomponentshade.RSTextFieldShade txtcat;
    // End of variables declaration//GEN-END:variables
}
