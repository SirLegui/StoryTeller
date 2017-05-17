package storyteller.program;
/*Librerias a usar*/
import java.io.File;
import java.util.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Object;
/*
 * CLASE FILE MANAGER
 * 
 * Implementa Serializable para poder serializar el objeto
 */
public class FileManager implements Serializable{
    /*
     * Variables globales
     */
    private FileInputStream fileInput;
    private BufferedInputStream bufferedInput;
    private FileOutputStream fileOutput;
    private BufferedOutputStream bufferedOutput;
    private byte[] bytes;
    private String file;
    /*
     * Constructor, Inicializa el nombre que tendra el arreglo simple de BYTES
     */
    public FileManager() 
    {
            bytes = null;
            file = null;
    }
    public FileManager(String pFile) 
    {
            bytes = null;
            file = pFile;
    }
    //get y set
    public byte[] getBytes()
    {
            return bytes;
    }
    public void setBytes(byte[] pByte)
    {
            bytes = pByte;
    }
    /*
     * Retorna el arreglo simple de bytes
     * lee el .psc y crea un arreglo de bytes 
     */
    public byte[] leerArchivo() 
    {
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
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        
        }finally{
            try {
                bufferedInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
    /*
     * Escribe en la variable global BYTES, el arreglos simple de bytes
     */
    public void escribirArchivo(byte[] pBytes, String pFile)
    {
        bytes = pBytes;
        try{
            fileOutput = new FileOutputStream (pFile);
        }catch(FileNotFoundException e1){
            e1.printStackTrace();
        }
            bufferedOutput = new BufferedOutputStream(fileOutput);
        try{
            bufferedOutput.write(bytes);
        }catch (Exception e) 
        {
            e.printStackTrace();
        }finally
        {
            try{
                bufferedOutput.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    /*
     * Obtengo el arreglo de bytes del .psc
     */
    public byte[] convertPscToBytes(String pfile)
    {
        FileInputStream fileInputStream = null;
        File file = new File(pfile);
        byte[] fileArray = new byte[(int) file.length()];
        try{
            // Con este código se obtienen los bytes del archivo.
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileArray);
            fileInputStream.close();
            // Con este código se agregan los bytes al archivo.
            FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\Usuario1\\Desktop\\progra1Estructuras2017\\guardaLosBytesDelPsc.txt");
            //FileOutputStream fileOuputStream = null;
            fileOuputStream.write(fileArray);
            fileOuputStream.close();
        }catch(Exception e){
            //Manejar Error
        }
        return fileArray;
    }
}
