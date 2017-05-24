package storyteller.program;
import java.awt.EventQueue;
import storyteller.Estructura.ArbolAVL;
import storyteller.Estructura.Nodo;
//import storyteller.interfaz.Interfaz;
import storyteller.interfaz.Interfaz;
/*
 * Clase Main
 * 
 */
public class Main {
	/*Main*/
	public static void main(String[] args)
	{
            //Inicio el programa Psicodelic canvas
            EventQueue.invokeLater(() -> {
            try {
<<<<<<< HEAD
                //Inicializo clases
               
//                Interfaz v1 = new Interfaz();
//                Logica c = Logica.getInstance();
//                //Habilito la interfaz
//                v1.setControlador(c);
//                c.setInterfaz(v1);  //Inicializo clase logica
//                c.setAVL(tree);     //Inicializo clase AVL
//                v1.setVisible(true);
//                
                System.out.println("--");
                ArbolAVL tree = new ArbolAVL();
                tree.setRaiz(tree.insert(tree.getRaiz(), "nzx"));
                tree.setRaiz(tree.insert(tree.getRaiz(), "zas"));
                tree.setRaiz(tree.insert(tree.getRaiz(), "caw"));
                tree.setRaiz(tree.insert(tree.getRaiz(), "abc"));
                tree.setRaiz(tree.insert(tree.getRaiz(), "aac"));
                tree.setRaiz(tree.insert(tree.getRaiz(), "cdd"));
                
                
                tree.inOrden(tree.getRaiz(),0);
                System.out.println("\n\n\n");
                tree.setRaiz(tree.deleteNode(tree.getRaiz(), "caw"));
                tree.inOrden(tree.getRaiz(),0);
                System.out.println("\n\n\n");
                tree.setRaiz(tree.deleteNode(tree.getRaiz(), "aac"));
                tree.inOrden(tree.getRaiz(),0);
=======
//                //Inicializo clases
                ArbolAVL tree = new ArbolAVL();
//                Interfaz v1 = new Interfaz();
//                Logica c = Logica.getInstance();
//                //Habilito la interfaz
//                v1.setControlador(c);
//                c.setInterfaz(v1);  //Inicializo clase logica
//                c.setAVL(tree);     //Inicializo clase AVL
//                v1.setVisible(true);
//                
                Nodo raiz = tree.getRaiz();
                
                tree.raiz = tree.insert(raiz, "abc");
                System.out.println("--");
                tree.raiz = tree.insert(raiz, "aaa");
                tree.raiz = tree.insert(raiz, "z");
                tree.deleteNode(raiz, "aaa");
                tree.inOrden(raiz);
>>>>>>> a5faa341499e819b3cccb5bb3b056a8fa8d564c4
                //c.recorreAVL();
                //...
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
            }); 
		//-------------------		
		//Termino el programa
		//-------------------
		//by.Jeremy Live G---
		//-------------------
	}
}



/*
                int a[]={1,22,33,14,5};
                c.sort(a);
                //
                ArbolAVL tree = new ArbolAVL();
                //
                Nodo raiz = tree.getRaiz();
                tree.raiz = tree.insert(tree.raiz, "abc");
                tree.raiz = tree.insert(tree.raiz, "aaa");
                tree.raiz = tree.insert(tree.raiz, "z");
                tree.raiz = tree.insert(tree.raiz, "c");
                tree.raiz = tree.insert(tree.raiz, "d");
                tree.raiz = tree.insert(tree.raiz, "h");
                //
                tree.preOrder(tree.raiz);
                System.out.println("\n");
                tree.inOrden(tree.raiz);
                System.out.println("\n");
                tree.postOrden(tree.raiz);

*/
