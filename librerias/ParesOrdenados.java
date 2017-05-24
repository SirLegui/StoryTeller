package storyteller.librerias;
/*Librerias a usar*/
import java.io.Serializable;

/**
 * 
 * @author edgerik, jeremy
 */
public class ParesOrdenados implements Serializable
{
    // Variables globales 
    private String[][] indices;
    /**
     * constructor
     */
    public ParesOrdenados() 
    {
        this.indices = null;
    }
    /**
     * 
     * @return Tabla de pares ordenados
     */
    public String[][] getIndices()
    {
        return indices;
    }
    public void setIndices(String[][] pIndices)
    {
        indices = pIndices;
    }
    /**
     * Serializa y guarda la clase en un archivo.pros
     */
    public void guardar_pares()
    {
        
    }
    // ->
   
}
