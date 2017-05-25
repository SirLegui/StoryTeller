package storyteller.Estructura;
/*Importes de bibliotecas*/
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import storyteller.librerias.Imagen;
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
    private boolean borrar;
    /*Constructores*/
    public Nodo()
    {
        this.Value = (T) new ArrayList<Imagen>();
        this.left = null;
        this.right = null;
        this.key = "";
        this.height = 1;
    }
    public Nodo(String d) 
    {
        this.Value = (T) new ArrayList<Imagen>();
        this.left = null;
        this.right = null;
        this.key = d;
        this.height = 1;
    }
    //Gets y Sets
    public boolean isBorrar()
    {
        return borrar;
    }

    public void setBorrar(boolean borrar) 
    {
        this.borrar = borrar;
    }
    public T getValue() 
    {
        return Value;
    }
    public void setValue(T pValue, Imagen ima) 
    {
        ArrayList<Imagen> array = (ArrayList<Imagen>)Value;
        array.add(ima);
        this.Value = pValue;
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
