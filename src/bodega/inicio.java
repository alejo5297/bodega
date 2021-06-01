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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


public class inicio extends javax.swing.JFrame {
    
    Bodega bd = new Bodega();
    public String nombre = bd.nombre;
    
    public inicio() {
       initComponents();
       this.setResizable(false);
       setLocationRelativeTo(null);
       int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
       activo.setText(nombre);
       llenartabla();
       reload();
       table.getColumnModel().getColumn(2).setPreferredWidth(300);
    }
    
    Conexion cn = new Conexion();
    
    public void reload() {
    Timer timer = new Timer(30000, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            llenartabla();
            table.getColumnModel().getColumn(2).setPreferredWidth(300);
        }
    });
    timer.start();        
}  
    public void llenartabla(){
       Connection conexion = cn.conector();
      String sql = "SELECT "
              + "inventario.CODIGO, "
              + "producto.CATEGORIA, "
              + "producto.DESCRIPCION, "
              + "inventario.ENTRADAS_UNITARIAS, "
              + "inventario.SALIDAS_UNITARIAS, "
              + "inventario.ENTRADAS_LBS, "
              + "inventario.SALIDAS_LBS, "
              + "(inventario.ENTRADAS_UNITARIAS - inventario.SALIDAS_UNITARIAS), "
              + "(inventario.ENTRADAS_LBS - inventario.SALIDAS_LBS), "
              + "CONCAT('Q ',inventario.PRECIO_UNIT),"
              + "CONCAT('Q ',inventario.PRECIO_LBS), "
              + "CONCAT('Q ',TRUNCATE((((inventario.ENTRADAS_UNITARIAS)-(inventario.SALIDAS_UNITARIAS))*(inventario.PRECIO_UNIT)),2)), "
              + "CONCAT('Q ',TRUNCATE((((inventario.ENTRADAS_LBS)-(inventario.SALIDAS_LBS))*(inventario.PRECIO_LBS)),2))" 
              + " FROM `bodega`.`inventario`, `bodega`.`producto` "
              + "WHERE ((producto.CODIGO = inventario.CODIGO) AND (inventario.ENTRADAS_UNITARIAS or inventario.ENTRADAS_LBS > 0))  "
              + "ORDER BY inventario.NO ;";
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Cod. Product");
      model.addColumn("Categoría");
      model.addColumn("Descripción");
      model.addColumn("Entradas Unitarias");
      model.addColumn("Salidas Unitarias");
      model.addColumn("Entradas Lbs");
      model.addColumn("Salidas Lbs");
      model.addColumn("Stock Unit");
      model.addColumn("Stock Lbs");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      model.addColumn("Costo Total Unit");
      model.addColumn("Costo Total Lbs");
      table.setModel(model);
      String[] dato = new String[13];
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
    Conexion cn = new Conexion();
    Connection conexion = cn.conector();
    String buscar = busqueda.getText();
      String sql = "SELECT "
              + "inventario.CODIGO, "
              + "producto.CATEGORIA, "
              + "producto.DESCRIPCION, "
              + "inventario.ENTRADAS_UNITARIAS, "
              + "inventario.SALIDAS_UNITARIAS, "
              + "inventario.ENTRADAS_LBS, "
              + "inventario.SALIDAS_LBS, "
              + "(inventario.ENTRADAS_UNITARIAS - inventario.SALIDAS_UNITARIAS), "
              + "(inventario.ENTRADAS_LBS - inventario.SALIDAS_LBS), "
              + "CONCAT('Q ',inventario.PRECIO_UNIT),"
              + "CONCAT('Q ',inventario.PRECIO_LBS), "
              + "CONCAT('Q ',TRUNCATE((((inventario.ENTRADAS_UNITARIAS)-(inventario.SALIDAS_UNITARIAS))*(inventario.PRECIO_UNIT)),2)), "
              + "CONCAT('Q ',TRUNCATE((((inventario.ENTRADAS_LBS)-(inventario.SALIDAS_LBS))*(inventario.PRECIO_LBS)),2))" 
              + " FROM `bodega`.`inventario`, `bodega`.`producto` "
              + "WHERE ((producto.CODIGO = inventario.CODIGO) AND (inventario.ENTRADAS_UNITARIAS or inventario.ENTRADAS_LBS > 0) AND DESCRIPCION like '%"+buscar+"%')  "
              + "ORDER BY inventario.NO ;";
      
      Statement st;
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Cod. Product");
      model.addColumn("Categoría");
      model.addColumn("Descripción");
      model.addColumn("Entradas Unitarias");
      model.addColumn("Salidas Unitarias");
      model.addColumn("Entradas Lbs");
      model.addColumn("Salidas Lbs");
      model.addColumn("Stock Unit");
      model.addColumn("Stock Lbs");
      model.addColumn("Precio Unit");
      model.addColumn("Precio Lbs");
      model.addColumn("Costo Total Unit");
      model.addColumn("Costo Total Lbs");
      table.setModel(model);
      String[] dato = new String[13];
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
                model.addRow(dato);
                System.out.println(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
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
        
        Phrase rt = new Phrase("\n\nKARDEX\n\n",fontcabecatable);
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
        Document doc = new Document(PageSize.LETTER.rotate(), 0,0,0,0);
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
        rSButtonShade1 = new rscomponentshade.RSButtonShade();
        rSButtonShade2 = new rscomponentshade.RSButtonShade();
        rSButtonShade3 = new rscomponentshade.RSButtonShade();
        rSButtonShade5 = new rscomponentshade.RSButtonShade();
        rSButtonShade4 = new rscomponentshade.RSButtonShade();
        rSButtonShade6 = new rscomponentshade.RSButtonShade();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new rojerusan.RSTableMetro();
        rSButtonShade7 = new rscomponentshade.RSButtonShade();
        jLabel1 = new javax.swing.JLabel();
        activo = new javax.swing.JLabel();
        rSToggleButtonShade1 = new rscomponentshade.RSToggleButtonShade();
        jLabel5 = new javax.swing.JLabel();
        rSButtonShade8 = new rscomponentshade.RSButtonShade();
        jLabel3 = new javax.swing.JLabel();
        rSButtonShade9 = new rscomponentshade.RSButtonShade();
        busqueda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rSButtonShade10 = new rscomponentshade.RSButtonShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Kardex y Pedidos a Bodega - JDL");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(26, 129, 135)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/entrada.png"))); // NOI18N
        rSButtonShade1.setText("Entradas");
        rSButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 810, 126, -1));

        rSButtonShade2.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salida.png"))); // NOI18N
        rSButtonShade2.setText("Salidas");
        rSButtonShade2.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 810, 126, -1));

        rSButtonShade3.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pedido.png"))); // NOI18N
        rSButtonShade3.setText("Pedidos");
        rSButtonShade3.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 810, 139, -1));

        rSButtonShade5.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/solicitar.png"))); // NOI18N
        rSButtonShade5.setText("Solicitar Product.");
        rSButtonShade5.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade5ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 810, 183, -1));

        rSButtonShade4.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minmax.png"))); // NOI18N
        rSButtonShade4.setText("Mín y Max");
        rSButtonShade4.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 810, 130, -1));

        rSButtonShade6.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar (1).png"))); // NOI18N
        rSButtonShade6.setText("Consulta");
        rSButtonShade6.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade6ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 810, 130, -1));

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
        table.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setFuenteFilas(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        table.setFuenteHead(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        table.setRowHeight(30);
        table.setSelectionBackground(new java.awt.Color(26, 129, 135));
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 226, 1817, 560));

        rSButtonShade7.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/productos.png"))); // NOI18N
        rSButtonShade7.setText("Productos");
        rSButtonShade7.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade7ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 810, 183, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Bienvenido:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 800, -1, -1));

        activo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        activo.setForeground(new java.awt.Color(0, 204, 0));
        activo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1630, 820, 105, 17));

        rSToggleButtonShade1.setBackground(new java.awt.Color(26, 129, 135));
        rSToggleButtonShade1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit.png"))); // NOI18N
        rSToggleButtonShade1.setToolTipText("");
        rSToggleButtonShade1.setBgHover(new java.awt.Color(26, 129, 135));
        rSToggleButtonShade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSToggleButtonShade1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSToggleButtonShade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1760, 810, 60, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        rSButtonShade8.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/proveedor.png"))); // NOI18N
        rSButtonShade8.setText("Proveedores");
        rSButtonShade8.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade8ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 810, 150, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 38)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 129, 135));
        jLabel3.setText("Sistema de Kardex y Pedidos a Bodega");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        rSButtonShade9.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/usuarios.png"))); // NOI18N
        rSButtonShade9.setText("Usuarios");
        rSButtonShade9.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade9ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 810, 130, -1));

        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busquedaKeyPressed(evt);
            }
        });
        jPanel1.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 330, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/buscar.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        rSButtonShade10.setBackground(new java.awt.Color(26, 129, 135));
        rSButtonShade10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imprimir.png"))); // NOI18N
        rSButtonShade10.setBgHover(new java.awt.Color(26, 129, 135));
        rSButtonShade10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonShade10ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonShade10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1790, 180, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1839, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonShade2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade2ActionPerformed
    salidas sd = new salidas();
    sd.setVisible(true);
    }//GEN-LAST:event_rSButtonShade2ActionPerformed

    private void rSButtonShade7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade7ActionPerformed
        nuevo_producto np = new nuevo_producto();
        np.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade7ActionPerformed

    private void rSButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade1ActionPerformed
    entradas ent = new entradas();
    ent.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade1ActionPerformed

    private void rSToggleButtonShade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSToggleButtonShade1ActionPerformed
        this.setVisible(false);
        login ln = new login();
        ln.setVisible(true);
    }//GEN-LAST:event_rSToggleButtonShade1ActionPerformed

    private void rSButtonShade3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade3ActionPerformed
    pedidos pd = new pedidos();
    pd.setVisible(true);
    }//GEN-LAST:event_rSButtonShade3ActionPerformed

    private void rSButtonShade5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade5ActionPerformed
        solicitar_producto sp = new solicitar_producto();
        sp.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade5ActionPerformed

    private void rSButtonShade4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade4ActionPerformed
        min_max mm = new min_max();
        mm.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade4ActionPerformed

    private void rSButtonShade6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade6ActionPerformed
        consulta con = new consulta();
        con.setVisible(true);   
    }//GEN-LAST:event_rSButtonShade6ActionPerformed

    private void rSButtonShade8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade8ActionPerformed
        nuevo_proveedor np = new nuevo_proveedor();
        np.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade8ActionPerformed

    private void rSButtonShade9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade9ActionPerformed
        usuarios us = new usuarios();
        us.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade9ActionPerformed

    private void busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyPressed
        buscar();
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
    }//GEN-LAST:event_busquedaKeyPressed

    private void rSButtonShade10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonShade10ActionPerformed
        docpdf();        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonShade10ActionPerformed

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
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel activo;
    private javax.swing.JTextField busqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rscomponentshade.RSButtonShade rSButtonShade1;
    private rscomponentshade.RSButtonShade rSButtonShade10;
    private rscomponentshade.RSButtonShade rSButtonShade2;
    private rscomponentshade.RSButtonShade rSButtonShade3;
    private rscomponentshade.RSButtonShade rSButtonShade4;
    private rscomponentshade.RSButtonShade rSButtonShade5;
    private rscomponentshade.RSButtonShade rSButtonShade6;
    private rscomponentshade.RSButtonShade rSButtonShade7;
    private rscomponentshade.RSButtonShade rSButtonShade8;
    public rscomponentshade.RSButtonShade rSButtonShade9;
    private rscomponentshade.RSToggleButtonShade rSToggleButtonShade1;
    private rojerusan.RSTableMetro table;
    // End of variables declaration//GEN-END:variables
}
