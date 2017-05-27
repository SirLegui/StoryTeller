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
    private String[][] indices;     // name, indice(donde empieza byte)
    
    /**
     * constructor
     */
    public ParesOrdenados() 
    {
        this.indices = null;
    }
    //Gets and Sets
    public String[][] getIndices()
    {
        return indices;
    }
    /**
     * 
     * @param name
     * @return 
     */
    public boolean esta_en(String name)
    {
        boolean c = false;
        for (int i =0; i<indices.length;i++)
        {
            if(indices[i][0].equals(name))
            {
                c = true;
                break;
            }
        }
        return c;
    }
    //Gets and Sets
    /**
     * 
     * @param name 
     * @param avl 
     * @param offset 
     * @param largo 
     */
    public void addAlbum(String name, Album avl, int offset, int largo)
    {
        // Obtengo el largo de pares ordenados
        int largo_indices = indices.length;
        
        // Validacion 
        
        // Hago set de name y total de bytes del album
        indices[largo_indices][0] = name;
        indices[largo_indices][1] = Integer.toString(offset);
        indices[largo_indices][2] = Integer.toString(largo);
        
        
        
    
    }
    /**
     * Serializa y guarda la clase en un archivo.pros
     */
    public void guardar_pares()
    {
        
    }
    // ->
   
}
