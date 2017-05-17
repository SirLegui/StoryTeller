package storyteller.Estructura;
/*Importes de bibliotecas*/
import storyteller.Estructura.Nodo;
/*
 * Clase ArbolAVL generica
 */
public class ArbolAVL<T>
{
    /*Variables Globales*/
    private Nodo<T> raiz;
    
    /*Constructor*/
    public ArbolAVL()
    {
        //Inicializo variables globales
        this.raiz = null;
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
    public Nodo<T> insert(Nodo<T> nodo, int llave) 
    {
        //Caso de que el avl este vacio
        if (nodo == null)
            return (new Nodo(llave));
        //Caso si es menor
        if (llave < nodo.getKey())
            nodo.setLeft(insert(nodo.getLeft(), llave));
        //Caso si es mayor
        else if (llave > nodo.getKey())
            nodo.setRight(insert(nodo.getRight(), llave));
        //No se permiten claves duplicadas
        else
            return nodo;
 
        //Actualizo la altura del nodo actual
        nodo.setHeight(1 + getMax(getHeight(nodo.getLeft()),
                              getHeight(nodo.getRight())));

        //Obtengo el factor balanceado del nodo actul 
        //para comprobar si este nodo se desequilibrado
        int balance = getBalance(nodo);
 
        //Si el nodo esta desbalanceado, se efectua los siguientes casos.
        //Left Left Case
        if (balance > 1 && llave < nodo.getLeft().getKey())
            return rotacionDerecha(nodo);
 
        //Right Right Case
        if (balance < -1 && llave > nodo.getRight().getKey())
            return rotacionIzquierda(nodo);
 
        //Left Right Case
        if (balance > 1 && llave > nodo.getLeft().getKey()) {
            nodo.setLeft(rotacionIzquierda(nodo.getLeft()));
            return rotacionDerecha(nodo);
        }
 
        // Right Left Case
        if (balance < -1 && llave < nodo.getRight().getKey()) {
            nodo.setRight(rotacionDerecha(nodo.getRight()));
            return rotacionIzquierda(nodo);
        }
        
        //Devuelve el puntero del nodo sin cambios 
        return nodo;
    }
 
    //Preorden
    public void preOrden(Nodo<T> nodo) 
    {
        if (nodo != null) 
        {
            System.out.print(nodo.getKey() + " ");
            preOrden(nodo.getLeft());
            preOrden(nodo.getRight());
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
    
    //Main
    public static void main(String[] args) 
    {
        //
        ArbolAVL tree = new ArbolAVL();
 
        //
        tree.raiz = tree.insert(tree.raiz, 10);
        tree.raiz = tree.insert(tree.raiz, 20);
        tree.raiz = tree.insert(tree.raiz, 30);
        tree.raiz = tree.insert(tree.raiz, 40);
        tree.raiz = tree.insert(tree.raiz, 50);
        tree.raiz = tree.insert(tree.raiz, 25);

        //The constructed AVL Tree would be
        //     30
        //    /  \
        //  20   40
        // /  \     \
        //10   25    50

        //
        tree.preOrden(tree.raiz);
        System.out.println("\n");
        tree.inOrden(tree.raiz);
        System.out.println("\n");
        tree.postOrden(tree.raiz);

    }

    //Fin..
}
