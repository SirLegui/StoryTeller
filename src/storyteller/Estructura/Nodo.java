package storyteller.Estructura;
/*Importes de bibliotecas*/
import java.io.Serializable;
/*
 * Clase Nodo generica
 */
public class Nodo<T> implements Serializable{
    /*Variables Globales*/
    private T Value;
    private Nodo<T> left;
    private Nodo<T> right;
    private String key;
    private int height;
    /*Constructores*/
    public Nodo(){
        this.Value = null;
        this.left = null;
        this.right = null;
    }
    
    public Nodo(String d) {
        this.Value = null;
        this.left = null;
        this.right = null;
        this.key = d;
        this.height = 1;
    }
    //Gets y Sets
    public void get()
    {
        
    }
    public T getValue() 
    {
        return Value;
    }
    public void setValue(T Value) 
    {
        this.Value = Value;
    }
    public Nodo<T> getLeft() 
    {
        return left;
    }
    public void setLeft(Nodo<T> left) 
    {
        this.left = left;
    }
    public Nodo<T> getRight() 
    {
        return right;
    }
    public void setRight(Nodo<T> right) 
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
