package storyteller.program;
import java.awt.EventQueue;
//import storyteller.interfaz.Interfaz;
import storyteller.interfaz.Ventana_Main;
import storyteller.librerias.MCS;
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
                    //String prueba = "{\"description\":{\"tags\":[\"cat\",\"laying\",\"animal\",\"indoor\",\"mammal\",\"lying\",\"top\",\"bed\",\"sitting\",\"sleeping\",\"orange\",\"blanket\",\"white\",\"grey\",\"blue\",\"red\",\"playing\"],\"captions\":[{\"text\":\"a cat lying on a bed\",\"confidence\":0.9485613629872266}]},\"requestId\":\"d682908f-ad5d-434f-9fb8-f948784e141c\",\"metadata\":{\"width\":460,\"height\":260,\"format\":\"Jpeg\"}}";

//                    Interfaz jFrame = new Interfaz();
//                    jFrame.setVisible(true);
                    Ventana_Main v1 = new Ventana_Main();
                    v1.setVisible(true);
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
