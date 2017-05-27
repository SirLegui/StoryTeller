package storyteller.Estructura;
/*Importes de bibliotecas*/
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import storyteller.librerias.Imagen;
/*
 * Clase Nodo generica
 */
public class Nodo implements Serializable{
    /*Variables Globales*/
    private ArrayList<Imagen> Value;
    private Nodo left;
    private Nodo right;
    private String key;             // Tag 
    private int height;
    private boolean borrar;
    /*Constructores*/
    public Nodo(String d, Imagen ima) 
    {
        Value = new ArrayList();
        Value.add(ima);
        this.left = null;
        this.right = null;
        this.key = d;
        this.height = 1;
    }
    //Gets y Sets
    public void addImagen(Imagen ima)
    {
            Value.add(ima);
    }
    
    public void setValueArray(ArrayList<Imagen> pArray)
    {
        Value = pArray;
    }
    public void setValue(Imagen ima) 
    {
        Value.add(ima);
    }
    public boolean isBorrar()
    {
        return borrar;
    }

    public void setBorrar(boolean borrar) 
    {
        this.borrar = borrar;
    }
    public ArrayList<Imagen> getValue() 
    {
        return Value;
    }
    public Nodo getLeft() 
    {
        return left;
    }
    public void setLeft(Nodo left) 
    {
        this.left = left;
    }
    public Nodo getRight() 
    {
        return right;
    }
    public void setRight(Nodo right) 
    {
        this.right = right;
    }
    public String getKey() 
    {
        return key;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }
    public int getHeight() 
    {
        return height;
    }
    public void setHeight(int height) 
    {
        this.height = height;
    }
    
    //
}
