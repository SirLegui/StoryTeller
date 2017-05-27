package storyteller.interfaz;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import storyteller.librerias.HiloSave;
import storyteller.program.Logica;

/*Librerias a usar*/
/**
 *
 * @author live, edgerik
 * Clase Interfaz02
 * 
 * Extiende JFrame
 */
public class Interfaz02 extends javax.swing.JFrame 
{
    // Variables Globales
    private String direccion_guardado;
    // Clases a usar
    private Logica controlador;
    // Constructor
    public Interfaz02() 
    {
        // Inicializa la interfaz
        initComponents();
        // Inicializo variables globales
        this.direccion_guardado = "";
    }
    //Gets and Sets
    public void setControlador(Logica controlador) 
    {
        this.controlador = controlador;
    }
    public String getDireccion_guardado() {
        return direccion_guardado;
    }
    // No borrar .........................................................
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Abrir = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Continue = new javax.swing.JButton();
        lista_albums = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        Abrir.setText("OPEN");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });

        Save.setText("SAVE");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Continue.setText("CONTINUE");
        Continue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lista_albums, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Abrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(Continue, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Continue, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(Abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lista_albums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /*
    Funcion del boton Guardar
    */
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        HiloSave save = new HiloSave(controlador);
        save.start();
    }//GEN-LAST:event_SaveActionPerformed
    /*
    Funcion del boton Abrir
    */
    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        try{
            
            controlador.botonAbrir((String) lista_albums.getSelectedItem());
            setVisible(false);
        } catch (RuntimeException e)
        {
            JOptionPane.showMessageDialog(null,"¡No hay album a abrir!","ERROR",2);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AbrirActionPerformed
    @Override
    public void setVisible(boolean vis)
    {
        super.setVisible(vis);
        
    }
    
    public void setModel(DefaultComboBoxModel model)
    {
        lista_albums.setModel(model);
    }
    /*
    Funcion del boton Continuar
    */
    private void ContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinueActionPerformed
        controlador.botonFollow();
        setVisible(false);
    }//GEN-LAST:event_ContinueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Abrir;
    private javax.swing.JButton Continue;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox<String> lista_albums;
    // End of variables declaration//GEN-END:variables
}
