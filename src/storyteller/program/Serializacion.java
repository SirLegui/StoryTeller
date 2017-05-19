package storyteller.program;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializacion<e> {
	
        public byte[] LeerBytes(String dire)
    {

        FileInputStream fileInputStream = null;
        File file = new File(dire);

        byte[] fileArray = new byte[(int) file.length()];
        

        try {
            // Con este código se obtienen los bytes del archivo.
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileArray);

            fileInputStream.close();

//            // Con este código se agregan los bytes al archivo.
//            FileOutputStream fileOuputStream = new FileOutputStream("C:\\ejemplo.txt");
//            fileOuputStream.write(fileArray);
//            fileOuputStream.close();

//            for (int i = 0; i < fileArray.length;i++)
//            {
//                    System.out.println(fileArray[i]);
//            }
            System.out.println("Tamaño de archivo = "+fileArray.length);

            
        } catch (Exception e) {
                //Manejar Error
        }
        return fileArray;
    }	
	public byte[] serializar(e objeto){
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
	
	public e deserializar(byte[] bytes){
		ByteArrayInputStream bs= new ByteArrayInputStream(bytes);
		ObjectInputStream is;
		e objeto = null;
		try {
			is = new ObjectInputStream(bs);
			objeto = (e)is.readObject();
			is.close();
		} catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
			System.out.println("Se ha ingresado un archivo con una extension invalida");
                        System.out.println("El archivo leido probablemente es de una version anterior del programa");
                }
		
		return objeto;
	}
}
