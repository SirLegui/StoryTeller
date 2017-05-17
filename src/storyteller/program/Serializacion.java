package storyteller.program;
/*Librerias a usar*/
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import java.io.*;
/*
 * CLASE SERIALIZACION
 * 
 * Clase generica, puede pasar en T el tipo de objeto que quiere usarse
 */
public class Serializacion<T> 
{
    /*
     * Serializa un objeto a un arreglo de bytes
     */
    public byte[] serializar(T objeto)
    {
            ByteArrayOutputStream bs= new ByteArrayOutputStream();
            ObjectOutputStream os;
            try {
                    os = new ObjectOutputStream (bs);
                    os.writeObject(objeto);
                    os.close();
                    bs.close();
            } catch (IOException e1) {
                    e1.printStackTrace();
                    return null;
            }
            return bs.toByteArray();
    }
    /*
     * Deserializa un objeto a un arreglo de bytes
     */
    @SuppressWarnings("unchecked")
    public T deserializar(byte[] bytes)
    {
            ByteArrayInputStream bs= new ByteArrayInputStream(bytes);
            ObjectInputStream is;
            T objeto = null;
            try {
                    is = new ObjectInputStream(bs);
                    objeto = (T)is.readObject();
                    is.close();
            } catch (IOException | ClassNotFoundException e1) {
                    //e1.printStackTrace();
            JOptionPane.showMessageDialog(null, null, "WARNING", JOptionPane.ERROR_MESSAGE);
            }
            return objeto;
    }

}
