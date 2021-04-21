package Http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

public class CGClient extends GenericClient{

    private static final String coinPriceUrl = "https://api.coingecko.com/api/v3/coins/";
    private static final String coinListUrl = "https://api.coingecko.com/api/v3/coins/list";

    public static JSONObject getStockPriceData(String id) throws URISyntaxException {
        Object response = getResponse(coinPriceUrl+id);
        if(response instanceof Integer && (Integer) response==404){
            try{
                return (JSONObject) getResponse(coinPriceUrl+ symbolIdMap().get(id.toLowerCase()));
            }catch (JSONException e) {
                return null;
            }
        }
        else{

        }
        return (JSONObject) getResponse(coinPriceUrl+id);
    }

    public static HashMap<String,String> symbolIdMap() throws URISyntaxException {
        HashMap<String,String> obj = new HashMap<>();
        JSONArray response = (JSONArray)getResponse(coinListUrl);
        response.forEach( j -> {
            JSONObject coin = new JSONObject(j.toString());
            obj.put(coin.getString("symbol").toLowerCase(),coin.getString("id"));
        });
        return obj;
    }
}
