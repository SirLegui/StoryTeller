package storyteller.librerias;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import javax.imageio.ImageIO;
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

public class MCS
{
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




    public void getImage(String urli, String dire) throws MalformedURLException, FileNotFoundException, IOException{
        // Url con la foto
        try{
            URL url = new URL(urli);
            //File dir = new File(dire);

            // establecemos conexion
            URLConnection urlCon = url.openConnection();

            // Sacamos por pantalla el tipo de fichero
            System.out.println(urlCon.getContentType());

            // Se obtiene el inputStream de la foto web y se abre el fichero
            // local.
            InputStream is = urlCon.getInputStream();
            
            FileOutputStream fos = new FileOutputStream(dire);

            // Lectura de la foto de la web y escritura en fichero local
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            int leido = is.read(array);
            while (leido > 0) {
                    fos.write(array, 0, leido);
                    leido = is.read(array);
            }

            // cierre de conexion y fichero.
            is.close();
            fos.close();
            
            //ImageIO.write(imagen, "jpg", dir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("No se ha podido cargar la imagen");
        }
    }

    /*
     *
     *
     */
    public String[] funcionJson(String json) throws ParseException
    {
        String[] rets = {"","","",""};
        System.out.println(json);

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(json);

        JSONObject jsonObject = (JSONObject) obj;

        JSONObject descripcion = (JSONObject) jsonObject.get("description");

        // loop array
        try{
            JSONArray tags = (JSONArray) descripcion.get("tags");
            Iterator<String> iterator = tags.iterator();

            JSONArray caption = (JSONArray) descripcion.get("captions");
            Iterator<JSONObject> iter = caption.iterator();
            JSONObject text = iter.next();
            String texto = (String) text.get("text");
            rets[0] = texto;
            for(int i = 1; i<4;i++)
            {
                if(iterator.hasNext()){
                    rets[i]= iterator.next();
                }
            }
        }catch(NullPointerException e)
        {
            rets[0]= "El url no es accesible, intente con otra imagen";
        }
        
        //System.out.println(texto);
        
        
    //manejo de error
        return rets;
    }
    
public String[] funcionJsonError(String json, String response) throws ParseException
    {
        String[] rets = {"","","",""};
        //System.out.println(json);

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(json);

        JSONObject jsonObject = (JSONObject) obj;

        String code = (String) jsonObject.get("code");

        // loop array
        String mensaje = (String) jsonObject.get("message");
        
        rets[0] = response;
        rets[1] = code;
        rets[2] = mensaje;
        
    //manejo de error
        return rets;
    }

}
