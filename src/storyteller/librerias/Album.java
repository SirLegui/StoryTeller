package storyteller.librerias;
/*Librerias a usar*/
import storyteller.Estructura.ArbolAVL;
/**
 * 
 * @author live, edgerik
 * 
 * Clase Album
 * 
 * AVL,name,c\\Direccion.x
 */
public class Album 
{
    // Variables Globales --------------------------------------------------
    private ArbolAVL tree;          // tree
    private String nombre;          // name album
    private String local_path;      // c://
    /**
     * Constructor ---------------------------------------------------------
     * @param tree
     * @param nombre
     * @param local_path 
     */
    public Album(ArbolAVL tree, String nombre, String local_path) 
    {
        // 
        this.tree = tree;
        this.nombre = nombre;
        this.local_path = local_path;
    }
    /**
     * Gets and Sets
     */
    public String getNombre() 
    {
        return nombre;
    }
    
    public String getLocal_path() 
    {
        return local_path;
    }
    
    public ArbolAVL getTree() 
    {
        return tree;
    }
    
    public void setLocal_path(String local_path) 
    {
        this.local_path = local_path;
    }
    
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public void setTree(ArbolAVL tree) 
    {
        this.tree = tree;
    } 
}
