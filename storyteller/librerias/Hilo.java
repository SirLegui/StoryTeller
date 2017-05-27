package storyteller.librerias;
import java.awt.Graphics;
import java.io.IOException;
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
            try {
                logica.inOrdenDesplegarImagenes(logica.getG(), logica.getAVL().getRaiz());
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Ya termino el slide show");
        //Fin del hilo	
    }
}