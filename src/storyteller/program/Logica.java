//Progra 02 - StoryTeller
package storyteller.program;
/*Librerias a usar*/
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import storyteller.Estructura.ArbolAVL;
import storyteller.Estructura.Nodo;
import storyteller.interfaz.Interfaz;
import storyteller.librerias.Imagen;
import storyteller.librerias.MCS;
/**
 * @author edgerik
 * @author jeremy
 */
public class Logica 
{
    //Variables globales-------------------------------------------------------   
    private static Logica Instance;
    private Iterator<String> iter;
    private JSONParser parser;
    private JSONObject jsonObject;
    private JSONArray urls;
    private ImageIcon icon;
    private Icon icono; 
    private Object obj;
    private String[] rets;
    private String local;
    private String actual;
    private Nodo<ArrayList<Imagen>> raiz;
    private ArrayList<Imagen> arrayImagen;
    //Clases a usar
    private Interfaz interfaz;
    private MCS api;
    private ArbolAVL avl;
    // Variables del quicksort
    private String[] numbers;
    private int number;
    private int i, j;
    private String pivot;
    //Constructor---------------------------------------------------------------
    public synchronized static Logica getInstance() 
    {
        if(Instance == null)
        {
            Instance = new Logica();
        }
        return Instance;
    }
    private Logica()
    {
        this.interfaz = null;   //No se inicializa porque se hace en el main
        this.raiz = new Nodo<>();
        this.api = new MCS();
        this.parser = new JSONParser();
        
        this.avl = null;
        this.obj = null;
        this.jsonObject = null;
        this.urls = null;
        this.iter = null;
        this.rets = null;
        this.icon = null;
        this.icono = null;
        this.local = null;
        this.numbers = null;
        this.pivot = null;
        this.number = 0;
        this.i = 0;
        this.j = 0;
    }
    // Inicializo Interfaz
    public void setInterfaz(Interfaz interfaz) 
    {
        this.interfaz = interfaz;
    }
    //Gets y Sets---------------------------------------------------------------
    public void setAVL(ArbolAVL avl1)
    {
        this.avl = avl1;
    }
    //Funciones StoryTeller-----------------------------------------------------
    /**
     * Recorro el avl
     */
    public void recorreAVL()
    {
        // Imprimo Arbol
        avl.inOrden(avl.raiz);
        
    }
    /**
     * Boton Cargar.
     * @throws IOException
     * @throws ParseException 
     */
    public void botonCargar() throws IOException, ParseException
    {
        // Direccion donde esta el Json, lo creo
        obj = parser.parse(new FileReader("C:\\Users\\Usuario1\\Desktop\\Prueba.json"));
        jsonObject = (JSONObject) obj;
        urls = (JSONArray) jsonObject.get("urls");
        iter = urls.iterator();
        // Lectura de ulrs .jpg
        do{
            // Direccion Url .jpg
            actual = iter.next();
            // Direccion .jpg local 
            local = interfaz.getDireccion_guardado() + "/imagen"+Integer.toString(interfaz.getFoto())+".jpg";
            // Logica del API Cognitive services Microsotf
            try {
                // Guardo bytes de .jpg(actual) a nuestro .jpg(local)
                Imagen Ima = api.getImagen(actual, local);
                // Descripcion de la foto
                rets = Ima.getTags();
                // Inserto al nodo: 3 tags y foto
                nodoInsertarTags(rets, Ima);
            // capta errores
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        // Hasta que se termine las ulrs .jpg del Json
        }while(iter.hasNext());
        //----------------------------------------------------------------------
        // Despues de que el avl este hecho, una vez insertado los nodos al arbol 
        // Y que ya hemos hecho la señalacion de punteros, osea nodo->Imagen[]
        //----------------------------------------------------------------------
        // Proceso el avl, borro punteros de nodos a foto
        avl.inOrdenDescartar(raiz);
        // Elimino nodos del avl y balanceo
        avl.deleteNode(raiz, local);
        // Despliego Slay Show
        recorreAVL();
        
        
       
        //...
    }
    /**
     * Inserto al nodo: 3 tags y foto
     * @param pTags
     * @param Ima 
     */
    public void nodoInsertarTags(String[] pTags, Imagen Ima)
    {
        // Inserto al AVL
        raiz = avl.getRaiz();
        // Marco como procesada la foto
        Ima.setCheck(true);
        // Inserto la Imagen 
        raiz.getValue().add(Ima);
        // Insercion 1
        avl.raiz = avl.insert(avl.raiz, rets[1]);
        arrayImagen = (ArrayList<Imagen>)avl.raiz.getValue();
        arrayImagen.add(Ima);
        // Insercion 2
        avl.raiz = avl.insert(avl.raiz, rets[2]);
        arrayImagen = (ArrayList<Imagen>)avl.raiz.getValue();
        arrayImagen.add(Ima);
        // Insercion 3
        avl.raiz = avl.insert(avl.raiz, rets[3]);
        arrayImagen = (ArrayList<Imagen>)avl.raiz.getValue();
        arrayImagen.add(Ima);
    }
    
    /**
     * Despliega la imagen en el Jframe correspondiente y hace el sleep para el slide show
     * @param foto Imagen a desplegar
     */
    public void desplegar_imagen(Imagen foto)
    {
        // Imprimo la Imagen en interfaz
        
            icon = new ImageIcon(foto.getImagen());
            icono = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_DEFAULT));
            interfaz.getLblFoto().setIcon(icono);
            interfaz.getLblFoto().setText(null);
            // Inserto en el arreglo los datos a usar
            // 1)Descriccion, 2)3)4)tags
            rets= foto.getTags();
            interfaz.getLblDescripcion().setText(rets[0]);
            interfaz.getLblTag1().setText(rets[1]);
            interfaz.getLblTag2().setText(rets[2]);
            interfaz.getLblTag3().setText(rets[3]);
            // Aumento contador de fotos
            interfaz.aumentarFoto();
            // Pinto
            interfaz.repaint();
            // Slepp
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            api.getImagen(interfaz.getDireccion_imagen().getText(), local);
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
    public void sort(String[] values) 
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
        // Imprimo indices
        for (int k = 0; k < number; k++) {
            System.out.println(numbers[k]);
        }
    }

    // Funcion que divide a la izquierda los menores y en la derecha los mayores
    private void quicksort(int low, int high) 
    {
        // Inicializo el i y j que se usarán
        i = low;
        j = high;
        // Obtengo el pivote el cual esta a la mitad del array
        pivot = numbers[low + (high-low)/2];
            
       int acumodador;
        // Se divide en dos listas
        while (i <= j) {
            // Si el numero es menor al pivote se coloca a la izquierda
            acumodador = numbers[i].compareTo(pivot);
            while (acumodador < 0) {
                i++;
            }
            // Si el numero es mayor al pivote se coloca a la derecha
            acumodador = numbers[j].compareTo(pivot);
            while (acumodador > 0) {
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
        String temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

}
