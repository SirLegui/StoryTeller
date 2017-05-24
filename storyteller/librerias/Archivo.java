package storyteller.librerias;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author edgerik
 */
public class Archivo {

    private String file;
    private Serializacion serial;
    private ParesOrdenados pares;

    private FileInputStream fileInput;
    private BufferedInputStream bufferedInput;

    private FileOutputStream fileOutput;
    private BufferedOutputStream bufferedOutput;

    private byte[] bytes = null;

    public Archivo(String pArchivo) {
        file = pArchivo;
        serial = new Serializacion();
    }

    public Archivo() {
    }

    public Album Abrir() {

        byte[] lectura;
        Album temp = null;
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

    public void Guardar(Album nuevo) {

        //pares.
        //Se serializa la lista de figuras
        byte[] ser = serial.serializar(nuevo);
        //Se nombra el archivo con el nombre leido
        if (file.isEmpty()) {
            file = "Albums.alb";
        } else {
            file += ".alb";
        }
        //Se escribe el archivo en .psc
        escribirArchivo(ser);

        System.out.println("Se ha guardado el archivo con el nombre: " + file);
        System.out.println("En la dirección: /home/edgerik/NetBeansProjects/Paint");

    }

    public byte[] leerArchivo() {
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }

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

    public void escribirArchivo(byte[] pBytes) {
        bytes = pBytes;
        try {
            fileOutput = new FileOutputStream(file, true);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
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
}
