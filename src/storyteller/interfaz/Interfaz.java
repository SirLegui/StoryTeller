package storyteller.interfaz;
//Librerias a importar
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.simple.parser.ParseException;
import storyteller.Estructura.Nodo;
import storyteller.librerias.Imagen;
import storyteller.program.Logica;
import sun.java2d.pipe.DrawImage;
/**
 * @author jeremy
 * @author edgerik
 * 
 * Clase Interfaz
 * 
 * Extiende JFrame
 */
public class Interfaz extends javax.swing.JFrame implements KeyListener
{
    //Variables globales
    private String tecla;
    private String direccion_guardado;
    private Image dbImage;
    private Graphics dbg;
    private int foto;
    private Image foto_actual;
    private Imagen nodo_foto;
    private ImageIcon icon;
    private Icon icono;
    private String[] rets;
    private String[] etiquetas;
    //Clases a usar
    private Logica controlador;
    private Interfaz02 v2;
    //Constructor
    public Interfaz() 
    {
        // Inicializa la interfaz
        initComponents();
        addKeyListener(this);
        // Inicializo variables globales
        this.v2 = new Interfaz02();
        this.direccion_guardado = "";
        this.foto = 0;
        this.tecla = "";
        this.etiquetas = null;
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
    public JTextField getDireccion_imagen() 
    {
        return direccion_imagen;
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
    public JLabel getLblTag2() 
    {
        return lblTag2;
    }
    public JLabel getLblTag3() 
    {
        return lblTag3;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save_path = new javax.swing.JFileChooser();
        lblFoto = new javax.swing.JLabel();
        direccion_imagen = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        lblTag1 = new javax.swing.JLabel();
        lblTag2 = new javax.swing.JLabel();
        lblTag3 = new javax.swing.JLabel();
        Cargar = new javax.swing.JButton();
        Procesar = new javax.swing.JButton();

        save_path.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        save_path.setCurrentDirectory(new java.io.File("C:\\Users\\Usuario1\\Escritorio"));
        save_path.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        save_path.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        save_path.setBorder(new javax.swing.border.MatteBorder(null));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        Cargar.setFont(new java.awt.Font("Modern No. 20", 1, 24)); // NOI18N
        Cargar.setText("Cargar");
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });

        Procesar.setFont(new java.awt.Font("Modern No. 20", 0, 18)); // NOI18N
        Procesar.setText("Procesar con link");
        Procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcesarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                        .addComponent(lblTag1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cargar)
                .addGap(129, 129, 129)
                .addComponent(lblTag3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(lblTag2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Procesar)
                    .addComponent(direccion_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblTag3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTag2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Procesar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(direccion_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTag1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
    Funcion del boton procesar.
    */
    private void ProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcesarActionPerformed
        setDireccion_guardado();
        controlador.botonProcesar();
    }//GEN-LAST:event_ProcesarActionPerformed
    /*
    Funcion del boton cargar
    */
    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarActionPerformed
        // Digite donde se van a guardar las fotos
        setDireccion_guardado();
        try {
            // Logica Cargar
            controlador.botonCargar();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CargarActionPerformed
    
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
        // Constructor
        super.paint(g);
        // Creo Image
        //icon = new ImageIcon(foto_actual);
        //icono = new ImageIcon(icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH));
        // Inicializo los datos de la Image
        //etiquetas= nodo_foto.getTags();
        // Inserto Descripcion, letra med
        //lblDescripcion.setText(nodo_foto.getCaption());
        // Inserto Titulo, letra grand
        //lblTag1.setText(etiquetas[0]);

        // Pinto la Image, con herencia
        //lblFoto.setIcon(icono);
        //lblFoto.setText(null);
        //controlador.inOrdenDesplegarImagenes(g, controlador.getAVL().getRaiz());
        
        // Despliego Slay Show
        if(controlador.isCargar_listo())
            nodo_foto.dibujar(g);
        //    controlador.inOrdenDesplegarImagenes(g,controlador.getAVL().getRaiz());
        
        // Pinto
//        repaint();
//        // Slepp
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        tecla = KeyEvent.getKeyText(e.getKeyCode());
        if(tecla != null)
        {
            v2.setControlador(controlador);
            v2.setVisible(true);
        }
    }


    //Fin---------------------------------------------------------
    /*
    Codigo Basura................................................
    */
    private void ContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinueActionPerformed
    }//GEN-LAST:event_ContinueActionPerformed
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
    }//GEN-LAST:event_SaveActionPerformed
    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
    }//GEN-LAST:event_AbrirActionPerformed
    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cargar;
    private javax.swing.JButton Procesar;
    private javax.swing.JTextField direccion_imagen;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblTag1;
    private javax.swing.JLabel lblTag2;
    private javax.swing.JLabel lblTag3;
    private javax.swing.JFileChooser save_path;
    // End of variables declaration//GEN-END:variables
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
/*
            // Digite donde se va a leer el Json de .jpg
            //save_path.showSaveDialog(this);
            //direccion_Json = ((File) save_path.getSelectedFile()).getAbsolutePath();
            
*/