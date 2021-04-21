import Http.CGClient;
import org.junit.Assert;
import org.junit.Test;
import java.net.URISyntaxException;

public class ConnectionTest {

    @Test
    public void getSymbol() throws URISyntaxException {
        Assert.assertEquals(
                CGClient.getStockPriceData("bitcoin").getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"),
                CGClient.getStockPriceData("btc").getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"),
                CGClient.getStockPriceData("BTC").getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"));
    }
}
