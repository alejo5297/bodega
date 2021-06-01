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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class solicitar_producto extends javax.swing.JFrame {
    Bodega bd = new Bodega();
    public String nombre = bd.nombre;
    public String usuario;
    String fechaact;
    /**
     * Creates new form solicitar_producto
     */
    public solicitar_producto() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenartabla2();
        fechaactual();
        obtenernombre();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
        activo.setText(usuario);
    }
    
    Conexion cn = new Conexion();
    String txtproducto;
    
    public void llenartabla(){
       if (cantidad.getText().length()!=0){
       DefaultTableModel modelo=(DefaultTableModel) tblDatos.getModel();
       Object [] fila=new Object[3];
       fila[0]=fechaact;
       fila[1]=txtproducto; 
       fila[2]=cantidad.getText(); 
       modelo.addRow(fila);
       tblDatos.setModel(modelo);
       cantidad.setText(null);
        }
       else{
           JOptionPane.showMessageDialog(null, "Ingrese la cantidad");
       }
    }   
    public void llenartabla2(){
      Connection conexion = cn.conector();
      String sql = "select producto.DESCRIPCION, (inventario.ENTRADAS_UNITARIAS - inventario.SALIDAS_UNITARIAS), "
              + "(inventario.ENTRADAS_LBS - inventario.SALIDAS_LBS), producto.MIN "
              + "from producto, inventario "
              + "where ((producto.CODIGO = inventario.CODIGO) and ((inventario.ENTRADAS_UNITARIAS - inventario.SALIDAS_UNITARIAS)) < producto.MIN);";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("PRODUCTO");
      model.addColumn("STOCK UNI");
      model.addColumn("STOCK LBS");
      model.addColumn("MIN");
      table.setModel(model);
      String[] dato = new String[4];
     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                dato[3]=result.getString(4);
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        ajustar();
  }
    public void buscar(){
      String buscar = busqueda.getText();
      Connection conexion = cn.conector();
      String sql = "select producto.DESCRIPCION, (inventario.ENTRADAS_UNITARIAS - inventario.SALIDAS_UNITARIAS), (inventario.ENTRADAS_LBS - inventario.SALIDAS_LBS),producto.MIN "
              + "from producto, inventario where ((producto.CODIGO = inventario.CODIGO) and ((inventario.ENTRADAS_UNITARIAS - inventario.SALIDAS_UNITARIAS)) < producto.MIN AND (producto.DESCRIPCION like '%"+buscar+"%'));";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("PRODUCTO");
      model.addColumn("STOCK UNI");
      model.addColumn("STOCK LBS");
      model.addColumn("MIN");
      table.setModel(model);
      String[] dato = new String[4];
     
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
                dato[0]=result.getString(1);
                dato[1]=result.getString(2);
                dato[2]=result.getString(3);
                dato[3]=result.getString(4);
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
        ajustar();
    }
    public void ajustar(){
        tblDatos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDatos.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblDatos.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    public void obtenernombre(){
        Connection conexion = cn.conector();
        String sql = "select usuarios.NOMBRE, usuarios.APELLIDO from usuarios where (usuarios.USUARIO = '"+nombre+"');";
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            
            while (result.next()){
              String nombre = result.getString(1);
              String apellido = result.getString(2);
              usuario = nombre +" "+apellido;
            }
        } catch (SQLException ex) {
            Logger.getLogger(solicitar_producto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
        
        Phrase rt = new Phrase("\n\nSOLICITUD DE COMPRA DE PRODUCTOS\n\n",fontcabecatable);
        Font font2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        
        Phrase ph = new Phrase("\n\nDetalles:",font2);

        String contenido = "\nSolicitado por: "+usuario;
          
        Phrase ph2 = new Phrase("\n\nProductos Solicitados:\n\n",font2);
        
        PdfPTable tbl = new PdfPTable(tblDatos.getColumnCount());
        for (int i = 0; i < tblDatos.getColumnCount(); i++){
            tbl.addCell(tblDatos.getColumnName(i));
        }
        
        for (int rows = 0; rows < tblDatos.getRowCount(); rows++){
            for (int cols = 0; cols < tblDatos.getColumnCount(); cols++) {
                    tbl.addCell(tblDatos.getModel().getValueAt(rows, cols).toString());

                }
        }
        tbl.setWidthPercentage(95);
        tbl.setSpacingBefore(0f);
        tbl.setSpacingAfter(0f);
        String contenido2 = "\n\n\nFirma:___________________________________________\n\n"+
                            "Fecha de solicitud: " + fecha02;
        
        try{
        FileOutputStream archivo = new FileOutputStream( ruta +".pdf" );
        Document doc = new Document(PageSize.LETTER);
        PdfWriter.getInstance(doc, archivo);
        doc.open();
        
        Image imagen = Image.getInstance("C:\\Bodega\\Kardex - JDL\\logo.png");//abrimos la imagen a insertar
        Paragraph Titulo = new Paragraph(rt);
        Paragraph Detalles = new Paragraph(ph);
        Paragraph parrafo1 = new Paragraph(contenido);
        Paragraph solicitados = new Paragraph(ph2);
        Paragraph parrafo2 = new Paragraph(contenido2);
        imagen.setAlignment(Element.ALIGN_CENTER);//alineamos la imagen al lado derecho superior del pdf
        Titulo.setAlignment(Element.ALIGN_CENTER);
        Detalles.setAlignment(Element.ALIGN_CENTER);
        parrafo1.setAlignment(Element.ALIGN_CENTER);
        solicitados.setAlignment(Element.ALIGN_CENTER);
        
        parrafo2.setAlignment(Element.ALIGN_CENTER);
        doc.add(imagen);
        doc.add(Titulo);
        doc.add(Detalles);
        doc.add(parrafo1);
        doc.add(solicitados);
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

     }// TODO add your handling code here:
                                        

    }
    public void fechaactual(){
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            fechaact = dia + "/" + mes +"/"+año;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSTableMetroBeanInfo1 = new rojerusan.RSTableMetroBeanInfo();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        activo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new rojerusan.RSTableMetro();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rSButtonShade1 = new rscomponentshade.RSButtonShade();
        rSButtonShade2 = new rscomponentshade.RSButtonShade();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        cantidad = new rscomponentshade.RSTextFieldShade();
        busqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Solicitud de Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Solicitud para Compra de Productos");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 129, 135));
        jLabel4.setText("Quien solicita:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        activo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        activo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 430, 17));

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Producto", "Cant."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatos.setColorBackgoundHead(new java.awt.Color(26, 129, 135));
        tblDatos.setColorFilasForeground1(new java.awt.Color(26, 129, 135));
        tblDatos.setColorFilasForeground2(new java.awt.Color(26, 129, 135));
        tblDatos.setColorSelBackgound(new java.awt.Color(26, 129, 135));
        tblDatos.setRowHeight(20);
        tblDatos.setSelectionBackground(new java.awt.Color(26, 129, 135));
        jScrollPane1.setViewportView(tblDatos);
        if (tblDatos.getColumnModel().getColumnCount() > 0) {
            tblDatos.getColumnModel().getColumn(0).setResizable(false);
            tblDatos.getColumnModel().getColumn(1).setResizable(false);
            tblDatos.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 562, 238));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Productos Solicitados:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 210, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 129, 135));
        jLabel6.setText("Seleccione el producto y la cantidad requerida:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        rSButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mas.png"))); // NOI18N
        rSButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 490, 66, -1));

        rSButtonShade2.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade2.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 490, 66, -1));

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
        table.setRowHeight(20);
        table.setSelectionBackground(new java.awt.Color(26, 129, 135));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 570, 240));

        cantidad.setPlaceholder("Cantidad");
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        jPanel1.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 140, -1));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 330, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade1ActionPerformed
            llenartabla();        
    }//GEN-LAST:event_rSButtonShade1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String descrip = String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        txtproducto = descrip;
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void rSButtonShade2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade2ActionPerformed
        docpdf();        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade2ActionPerformed

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
            java.util.logging.Logger.getLogger(solicitar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(solicitar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(solicitar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(solicitar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new solicitar_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel activo;
    private javax.swing.JTextField busqueda;
    private rscomponentshade.RSTextFieldShade cantidad;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private rscomponentshade.RSButtonShade rSButtonShade1;
    private rscomponentshade.RSButtonShade rSButtonShade2;
    private rojerusan.RSTableMetroBeanInfo rSTableMetroBeanInfo1;
    private rojerusan.RSTableMetro table;
    private rojerusan.RSTableMetro tblDatos;
    // End of variables declaration//GEN-END:variables
}
