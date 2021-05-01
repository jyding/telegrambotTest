package Bot.Http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class GenericClient {

    public static Object getResponse(String endpoint) throws URISyntaxException {

        HttpClient client = new DefaultHttpClient();
        String url = String.format(endpoint);
        URIBuilder builder = new URIBuilder(url);

        HttpGet request = new HttpGet(builder.build());
        try{
            HttpResponse response = client.execute(request);
            String entity = EntityUtils.toString(response.getEntity());
            if(response.getStatusLine().getStatusCode()==429){
                //TODO: RETRY
                System.out.println("CALL FAILED: " + endpoint);
                return null;
            }
            else if(response.getStatusLine().getStatusCode()==404){
                return 404;
            }

            Object json = new JSONTokener(entity).nextValue();
            if (json instanceof JSONObject){
                JSONObject jsonResponse = new JSONObject(entity);
                return jsonResponse;
            }
            else if (json instanceof JSONArray){
                JSONArray jsonResponse = new JSONArray(entity);
                return jsonResponse;
            }

        }catch(Exception e){
            try {
                System.out.println("EXCEPTION: "+builder.build().toURL().toString());
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

        }
        return new JSONObject();
    }
}
