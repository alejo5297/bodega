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
public class entradas extends javax.swing.JFrame {
    
    public entradas() {
        initComponents();
        llenartabla();
        this.setResizable(false);
        setLocationRelativeTo(null);
        tamanio();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
    }
    
    String id;
    Conexion cn = new Conexion();
    
    public void tamanio(){
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
       table.getColumnModel().getColumn(1).setPreferredWidth(1);
       table.getColumnModel().getColumn(2).setPreferredWidth(30);
       table.getColumnModel().getColumn(3).setPreferredWidth(30);
       table.getColumnModel().getColumn(4).setPreferredWidth(30);
       table.getColumnModel().getColumn(5).setPreferredWidth(200);
       table.getColumnModel().getColumn(6).setPreferredWidth(30);
       table.getColumnModel().getColumn(7).setPreferredWidth(30);
       table.getColumnModel().getColumn(8).setPreferredWidth(30);
       table.getColumnModel().getColumn(9).setPreferredWidth(30);
       table.getColumnModel().getColumn(10).setPreferredWidth(30);
       table.getColumnModel().getColumn(11).setPreferredWidth(30);
       table.getColumnModel().getColumn(12).setPreferredWidth(30);
       table.getColumnModel().getColumn(13).setPreferredWidth(30);
       table.getColumnModel().getColumn(14).setPreferredWidth(30);
    }
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT entradas.NO, entradas.CODIGO, entradas.PROVEEDOR, entradas.USUARIO, producto.CATEGORIA, producto.DESCRIPCION, DATE_FORMAT(entradas.FECHA, '%d-%m-%Y'), entradas.NO_FACTURA, "
              + "entradas.NO_ENVIO,CONCAT('Q ',entradas.PRECIO_UNITARIO), "
              + "CONCAT('Q ',entradas.PRECIO_LBS), entradas.CANT_UNIT, entradas.CANT_LBS,CONCAT('Q ',TRUNCATE(entradas.PRECIO_UNITARIO*entradas.CANT_UNIT,2)), "
              + "CONCAT('Q ',TRUNCATE(entradas.PRECIO_LBS*entradas.CANT_LBS,2)), DATE_FORMAT(entradas.FECHA_VENCIMIENTO, '%d-%m-%Y')"
              + " FROM `bodega`.`entradas`, `bodega`.`producto` WHERE producto.CODIGO = entradas.CODIGO ORDER BY entradas.NO ;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("No.");
      model.addColumn("Cod. Product");
      model.addColumn("Proveedor");
      model.addColumn("Usuario");
      model.addColumn("Categoría");
      model.addColumn("Descripción");
      model.addColumn("Fecha");
      model.addColumn("No. Factura");
      model.addColumn("No. Envío");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      model.addColumn("Cant. Unit");
      model.addColumn("Cant. Lbs");
      model.addColumn("Costo Total Unit");
      model.addColumn("Costo Total Lbs");
      model.addColumn("Fecha Vencimiento");
      table.setModel(model);
      String[] dato = new String[18];
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
                dato[12]=result.getString(13);
                dato[13]=result.getString(14);
                dato[14]=result.getString(15);
                dato[15]=result.getString(16);
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      finally{
            cn.cierraConexion();
        }
  }  
    public void buscar(){
    Conexion cn = new Conexion();
    Connection conexion = cn.conector();
    String buscar = busqueda.getText();
      String sql = "SELECT entradas.NO, entradas.CODIGO, entradas.PROVEEDOR, entradas.USUARIO, producto.CATEGORIA, producto.DESCRIPCION, DATE_FORMAT(entradas.FECHA, '%d-%m-%Y'), entradas.NO_FACTURA, "
              + "entradas.NO_ENVIO,CONCAT('Q ',entradas.PRECIO_UNITARIO), "
              + "CONCAT('Q ',entradas.PRECIO_LBS), entradas.CANT_UNIT, entradas.CANT_LBS,CONCAT('Q ',TRUNCATE(entradas.PRECIO_UNITARIO*entradas.CANT_UNIT,2)), "
              + "CONCAT('Q ',TRUNCATE(entradas.PRECIO_LBS*entradas.CANT_LBS,2)), DATE_FORMAT(entradas.FECHA_VENCIMIENTO, '%d-%m-%Y')"
              + " FROM `bodega`.`entradas`, `bodega`.`producto` WHERE ((producto.CODIGO = entradas.CODIGO) AND producto.DESCRIPCION like '%"+buscar+"%') ORDER BY entradas.NO ;";

      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("No.");
      model.addColumn("Cod. Product");
      model.addColumn("Proveedor");
      model.addColumn("Usuario");
      model.addColumn("Categoría");
      model.addColumn("Descripción");
      model.addColumn("Fecha");
      model.addColumn("No. Factura");
      model.addColumn("No. Envío");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      model.addColumn("Cant. Unit");
      model.addColumn("Cant. Lbs");
      model.addColumn("Costo Total Unit");
      model.addColumn("Costo Total Lbs");
      model.addColumn("Fecha Vencimiento");
      table.setModel(model);
      String[] dato = new String[18];
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
                dato[12]=result.getString(13);
                dato[13]=result.getString(14);
                dato[14]=result.getString(15);
                dato[15]=result.getString(16);
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      finally{
            cn.cierraConexion();
        }
     
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
        
        Phrase rt = new Phrase("\n\nENTRADAS DE PRODUCTO\n\n",fontcabecatable);
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
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        rSButtonShade1 = new rscomponentshade.RSButtonShade();
        jLabel3 = new javax.swing.JLabel();
        busqueda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entradas de Producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1805, 370));

        rSButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mas.png"))); // NOI18N
        rSButtonShade1.setText("Nueva Entrada");
        rSButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 620, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 38)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Entradas de Producto");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 170, 330, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, -1, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1770, 160, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1827, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String codigo=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String descrip = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        this.id = codigo;
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void rSButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade1ActionPerformed
    nueva_entrada ne = new nueva_entrada();
    this.setVisible(false);
    ne.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade1ActionPerformed

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
        tamanio();
    }//GEN-LAST:event_busquedaKeyPressed

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
            java.util.logging.Logger.getLogger(entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new entradas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSButtonShade rSButtonShade1;
    private rscomponentshade.RSButtonShade rSButtonShade4;
    public rojerusan.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
