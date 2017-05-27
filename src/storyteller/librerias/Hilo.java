package storyteller.librerias;
import java.awt.Graphics;
/*Importe de librerias*/
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import storyteller.Estructura.Nodo;
import storyteller.program.Logica;
/*
 * Clase Hilo
 * 
 * implementa Runnable para poder trabajr con los hilos
 */
public class Hilo implements Runnable
{
    /*Variable Globales*/
    private Thread hilo;						//Hilo principal
    private int nivel;
    //Nodos
    private Nodo aux;
    //Clases 
    private Logica logica;
    private Imagen imagen;
    /*Constructor. Inicia La Chayotera*/
    public Hilo(Nodo aux1, int nivel1, Logica logica1)
    {	  
    	//
        this.logica = logica1;
        //
        this.aux = aux1;
        this.nivel = nivel1;
        this.hilo = new Thread(this);
    }
    /*Set y Get*/
    public void start()
    {
    	hilo.start();
    }
    
    @Override
    public void run() 
    {
        if(logica.getSeguirHilo() == true)
        {
            logica.inOrdenDesplegarImagenes(logica.getG(), logica.getAVL().getRaiz());
        }
//        // Slepp
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //Fin del hilo	
    }
}