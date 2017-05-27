package storyteller.librerias;
/*Librerias a usar*/
import storyteller.librerias.Serializacion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import storyteller.librerias.Album;
import storyteller.librerias.ParesOrdenados;
/**
 *
 * @author edgerik, live
 */
public class Archivo 
{
    // Variables Globables ---------------------------------------------
    private FileInputStream fileInput;
    private BufferedInputStream bufferedInput;
    private FileOutputStream fileOutput;
    private BufferedOutputStream bufferedOutput;
    private byte[] bytes;
    private String file;
    // Clases a usar
    private Serializacion serial;
    /**
     * Constructor -----------------------------------------------------
     * @param pArchivo 
     * @param ss1 
     */
    public Archivo(String pArchivo, Serializacion ss1) 
    {
        // Inicializo el nombre del album
        file = pArchivo;
        // Inicializo la clase Serializacion
        serial = ss1;
        // Inicializo variables globales
        this.bytes = null;
    }
    /**
     * 
     * @return 
     */
    public byte[] leerArchivo() throws IOException 
    {
        //
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            return null;
        } catch (RuntimeException e2) {
            JOptionPane.showMessageDialog(null,"¡No hay Album a guardar!","ERROR",2);
        }
        //
        bufferedInput = new BufferedInputStream(fileInput);
        int largo = bufferedInput.available();
        try {
            bytes = new byte[largo];
            bufferedInput.read(bytes);
        } catch (Exception e) {
            bytes = new byte[0];
            return bytes;
        } finally {
            try {
                bufferedInput.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bytes;
    }
    public byte[] leerArchivo(int offset, int len) throws IOException 
    {
        //
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            return null;
        } catch (RuntimeException e2) {
            JOptionPane.showMessageDialog(null,"¡No hay Album a guardar!","ERROR",2);
        }
        //
        bufferedInput = new BufferedInputStream(fileInput);
        try {
            bytes = new byte[len];
            bufferedInput.read(bytes, offset, len);
        } catch (Exception e) {
            bytes = new byte[0];
            return bytes;
        } finally {
            try {
                bufferedInput.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bytes;
    }
    /**
     * 
     * @param pBytes 
     * @param bo 
     * @return  
     * @throws java.io.IOException 
     */
    public int escribirArchivo(byte[] pBytes, boolean bo) throws IOException 
    {
        //
        bytes = pBytes;
        int total= 0;
        
        try {
            fileOutput = new FileOutputStream(file, bo);
            fileInput = new FileInputStream(file);
            total = fileInput.available();
            fileInput.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        //
        bufferedOutput = new BufferedOutputStream(fileOutput);
        try {
            bufferedOutput.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutput.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return total;
    }
    /**
     * 
     * @return 
     */
    public Album Abrir() 
    {
        //
        byte[] lectura;
        Album temp = null;
        // 
        try {
            if (!file.isEmpty()) {
                lectura = leerArchivo();
                temp = (Album) serial.deserializar(lectura);
            } else {
                temp = null;
            }
        } catch (Exception e) {
            System.out.println("No se ha cargado un dibujo");
        }
        return temp;
    }
}
