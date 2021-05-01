package Bot.Util;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class MessageGenerator {

    private DecimalFormat df = new DecimalFormat("####0.00");
    private DecimalFormat df_whole = new DecimalFormat("#.##");

    private static final String chartUp = "\uD83D\uDCC8";
    private static final String chartDown = "\uD83D\uDCC9";
    private static final String rocket = "\uD83D\uDE80";
    private static final String crying = "\uD83D\uDE3F";

    private final String priceMessageTemplate = " " +
            "\uD83D\uDCB0 Price [USD]: $ {price} \n" +
            "  ⚖️  H: $0.168754 | L: $ {low}\n" +
            "  ⚡️ Price [BTC]: {btc} \u20BF \n" +
            "  ✨ Price [ETH]: {etc} Ξ\n" +
            "  {1h_emoji} 1h : {1h} \n" +
            "  {24h_emoji} 24h : {24h} \n" +
            "  {7d_emoji} 7d :{7d} \n" +
            "  \uD83D\uDCCA Volume : {volume} \n" +
            "  \uD83D\uDC8E MarketCap : ${market} ";

    public String priceMessageCoinGecko(JSONObject json) {
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        df_whole.setGroupingUsed(true);
        df_whole.setGroupingSize(3);

        String message = priceMessageTemplate;
        message = message.replace("{price}",String.valueOf(df.format(json.getJSONObject("market_data").getJSONObject("current_price").getDouble("usd"))));
        message = message.replace("{high}",String.valueOf(df.format(json.getJSONObject("market_data").getJSONObject("high_24h").getDouble("usd"))));
        message = message.replace("{low}",String.valueOf(df.format(json.getJSONObject("market_data").getJSONObject("low_24h").getDouble("usd"))));
        message = message.replace("{btc}",String.valueOf(df.format(json.getJSONObject("market_data").getJSONObject("current_price").getDouble("btc"))));
        message = message.replace("{etc}",String.valueOf(df.format(json.getJSONObject("market_data").getJSONObject("current_price").getDouble("eth"))));
        message = message.replace("{1h}", df.format(json.getJSONObject("market_data").getJSONObject("price_change_percentage_1h_in_currency").getDouble("usd")) +"%");
        message = message.replace("{24h}", df.format(json.getJSONObject("market_data").getDouble("price_change_percentage_24h")) +"%");
        message = message.replace("{7d}", df.format(json.getJSONObject("market_data").getDouble("price_change_percentage_7d")) +"%");
        message = message.replace("{volume}",String.valueOf(df_whole.format(json.getJSONObject("market_data").getJSONObject("total_volume").getDouble("usd"))));
        message = message.replace("{market}",String.valueOf(df_whole.format(json.getJSONObject("market_data").getJSONObject("market_cap").getDouble("usd"))));

        //1 Hour Modifier
        if(json.getJSONObject("market_data").getJSONObject("price_change_percentage_1h_in_currency").getDouble("usd") > 1){
            message = message.replace("{1h_emoji}",rocket);
        }
        else if(json.getJSONObject("market_data").getJSONObject("price_change_percentage_1h_in_currency").getDouble("usd") > 0) {
            message = message.replace("{1h_emoji}",chartUp);
        }
        else if(json.getJSONObject("market_data").getJSONObject("price_change_percentage_1h_in_currency").getDouble("usd") < -1){
            message = message.replace("{1h_emoji}",crying);
        }
        else if(json.getJSONObject("market_data").getJSONObject("price_change_percentage_1h_in_currency").getDouble("usd") < 0){
            message = message.replace("{1h_emoji}",chartDown);
        }
        else {
            message = message.replace("{1h_emoji}","\uD83D\uDE10");
        }

        //24 Hour Modifier
        if(json.getJSONObject("market_data").getDouble("price_change_percentage_24h") > 1){
            message = message.replace("{24h_emoji}",rocket);
        }
        else if(json.getJSONObject("market_data").getDouble("price_change_percentage_24h") > 0) {
            message = message.replace("{24h_emoji}",chartUp);
        }
        else if(json.getJSONObject("market_data").getDouble("price_change_percentage_24h") < -1){
            message = message.replace("{24h_emoji}",crying);
        }
        else if(json.getJSONObject("market_data").getDouble("price_change_percentage_24h") < 0){
            message = message.replace("{24h_emoji}",chartDown);
        }
        else {
            message = message.replace("{24h_emoji}","\uD83D\uDE10");
        }

        //One Week Modifier
        if(json.getJSONObject("market_data").getDouble("price_change_percentage_7d") > 1){
            message = message.replace("{7d_emoji}",rocket);
        }
        else if(json.getJSONObject("market_data").getDouble("price_change_percentage_7d") > 0) {
            message = message.replace("{7d_emoji}",chartUp);
        }
        else if(json.getJSONObject("market_data").getDouble("price_change_percentage_7d") < -1){
            message = message.replace("{7d_emoji}",crying);
        }
        else if(json.getJSONObject("market_data").getDouble("price_change_percentage_7d") < 0){
            message = message.replace("{7d_emoji}",chartDown);
        }
        else {
            message = message.replace("{7d_emoji}","\uD83D\uDE10");
        }

        return message;
    }
}
