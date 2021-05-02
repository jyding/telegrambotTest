package Bot.BotImpl;

import Bot.Http.CGClient;
import Bot.Util.MessageGenerator;
import Bot.Util.MessageParser;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;

public class PricingBot extends TelegramLongPollingBot {

    private static String botUsername = "sloth_announcement_bot";
    private String botToken = null;

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        try (InputStream input = new FileInputStream("src/main/resources/key.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            botToken = prop.getProperty("key");
        } catch (IOException io) {
            io.printStackTrace();
        }
        return botToken;
    }

    public void onUpdateReceived(Update update) {
        MessageGenerator m = new MessageGenerator();
        String chatId = update.getMessage().getChatId().toString();
        if (update.hasMessage() && update.getMessage().hasText()) {
            ArrayList<String> stockSymbols = MessageParser.getSymbols(update.getMessage().getText());
            stockSymbols.forEach(sym -> {
                try {
                    JSONObject response = CGClient.getStockPriceData(sym.replace("$","").trim());
                    String text = m.priceMessageCoinGecko(response);
                    sendMessage(text,chatId);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });

            if(stockSymbols.isEmpty() && update.getMessage().getText().contains("/price")){
                try {
                    JSONObject response = CGClient.getStockPriceData("sleepy-sloth");
                    String text = m.priceMessageCoinGecko(response);
                    sendMessage(text,chatId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("/info")){
                String text = MessageParser.readLineByLine("src/main/resources/info.txt");
                sendMessage(text,chatId);
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("/website")){
                sendMessage("https://sleepysloth.finance",chatId);
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("/chart")){
                sendMessage("https://poocoin.app/tokens/0x86432b9bea373172c852d5bbab1c275fec3f15ae",chatId);
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("love you")){
                sendMessage("love u too baby",chatId);
            }
        }
    }

    private void sendMessage(String text,String chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
