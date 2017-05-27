package storyteller.interfaz;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.simple.parser.ParseException;
import storyteller.librerias.Imagen;
import storyteller.program.Logica;

/**
 *
 * @author live
 */
public class Interfaz00 extends javax.swing.JFrame 
{
  //Variables globales
    private String tecla;
    private String direccion_guardado;
    private Image dbImage;
    private Graphics dbg;
    private int foto;
    private Image foto_actual;
    private Imagen nodo_foto;
    //Clases a usar
    private Logica controlador;
    private Interfaz02 v2;
    //Constructor
    public Interfaz00() {
        initComponents();
        // Inicializo variables globales
        this.v2 = new Interfaz02();
        this.direccion_guardado = "";
        this.foto = 0;
        this.tecla = "";
    }
      //Gets y Sets
    public void setDescripcion(String caption){
        lblDescripcion.setText(caption);
    }
    public void setTitulo(String titu)
    {
        lblTag1.setText(titu);
    }
    public void setNodo_foto(Imagen nodo_foto) {
        this.nodo_foto = nodo_foto;
    }
    public void setFoto_actual(Image foto_actual) {
        this.foto_actual = foto_actual;
    }
    public Imagen getNodo_foto() {
        return nodo_foto;
    }
    public void setControlador(Logica controlador) 
    {
        this.controlador = controlador;
    }
    public int getFoto() {
        return foto;
    }
    public void setDireccion_guardado() {
        save_path.showSaveDialog(this);
        direccion_guardado = ((File) save_path.getSelectedFile()).getAbsolutePath();
    }
    public Image getFoto_actual() {
        return foto_actual;
    }
    public String getDireccion_guardado() {
        return direccion_guardado;
    }
    public int aumentarFoto() 
    {
        return foto++;
    }
    public JLabel getLblDescripcion() 
    {
        return lblDescripcion;
    }
    public JLabel getLblFoto() 
    {
        return lblFoto;
    }
    public JLabel getLblTag1() 
    {
        return lblTag1;
    }
    @Override
    public void paint(Graphics g)
    {        
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
        // Le paso el g a Logica
        controlador.setG(g);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paint(g);
        repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save_path = new javax.swing.JFileChooser();
        cargar = new javax.swing.JButton();
        lblTag1 = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();

        save_path.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cargar.setText("jButton1");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        lblTag1.setText("jLabel1");

        lblDescripcion.setText("jLabel2");

        lblFoto.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(lblTag1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cargar)
                .addGap(62, 62, 62)
                .addComponent(lblTag1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        // TODO add your handling code here:
           // Digite donde se van a guardar las fotos
        setDireccion_guardado();

        try {
            // Logica Cargar
            controlador.botonCargar();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cargarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargar;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblTag1;
    private javax.swing.JFileChooser save_path;
    // End of variables declaration//GEN-END:variables
}
