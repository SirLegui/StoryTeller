package storyteller.librerias;
/*Librerias a usar*/
import storyteller.librerias.Serializacion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public byte[] leerArchivo() 
    {
        //
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }
        //
        bufferedInput = new BufferedInputStream(fileInput);
        try {
            bytes = new byte[1000];
            bufferedInput.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
     */
    public void escribirArchivo(byte[] pBytes, boolean bo) 
    {
        //
        bytes = pBytes;
        try {
            fileOutput = new FileOutputStream(file, bo);
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
