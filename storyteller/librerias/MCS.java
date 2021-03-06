package storyteller.librerias;
/*Librerias a usar*/
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import storyteller.interfaz.Interfaz;
/**
 * Clase para descarga y procesamiento(mediante Microsoft Cognitive Service) de imagenes mediante el URL con extension .jpg 
 * @author Edgerik Leguizamon
 * @author Jeremy Live Gonzalez
 * 
 */
public class MCS implements Serializable
{
    /**
     * 
     *@param url: Link de la imagen a procesar
     *@return String[0]: Descripcion ("text") de la imagen , String[1-3]: Las etiquetas ("tags") de la imagen
     */
    public String[] getDescription(String url)
    {
        HttpClient httpclient = HttpClients.createDefault();
        String[] rets = null;
        try
        {
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/describe");
            builder.setParameter("maxCandidates", "1");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            //request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "b5c253c908914a40b7b6c336c7c298e6");
            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\""+url+"\"}");
            request.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) 
            {
                if (response.getStatusLine().getStatusCode() != 200){
                    rets = funcionJsonError(EntityUtils.toString(entity), "Response: "+Integer.toString(response.getStatusLine().getStatusCode()));
                }else{
                    rets = funcionJson(EntityUtils.toString(entity));
                }
            }
        }
        catch (URISyntaxException | IOException | org.apache.http.ParseException | ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return rets;
    }
    /**
     * 
     * @param urli Link de la imagen a descargar
     * @param interfaz
     * @param progress
     * @param inc
     * @return lista de bytes de la imagen
     * @throws MalformedURLException
     * @throws FileNotFoundException
     * @throws IOException 
     * @throws java.lang.InterruptedException 
     */
    public Imagen getImagen(String urli, Interfaz interfaz,int progress, int inc) throws MalformedURLException, FileNotFoundException, IOException, InterruptedException
    {
        // Url con la foto
        Imagen imagen = null;
        try
        {
            URL url = new URL(urli);
            // Establecemos conexion
            URLConnection urlCon = url.openConnection();
            // Sacamos por pantalla el tipo de fichero
            //System.out.println(urlCon.getContentType());
            InputStream recibida;
            // Se obtiene el inputStream de la foto web y se abre el fichero local.
            recibida = urlCon.getInputStream();
            //Convierto a byte[]
            for (int i = 0; i < 5; i++) {
                progress += (inc/5)/2;
                interfaz.setBarValue(progress,"");
                Thread.sleep(1000);
            }
            
            
            byte[] bytes = new byte[recibida.available()];
            recibida.read(bytes);
            
            //Saco el json del API
            String[] rets;
            rets = getDescription(urli);
            
            // Creo Image e igualo a la imagen a retornar
            imagen = new Imagen(bytes, rets, urli);
        // Capto WARNING
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("No se ha podido cargar la imagen");
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null,"Imagen invalida","Error",2);
        }
        return imagen;
    }
    
    /**
     * Recibe el json y retorna una lista de Strings. 
     *@param json: Contiene el json (sin errores) de la respuesta del api
     *@return String[0]: Descripcion ("text") de la imagen , String[1-3]: Las etiquetas ("tags") de la imagen
     *@throws org.json.simple.parser.ParseException cuando no se pudo leer el json correctamente
     */
    public String[] funcionJson(String json) throws ParseException
    {
        // creo el string de datos, (descrpcion y 3 tags)
        String[] rets = {"","","",""};
        //System.out.println(json);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject descripcion = (JSONObject) jsonObject.get("description");
        // loop array
        try
        {
            //
            JSONArray tags = (JSONArray) descripcion.get("tags");
            Iterator<String> iterator = tags.iterator();
            //
            JSONArray caption = (JSONArray) descripcion.get("captions");
            Iterator<JSONObject> iter = caption.iterator();
            JSONObject text = iter.next();
            String texto = (String) text.get("text");
            // 
            rets[0] = texto;
            for(int i = 1; i<4;i++)
            {
                if(iterator.hasNext())
                {
                    rets[i]= iterator.next();
                }
            }
        // 
        }catch(NullPointerException e)
        {
            rets[0]= "El url no es accesible, intente con otra imagen";
        }    
        return rets;
    }
    /**
     * 
     * @param json : Contiene un json solo con mensajes de error
     * @param response : Contiene el codigo de error
     * @return Strin[0]: Nombre del error, String[1]: Descripcion del error, String[2]: Mensaje de error
     * @throws ParseException 
     */
    public String[] funcionJsonError(String json, String response) throws ParseException
    {
        // creo el string de datos
        String[] rets = {"","","",""};
        // creo el JSON
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;
        String code = (String) jsonObject.get("code");
        String mensaje = (String) jsonObject.get("message");
        // inserto en el string de datos
        rets[0] = response;
        rets[1] = code;
        rets[2] = mensaje;
        //manejo de error
        return rets;
    }

}