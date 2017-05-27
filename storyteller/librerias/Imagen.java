package storyteller.librerias;

import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import storyteller.program.Logica;
/**
 * Para manejo de fotografias y los datos del API Microsoft
 * @author eleguizamon
 */
public class Imagen implements Serializable
{
    //Variables globales----------------------------
    private final byte[] imagen;     
    private final String caption;   // Describcion
    private final String url;       // http
    private String[] tags;          // 3 tags
    private boolean check;          // Marca Procesada
    //Constructor
    public Imagen(byte[] imagen, String[] tagss,String url) {
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
    public void dibujar() throws IOException
    {
        // Dibujo el titulo,imagen,descripcion
        Logica.getInstance().dibujar(imagen);
    }
    @Override
    public String toString()
    {
        String ret = "vacio";
        
        if(caption != null)
            ret = caption;
        
        return ret;
    }
    public byte[] getImagen() {
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

    public void setCheck(boolean check2) {
        check = check2;
    }
    public void check(){
        check = true;
        
    }
    
     //Gets and Sets
    
    //All the funcion

    public boolean isCheck() {
        return check;
    }

    
}
