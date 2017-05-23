package storyteller.Estructura;
/*Importes de bibliotecas*/
import java.util.ArrayList;
import storyteller.librerias.Imagen;
import storyteller.program.Logica;
/*
 * Clase ArbolAVL generica
 */
public class ArbolAVL<T>
{
    /*Variables Globales*/
    public Nodo<T> raiz;
    public Logica t;
    /*Constructor*/
    public ArbolAVL()
    {
        //Inicializo variables globales
        this.raiz = new Nodo();
    }
    //Gets and Sets------------------------------------------------------------
    public Nodo<T> getRaiz()
    {
        return raiz;
    }
    //Retorna el ancho del avl
    public int getHeight(Nodo<T> Nodo) 
    {
        if (Nodo == null)
            return 0;
 
        return Nodo.getHeight();
    }
 
    //Obtiene el maximo entre dos valores
    public int getMax(int a, int b) 
    {
        return (a > b) ? a : b;
    }
 
    //Roto a la derecha
    public Nodo<T> rotacionDerecha(Nodo<T> y) 
    {
        //Inicializo nodos izq y der.
        Nodo<T> x = y.getLeft();
        Nodo<T> T2 = x.getRight();
        //Realiza rotaciones
        x.setRight(y);
        y.setLeft(T2);
        //Actualiza las alturas
        y.setHeight(getMax(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);
        x.setHeight(getMax(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        //Retorno nueva raiz
        return x;
    }
 
    //Roto a la izquierda
    public Nodo<T> rotacionIzquierda(Nodo<T> x) 
    {
        //Inicializo nodos izq y der.
        Nodo<T> y = x.getRight();
        Nodo<T> T2 = y.getLeft();
        //Realiza rotaciones
        y.setLeft(x);
        x.setRight(T2);
        //Actualiza las alturas
        x.setHeight(getMax(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        y.setHeight(getMax(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);
        //Retorno nueva raiz
        return y;
    }
 
    //Realizo el balanceo en el avl
    public int getBalance(Nodo<T> Nodo) 
    {
        //Caso de que el nodo sea null, se retorna un cero 
        if (Nodo == null)
            return 0;
        //Obtengo la altura del nodo deseado
        return getHeight(Nodo.getLeft()) - getHeight(Nodo.getRight());
    }
 
    //Inserto nodo en el avl, con sus debidos casos a efectuar
    public Nodo<T> insert(Nodo<T> node, String key) 
    {
        //Caso de que el avl este vacio
        if (node == null)
            return (new Nodo(key));
        
        int acumodador = key.compareTo(node.getKey());
        //Caso si es menor
        if (acumodador < 0)
            node.setLeft(insert(node.getLeft(), key));
        //Caso si es mayor
        else if (acumodador > 0)
            node.setRight(insert(node.getRight(), key));
        //No se permiten claves duplicadas
        else
            return node;
 
        //Actualizo la altura del nodo actual
        node.setHeight(1 + getMax(getHeight(node.getLeft()),
                              getHeight(node.getRight())));

        //Obtengo el factor balanceado del nodo actul 
        //para comprobar si este nodo se desequilibrado
        int balance = getBalance(node);
 
        //Si el nodo esta desbalanceado, se efectua los siguientes casos.
        //Left Left Case
        if (balance > 1 && acumodador < 0)
            return rotacionDerecha(node);
 
        //Right Right Case
        if (balance < -1 && acumodador > 0)
            return rotacionIzquierda(node);
 
        //Left Right Case
        if (balance > 1 && acumodador > 0) {
            node.setLeft(rotacionIzquierda(node.getLeft()));
            return rotacionDerecha(node);
        }
 
        // Right Left Case
        if (balance < -1 && acumodador < 0) {
            node.setRight(rotacionDerecha(node.getRight()));
            return rotacionIzquierda(node);
        }
        
        //Devuelve el puntero del nodo sin cambios 
        return node;
    }
 
    //Preorden
    public void preOrder(Nodo<T> nodo) 
    {
        if (nodo != null) 
        {
            ArrayList<Imagen> fotos = ((ArrayList<Imagen>) nodo.getValue());
            for (Imagen foto : fotos)
            {
                t.desplegar_imagen(foto);
            };
            preOrder(nodo.getLeft());
            preOrder(nodo.getRight());
        }
    }
    
    //Inorden
    public void inOrden(Nodo<T> nodo)
    {
        if(nodo != null)
        {
            inOrden(nodo.getLeft());
            System.out.print(nodo.getKey() + " ");
            inOrden(nodo.getRight());
        }
    }
    
    //PostOrden
    public void postOrden(Nodo<T> nodo)
    {
        if(nodo != null)
        {
            postOrden(nodo.getLeft());
            postOrden(nodo.getRight());
            System.out.print(nodo.getKey() + " ");
        }
    }
}
