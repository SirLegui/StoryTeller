package storyteller.program;
import java.awt.EventQueue;
import storyteller.Estructura.ArbolAVL;
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
