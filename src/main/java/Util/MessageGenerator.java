package Util;

import org.json.JSONObject;

public class MessageGenerator {

    private String priceMessageTemplate = " " +
            "\uD83D\uDCB0 Price [USD]: $%s \n" +
            "  ⚖️ H: $0.168754 | L: $%s\n" +
            "  ⚡️ Price [BTC]: %s \u20BF \n" +
            "  ✨ Price [ETH]: %s Ξ\n" +
            "  \uD83D\uDCC8 1h : %s \n" +
            "  \uD83D\uDE80 24h : %s \n" +
            "  \uD83D\uDCA9 7d : %s \n" +
            "  \uD83D\uDCCA Volume : %s \n" +
            "  \uD83D\uDC8E MarketCap : %s ";

    public String priceMessageCoinGecko(JSONObject json) {
        String price = String.valueOf(json.getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"));
        String high = String.valueOf(json.getJSONObject("market_data").getJSONObject("high_24h").getDouble("usd"));
        String low = String.valueOf(json.getJSONObject("market_data").getJSONObject("low_24h").getDouble("usd"));
        String priceBtc = String.valueOf(json.getJSONObject("market_data").getJSONObject("current_price").getDouble("btc"));
        String priceEth = String.valueOf(json.getJSONObject("market_data").getJSONObject("current_price").getDouble("eth"));
        String hourD = String.valueOf(json.getJSONObject("market_data").getJSONObject("price_change_percentage_1h_in_currency").getDouble("usd"));
        String dayD = String.valueOf(json.getJSONObject("market_data").getDouble("price_change_percentage_24h"));
        String weekD = String.valueOf(json.getJSONObject("market_data").getDouble("price_change_percentage_7d"));
        String volume = String.valueOf(json.getJSONObject("market_data").getJSONObject("total_volume").getDouble("usd"));
        String marketCap = String.valueOf(json.getJSONObject("market_data").getJSONObject("market_cap").getDouble("usd"));
        return String.format(priceMessageTemplate,price,high,low,priceBtc,priceEth,hourD,dayD,weekD,volume,marketCap);
    }
}
