package storyteller.librerias;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
    public static void main(String[] args)
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            //Editor : Edgerik
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/vision/v1.0/analyze");

            builder.setParameter("visualFeatures", "Categories");
            builder.setParameter("details", "{string}");
            builder.setParameter("language", "en");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "b5c253c908914a40b7b6c336c7c298e6");


            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\"http://viajar.especiales.elperiodico.com/50-viajes-extraordinarios/files/2013/05/selva-del-tigre.jpg\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();



            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }





    /*
     *
     *
     */
    public void funcionJson() throws ParseException
    {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("c:\\FileTests\\prueba.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String blog = (String) jsonObject.get("Blog");
            System.out.println(blog);

            String temas = (String) jsonObject.get("Temas");
            System.out.println(temas);

            long inicio = (Long) jsonObject.get("Inicio");
            System.out.println(inicio);

            JSONObject innerObject = (JSONObject) jsonObject.get("Posts");
            System.out.println(innerObject.toJSONString());

            // loop array
            JSONArray tags = (JSONArray) jsonObject.get("Tags");
            Iterator<String> iterator = tags.iterator();

            while (iterator.hasNext()) {
                    System.out.println(iterator.next());
            }
        } catch (FileNotFoundException e) {
                //manejo de error
        }catch (IOException e) {
                //manejo de error
        }
    //manejo de error

    }


}
