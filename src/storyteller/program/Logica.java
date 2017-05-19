//Progra 02 - StoryTeller
package storyteller.program;


import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    MCS api;
    JSONParser parser;
    Object obj;
    JSONObject jsonObject;
    JSONArray urls;
    Iterator<JSONObject> iter;
    String[] rets;
    ImageIcon icon;
    Icon icono;
    
    //Constructor---------------------------------------------------------------

    public Logica() {
        this.interfaz = null;
        this.api = null;
        this.parser = null;
        this.obj = null;
        this.jsonObject = null;
        this.urls = null;
        this.iter = null;
        this.rets = null;
        this.icon = null;
        this.icono = null;
    }
     

    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    
    
    //Gets y Sets---------------------------------------------------------------
    
    //Funciones StoryTeller-----------------------------------------------------
    //Boton Cargar.
    public void botonCargar() throws IOException, ParseException
    {
        api = new MCS();
        parser = new JSONParser();
        obj = parser.parse(new FileReader("Prueba.json"));
        jsonObject = (JSONObject) obj;
        urls = (JSONArray) jsonObject.get("urls");
        iter = urls.iterator();
        do{
            rets = null;
            //FileChooser fc = new FileChooser();
            //fc.showOpenDialog(null);
            icon = new ImageIcon(interfaz.getDireccion_imagen().getText());
            icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_DEFAULT));
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
        }while(iter.hasNext());
    }
    
    //Boton Procesar.
    public void botonProcesar()
    {
        api = new MCS();
        rets = null;
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
        
        icon = new ImageIcon(local);
        icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_SMOOTH));
        interfaz.getLblFoto().setIcon( icono );
        
        interfaz.getLblFoto().setText(null);
        interfaz.getLblDescripcion().setText(rets[0]);
        interfaz.getLblTag1().setText(rets[1]);
        interfaz.getLblTag2().setText(rets[2]);
        interfaz.getLblTag3().setText(rets[3]);
        interfaz.aumentarFoto();
    }

}
