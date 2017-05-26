package storyteller.librerias;
/*Librerias a usar*/
import java.io.Serializable;
import java.util.ArrayList;
import storyteller.Estructura.ArbolAVL;

/**
 * 
 * @author edgerik, jeremy
 */
public class ParesOrdenados implements Serializable
{
    // Variables globales 
    private String[][] indices;     // name, indice(donde empieza byte)
    private int largo_indices;
    /**
     * constructor
     */
    public ParesOrdenados() 
    {
        this.indices = null;
        this.largo_indices = 0;
    }
    
    
    //Gets and Sets
    /**
     * 
     * @param name 
     */
    public void addAlbum(String name, Album avl, int largo)
    {
        // Obtengo el largo de pares ordenados
        largo_indices = indices.length;
        
        // Validacion 
        
        // Hago set de name y total de bytes del album
        indices[largo_indices][0] = name;
        indices[largo_indices][1] = Integer.toString(largo);
        
        
    
    }
    /**
     * Serializa y guarda la clase en un archivo.pros
     */
    public void guardar_pares()
    {
        
    }
    // ->
   
}
