/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jagh_
 */
public class reporte_salidas extends javax.swing.JFrame {

    /**
     * Creates new form reporte_salidas
     */
    public reporte_salidas() {
        initComponents();
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
    }
        String desdetxt, hastatxt;
        Conexion cn = new Conexion();
        
    public void capturarfechas(){
        SimpleDateFormat desdeFormat = new SimpleDateFormat("yyyy-MM-dd");
        desdetxt = desdeFormat.format(desde.getDate());
        SimpleDateFormat hastaFormat = new SimpleDateFormat("yyyy-MM-dd");
        hastatxt = hastaFormat.format(hasta.getDate());
    }
    
    public void pordepto(){
        try {
           capturarfechas();
            if(desdetxt!=null && hastatxt!=null){
                Connection conexion = cn.conector();
                String sql = "SELECT salidas.DEPTO, sum(CANT_UNIT),  sum(CANT_LBS) FROM bodega.salidas "
                        + "WHERE (salidas.FECHA_EGRESO >= '"+desdetxt+"' AND salidas.FECHA_EGRESO <= '"+hastatxt+"' ) group by salidas.DEPTO;";
                Statement st;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Departamento");
                model.addColumn("Unidades de productos solicitadas");
                model.addColumn("Libras de productos solicitadas");
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
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(reporte_salidas.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    cn.cierraConexion();
                }
            }
        } catch(Exception e){
            Logger.getLogger(reporte_salidas.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Seleccione un intervalo de fechas válido.");
        }
        
    }
    
    public void porproduct(){
        try {
           capturarfechas();
            if(desdetxt!=null && hastatxt!=null){
                Connection conexion = cn.conector();
                String sql = "SELECT producto.DESCRIPCION, sum(CANT_UNIT),  sum(CANT_LBS) FROM bodega.salidas, bodega.producto "
                        + "WHERE ((producto.CODIGO = salidas.CODIGO) AND (salidas.FECHA_EGRESO >= '"+desdetxt+"' AND salidas.FECHA_EGRESO <= '"+hastatxt+"' )) group by salidas.CODIGO;" ;
                Statement st;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Producto");
                model.addColumn("Unidades solicitadas");
                model.addColumn("Libras solicitadas");
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
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(reporte_salidas.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    cn.cierraConexion();
                }
            }
        } catch(Exception e){
            Logger.getLogger(reporte_salidas.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Seleccione un intervalo de fechas válido.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        rSButtonShade3 = new rscomponentshade.RSButtonShade();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();
        jLabel4 = new javax.swing.JLabel();
        desde = new com.toedter.calendar.JDateChooser();
        hasta = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setColorBackgoundHead(new java.awt.Color(26, 129, 135));
        table.setColorFilasForeground1(new java.awt.Color(26, 129, 135));
        table.setColorFilasForeground2(new java.awt.Color(26, 129, 135));
        table.setColorSelBackgound(new java.awt.Color(26, 129, 135));
        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setFuenteFilas(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setRowHeight(30);
        table.setSelectionBackground(new java.awt.Color(26, 129, 135));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 1010, 430));

        rSButtonShade3.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/todo.png"))); // NOI18N
        rSButtonShade3.setText("Por departamento");
        rSButtonShade3.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 190, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/productos.png"))); // NOI18N
        rSButtonShade4.setText("Por producto");
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 170, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 129, 135));
        jLabel4.setText("Hasta:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));
        jPanel1.add(desde, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 110, -1));
        jPanel1.add(hasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 110, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Intérvalo de fecha:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 129, 135));
        jLabel6.setText("Desde:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 129, 135));
        jLabel7.setText("Reporte de salidas");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Reporte de salidas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void rSButtonShade3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade3ActionPerformed
        pordepto();
    }//GEN-LAST:event_rSButtonShade3ActionPerformed

    private void rSButtonShade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade4ActionPerformed
        porproduct();
    }//GEN-LAST:event_rSButtonShade4ActionPerformed

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
            java.util.logging.Logger.getLogger(reporte_salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporte_salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporte_salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporte_salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporte_salidas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser desde;
    private com.toedter.calendar.JDateChooser hasta;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSButtonShade rSButtonShade3;
    private rscomponentshade.RSButtonShade rSButtonShade4;
    private rojerusan.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
