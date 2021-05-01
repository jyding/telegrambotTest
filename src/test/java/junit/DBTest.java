package junit;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Practicing for an interview. Don't need Couchbase in particular
 */
public class DBTest {

    @Ignore
    @Test
    public void addDB(){
        Cluster cluster = Cluster.connect("127.0.0.1", "Administrator", "@QvS3Bj5VApkHn@");
        Bucket bucket = cluster.bucket("telegram_tokens");
        Collection collection = bucket.defaultCollection();
        // Upsert Document
        collection.upsert("token1", JsonObject.create().put("TOKEN", "1799355002:AAHsERK4346yaXXGwDyiqGMsp3HJ_Uw1gSg").put("bot_name","sloth_announcement_bot"));
        collection.upsert("token2", JsonObject.create().put("TOKEN", "1780240362:AAG8wUtVeTqaK8WzAEg0cNoU8GqlWr7qp4w").put("bot_name","Sloth_gurl_bot."));
        GetResult getResult = collection.get("token2");
        String name = getResult.contentAsObject().getString("TOKEN");
        System.out.println(name);
    }

    @Ignore
    @Test
    public void getTokenFromDB() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/local.properties"));
        Cluster cluster = Cluster.connect(prop.getProperty("cluster"), prop.getProperty("user"), prop.getProperty("pass"));
        Collection collection = cluster.bucket("telegram_tokens").defaultCollection();
        GetResult getResult = collection.get("token1");
    }

}
