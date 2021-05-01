package Bot.BotImpl;

import Bot.Http.CGClient;
import Bot.Util.MessageGenerator;
import Bot.Util.MessageParser;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class PricingBot extends TelegramLongPollingBot {

    private String botUsername = "sloth_announcement_bot";
    private String botToken = "1799355002:AAHsERK4346yaXXGwDyiqGMsp3HJ_Uw1gSg";

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


            if(update.getMessage().hasText() && update.getMessage().getText().contains("/info")){
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                String text = MessageParser.readLineByLine("src/main/resource/info.txt");
                message.setText(text);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("/website")){
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("https://Sleepysloth.finance/");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("/chart")){
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("https://poocoin.app/tokens/0x86432b9bea373172c852d5bbab1c275fec3f15ae");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if(update.getMessage().hasText() && update.getMessage().getText().contains("love you")){
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText("love u too baby");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
