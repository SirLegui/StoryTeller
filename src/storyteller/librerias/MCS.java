package storyteller.librerias;


//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class MCS
{
	/*
	 * 
	 * 
	 */
	public static void main(String[] args) 
	{
		//Empieza la lectura de la foto, proceso del cognitive services 
		HttpClient httpclient = HttpClients.createDefault();
		

		try
		{
			//
			URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/vision/v1.0/describe");
            builder.setParameter("maxCandidates", "1");
           
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            //HttpPut request = new HttpPut("westus.api.cognitive.microsoft.com");
            
           
		    request.setHeader("Content-Type", "application/json");
		    request.setHeader("Ocp-Apim-Subscription-Key", "65bafc9674c94c4aa62815cce7552de8");
		    
		    //request body
		    StringEntity reqEntity = new StringEntity("{\"url\":\"http://www.infotigres.com/Imagenes/caracteristicas-del-cuerpo-del-tigre.jpg\"}");   
		    request.setEntity(reqEntity);
		    
		    HttpResponse response = httpclient.execute(request);
		    HttpEntity entity = response.getEntity();
		    //
			if (entity != null) 
			{
				//JSON
				System.out.println(EntityUtils.toString(entity));
				
			}  
		//
		}catch (Exception e)
	    {
			//
			System.out.println(e.getMessage());
			e.printStackTrace();
	    }
			
	}
	/*
	 * 
	 * 
	 */
	public void funcionJson() 
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
		} catch (IOException e) {
			//manejo de error
		} catch (ParseException e) {
			//manejo de error
		}
	}	
	 
//Termina el program StoryTeller----------------------------------------
}	

/*CODIGO DE PRUEBA-------------------------------------------------------

@Override
	 public int sendPut(String data, String url) 
	 {
		 int responseCode = -1;
	     HttpClient httpClient = new DefaultHttpClient();
	     try 
	     {
	    	 HttpPut request = new HttpPut(url);
	         StringEntity params =new StringEntity(data,"UTF-8");
		     params.setContentType("application/json");
		     request.addHeader("content-type", "application/json");
		     request.addHeader("Accept", );
		     request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		     request.addHeader("Accept-Language", "en-US,en;q=0.8");
		     request.setEntity(params);
		     HttpResponse response = httpClient.execute(request);
		     responseCode = response.getStatusLine().getStatusCode();
			   
		     if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) 
		     {
		         BufferedReader br = new BufferedReader(
		                 new InputStreamReader((response.getEntity().getContent())));
		
		         String output;
		         //System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n");
		         while ((output = br.readLine()) != null) 
		         {
		            //System.out.println(output);
		         }
		     }
		     else{
		         logger.error(response.getStatusLine().getStatusCode());
		
		         throw new RuntimeException("Failed : HTTP error code : "
		                 + response.getStatusLine().getStatusCode());
		     }
		 
		 }catch (Exception ex) 
		 {
		     logger.error("ex Code sendPut: " + ex);
		     logger.error("url:" + url);
		     logger.error("data:" + data);
		     }finally 
		     {
		         httpClient.getConnectionManager().shutdown();
	     }	
	     return responseCode;
	 }
	 }
*/