package Bot.Util;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CouchbaseConnection {

    private Properties prop = new Properties();
    private Cluster cluster;

    public CouchbaseConnection() throws IOException {
        prop.load(new FileInputStream("src/main/local.properties"));
        cluster = Cluster.connect(prop.getProperty("cluster"), prop.getProperty("user"), prop.getProperty("pass"));
    }

    public String getToken(){
        Collection collection = cluster.bucket("telegram_tokens").defaultCollection();
        GetResult getResult = collection.get("token1");
        return getResult.contentAsObject().getString("TOKEN");
    }
}
