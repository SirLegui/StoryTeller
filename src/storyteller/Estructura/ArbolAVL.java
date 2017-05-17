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
        this.raiz = new Nodo<T>();
    }
    
    //Retorna el ancho del avl
    public int getHeight(Nodo<T> Nodo) 
    {
        if (Nodo == null)
            return 0;
 
        return Nodo.height;
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
        Nodo<T> x = y.left;
        Nodo<T> T2 = x.right;
        //Realiza rotaciones
        x.right = y;
        y.left = T2;
        //Actualiza las alturas
        y.height = getMax(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = getMax(getHeight(x.left), getHeight(x.right)) + 1;
        //Retorno nueva raiz
        return x;
    }
 
    //Roto a la izquierda
    public Nodo<T> rotacionIzquierda(Nodo<T> x) 
    {
        //Inicializo nodos izq y der.
        Nodo<T> y = x.right;
        Nodo<T> T2 = y.left;
        //Realiza rotaciones
        y.left = x;
        x.right = T2;
        //Actualiza las alturas
        x.height = getMax(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = getMax(getHeight(y.left), getHeight(y.right)) + 1;
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
        return getHeight(Nodo.left) - getHeight(Nodo.right);
    }
 
    //Inserto nodo en el avl, con sus debidos casos a efectuar
    public Nodo<T> insert(Nodo<T> node, int key) 
    {
        //Caso de que el avl este vacio
        if (node == null)
            return (new Nodo(key));
        //Caso si es menor
        if (key < node.key)
            node.left = insert(node.left, key);
        //Caso si es mayor
        else if (key > node.key)
            node.right = insert(node.right, key);
        //No se permiten claves duplicadas
        else
            return node;
 
        //Actualizo la altura del nodo actual
        node.height = 1 + getMax(getHeight(node.left),
                              getHeight(node.right));

        //Obtengo el factor balanceado del nodo actul 
        //para comprobar si este nodo se desequilibrado
        int balance = getBalance(node);
 
        //Si el nodo esta desbalanceado, se efectua los siguientes casos.
        //Left Left Case
        if (balance > 1 && key < node.left.key)
            return rotacionDerecha(node);
 
        //Right Right Case
        if (balance < -1 && key > node.right.key)
            return rotacionIzquierda(node);
 
        //Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = rotacionIzquierda(node.left);
            return rotacionDerecha(node);
        }
 
        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rotacionDerecha(node.right);
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
            System.out.print(nodo.key + " ");
            preOrder(nodo.left);
            preOrder(nodo.right);
        }
    }
    
    //Inorden
    public void inOrden(Nodo<T> nodo)
    {
        if(nodo != null)
        {
            preOrder(nodo.left);
            System.out.print(nodo.key + " ");
            preOrder(nodo.right);
        }
    }
    
    //Main
    public static void main(String[] args) {
        ArbolAVL tree = new ArbolAVL();
 
        //Constructing tree given in the above figure 
        tree.raiz = tree.insert(tree.raiz, 10);
        tree.raiz = tree.insert(tree.raiz, 20);
        tree.raiz = tree.insert(tree.raiz, 30);
        tree.raiz = tree.insert(tree.raiz, 40);
        tree.raiz = tree.insert(tree.raiz, 50);
        tree.raiz = tree.insert(tree.raiz, 25);
        tree.raiz = tree.insert(tree.raiz, 49);
 
        //The constructed AVL Tree would be
        //     30
        //    /  \
        //  20   40
        // /  \     \
        //10  25    50
        
        System.out.println("Preorder traversal" +
                        " of constructed tree is : ");
        tree.preOrder(tree.raiz);
        System.out.println("\n");
        tree.inOrden(tree.raiz);
    }

    //Fin..
}
