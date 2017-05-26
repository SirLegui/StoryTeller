package storyteller.librerias;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.String;
import storyteller.interfaz.Interfaz;
/**
 * Para manejo de fotografias y los datos del API Microsoft
 * @author eleguizamon
 */
public class Imagen
{
    //Variables globales----------------------------
    private final Image imagen;     
    private final String caption;   // Describcion
    private final String url;       // http
    private String[] tags;          // 3 tags
    private boolean check;          // Marca Procesada
    //Constructor
    public Imagen(Image imagen, String[] tagss,String url) {
        this.imagen = imagen;
        this.caption = tagss[0];
        this.url = url;
        this.tags = new String[3];
        this.tags[0] = tagss[1];
        this.tags[1] = tagss[2];
        this.tags[2] = tagss[3];
        this.check = false;
    }
    // Dibujar
    public void dibujar(Graphics g)
    {
        // Dibujo el titulo,imagen,descripcion
        g.drawImage(imagen, 10, 10, null);
        
    }
    public Image getImagen() {
        return imagen;
    }

    public String[] getTags() {
        return tags;
    }

    public String getCaption() {
        return caption;
    }

    public String getUrl() {
        return url;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public void check(){
        if(!check){
            check = true;
        }
    }
    
    

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
     //Gets and Sets
    
    //All the funcion

    public boolean isCheck() {
        return check;
    }

    
}
