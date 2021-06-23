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
public class usuarios extends javax.swing.JFrame {

    /**
     * Creates new form usuarios
     */
    public usuarios() {
        initComponents();
        this.setResizable(false);
        setLocationRelativeTo(null);
        llenartabla();
        llenarcombo();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
        editar.setVisible(false);
        eliminar.setVisible(false);
    }
    
    String id;
    Conexion cn = new Conexion();
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        jLabel5 = new javax.swing.JLabel();
        editar = new rscomponentshade.RSButtonShade();
        txtapellido = new rscomponentshade.RSTextFieldShade();
        txtusuario = new rscomponentshade.RSTextFieldShade();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new rscomponentshade.RSTextFieldShade();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comborol = new javax.swing.JComboBox<>();
        combodepto = new javax.swing.JComboBox<>();
        guardar = new rscomponentshade.RSButtonShade();
        txtcontrasena = new rscomponentshade.RSPassFieldShade();
        eliminar = new rscomponentshade.RSButtonShade();
        busqueda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();
        Editar = new rscomponentshade.RSButtonShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setToolTipText("");
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 870, 300));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 129, 135));
        jLabel5.setText("Usuarios");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        editar.setBackground(new java.awt.Color(26, 129, 135));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/editar.png"))); // NOI18N
        editar.setBgHover(new java.awt.Color(26, 129, 135));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, 49, -1));

        txtapellido.setToolTipText("");
        txtapellido.setPlaceholder("Apellido");
        jPanel1.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 350, -1));

        txtusuario.setToolTipText("");
        txtusuario.setPlaceholder("Usuario");
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 350, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 129, 135));
        jLabel2.setText("Tipo de Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, -1, -1));

        txtnombre.setPlaceholder("Nombre");
        txtnombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnombreMouseClicked(evt);
            }
        });
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 350, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 129, 135));
        jLabel1.setText("Agregar nuevo usuario:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Departamento:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, -1, -1));

        comborol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Administrador", "Invitado" }));
        jPanel1.add(comborol, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 600, 210, -1));

        jPanel1.add(combodepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 600, 180, -1));

        guardar.setBackground(new java.awt.Color(26, 129, 135));
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/guardar.png"))); // NOI18N
        guardar.setBgHover(new java.awt.Color(26, 129, 135));
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 650, 49, -1));

        txtcontrasena.setPlaceholder("Contraseña");
        jPanel1.add(txtcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 530, 350, -1));

        eliminar.setBackground(new java.awt.Color(26, 129, 135));
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eliminar.png"))); // NOI18N
        eliminar.setBgHover(new java.awt.Color(26, 129, 135));
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 50, -1));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 330, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 40, 40));

        Editar.setBackground(new java.awt.Color(26, 129, 135));
        Editar.setText("Editar");
        Editar.setBgHover(new java.awt.Color(26, 129, 135));
        Editar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPanel1.add(Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 600, 70, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void llenartabla(){
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`usuarios`;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("ID");
      model.addColumn("Nombre");
      model.addColumn("Apellido");
      model.addColumn("Usuario");
      model.addColumn("Contraseña");
      model.addColumn("Departamento");
      model.addColumn("Tipo Usuario");
      table.setModel(model);
      String[] dato = new String[7];
     
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
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
  }
    public void llenarcombo(){
    Connection conexion = cn.conector();
    String sql = "SELECT NOMBRE FROM departamento;";
    Statement st;
    combodepto.addItem(" ");
    try {
            st = conexion.createStatement();
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
            combodepto.addItem(result.getString("NOMBRE"));
   }
        } catch (SQLException ex) {
            Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
    }
    public void buscar(){
      String buscar = busqueda.getText();
      Connection conexion = cn.conector();
      String sql = "SELECT * FROM `bodega`.`usuarios` WHERE usuarios.USUARIO like '%"+buscar+"%';";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("ID");
      model.addColumn("Nombre");
      model.addColumn("Apellido");
      model.addColumn("Usuario");
      model.addColumn("Contraseña");
      model.addColumn("Departamento");
      model.addColumn("Tipo Usuario");
      table.setModel(model);
      String[] dato = new String[7];
     
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
                model.addRow(dato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cn.cierraConexion();
        }
    }
    public void guardar(){
        if(txtnombre.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Ingrese un usuario válido");
        }
        else{
            Connection conexion = cn.conector();
            String nombre = txtnombre.getText();
            String apellido = txtapellido.getText();
            String usuario = txtusuario.getText();
            String contrasena = txtcontrasena.getText();
            String depto = (String)combodepto.getSelectedItem();
            String rol = (String)comborol.getSelectedItem();
            String sql = "INSERT INTO `bodega`.`usuarios` (`NOMBRE`, `APELLIDO`, `USUARIO`,`CONTRASENA`,`DEPARTAMENTO`,`TIPO_USUARIO`) VALUES ('"+nombre+"', '"+apellido+"', '"+usuario+"', "
                    + "'"+contrasena+"', '"+depto+"', '"+rol+"');";

            try {
                PreparedStatement st = conexion.prepareStatement(sql);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario registrado");
                //this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        llenartabla();
        txtnombre.setText(null);
        txtapellido.setText(null);
        txtusuario.setText(null);
        txtcontrasena.setText(null);
        combodepto.setSelectedItem(null);
        comborol.setSelectedItem(null);
    }
    public void editar(){
        Connection conexion = cn.conector();
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        String usuario = txtusuario.getText();
        String contrasena = txtcontrasena.getText();
        String depto = (String)combodepto.getSelectedItem();
        String rol = (String)comborol.getSelectedItem();
        String sql = "UPDATE `bodega`.`usuarios` SET `NOMBRE` = '"+nombre+"',`APELLIDO` = '"+apellido+"', `USUARIO` = '"+usuario+"', "
                + "`CONTRASENA` = '"+contrasena+"', `DEPARTAMENTO` = '"+depto+"', `TIPO_USUARIO` = '"+rol+"' WHERE (`CODIGO` = '"+this.id+"');";

        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario actualizado");
            llenartabla();
            txtnombre.setText(null);
            txtapellido.setText(null);
            txtusuario.setText(null);
            txtcontrasena.setText(null);
            combodepto.setSelectedItem(null);
            comborol.setSelectedItem(null);
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);

        }finally{
            cn.cierraConexion();
        }
        guardar.setVisible(true);
        editar.setVisible(false);
    }
    public void eliminar(){
        Connection conexion = cn.conector();
        String sql = "DELETE FROM `bodega`.`usuarios` WHERE (`CODIGO` = '"+this.id+"');";
        try {
            PreparedStatement st = conexion.prepareStatement(sql);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
            llenartabla();
            txtnombre.setText(null);
            txtapellido.setText(null);
            txtusuario.setText(null);
            txtcontrasena.setText(null);
            combodepto.setSelectedItem(null);
            comborol.setSelectedItem(null);
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_producto.class.getName()).log(Level.SEVERE, null, ex);

        }finally{
            cn.cierraConexion();
        }
        guardar.setVisible(true);
        editar.setVisible(false);
        eliminar.setVisible(false);
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
        
        Phrase rt = new Phrase("\n\nLISTADO DE USUARIOS\n\n",fontcabecatable);
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

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        this.editar.setVisible(true);
        this.eliminar.setVisible(true);
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        String codigo = String.valueOf(tm.getValueAt(table.getSelectedRow(),0));
        String nombre=String.valueOf(tm.getValueAt(table.getSelectedRow(),1));
        String apellido = String.valueOf(tm.getValueAt(table.getSelectedRow(),2));
        String usuario = String.valueOf(tm.getValueAt(table.getSelectedRow(),3));
        String contrasena = String.valueOf(tm.getValueAt(table.getSelectedRow(),4));
        String departamento = String.valueOf(tm.getValueAt(table.getSelectedRow(),5));
        String rol = String.valueOf(tm.getValueAt(table.getSelectedRow(),6));
        txtnombre.setText(nombre);
        txtapellido.setText(apellido);
        txtusuario.setText(usuario);
        txtcontrasena.setText(contrasena);
        combodepto.setSelectedItem(departamento);
        comborol.setSelectedItem(rol);
        this.id = codigo;
    }//GEN-LAST:event_tableMouseClicked

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        int input = JOptionPane.showConfirmDialog(null, 
                "¿Quiere modificar el usuario seleccionado?", "Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    editar();
                }
    }//GEN-LAST:event_editarActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void txtnombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnombreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreMouseClicked

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_guardarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
       int input = JOptionPane.showConfirmDialog(null, 
                "¿Quiere eliminar el usuario seleccionado?", "Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
                if (input == JOptionPane.YES_OPTION) {
                    eliminar();
                }
    }//GEN-LAST:event_eliminarActionPerformed

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
    }//GEN-LAST:event_busquedaKeyPressed

    private void rSButtonShade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade4ActionPerformed
        docpdf();        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade4ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        departamentos depto = new departamentos();
        depto.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_EditarActionPerformed

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
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rscomponentshade.RSButtonShade Editar;
    private javax.swing.JTextField busqueda;
    private javax.swing.JComboBox<String> combodepto;
    private javax.swing.JComboBox<String> comborol;
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
    private rscomponentshade.RSButtonShade rSButtonShade4;
    private rojerusan.RSTableMetro table;
    private rscomponentshade.RSTextFieldShade txtapellido;
    private rscomponentshade.RSPassFieldShade txtcontrasena;
    private rscomponentshade.RSTextFieldShade txtnombre;
    private rscomponentshade.RSTextFieldShade txtusuario;
    // End of variables declaration//GEN-END:variables
}
