package Util;

import com.couchbase.client.java.Cluster;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        return "";
    }
}
