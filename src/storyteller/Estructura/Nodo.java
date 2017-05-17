package storyteller.Estructura;
/*Importes de bibliotecas*/

/*
 * Clase Nodo generica
 */
public class Nodo<T> {
    /*Variables Globales*/
    int key, height;
    Nodo<T> left, right;
    /*Constructores*/
    public Nodo()
    {
        
    }
    public Nodo(int d) 
    {
        key = d;
        height = 1;
    }
    
    //Fin..
}
