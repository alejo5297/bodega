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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jagh_
 */
public class login extends javax.swing.JFrame {
    public String nombre;
    public String usuario;
    
    public login() {
       initComponents();
       this.setResizable(false);
       setLocationRelativeTo(null);
       this.setIconImage(new ImageIcon(getClass().getResource("/icon/logo2.png")).getImage());
    }

    void acceder(String usuario, String pass){
        String cap =""; 
        String sql="SELECT * FROM usuarios WHERE USUARIO = '"+usuario+"' && CONTRASENA = '"+pass+"'";
        try {
            Statement st = cn1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cap = rs.getString("TIPO_USUARIO");
            }
            if(cap.equals("Administrador")){
                this.setVisible(false);
                inicio ini= new inicio();
                ini.setVisible(true);
            }
            if(cap.equals("Invitado")){
                this.setVisible(false);
                inicio2 ini= new inicio2();
                ini.setVisible(true);
            }
            
            if((!cap.equals("Administrador"))&&(!cap.equals("Invitado"))){
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //cc1.cierraConexion();
        }
    }
 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtusuario = new rscomponentshade.RSFormatFieldShade();
        txtpassword = new rscomponentshade.RSPassFieldShade();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btningreso = new rscomponentshade.RSButtonShade();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N
        jLabel3.setToolTipText("");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jPanel2.setBackground(new java.awt.Color(26, 129, 135));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtusuario.setPlaceholder("Escriba su usuario");
        jPanel2.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 200, -1));

        txtpassword.setPlaceholder("Escriba su contraseña");
        jPanel2.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bienvenido");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/padlock (1).png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/usuarios.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        btningreso.setBackground(new java.awt.Color(26, 129, 135));
        btningreso.setText("Entrar");
        btningreso.setBgHover(new java.awt.Color(26, 129, 135));
        btningreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresoActionPerformed(evt);
            }
        });
        jPanel2.add(btningreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 110, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 300, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btningresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresoActionPerformed
        Bodega bd = new Bodega();
        String user = new String(this.txtusuario.getText());
        String pass = new String(txtpassword.getPassword());
        bd.nombre = user;
        acceder(user,pass); 
        System.out.println(bd.nombre);
    }//GEN-LAST:event_btningresoActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rscomponentshade.RSButtonShade btningreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rscomponentshade.RSPassFieldShade txtpassword;
    public rscomponentshade.RSFormatFieldShade txtusuario;
    // End of variables declaration//GEN-END:variables
Conexion cc1 = new Conexion();
Connection cn1 = cc1.conector();
Conexion cc2 = new Conexion();
Connection cn2 = cc2.conector();
}
