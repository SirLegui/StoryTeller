//Progra 02 - StoryTeller
package storyteller.program;
/*Librerias a usar*/
import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
import storyteller.interfaz.Interfaz02;
import storyteller.librerias.Album;
import storyteller.librerias.Archivo;
import storyteller.librerias.Hilo;
import storyteller.librerias.Imagen;
import storyteller.librerias.MCS;
import storyteller.librerias.ParesOrdenados;
import storyteller.librerias.Serializacion;
/**
 * @author edgerik
 * @author jeremy
 */
public class Logica implements Serializable
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
    private boolean seguirHilo;	
    private ArrayList<String> array_keys;//Condicion del while
    private String ruta_guardado;
    private String tecla;
    private int total_bytes;
    private byte[] serial;
    // Variables Json
    private String[] rets;
    private String local;       // Json ubicacion
    private String actual;      // htpp
    private final String FILES_LOCATION = "/src/storyteller/librerias/";
    // Variables avl
    private Nodo raiz;
    //Clases a usar
    private Interfaz interfaz;
    private Interfaz02 interfaz02;
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
        this.array_keys = null;
        this.ruta_guardado = "";
        this.tecla = null;
        this.total_bytes = 0;
        this.serial = null;
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
        this.seguirHilo = false;
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
    public int getTotalBytes()
    {
        return total_bytes;
    }
    public  void setSerial(byte[] se)
    {
        this.serial = se;
    }
    public void setTotalBytes(int total)
    {
        this.total_bytes = total;
    }
    public void setAlbum(Album a)
    {
        this.album = a;
    }
    public Album getAlbum()
    {
        return album;
    }
    public String getLocal()
    {
        return local;
    }

    public ParesOrdenados getPares_ordenados() {
        return pares_ordenados;
    }
    
    public String getRuta_guardado() {
        return ruta_guardado;
    }

    public void setRuta_guardado(String ruta_guardado) {
        this.ruta_guardado = ruta_guardado;
    }
    
    public void addArray(String name)
    {
        this.array_keys.add(name);
    }
    public void setSeguirHilo(boolean h)
    {
        seguirHilo = h;
    }
    public boolean getSeguirHilo()
    {
        return seguirHilo;
    }
    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Icon getIcono() {
        return icono;
    }

    public void setIcono(Icon icono) {
        this.icono = icono;
    }

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
     * @throws java.net.MalformedURLException 
     * @throws java.lang.InterruptedException 
     * @throws java.net.URISyntaxException 
     */
    public void botonCargar() throws IOException, ParseException, MalformedURLException, InterruptedException, URISyntaxException
    {
        // Direccion donde esta el Json, lo creo
        
        try{
            CodeSource codeSource = Interfaz.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            File jarDir = jarFile.getParentFile().getParentFile();
            setRuta_guardado(jarDir.getAbsolutePath()+FILES_LOCATION);
            //System.out.println(getRuta_guardado()+"Prueba.json");
            obj = parser.parse(new FileReader(getRuta_guardado()+"Prueba.json"));
        }catch(FileNotFoundException e)
        {
            System.out.println("No se cargo el JSON correctamente"); 
        }
        int progress = 5;
        interfaz.setBarValue(progress, "El JSON con urls");
        interfaz.repaint();
        jsonObject = (JSONObject) obj;
        urls = (JSONArray) jsonObject.get("urls");
        iter = urls.iterator();
        // Lectura de ulrs .jpg
        //System.out.println("Se supone que ya cargo el JSON");
        int inc = 90/urls.size();
        do{
            // Direccion Url .jpg
            actual = iter.next();
            // Direccion .jpg local 
            //local = interfaz.getDireccion_guardado() + "/imagen"+Integer.toString(interfaz.getFoto())+".jpg";
            // Logica del API Cognitive services Microsotf
            progress += inc;
            
            try {
                //Imagen Ima = api.getImagen(actual, local);
                Imagen Ima = api.getImagen(actual,interfaz, progress, inc);
                //interfaz.setBarValue(progress,Ima.getCaption());
                // Descripcion de la foto
                rets = Ima.getTags();
                nodoInsertarImaTags(rets, Ima);
                // Aumento contador de fotos
                interfaz.aumentarFoto();
        
            // capta errores
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "No se ah podido descagar la imagen", "Error", 2);
            }
            
        // Hasta que se termine las urls.jpg del Json
        }while(iter.hasNext());
        //----------------------------------------------------------------------
        // Despues de que el avl este hecho, una vez insertado los nodos al arbol 
        // Y que ya hemos hecho la señalacion de punteros, osea nodo->Imagen[]
        //----------------------------------------------------------------------
        // Proceso el avl, borro punteros de nodos a foto
        // 
        //Thread.sleep(15000);
        avl.inOrden(avl.getRaiz(), 0);
        // 
        array_keys = new ArrayList<>();
        avl.inOrdenDescartar(avl.getRaiz(), this);
        // Elimino nodos del avl y balanceo
        // avl.inOrdenElimina(avl.getRaiz());
        array_keys.forEach((key) -> {
            avl.setRaiz(avl.deleteNode(avl.getRaiz(), key));
        }); // 
        
        avl.inOrden(avl.getRaiz(), 0);
        progress = 100;
        interfaz.setBarValue(progress, "Se ha creado con el exito el album");
        interfaz.repaint();
        // Despliego Slay Show
        //inOrdenDesplegarImagenes(g, avl.getRaiz());
        Comienza();
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
        //arrayImagen = avl.getRaiz().getValue();
        // Insercion tag 2
        avl.setRaiz(avl.insert(avl.getRaiz(), pTags[1], Ima));
        //arrayImagen = avl.getRaiz().getValue(); 
        // Insercion tag 3
        avl.setRaiz(avl.insert(avl.getRaiz(), pTags[2], Ima));
        //arrayImagen = avl.getRaiz().getValue();
    }
    /*Inicia hilo*/
    public void Comienza()
    {
    	Hilo inicio = new Hilo(avl.getRaiz(), 0, this);
    	setSeguirHilo(true);
    	inicio.start();
    }
    /**
     * Recorrido e impresion en orden
     *
     * @param g
     * @param nodo
     */
    public void inOrdenDesplegarImagenes(Graphics g,Nodo nodo) throws IOException {
        if (nodo != null) {
            //
            inOrdenDesplegarImagenes(g, nodo.getLeft());
            //
            int largo = nodo.getValue().size();
            //System.out.println(largo);
            //System.out.println(nodo.getValue().get(0).getUrl());
            //System.out.println(nodo.getValue().get(0).getCaption());
            //String[] a = nodo.getValue().get(0).getTags();
            //System.out.println(a[0]);

            for (int i = largo - 1; i >= 0; i--) 
            {
                // Obtengo la Clase Imagen 
                Imagen nueva_Imagen = nodo.getValue().get(i);
                // Dibujo la Image
                //nueva_Imagen.dibujar(g, interfaz);
                //interfaz.setFoto_actual(nueva_Imagen.getImagen());
                interfaz.setNodo_foto(nueva_Imagen);
                interfaz.setDescripcion(nueva_Imagen.getCaption());
                String[] tags = nueva_Imagen.getTags();
                interfaz.setTitulo(tags[0]);
                
                if(nueva_Imagen.getImagen() != null){
                    ImageIcon icon2 = null;
                    try{
                        icon2 = new ImageIcon(ImageIO.read(new ByteArrayInputStream(nueva_Imagen.getImagen())));
                    }catch(NullPointerException e){
                        System.out.println("Imposible cargar la imagen");
                    }
                    setIcon(icon2);
                    Icon icono2 = new ImageIcon(icon.getImage().getScaledInstance(interfaz.getLblFoto().getWidth(), interfaz.getLblFoto().getHeight(), Image.SCALE_DEFAULT));
                    setIcono(icono2);
                    interfaz.getLblFoto().setIcon(getIcono());
                    // Slepp 5 segundos slide show
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                while(!getSeguirHilo())
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //while (condicion_true) {}
                // Repinto
                interfaz.repaint();
                //controlador.desplegar_imagen(nodo.getValue().get(i));
            
            }
            //System.out.println("Sale del for");
            //
            inOrdenDesplegarImagenes(g, nodo.getRight());
        }
    }
    public byte[] cargarPares(){
        Archivo f1 = new Archivo(getRuta_guardado()+"Pares.pros", getSerializacion());
        byte[] pares = null;
        try{
            pares= f1.leerArchivo();
        }catch(RuntimeException e){
            pares = null;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            if(pares.length != 0)
            {
                pares_ordenados = (ParesOrdenados) s1.deserializar(pares);
            }  
        }catch(NullPointerException e)
        {
            System.out.println("El archivo está vacio, se creará la primera inserción");
        }
        return pares;
    }
    /**
     * Boton save
     * @throws java.io.IOException
     */
    public void botonSave() throws IOException
    {
        // Deserealizar los pares ordenados
        Archivo f1 = new Archivo(getRuta_guardado()+"Pares.pros", getSerializacion());
        byte[] pares = cargarPares();
        // Le pedimos el nombre del album al usuario
        String name = JOptionPane.showInputDialog(null,"Digite el nombre del album a guardar","NUEVO ALBUM",3);
        if(!name.isEmpty()){

            // Busqueda binaria, ver si no esta repetido
            boolean encontrado = false;
            if(pares_ordenados == null){
                pares_ordenados = new ParesOrdenados();
            }
            while(!encontrado)
            {  
                if(!pares_ordenados.esta_en(name))    //Si es true, add
                {
                    encontrado = true;
                    // Creo el Album, como parametros 1)tree, 2)nombre del album, 3)c://
                    Album a1 = new Album(getAVL(), name, getLocal());
                    setAlbum(a1);
                    // Serializo el AVL************************************
                    // Creo instancia para R/W bytes .alb
                    Archivo f2 = new Archivo(getRuta_guardado()+"Albums.alb", getSerializacion());
                    // Creo el byte[] de album
                    setSerial(s1.serializar(a1));
                    // Inicializo el largo del byte[] del album
                    setTotalBytes(serial.length);
                    // Escribo en el Album.alb el byte[] del album a insertar en el final
                    //Inserto al final
                    // Inserto al final el album en los pares ordenados
                    int len = f2.escribirArchivo(serial, true);
                    pares_ordenados.addAlbum(name, len, serial.length);
                    // Termino el save
                    JOptionPane.showMessageDialog(null, "Se ha guardado el album: "+name,"¡ERES TODO UN PROFECIONAL!" , JOptionPane.INFORMATION_MESSAGE);
                }else{
                    name = JOptionPane.showInputDialog(null,"Digite el nombre del album a guardar","NUEVO ALBUM",3);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Favor digite un nombre valido", "Error", 0);
        }
        //Hacer Quicksort
        
        // Serializar pares ordenados
        pares = s1.serializar(pares_ordenados);
        f1.escribirArchivo(pares, false);   //Le caigo encima al Pares.pros
        //....
    }
    /**
     * Boton continuar
     */
    public void botonFollow()
    {
        // Sigo el hilo
        setSeguirHilo(true);
    };
    /**
     * Boton abrir
     * @param name
     * @throws java.io.IOException
     */
    public void botonAbrir(String name) throws IOException
    {
        // Deserealizar los pares ordenados
        Archivo f1 = new Archivo(getRuta_guardado()+"Pares.pros", getSerializacion());
        byte[] pares = f1.leerArchivo();
        if(pares.length != 0)
        {
            pares_ordenados = (ParesOrdenados) s1.deserializar(pares);
        }  
        String[] nombre_albums = pares_ordenados.getOnlyIndices();
        boolean cargar = false;
        System.out.println(nombre_albums[0]);
        for(int i = 0; i<nombre_albums.length;i++){
            System.out.println(nombre_albums[i]+"   "+name);
            if(name.equals(nombre_albums[i])){
                cargar = true;
                break;
            }
        }
        if(cargar){
            String[] retornos = pares_ordenados.getIndices().get(i);
            Archivo f2 = new Archivo(getRuta_guardado()+"Albums.alb", getSerializacion());
            int offset = Integer.getInteger(retornos[1]);
            int len = Integer.getInteger(retornos[2]);
            System.out.println("Offset: "+offset+"\tLen: "+len);
            byte[] temp= f2.leerArchivo(offset, len);
            setAlbum((Album) s1.deserializar(temp));
            
        }else{
            System.out.println("No se ha cargado");
        }
        Comienza();
        //interfaz02.text.add(nombre_albums)
        // Validar si ese nombre existe en los pares ordenados
        
        // Obtener AVL
        
        // SLIDE SHOW
        
        //..
        //JOptionPane.showMessageDialog(null, "", "Opciones de ALBUNES disponibles en la TIENDA LIVE PURA VIDA", 2);
    }
    public void dibujar( byte[] imagen) throws IOException{
        Image foto = ImageIO.read(new ByteArrayInputStream(imagen));
        g.drawImage(foto, 10, 10, null);
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
/*
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tecla = KeyEvent.getKeyText(e.getKeyCode());
        System.out.println(tecla);
        if(tecla.equals(Integer.toString(KeyEvent.VK_ENTER)))
        {
            interfaz02.setControlador(this);
            interfaz02.setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
}
/*
        //avl.raiz = avl.insert(avl.raiz, pTags[1]);
        //arrayImagen = (ArrayList<Imagen>)avl.raiz.getValue();
        //arrayImagen.add(Ima);

*/

    /**
     *  Boton Procesar
     * @throws java.net.MalformedURLException
     * @throws java.lang.InterruptedException
     */
    /*
    public void botonProcesar() throws MalformedURLException, InterruptedException
    {
        //
        api = new MCS();
        rets = null;
        //FileChooser fc = new FileChooser();
        //fc.showOpenDialog(null);
        local = interfaz.getDireccion_guardado() + "/imagen"+Integer.toString(interfaz.getFoto())+".jpg";
        try {
            api.getImagen(interfaz.getDireccion_imagen().getText());
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
    */