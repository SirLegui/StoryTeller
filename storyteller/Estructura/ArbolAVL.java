package storyteller.Estructura;
/*Importes de bibliotecas*/
import java.io.Serializable;
import java.util.ArrayList;
import storyteller.librerias.Imagen;
import storyteller.program.Logica;
/*
 * Clase ArbolAVL generica
 */
public class ArbolAVL<T> implements Serializable
{
    /*Variables Globales*/
    public Nodo<T> raiz;
    public Logica t;
    /*Constructor*/
    public ArbolAVL()
    {
        this.t = Logica.getInstance();
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
 
    public Nodo<T> delete(Nodo<T> node, String key)
    {
        //Caso de que el avl este vacio
        if (node == null)
            return null;
        
        int acumodador = key.compareTo(node.getKey());
        //Caso si es menor
        String tag = node.getKey();
        ArrayList<Imagen> matriz = (ArrayList<Imagen>) node.getValue();
        
 
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
            fotos.stream().forEach((foto) -> {
                t.desplegar_imagen(foto);
            });
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
            ArrayList<Imagen> fotos = ((ArrayList<Imagen>) nodo.getValue());
            fotos.stream().forEach((foto) -> {
                t.desplegar_imagen(foto);
            });
            inOrden(nodo.getRight());
        }
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

    int max(int a, int b)
    {
        return (a > b) ? a : b;
    }
    
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
        root.setHeight(max(root.getLeft().getHeight(), root.getRight().getHeight() )+ 1);

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
    /**
     * 
     * @param nodo 
     */
    public void inOrdenDepurar(Nodo<T> nodo)
    {
        if(nodo != null)
        {
            inOrden(nodo.getLeft());
            ArrayList<Imagen> fotos = ((ArrayList<Imagen>) nodo.getValue());
            
            fotos.stream().forEach((foto) -> {
                Imagen fo = foto;
                if(fo.isCheck()){
                   fotos.remove(fo);
                }
            });
            
            if(fotos.isEmpty())
                nodo.setBorrar(true);
            
            
            delete(nodo, nodo.getKey());
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
