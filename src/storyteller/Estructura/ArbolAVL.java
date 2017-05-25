package storyteller.Estructura;
/*Importes de bibliotecas*/
import java.io.Serializable;
import java.util.ArrayList;
import storyteller.librerias.Imagen;
import storyteller.program.Logica;
/**
 * Clase AVL para manejo de estructura
 * Utiliza la instancia Singleton de Logica
 * @author eleguizamon
 * 
 */
public class ArbolAVL implements Serializable
{
    /*Variables Globales*/
    private Nodo raiz;
    private Logica controlador;
    private boolean depurado;
    /**
     * Constructor de la clase con llamada a Singleton Logica
     */
    public ArbolAVL(Logica c)
    {
        // Singlenton
        this.controlador = c;
        // Inicializo nodo
        this.raiz = null;
        // Depurar en false
        depurado = false;
    }
    //Gets and Sets-----------------------------------------------------------
    public boolean isDepurado() 
    {
        return depurado;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public void setDepurado(boolean depurado) 
    {
        this.depurado = depurado;
    }
    public Nodo getRaiz()
    {
        return raiz;
    }
    
    /**
     * Recorrido e impresion en orden
     * @param nodo 
     */
    public void inOrdenDesplegarImagenes(Nodo nodo)
    {
        if(nodo != null)
        {
            //
            inOrdenDesplegarImagenes(nodo.getLeft());
            //
            int largo = nodo.getValue().size();
            for (int i = largo-1; i >0; i--) {
                if(nodo.getValue().get(i) != null) {
                    controlador .desplegar_imagen(nodo.getValue().get(i));
                }
            }
            //
            inOrdenDesplegarImagenes(nodo.getRight());
        }
    }
    /**
     * 
     * @param nodo
     * @param nivel 
     */
    public void inOrden(Nodo nodo, int nivel)
    {
        if(nodo != null)
        {
            inOrden(nodo.getLeft(), nivel+1);
            String tabs = "";
            for(int i = 0; i<nivel ;i++)
            {
                tabs += "\t";
            }
            System.out.print(tabs + nodo.getKey() + " en el nivel "+ nivel+"\n");
            inOrden(nodo.getRight(), nivel+1);
        }
    }

    /**
     * Recorre el arbol y marca a los nodos inutilizados para despues borrarlos
     * @param nodo Actual a procesar
     */
    public void inOrdenDescartar(Nodo nodo)
    {
        if(nodo != null)
        {
            //
            inOrdenDescartar(nodo.getLeft());
            //
            int largo = nodo.getValue().size();
            for (int i = largo-1; i >0; i--) {
                Imagen fo = nodo.getValue().get(i);
                if(fo.isCheck()){
                   nodo.getValue().remove(fo);
                }
            }

            if(nodo.getValue().isEmpty())         // Borra el nodo si el array esta vacio
            {
                nodo.setBorrar(true);   
            }
            //
            inOrdenDescartar(nodo.getRight());
        }
    }
    /**
     * Recorre el arbol y elimina a los nodos inutilizados para despues borrarlos
     * @param nodo Actual a procesar
     */
    public void inOrdenElimina(Nodo nodo)
    {
        if(nodo != null)
        {
            inOrdenElimina(nodo.getLeft());
            
            if(nodo.isBorrar())
            {
                deleteNode(raiz, nodo.getKey());
            }
            
            inOrdenElimina(nodo.getRight());
        }
    }
    /**
     * 
     * @param Nodo altura del nodo
     * @return altura del nodo
     */
    public int getHeight(Nodo Nodo) 
    {
        if (Nodo == null)
            return 0;
 
        return Nodo.getHeight();
    }
    /**
     * Obtiene el maximo entre dos valores
     * @param a
     * @param b
     * @return 
     */
    public int getMax(int a, int b) 
    {
        return (a > b) ? a : b;
    }
    /**
     * Zag
     * @param y Nodo a rotar
     * @return Ultimo nodo rotado
     */
    public Nodo rotacionDerecha(Nodo y) 
    {
        //Inicializo nodos izq y der.
        Nodo x = y.getLeft();
        Nodo T2 = x.getRight();
        //Realiza rotaciones
        x.setRight(y);
        y.setLeft(T2);
        //Actualiza las alturas
        y.setHeight(getMax(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);
        x.setHeight(getMax(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        //Retorno nueva raiz
        return x;
    }
    /**
     * Zig
     * @param x Nodo a rotar
     * @return Ultimo nodo rotado
     */
    public Nodo rotacionIzquierda(Nodo x) 
    {
        //Inicializo nodos izq y der.
        Nodo y = x.getRight();
        Nodo T2 = y.getLeft();
        //Realiza rotaciones
        y.setLeft(x);
        x.setRight(T2);
        //Actualiza las alturas
        x.setHeight(getMax(getHeight(x.getLeft()), getHeight(x.getRight())) + 1);
        y.setHeight(getMax(getHeight(y.getLeft()), getHeight(y.getRight())) + 1);
        //Retorno nueva raiz
        return y;
    }
    /**
     * Saca el balance del nodo
     * @param Nodo nodo a buscar balance
     * @return numero en [-1,1] si esta en balance, sino, esta desbalanceado
     */
    public int getBalance(Nodo Nodo) 
    {
        //Caso de que el nodo sea null, se retorna un cero 
        if (Nodo == null)
            return 0;
        //Obtengo la altura del nodo deseado
        return getHeight(Nodo.getLeft()) - getHeight(Nodo.getRight());
    }
    /**
     * Inserto nodo en el avl, con sus debidos casos a efectuar
     * @param node Nodo a insertar
     * @param key Llave para comparar y colocar el Nodo correctamente
     * @return 
     */
    public Nodo insert(Nodo node, String key, Imagen ima) 
    {
        //Caso de que el avl este vacio
        if (node == null)
            return (new Nodo(key, ima));
        
        int acumodador = key.compareTo(node.getKey());
        //Caso si es menor
        if (acumodador < 0)
            node.setLeft(insert(node.getLeft(), key, ima));
        //Caso si es mayor
        else if (acumodador > 0)
            node.setRight(insert(node.getRight(), key, ima));
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
    /**
     * Encuentra el valor minimo del arbol
     * @param node
     * @return 
     */
    public Nodo minValueNode(Nodo node)
    {
        Nodo current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)
           current = current.getLeft();

        return current;
    }
    /**
     * 
     * @param root
     * @param key
     * @return 
     */
    public Nodo deleteNode(Nodo root, String key)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        int compa = key.compareTo(root.getKey());
        if (compa < 0)
            root.setLeft(deleteNode(root.getLeft(), key));

        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (compa > 0)
            root.setRight(deleteNode(root.getRight(), key));

        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null))
            {
                Nodo temp = null;
                if (temp == root.getLeft())
                    temp = root.getRight();
                else
                    temp = root.getLeft();

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else   // One child case
                    root = temp; // Copy the contents of
                                 // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Nodo temp = minValueNode(root.getRight());

                // Copy the inorder successor's data to this node
                root.setKey(temp.getKey());

                // Delete the inorder successor
                root.setRight(deleteNode(root.getRight(), temp.getKey()));
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        try{
            root.setHeight(getMax(root.getLeft().getHeight(), root.getRight().getHeight() )+ 1);
        }catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            root.setHeight(1);
        }
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rotacionDerecha(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0)
        {
            root.setLeft(rotacionIzquierda(root.getLeft()));
            return rotacionDerecha(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return rotacionIzquierda(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0)
        {
            root.setRight(rotacionDerecha(root.getRight()));
            return rotacionIzquierda(root);
        }

        return root;
    }
}
/*
//            fotos.stream().forEach((foto) -> {
//                Imagen fo = foto;
//                if(fo.isCheck()){
//                   fotos.remove(fo);
//                }
//            });
//            
*/