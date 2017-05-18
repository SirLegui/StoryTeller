package storyteller.program;
import java.awt.EventQueue;
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
                //String prueba = "{\"description\":{\"tags\":[\"cat\",\"laying\",\"animal\",\"indoor\",\"mammal\",\"lying\",\"top\",\"bed\",\"sitting\",\"sleeping\",\"orange\",\"blanket\",\"white\",\"grey\",\"blue\",\"red\",\"playing\"],\"captions\":[{\"text\":\"a cat lying on a bed\",\"confidence\":0.9485613629872266}]},\"requestId\":\"d682908f-ad5d-434f-9fb8-f948784e141c\",\"metadata\":{\"width\":460,\"height\":260,\"format\":\"Jpeg\"}}";
                
                Interfaz v1 = new Interfaz();
                Logica c = new Logica();
                v1.setControlador(c);
                c.setInterfaz(v1);
                v1.setVisible(true);
            } catch (Exception e) {
                System.out.println("ERROR FATAL");
            }
        }); 
		//-------------------		
		//Termino el programa
		//-------------------
		//by.Jeremy Live G---
		//-------------------
	}
}
