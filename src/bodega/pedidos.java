/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jagh_
 */
public class pedidos extends javax.swing.JFrame {
    
    public pedidos() {
       initComponents();
       llenartabla();
       this.setResizable(false);
       setLocationRelativeTo(null);
       ajustar();
       reload();
       this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
       btnpedidos.setVisible(false);
       eliminar.setVisible(false);
    }
   
    String id;
    Conexion cn = new Conexion();
    @SuppressWarnings("unchecked")
    
    public void reload() {
    Timer timer = new Timer(30000, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            llenartabla();
            ajustar();
        }
    });

    timer.start();        
}    
    
    public void ajustar(){
       table.getColumnModel().getColumn(0).setPreferredWidth(30);
       table.getColumnModel().getColumn(1).setPreferredWidth(100);
       table.getColumnModel().getColumn(2).setPreferredWidth(70);
       table.getColumnModel().getColumn(3).setPreferredWidth(100);
       table.getColumnModel().getColumn(4).setPreferredWidth(300);
       table.getColumnModel().getColumn(5).setPreferredWidth(70);
       table.getColumnModel().getColumn(6).setPreferredWidth(70);
       table.getColumnModel().getColumn(7).setPreferredWidth(200);
       table.getColumnModel().getColumn(8).setPreferredWidth(80);
       table.getColumnModel().getColumn(9).setPreferredWidth(100);
       table.getColumnModel().getColumn(10).setPreferredWidth(80);
       table.getColumnModel().getColumn(11).setPreferredWidth(100);
    }
    public void llenartabla(){
        Connection conexion = cn.conector();
        String sql = "SELECT "
              + "pedidos.NO, "
              + "pedidos.NUM_PEDIDO, "
              + "pedidos.CODIGO, "
              + "producto.CATEGORIA,"
              + "producto.DESCRIPCION," 
              + "DATE_FORMAT(pedidos.FECHA, '%d-%m-%Y'), "
              + "pedidos.CANTIDAD, "
              + "pedidos.USO, "
              + "pedidos.DEPARTAMENTO, "
              + "pedidos.QUIEN_SOLICITA, "
              + "pedidos.AUTORIZA, "
              + "pedidos.ESTADO " 
              + "FROM `bodega`.`pedidos`, `bodega`.`producto` "
              + "WHERE (pedidos.CODIGO = producto.CODIGO)and(pedidos.ESTADO = 'PENDIENTE') ORDER BY pedidos.NO ;";
        Statement st;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("No. Pedido");
        model.addColumn("Cod. Product");
        model.addColumn("Categoría");
        model.addColumn("Descripción");
        model.addColumn("Fecha");
        model.addColumn("Cantidad");
        model.addColumn("Uso");
        model.addColumn("Depto");
        model.addColumn("Solicita");
        model.addColumn("Autoriza");
        model.addColumn("Estado");
        table.setModel(model);
        String[] dato = new String[12];
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                dato[3]=result.getString(4);
                dato[4]=result.getString(5);
                dato[5]=result.getString(6);
                dato[6]=result.getString(7);
                dato[7]=result.getString(8);
                dato[8]=result.getString(9);
                dato[9]=result.getString(10);
                dato[10]=result.getString(11);
                dato[11]=result.getString(12);
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        btnpedidos.setVisible(false);
        eliminar.setVisible(false);
  }
    public void obtenerdatos(){
        Bodega b = new Bodega();
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String id=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String num_pedido = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        String codproducto = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
        String producto = String.valueOf(tm.getValueAt(table.getSelectedRow(),4));
        String fechapedido = String.valueOf(tm.getValueAt(table.getSelectedRow(),5));
        String cantidad = String.valueOf(tm.getValueAt(table.getSelectedRow(),6));
        String depto = String.valueOf(tm.getValueAt(table.getSelectedRow(), 8));
        b.id = id;
        this.id = id;
        b.numpedido = num_pedido;
        b.depto = depto;
        b.producto = producto;
        b.cant = cantidad;
        b.codproduct = codproducto;
        System.out.println(b.id);
    }
    public void buscar(){
        String buscar = busqueda.getText();
        Connection conexion = cn.conector();
        String sql = "SELECT "
              + "pedidos.NO, "
              + "pedidos.NUM_PEDIDO, "
              + "pedidos.CODIGO, "
              + "producto.CATEGORIA,"
              + "producto.DESCRIPCION," 
              + "DATE_FORMAT(pedidos.FECHA, '%d-%m-%Y'), "
              + "pedidos.CANTIDAD, "
              + "pedidos.USO, "
              + "pedidos.DEPARTAMENTO, "
              + "pedidos.QUIEN_SOLICITA, "
              + "pedidos.AUTORIZA, "
              + "pedidos.ESTADO " 
              + "FROM `bodega`.`pedidos`, `bodega`.`producto` "
              + "WHERE ((pedidos.CODIGO = producto.CODIGO)and(pedidos.ESTADO = 'PENDIENTE') and (producto.DESCRIPCION like '%"+buscar+"%')) ORDER BY pedidos.NO ;";
        Statement st;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("No. Pedido");
        model.addColumn("Cod. Product");
        model.addColumn("Categoría");
        model.addColumn("Descripción");
        model.addColumn("Fecha");
        model.addColumn("Cantidad");
        model.addColumn("Uso");
        model.addColumn("Depto");
        model.addColumn("Solicita");
        model.addColumn("Autoriza");
        model.addColumn("Estado");
        table.setModel(model);
        String[] dato = new String[12];
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                dato[3]=result.getString(4);
                dato[4]=result.getString(5);
                dato[5]=result.getString(6);
                dato[6]=result.getString(7);
                dato[7]=result.getString(8);
                dato[8]=result.getString(9);
                dato[9]=result.getString(10);
                dato[10]=result.getString(11);
                dato[11]=result.getString(12);
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        ajustar();
        btnpedidos.setVisible(false);
       eliminar.setVisible(false);
    }
    public void rechazar(){
        Connection conexion = cn.conector();
    String sql = "UPDATE `bodega`.`pedidos`"
            + "SET `ESTADO` = 'RECHAZADO' WHERE (NO = '"+id+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();   
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        llenartabla();
    }
    private void docpdf(){//metodo para la generacion de pdf de la ficha

        String ruta = null;                                     //Generacion de PDF de Ficha.

        JFileChooser dlg = new JFileChooser();
        int option = dlg.showSaveDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
        File f= dlg.getSelectedFile();
        ruta = f.toString();
        java.util.Date fecha2 = new Date();
        String fecha02 = fecha2.toString();
        
        Font fontcabecatable = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
        
        Phrase rt = new Phrase("\n\nLISTADO DE PEDIDOS\n\n",fontcabecatable);
        Font font2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

        PdfPTable tbl = new PdfPTable(table.getColumnCount());
        for (int i = 0; i < table.getColumnCount(); i++){
            tbl.addCell(table.getColumnName(i));
        }
        
        for (int rows = 0; rows < table.getRowCount(); rows++){
            for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    tbl.addCell(table.getModel().getValueAt(rows, cols).toString());

                }
        }
        tbl.setWidthPercentage(95);
        tbl.setSpacingBefore(0f);
        tbl.setSpacingAfter(0f);
        String contenido2 = "\n\nFecha de impresión: " + fecha02;
        
        try{
        FileOutputStream archivo = new FileOutputStream( ruta +".pdf" );
        Document doc = new Document(PageSize.LETTER.rotate());
        PdfWriter.getInstance(doc, archivo);
        doc.open();
        
        Image imagen = Image.getInstance("C:\\Bodega\\Kardex - JDL\\logo.png");//abrimos la imagen a insertar
        Paragraph Titulo = new Paragraph(rt);
        Paragraph parrafo2 = new Paragraph(contenido2);
        imagen.setAlignment(Element.ALIGN_CENTER);//alineamos la imagen al lado derecho superior del pdf
        Titulo.setAlignment(Element.ALIGN_CENTER);
        parrafo2.setAlignment(Element.ALIGN_CENTER);
        doc.add(imagen);
        doc.add(Titulo);
        doc.add(tbl);
        doc.add(parrafo2);
        JOptionPane.showMessageDialog(null, "Documento guardado");
        doc.close();
        }
        catch(Exception e){
            System.out.println("error");
        }
        } 
        try {
            File objetofile = new File (ruta+".pdf");     //abre el archivo generado
            Desktop.getDesktop().open(objetofile);
     }catch (IOException ex) {
            System.out.println(ex);
     }                                     
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        btnpedidos = new rscomponentshade.RSButtonShade();
        rSButtonShade3 = new rscomponentshade.RSButtonShade();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();
        jLabel5 = new javax.swing.JLabel();
        busqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        eliminar = new rscomponentshade.RSButtonShade();
        rSButtonShade5 = new rscomponentshade.RSButtonShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos a Bodega");

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 144, 1470, 440));

        btnpedidos.setBackground(new java.awt.Color(26, 129, 135));
        btnpedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/distribucion.png"))); // NOI18N
        btnpedidos.setText(" Despachar");
        btnpedidos.setBgHover(new java.awt.Color(26, 129, 135));
        btnpedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpedidosActionPerformed(evt);
            }
        });
        jPanel1.add(btnpedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 620, 150, -1));

        rSButtonShade3.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/actualizar.png"))); // NOI18N
        rSButtonShade3.setText(" Refrescar Tabla");
        rSButtonShade3.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 191, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/historial.png"))); // NOI18N
        rSButtonShade4.setText(" Historial");
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1349, 620, 130, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Pedidos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 330, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        eliminar.setBackground(new java.awt.Color(26, 129, 135));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eliminar.png"))); // NOI18N
        eliminar.setText("Rechazar Pedido");
        eliminar.setBgHover(new java.awt.Color(26, 129, 135));
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 620, 180, -1));

        rSButtonShade5.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade5.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade5ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 100, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShade3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade3ActionPerformed
    llenartabla();
    ajustar();// TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade3ActionPerformed

    private void rSButtonShade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade4ActionPerformed
    historial_pedidos hp = new historial_pedidos();
    hp.setVisible(true);
    }//GEN-LAST:event_rSButtonShade4ActionPerformed

    private void btnpedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpedidosActionPerformed
    nueva_salida ns = new nueva_salida();
    ns.setVisible(true);
    }//GEN-LAST:event_btnpedidosActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        btnpedidos.setVisible(true);
        eliminar.setVisible(true);
        obtenerdatos();        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
    }//GEN-LAST:event_busquedaKeyPressed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int input = JOptionPane.showConfirmDialog(null,
            "¿Quiere rechazar el pedido seleccionado?", "Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            rechazar();
        }
        ajustar();
    }//GEN-LAST:event_eliminarActionPerformed

    private void rSButtonShade5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade5ActionPerformed
        docpdf();        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade5ActionPerformed

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
            java.util.logging.Logger.getLogger(pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rscomponentshade.RSButtonShade btnpedidos;
    private javax.swing.JTextField busqueda;
    private rscomponentshade.RSButtonShade eliminar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSButtonShade rSButtonShade3;
    private rscomponentshade.RSButtonShade rSButtonShade4;
    private rscomponentshade.RSButtonShade rSButtonShade5;
    private rojerusan.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
