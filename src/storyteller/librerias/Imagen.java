package storyteller.librerias;

import java.awt.Image;

public class Imagen
{
    //Variables globales----------------------------
    private Image imagen;
    private String caption;
    private String url;
    private String[] tags;
    private boolean check;
    //Constructor
    
   
    public Imagen(Image imagen, String[] tagss,String url) {
        this.imagen = imagen;
        this.caption = tagss[0];
        this.url = url;
        tags[0] = tagss[1];
        tags[1] = tagss[2];
        tags[2] = tagss[3];
        this.check = false;
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
