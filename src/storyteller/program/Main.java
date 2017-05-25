package storyteller.program;
import java.awt.EventQueue;
import java.util.ArrayList;
import storyteller.Estructura.ArbolAVL;
import storyteller.*;
import storyteller.interfaz.Interfaz;
import storyteller.librerias.Imagen;
import storyteller.librerias.Serializacion;
/*
 * Clase Main
 * 
 */
public class Main {
    /*Main*/
    public static void main(String[] args)
    {
        // Inicio el programa Psicodelic canvas
        EventQueue.invokeLater(() -> {
        try {
            // Inicializo clases
            Interfaz v1 = new Interfaz();
            Logica c = Logica.getInstance();
            Serializacion  s = new Serializacion();
            // Creo el arbol, le paso la Logica por parametro
            ArbolAVL tree = new ArbolAVL(c);
            // Habilito la interfaz
            v1.setControlador(c);
            c.setInterfaz(v1);  //Inicializo clase logica
            c.setAVL(tree);     //Inicializo clase AVL
            v1.setVisible(true);

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

            // Prueba........
            System.out.println("--");
            tree.setRaiz(tree.insert(tree.getRaiz(), "nzx"));
            tree.setRaiz(tree.insert(tree.getRaiz(), "zas"));
            tree.setRaiz(tree.insert(tree.getRaiz(), "caw"));
            tree.setRaiz(tree.insert(tree.getRaiz(), "abc"));
            tree.setRaiz(tree.insert(tree.getRaiz(), "aac"));
            tree.setRaiz(tree.insert(tree.getRaiz(), "cdd"));
            // printf
            tree.inOrden(tree.getRaiz(),0);
            System.out.println("\n\n\n");
            tree.setRaiz(tree.deleteNode(tree.getRaiz(), "caw"));
            tree.inOrden(tree.getRaiz(),0);
            System.out.println("\n\n\n");
            tree.setRaiz(tree.deleteNode(tree.getRaiz(), "aac"));
            tree.inOrden(tree.getRaiz(),0);

 */