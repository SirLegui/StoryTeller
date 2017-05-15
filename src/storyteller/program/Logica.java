//Progra 02 - StoryTeller
package storyteller.program;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import storyteller.interfaz.Interfaz;
import storyteller.librerias.MCS;

/**
 *
 * @author edgerik
 * @author jeremy
 */
public class Logica {
    //Variables globales-------------------------------------------------------
    
    
    //Clases a usar
    Interfaz interfaz;
    
    //Constructor---------------------------------------------------------------
    public Logica()
    {
    }        

    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    
    
    //Gets y Sets---------------------------------------------------------------
    
    //Funciones StoryTeller-----------------------------------------------------
    //Boton Cargar.
    public void botonCargar()
    {
        MCS api = new MCS();
        String[] rets = null;
        //FileChooser fc = new FileChooser();
        //fc.showOpenDialog(null);
        ImageIcon icon = new ImageIcon(interfaz.getDireccion_imagen().getText());
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_DEFAULT));
        interfaz.getLblFoto().setIcon( icono );
        try {
            rets = api.getDescriptionBYTES(api.getImageBYTES(interfaz.getDireccion_imagen().getText()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.getLblFoto().setText(null);
        interfaz.getLblDescripcion().setText(rets[0]);
        interfaz.getLblTag1().setText(rets[1]);
        interfaz.getLblTag2().setText(rets[2]);
        interfaz.getLblTag3().setText(rets[3]);
        interfaz.aumentarFoto();
    }
    
    //Boton Procesar.
    public void botonProcesar()
    {
        MCS api = new MCS();
        String[] rets = null;
        //FileChooser fc = new FileChooser();
        //fc.showOpenDialog(null);
        String local = interfaz.getDireccion_guardado() + "imagen"+Integer.toString(interfaz.getFoto())+".jpg";
        try {
            api.getImage(interfaz.getDireccion_imagen().getText(), local);
            rets = api.getDescription(interfaz.getDireccion_imagen().getText());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageIcon icon = new ImageIcon(local);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_SMOOTH));
        interfaz.getLblFoto().setIcon( icono );
        
        interfaz.getLblFoto().setText(null);
        interfaz.getLblDescripcion().setText(rets[0]);
        interfaz.getLblTag1().setText(rets[1]);
        interfaz.getLblTag2().setText(rets[2]);
        interfaz.getLblTag3().setText(rets[3]);
        interfaz.aumentarFoto();
    }

}
