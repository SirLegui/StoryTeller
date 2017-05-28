package storyteller.librerias;
/*Librerias a usar*/
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author edgerik, jeremy
 */
public class ParesOrdenados implements Serializable
{
    // Variables globales 
    private ArrayList<String[]> indices;     // name, indice(donde empieza byte)
    
    /**
     * constructor
     */
    public ParesOrdenados() 
    {
        this.indices = new ArrayList<>();
    }
    public int len(){
        return indices.size();
    }
    //Gets and Sets
    public ArrayList<String[]> getIndices()
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
        try{
            return indices.stream().anyMatch((indice) -> (indice[0].equals(name)));
        }catch(NullPointerException e)
        {
            return false;
        }
    }
    //Gets and Sets
    /**
     * 
     * @param name 
     * @param offset 
     * @param largo 
     */
    public void addAlbum(String name, int offset, int largo)
    {
        // Obtengo el largo de pares ordenados
        int largo_indices = len();
        System.out.println("Largo de indices: "+largo_indices);
        System.out.println("Inserto en offset: "+offset+ " "+largo+"bytes...");
        // Validacion 
        
        // Hago set de name y total de bytes del album
        String[] e = {name,Integer.toString(offset), Integer.toString(largo)};
        indices.add(e);
    }
    
    public String[] getOnlyIndices(){
        String[] local = new String[len()];
        for(int i = 0; i<len();i++){
            local[i] = indices.get(i)[0];
        }
        return local;
    }
   
}
