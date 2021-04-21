package Bot;

import Http.CGClient;
import Util.MessageGenerator;
import Util.MessageParser;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class JdingBot extends TelegramLongPollingBot {

    private String botUsername = "sloth_announcement_bot";
    private String botToken = "";

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }


    public void onUpdateReceived(Update update) {
        MessageGenerator m = new MessageGenerator();

        if (update.hasMessage() && update.getMessage().hasText()) {

            ArrayList<String> stockSymbols = MessageParser.getSymbols(update.getMessage().getText());
            stockSymbols.forEach(sym -> {
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                String text = "";
                try {
                    JSONObject response = CGClient.getStockPriceData(sym.replace("$","").trim());
                    text = m.priceMessageCoinGecko(response);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                message.setText(text);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            });


        }
    }
}
