package storyteller.program;
import java.awt.EventQueue;
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
	  EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {	
	            	Interfaz jFrame = new Interfaz();
	                jFrame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }); 
		//-------------------		
		//Termino el programa
		//-------------------
		//by.Jeremy Live G---
		//-------------------
	}
}
