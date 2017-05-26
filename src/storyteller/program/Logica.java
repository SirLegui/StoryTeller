//Progra 02 - StoryTeller
package storyteller.program;
/*Librerias a usar*/
import com.sun.jna.platform.mac.MacFileUtils.FileManager;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import storyteller.Estructura.ArbolAVL;
import storyteller.Estructura.Nodo;
import storyteller.interfaz.Interfaz;
import storyteller.librerias.Album;
import storyteller.librerias.Archivo;
import storyteller.librerias.Imagen;
import storyteller.librerias.MCS;
import storyteller.librerias.ParesOrdenados;
import storyteller.librerias.Serializacion;
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
    private Graphics g;
    private boolean cargar_listo;
    // Variables Json
    private String[] rets;
    private String local;       // Json ubicacion
    private String actual;      // htpp
    // Variables avl
    private Nodo raiz;
    private ArrayList<Imagen> arrayImagen;
    //Clases a usar
    private Interfaz interfaz;
    private MCS api;
    private ArbolAVL avl;
    private Serializacion s1;
    private Album album;
    private ParesOrdenados pares_ordenados;
    // Variables del quicksort
    private String[][] numbers;
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
        this.raiz = null;
        this.api = new MCS();
        this.parser = new JSONParser();
        this.s1 = new Serializacion();
        this.pares_ordenados = new ParesOrdenados();
        this.album = null;
        this.avl = null;
        this.obj = null;
        this.cargar_listo = false;
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

    public boolean isCargar_listo() {
        return cargar_listo;
    }

    public void setCargar_listo(boolean cargar_listo) {
        this.cargar_listo = cargar_listo;
    }
    // Inicializo Interfaz
    public void setInterfaz(Interfaz interfaz) 
    {
        this.interfaz = interfaz;
    }
    //Gets y Sets---------------------------------------------------------------

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public void setAVL(ArbolAVL avl1)
    {
        this.avl = avl1;
    }
    public ArbolAVL getAVL()
    {
        return avl;
    }
    public void setSerializacion(Serializacion s)
    {
        this.s1 = s;
    }
    public Serializacion getSerializacion()
    {
        return s1;
    }
    //Funciones StoryTeller-----------------------------------------------------
    /**
     * Boton Cargar.
     * @throws IOException
     * @throws ParseException 
     */
    public void botonCargar() throws IOException, ParseException
    {
        // Direccion donde esta el Json, lo creo
        obj = parser.parse(new FileReader("C:\\Users\\Usuario1\\Documents\\NetBeansProjects\\StoryTeller\\src\\storyteller\\librerias\\Prueba.json"));
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
                //
                
                // Descripcion de la foto
                rets = Ima.getTags();
                System.out.println(rets[0]);
                System.out.println(rets[1]);
                System.out.println(rets[2]);
                // 
                Ima.getImagen();
                // Inserto al nodo: 3 tags y foto
                nodoInsertarImaTags(rets, Ima);
                // Aumento contador de fotos
                interfaz.aumentarFoto();
        
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
        avl.inOrden(avl.getRaiz(), 0);
        avl.inOrdenDescartar(avl.getRaiz());
        avl.inOrden(avl.getRaiz(), 0);
        
        // Despliego Slay Show
        inOrdenDesplegarImagenes(g, avl.getRaiz());
        //...
    }
    /**
     * Inserto al nodo: 3 tags y foto
     * @param pTags
     * @param Ima 
     */
    public void nodoInsertarImaTags(String[] pTags, Imagen Ima)
    {
        
        // Insercion tag 1
        avl.setRaiz(avl.insert(avl.getRaiz(), pTags[0], Ima));
        arrayImagen = avl.getRaiz().getValue();
        // Insercion tag 2
        avl.setRaiz(avl.insert(avl.getRaiz(), pTags[1], Ima));
        arrayImagen = avl.getRaiz().getValue(); 
        // Insercion tag 3
        avl.setRaiz(avl.insert(avl.getRaiz(), pTags[2], Ima));
        arrayImagen = avl.getRaiz().getValue();
        
    }
    
    /**
     * Despliega la imagen en el Jframe correspondiente y hace el sleep para el slide show
     * @param foto Imagen a desplegar
     */
    public void desplegar_imagen(Imagen foto)
    {
        // Inicializo clase Imagen y Image(foto)
        //interfaz.setNodo_foto(foto);
        //interfaz.setFoto_actual(foto.getImagen());
        
    }
    /**
     * Recorrido e impresion en orden
     *
     * @param nodo
     */
    public void inOrdenDesplegarImagenes(Graphics g,Nodo nodo) {
        if (nodo != null) {
            //
            inOrdenDesplegarImagenes(g, nodo.getLeft());
            //
            int largo = nodo.getValue().size();
            System.out.println(largo);
            System.out.println(nodo.getValue().get(0).getUrl());
            System.out.println(nodo.getValue().get(0).getCaption());
            String[] a = nodo.getValue().get(0).getTags();
            System.out.println(a[0]);

            for (int i = largo - 1; i >= 0; i--) 
            {
                // Obtengo la Clase Imagen 
                Imagen nueva_Imagen = nodo.getValue().get(i);
                // Dibujo la Image
                //nueva_Imagen.dibujar(g, interfaz);
                interfaz.setFoto_actual(nueva_Imagen.getImagen());
                interfaz.setNodo_foto(nueva_Imagen);
                interfaz.setDescripcion(nueva_Imagen.getCaption());
                String[] tags = nueva_Imagen.getTags();
                interfaz.setTitulo(tags[0]);
                interfaz.repaint();
                //controlador.desplegar_imagen(nodo.getValue().get(i));
                
            }
            //
            inOrdenDesplegarImagenes(g, nodo.getRight());
        }
    }
    /**
     * Boton save
     */
    public void botonSave()
    {
        // Le pedimos el nombre del album al usuario
        String name = JOptionPane.showInputDialog(null,null,"Digite el nombre del album a guardar",3);
        name+=".alb";
        // Busqueda binaria, ver si no esta repetido
        if(avl.busquedaBinaria(avl.getRaiz(), name))    //Si es true, add
        {
            // Creo el Album, como parametros 1)tree, 2)nombre del album, 3)c://
            album = new Album(avl, name, local);
            // Serializo el AVL************************************
            // Creo instancia para R/W bytes .alb
            Archivo f1 = new Archivo(name, getSerializacion());
            // Creo el byte[] de album
            byte[] serial = s1.serializar(album);
            // Inicializo el largo del byte[] del album
            int total_bytes = serial.length;
            // Escribo en el Album.alb el byte[] del album a insertar en el final
            f1.escribirArchivo(serial);
            // Inserto al final del album
            pares_ordenados.addAlbum(name, album, total_bytes);
            //   
            JOptionPane.showMessageDialog(null, null, "HAZ GUARDADO SATISFACTORIAMENTE EL ALBUM"+name+"\n ¡ERES TODO UN PROFECIONAL!" , number);

        }
        //....
    }
    /**
     * Boton continuar
     */
    public void botonFollow()
    {
        //
        
    };
    /**
     * Boton abrir
     */
    public void botonAbrir()
    {
        //
        
    }
    /**
     *  Boton Procesar
     */
    public void botonProcesar()
    {
        //
        api = new MCS();
        rets = null;
        //FileChooser fc = new FileChooser();
        //fc.showOpenDialog(null);
        local = interfaz.getDireccion_guardado() + "/imagen"+Integer.toString(interfaz.getFoto())+".jpg";
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
     * Funcion principal del quicksort
     */
    public void sort(String[][] values) 
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

    /**
     * Funcion que divide a la izquierda los menores y en la derecha los mayores
     */
    private void quicksort(int low, int high) 
    {
        // Inicializo el i y j que se usarán
        i = low;
        j = high;
        // Obtengo el pivote el cual esta a la mitad del array
        pivot = numbers[low + (high-low)/2][0];
            
       int acumodador;
        // Se divide en dos listas
        while (i <= j) {
            // Si el numero es menor al pivote se coloca a la izquierda
            acumodador = numbers[i][0].compareTo(pivot);
            while (acumodador < 0) {
                i++;
            }
            // Si el numero es mayor al pivote se coloca a la derecha
            acumodador = numbers[j][0].compareTo(pivot);
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

    /**
     * Cambia el i con el j y viceversa
     */
    private void exchange(int i, int j) 
    {
        String[] temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

}
/*
        //avl.raiz = avl.insert(avl.raiz, pTags[1]);
        //arrayImagen = (ArrayList<Imagen>)avl.raiz.getValue();
        //arrayImagen.add(Ima);

*/