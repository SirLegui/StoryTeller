package storyteller.librerias;
/*Librerias a usar*/
import java.io.Serializable;
import java.util.ArrayList;
/*
Clase de Pares ordenados

Implemento Serializacion para serializar el array de strings de pares ordenados
*/
public class ParesOrdenados implements Serializable
{
    // Variables globales 
    private String[][] indices;
    // Constructor
    public ParesOrdenados() 
    {
        this.indices = null;
    }
    // Gets ands Sets
    public String[][] getIndices()
    {
        return indices;
    }
    public void setIndices(String[][] pIndices)
    {
        indices = pIndices;
    }
    // Funciones de PäresOrdenados
    // Funcion que convierte el String[][] a un byte[][] Serializado
    public void conviertoBytes()
    {
        
    }
    // ->
   
}
