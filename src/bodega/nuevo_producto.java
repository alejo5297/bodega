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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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
public class nuevo_producto extends javax.swing.JFrame {

    /**
     * Creates new form nuevo_producto
     */
    public nuevo_producto() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenarcombo();
        llenartabla();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
        String producto;
        editar.setVisible(false);
        eliminar.setVisible(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
    String id;
    Conexion cn = new Conexion();
    
    public void llenarcombo(){
        Conexion cn = new Conexion();
    Connection conexion = cn.conector();
    String sql = "SELECT NOMBRE FROM cat_producto order by cat_producto.NOMBRE;";
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
      model.addColumn("MINIMO");
      model.addColumn("MAXIMO");
      model.addColumn("TIPO");
      table.setModel(model);
      String[] dato = new String[6];
     
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
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
     
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
      model.addColumn("MINIMO");
      model.addColumn("MAXIMO");
      model.addColumn("TIPO");
      table.setModel(model);
      String[] dato = new String[6];
     
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
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    public void editar(){
        Connection conexion = cn.conector();
        String producto = txtproducto.getText();
        String minimo = min.getText();
        String maximo = max.getText();
        String categoria = (String)combobox.getSelectedItem();
        String tipo = (String)combotipo.getSelectedItem();
        String sql = "UPDATE `bodega`.`producto` SET `DESCRIPCION` = '"+producto+"',`CATEGORIA` = '"+categoria+"', `MIN` = '"+minimo+"', `MAX` = '"+maximo+"', `TIPO_UNIDADES` = '"+tipo+"' WHERE (`CODIGO` = '"+this.id+"');";

        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto actualizado");
            llenartabla();
            editar.setVisible(false);
            txtproducto.setText(null);

        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Verifique los datos");
        }finally{
            cn.cierraConexion();
        }
        guardar.setVisible(true);
        editar.setVisible(false);
        eliminar.setVisible(false);
        min.setText(null);
        max.setText(null);
        combobox.setSelectedItem(null);
        combotipo.setSelectedItem(null);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
    }
    public void eliminar(){
        Connection conexion = cn.conector();
        String sql = "DELETE FROM `bodega`.`producto` WHERE (`CODIGO` = '"+this.id+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto eliminado");
            llenartabla();
            txtproducto.setText(null);
            max.setText(null);
            min.setText(null);
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
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
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
        
        Phrase rt = new Phrase("\n\nLISTADO DE PRODUCTOS\n\n",fontcabecatable);
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
    public void guardar(){
        if(txtproducto.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Ingrese un producto válido");
        }
        else{
            Connection conexion = cn.conector();
            String producto = txtproducto.getText();
            String categoria = (String)combobox.getSelectedItem();
            String minimo = min.getText();
            String maximo = max.getText();
            String tipo = (String)combotipo.getSelectedItem();
            String sql = "INSERT INTO `bodega`.`producto` (`DESCRIPCION`, `CATEGORIA`, `MIN`, `MAX`, `TIPO_UNIDADES`) VALUES ('"+producto+"', '"+categoria+"', "+minimo+", "+maximo+",'"+tipo+"');";
            try {
                PreparedStatement st = conexion.prepareStatement(sql);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Producto registrado");
                //this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Verifique los datos");
            }
            finally{
            cn.cierraConexion();
        }
        }
        llenartabla();
        txtproducto.setText(null);
        combobox.setSelectedItem(null);
        combotipo.setSelectedItem(null);
        min.setText(null);
        max.setText(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        combobox = new javax.swing.JComboBox<>();
        txtproducto = new rscomponentshade.RSTextFieldShade();
        guardar = new rscomponentshade.RSButtonShade();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        editar = new rscomponentshade.RSButtonShade();
        max = new rscomponentshade.RSTextFieldShade();
        min = new rscomponentshade.RSTextFieldShade();
        jLabel5 = new javax.swing.JLabel();
        busqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        eliminar = new rscomponentshade.RSButtonShade();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();
        rSToggleButtonShade1 = new rscomponentshade.RSToggleButtonShade();
        combotipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combobox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 170, -1));

        txtproducto.setPlaceholder("Descripción del producto");
        txtproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtproductoMouseClicked(evt);
            }
        });
        txtproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductoActionPerformed(evt);
            }
        });
        jPanel1.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 388, 780, -1));

        guardar.setBackground(new java.awt.Color(26, 129, 135));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.png"))); // NOI18N
        guardar.setBgHover(new java.awt.Color(26, 129, 135));
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 511, 49, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 128, 900, 223));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 129, 135));
        jLabel1.setText("Agregar nuevo producto:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 358, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 129, 135));
        jLabel2.setText("Tipo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, -1, -1));

        editar.setBackground(new java.awt.Color(26, 129, 135));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editar.png"))); // NOI18N
        editar.setBgHover(new java.awt.Color(26, 129, 135));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, 49, -1));

        max.setToolTipText("");
        max.setPlaceholder("Stock Máximo");
        max.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxActionPerformed(evt);
            }
        });
        jPanel1.add(max, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 120, -1));

        min.setToolTipText("");
        min.setPlaceholder("Stock Mínimo");
        jPanel1.add(min, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Productos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 24, -1, -1));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 98, 330, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 98, -1, -1));

        eliminar.setBackground(new java.awt.Color(26, 129, 135));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eliminar.png"))); // NOI18N
        eliminar.setBgHover(new java.awt.Color(26, 129, 135));
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 511, 50, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 40, 40));

        rSToggleButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSToggleButtonShade1.setText("Editar");
        rSToggleButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSToggleButtonShade1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rSToggleButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSToggleButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSToggleButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 70, 40));

        combotipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combotipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "UNIDADES", "LIBRAS" }));
        jPanel1.add(combotipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 460, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Categoría");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        this.editar.setVisible(true);
        this.eliminar.setVisible(true);
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String codigo=String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String descrip = String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        String cat = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
        String minimo = String.valueOf(tm.getValueAt(table.getSelectedRow(),3));
        String maximo = String.valueOf(tm.getValueAt(table.getSelectedRow(),4));
        String tipo = String.valueOf(tm.getValueAt(table.getSelectedRow(),5));
        txtproducto.setText(descrip);
        min.setText(minimo);
        max.setText(maximo);
        combobox.setSelectedItem(cat);
        combotipo.setSelectedItem(tipo);
        this.id = codigo;
    }//GEN-LAST:event_tableMouseClicked

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        editar();
    }//GEN-LAST:event_editarActionPerformed

    private void txtproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductoActionPerformed

    private void txtproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtproductoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductoMouseClicked

    private void maxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_guardarActionPerformed

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
    }//GEN-LAST:event_busquedaKeyPressed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int input = JOptionPane.showConfirmDialog(null,
            "¿Quiere eliminar el producto seleccionado? Al elimiarlo tambíen se borran los registros en el inventario.", "Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            eliminar();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void rSButtonShade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade4ActionPerformed
        docpdf();        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade4ActionPerformed

    private void rSToggleButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSToggleButtonShade1ActionPerformed
        categorias cat = new categorias();
        this.setVisible(false);
        cat.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rSToggleButtonShade1ActionPerformed

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
            java.util.logging.Logger.getLogger(nuevo_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevo_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevo_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevo_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nuevo_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JComboBox<String> combotipo;
    private rscomponentshade.RSButtonShade editar;
    private rscomponentshade.RSButtonShade eliminar;
    private rscomponentshade.RSButtonShade guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSTextFieldShade max;
    private rscomponentshade.RSTextFieldShade min;
    private rscomponentshade.RSButtonShade rSButtonShade4;
    private rscomponentshade.RSToggleButtonShade rSToggleButtonShade1;
    private rojerusan.RSTableMetro table;
    private rscomponentshade.RSTextFieldShade txtproducto;
    // End of variables declaration//GEN-END:variables
}
