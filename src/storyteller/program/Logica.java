//Progra 02 - StoryTeller
package storyteller.program;


import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import storyteller.interfaz.Interfaz;
import storyteller.librerias.MCS;

/**
 *
 * @author edgerik
 * @author jeremy
 */
public class Logica {
    //Variables globales-------------------------------------------------------
    private JSONParser parser;
    private Object obj;
    private JSONObject jsonObject;
    private JSONArray urls;
    private Iterator<String> iter;
    private String[] rets;
    private ImageIcon icon;
    private Icon icono; 
    private String local, actual;
    //Clases a usar
    private Interfaz interfaz;
	private MCS api;
    // Variables del quicksort
    private int[] numbers;
    private int number;
    private int i, j, pivot;
    //Constructor---------------------------------------------------------------
    public Logica() 
    {
		this.api = new MCS();
        this.interfaz = null;
        this.parser = null;
        this.obj = null;
        this.jsonObject = null;
        this.urls = null;
        this.iter = null;
        this.rets = null;
        this.icon = null;
        this.icono = null;
        this.local = null;
        this.numbers = null;
        this.number = 0;
        this.i = 0;
        this.j = 0;
        this.pivot = 0;
    }
    // Inicializo Interfaz
    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    //Gets y Sets---------------------------------------------------------------
    
    
    
    //Funciones StoryTeller-----------------------------------------------------
    
    //Boton Cargar.
    public void botonCargar() throws IOException, ParseException
    {
        api = new MCS();
        parser = new JSONParser();
        obj = parser.parse(new FileReader("/home/edgerik/StoryTeller/src/storyteller/program/Prueba.json"));
        jsonObject = (JSONObject) obj;
        urls = (JSONArray) jsonObject.get("urls");
        iter = urls.iterator();
        do{
            actual = iter.next();
            System.out.println(actual);
            rets = null;
            //FileChooser fc = new FileChooser();
            //fc.showOpenDialog(null);
            local = interfaz.getDireccion_guardado() + "/imagen"+Integer.toString(interfaz.getFoto())+".jpg";
            System.out.println(local);
            try {
                api.getImage(actual, local);
                rets = api.getDescription(actual);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(rets);
            icon = new ImageIcon(local);
            icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_DEFAULT));
            interfaz.getLblFoto().setIcon( icono );
            
            interfaz.getLblFoto().setText(null);
            interfaz.getLblDescripcion().setText(rets[0]);
            interfaz.getLblTag1().setText(rets[1]);
            interfaz.getLblTag2().setText(rets[2]);
            interfaz.getLblTag3().setText(rets[3]);
            interfaz.aumentarFoto();
            interfaz.repaint();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(iter.hasNext());
    }
    
    //Boton Procesar.
    public void botonProcesar()
    {
        api = new MCS();
        rets = null;
        //FileChooser fc = new FileChooser();
        //fc.showOpenDialog(null);
        local = interfaz.getDireccion_guardado() + "imagen"+Integer.toString(interfaz.getFoto())+".jpg";
        try {
            api.getImage(interfaz.getDireccion_imagen().getText(), local);
            rets = api.getDescription(interfaz.getDireccion_imagen().getText());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        icon = new ImageIcon(local);
        icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_SMOOTH));
        interfaz.getLblFoto().setIcon( icono );
        
        interfaz.getLblFoto().setText(null);
        interfaz.getLblDescripcion().setText(rets[0]);
        interfaz.getLblTag1().setText(rets[1]);
        interfaz.getLblTag2().setText(rets[2]);
        interfaz.getLblTag3().setText(rets[3]);
        interfaz.aumentarFoto();
    }

    /*
     * Quicksort
     */
    // Funcion principal del quicksort
    public void sort(int[] values) 
    {
        // Valida si el array en null
        if (values ==null || values.length==0){
            return;
        }
        // Inicializo array con que se trabajará y el largo
        this.numbers = values;
        number = values.length;
        // Inicio el quicksort
        quicksort(0, number - 1);
    }

    // Funcion que divide a la izquierda los menores y en la derecha los mayores
    private void quicksort(int low, int high) 
    {
        // Inicializo el i y j que se usarán
        i = low;
        j = high;
        // Obtengo el pivote el cual esta a la mitad del array
        pivot = numbers[low + (high-low)/2];

        // Se divide en dos listas
        while (i <= j) {
            // Si el numero es menor al pivote se coloca a la izquierda
            while (numbers[i] < pivot) {
                i++;
            }
            // Si el numero es mayor al pivote se coloca a la derecha
            while (numbers[j] > pivot) {
                j--;
            }

            // Si encuentri un valor menor en lado derecho y un valor mayor en 
            // el lado izquierdo al pivote, se realiza el exchange
            if (i <= j) 
            {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    // Cambia el i con el j y viceversa
    private void exchange(int i, int j) 
    {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
