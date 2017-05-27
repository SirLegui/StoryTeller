package storyteller.librerias;
/*Librerias a usar*/
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/*
Clase Serializacion 

Generica, serializa un byte[]
*/
public class Serializacion<e> 
{
  /**
   * 
   * @param objeto
   * @return 
   */
  public byte[] serializar(e objeto)
  {
        ByteArrayOutputStream bs= new ByteArrayOutputStream();
        ObjectOutputStream os;
        try {
                os = new ObjectOutputStream (bs);
                os.writeObject(objeto);
                os.close();
        } catch (IOException e1) {
                e1.printStackTrace();
        }
        return bs.toByteArray();
    }
    /**
     * 
     * @param bytes
     * @return 
     */
    public e deserializar(byte[] bytes){
        ByteArrayInputStream bs= new ByteArrayInputStream(bytes);
        ObjectInputStream is;
        e objeto = null;
        try {
                is = new ObjectInputStream(bs);
                objeto = (e)is.readObject();
                is.close();
        } catch (IOException | ClassNotFoundException e1) {
                System.out.println("Se ha ingresado un archivo con una extension invalida");
                System.out.println("El archivo leido probablemente es de una version anterior del programa");
                
        }
        return objeto;
    }
}
