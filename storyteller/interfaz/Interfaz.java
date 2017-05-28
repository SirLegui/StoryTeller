package storyteller.interfaz;
//Librerias a importar
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import org.json.simple.parser.ParseException;
import storyteller.librerias.HiloCargar;
import storyteller.librerias.Imagen;
import storyteller.program.Logica;
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
    }
    //Gets y Sets
    public void setBarValue(int val, String message){
        barra.setValue(val);
        //mensajes.append(message);
        //JOptionPane.showMessageDialog(null,message,"Se ha cargado...", 1);
        lblDescripcion.setText("Se ha cargado... "+message);
    }
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save_path = new javax.swing.JFileChooser();
        lblFoto = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblTag1 = new javax.swing.JLabel();
        Cargar = new javax.swing.JButton();
        STOP = new javax.swing.JButton();
        barra = new javax.swing.JProgressBar();

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

        lblFoto.setBackground(new java.awt.Color(255, 255, 255));
        lblFoto.setFont(new java.awt.Font("Consolas", 3, 11)); // NOI18N
        lblFoto.setText("-----------------------------------------------------Imagen a desplegar en el SLIDE SHOW---------------------------------------------------------");
        lblFoto.setDoubleBuffered(true);

        lblDescripcion.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblDescripcion.setText("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Descripcion de la Imagen\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");

            lblTag1.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
            lblTag1.setText("Titulo");

            Cargar.setFont(new java.awt.Font("Modern No. 20", 1, 24)); // NOI18N
            Cargar.setText("Cargar");
            Cargar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    CargarActionPerformed(evt);
                }
            });

            STOP.setFont(new java.awt.Font("Modern No. 20", 1, 18)); // NOI18N
            STOP.setText("STOP");
            STOP.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    STOPActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Cargar)
                                    .addGap(356, 356, 356)
                                    .addComponent(lblTag1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(217, 217, 217)
                                    .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(156, 156, Short.MAX_VALUE)
                    .addComponent(STOP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(109, 109, 109))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(STOP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cargar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(lblTag1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                    .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(113, 113, 113))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

   /*
    Funcion del boton cargar
    */
    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarActionPerformed
        // Digite donde se van a guardar las fotos
        //setDireccion_guardado();

        HiloCargar h = new HiloCargar(controlador);
        h.start();
    }//GEN-LAST:event_CargarActionPerformed
    
    @Override
    public void paint(Graphics g)
    {        
//        dbImage = createImage(getWidth(), getHeight());
//        dbg = dbImage.getGraphics();
//        paintComponent(dbg);
//        g.drawImage(dbImage, 0, 0, this);
        // Le paso el g a Logica
        super.paint(g);
        controlador.setG(g);
    }
//    public void paintComponent(Graphics g)
//    {
//        super.paint(g);
//        repaint();
//    }
    @Override
    public void keyPressed(KeyEvent e) {
        tecla = KeyEvent.getKeyText(e.getKeyCode());
        System.out.println(tecla);
        if(tecla.equals(Integer.toString(KeyEvent.VK_ENTER)))
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

    private void STOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_STOPActionPerformed
        // TODO add your handling code here:
        controlador.cargarPares();
        
        controlador.setSeguirHilo(false);
        v2.setControlador(controlador);
        controlador.cargarPares();
        
        v2.setVisible(true);
    }//GEN-LAST:event_STOPActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cargar;
    private javax.swing.JButton STOP;
    private javax.swing.JProgressBar barra;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblTag1;
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
        //if(controlador.isCargar_listo())
        //    nodo_foto.dibujar(g);
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
*/