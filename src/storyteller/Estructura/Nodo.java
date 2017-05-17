package storyteller.Estructura;
/*Importes de bibliotecas*/

/*
 * Clase Nodo generica
 */
public class Nodo<T> {
    /*Variables Globales*/
    private T Value;
    private Nodo<T> left;
    private Nodo<T> right;
    private int key;
    private int height;
    /*Constructores*/
    public Nodo()
    {
        left = null;
		right = null;
    }
    public Nodo(int d) 
    {
        key = d;
        height = 1;
    }
    //Gets y Sets
    public T getValue() {
        return Value;
    }

    public void setValue(T Value) {
        this.Value = Value;
    }

    public Nodo<T> getLeft() {
        return left;
    }

    public void setLeft(Nodo<T> left) {
        this.left = left;
    }

    public Nodo<T> getRight() {
        return right;
    }

    public void setRight(Nodo<T> right) {
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    //
}
