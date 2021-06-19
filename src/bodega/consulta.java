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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jagh_
 */
public class consulta extends javax.swing.JFrame {

    /**
     * Creates new form consulta
     */
    public consulta() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenartabla();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
    }
    
    Conexion cn = new Conexion();
   
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT registro_movimientos.USUARIO, DATE_FORMAT(registro_movimientos.FECHA, '%d-%m-%Y'), "
              + "registro_movimientos.MOVIMIENTO, producto.DESCRIPCION, registro_movimientos.CANT_UNIT, "
              + "registro_movimientos.CANT_LBS, registro_movimientos.PRECIO_UNIT, "
              + "registro_movimientos.PRECIO_LBS "
              + "from bodega.registro_movimientos, bodega.producto "
              + "where(producto.CODIGO=registro_movimientos.CODIGO) "
              + "order by registro_movimientos.ID;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Responsable");
      model.addColumn("Fecha");
      model.addColumn("Tipo de Movimiento");
      model.addColumn("Producto");
      model.addColumn("Unidades");
      model.addColumn("Lbs");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      table.setModel(model);
      String[] dato = new String[8];
     
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
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
     table.getColumnModel().getColumn(2).setPreferredWidth(250);  
     table.getColumnModel().getColumn(3).setPreferredWidth(300);
    }
    public void buscar(){
        String buscar = busqueda.getText();
        Connection conexion = cn.conector();
        String sql = "SELECT registro_movimientos.USUARIO, DATE_FORMAT(registro_movimientos.FECHA, '%d-%m-%Y'), "
              + "registro_movimientos.MOVIMIENTO, producto.DESCRIPCION, registro_movimientos.CANT_UNIT, "
              + "registro_movimientos.CANT_LBS, registro_movimientos.PRECIO_UNIT, "
              + "registro_movimientos.PRECIO_LBS "
              + "from bodega.registro_movimientos, bodega.producto "
              + "where((producto.CODIGO=registro_movimientos.CODIGO)and(producto.DESCRIPCION like '%"+buscar+"%')) "
              + "order by registro_movimientos.ID;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Responsable");
      model.addColumn("Fecha");
      model.addColumn("Tipo de Movimiento");
      model.addColumn("Producto");
      model.addColumn("Unidades");
      model.addColumn("Lbs");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      table.setModel(model);
      String[] dato = new String[8];
     
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
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
     table.getColumnModel().getColumn(2).setPreferredWidth(250);  
     table.getColumnModel().getColumn(3).setPreferredWidth(300);
    }
    public void buscarentradas(){
        String buscar = busqueda.getText();
        Connection conexion = cn.conector();
        String sql = "SELECT registro_movimientos.USUARIO, DATE_FORMAT(registro_movimientos.FECHA, '%d-%m-%Y'), "
              + "registro_movimientos.MOVIMIENTO, producto.DESCRIPCION, registro_movimientos.CANT_UNIT, "
              + "registro_movimientos.CANT_LBS, registro_movimientos.PRECIO_UNIT, "
              + "registro_movimientos.PRECIO_LBS "
              + "from bodega.registro_movimientos, bodega.producto "
              + "where((producto.CODIGO=registro_movimientos.CODIGO)and(producto.DESCRIPCION like '%"+buscar+"%')and(registro_movimientos.MOVIMIENTO = 'ENTRADA DE PRODUCTO')) "
              + "order by registro_movimientos.ID;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Responsable");
      model.addColumn("Fecha");
      model.addColumn("Tipo de Movimiento");
      model.addColumn("Producto");
      model.addColumn("Unidades");
      model.addColumn("Lbs");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      table.setModel(model);
      String[] dato = new String[8];
     
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
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
     table.getColumnModel().getColumn(2).setPreferredWidth(250);  
     table.getColumnModel().getColumn(3).setPreferredWidth(300);
    }
    public void buscarsalidas(){
        String buscar = busqueda.getText();
        Connection conexion = cn.conector();
        String sql = "SELECT registro_movimientos.USUARIO, DATE_FORMAT(registro_movimientos.FECHA, '%d-%m-%Y'), "
              + "registro_movimientos.MOVIMIENTO, producto.DESCRIPCION, registro_movimientos.CANT_UNIT, "
              + "registro_movimientos.CANT_LBS, registro_movimientos.PRECIO_UNIT, "
              + "registro_movimientos.PRECIO_LBS "
              + "from bodega.registro_movimientos, bodega.producto "
              + "where((producto.CODIGO=registro_movimientos.CODIGO)and(producto.DESCRIPCION like '%"+buscar+"%')and(registro_movimientos.MOVIMIENTO = 'SALIDA DE PRODUCTO')) "
              + "order by registro_movimientos.ID;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Responsable");
      model.addColumn("Fecha");
      model.addColumn("Tipo de Movimiento");
      model.addColumn("Producto");
      model.addColumn("Unidades");
      model.addColumn("Lbs");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      table.setModel(model);
      String[] dato = new String[8];
     
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
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
     table.getColumnModel().getColumn(2).setPreferredWidth(250);  
     table.getColumnModel().getColumn(3).setPreferredWidth(300);
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
        
        Phrase rt = new Phrase("\n\nCONSULTA DE MOVIMIENTOS\n\n",fontcabecatable);
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
        String contenido2 = "\n\nFecha de impresión: " + fecha02;
        
        try{
        FileOutputStream archivo = new FileOutputStream( ruta +".pdf" );
        Document doc = new Document(PageSize.LETTER);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        busqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rSButtonShade1 = new rscomponentshade.RSButtonShade();
        rSButtonShade2 = new rscomponentshade.RSButtonShade();
        rSButtonShade3 = new rscomponentshade.RSButtonShade();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimiento de Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Consulta de Movimientos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 1190, 440));

        busqueda.setToolTipText("");
        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 380, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        rSButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/entrada.png"))); // NOI18N
        rSButtonShade1.setText("Entradas");
        rSButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 140, -1));

        rSButtonShade2.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salida.png"))); // NOI18N
        rSButtonShade2.setText("Salidas");
        rSButtonShade2.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 140, -1));

        rSButtonShade3.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/todo.png"))); // NOI18N
        rSButtonShade3.setText("Todo");
        rSButtonShade3.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 130, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 170, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
  
    }//GEN-LAST:event_tableMouseClicked

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
    }//GEN-LAST:event_busquedaKeyPressed

    private void rSButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade1ActionPerformed
        buscarentradas();
    }//GEN-LAST:event_rSButtonShade1ActionPerformed

    private void rSButtonShade2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade2ActionPerformed
        buscarsalidas();
    }//GEN-LAST:event_rSButtonShade2ActionPerformed

    private void rSButtonShade3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade3ActionPerformed
        llenartabla();
    }//GEN-LAST:event_rSButtonShade3ActionPerformed

    private void rSButtonShade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade4ActionPerformed
        docpdf();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSButtonShade rSButtonShade1;
    private rscomponentshade.RSButtonShade rSButtonShade2;
    private rscomponentshade.RSButtonShade rSButtonShade3;
    private rscomponentshade.RSButtonShade rSButtonShade4;
    private rojerusan.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
