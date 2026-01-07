package net.othmane.emsibot.telegram;

import jakarta.annotation.PostConstruct;
import net.othmane.emsibot.agents.AIAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${telegram.api.key}")
    private String telegramBotToken;
    private AIAgent aiAgent;

    public TelegramBot(AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }
    @PostConstruct
    public void registerTelegramBot() {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onUpdateReceived(Update telegraRequest) {
        try {
            if(!telegraRequest.hasMessage()) return;
            String messageText = telegraRequest.getMessage().getText();
            Long chatId = telegraRequest.getMessage().getChatId();
            senTypingQuestion(chatId);
            String answer=aiAgent.askAgent(messageText);
            sendTextMessage(chatId, answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "Emsisbot";
    }
    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    private void sendTextMessage(long chatid, String text) throws TelegramApiException {
     SendMessage sendMessage = new SendMessage(String.valueOf(chatid), text);
     execute(sendMessage);
    }

    private void senTypingQuestion(long chatid) throws TelegramApiException {
        SendChatAction sendChatAction = new SendChatAction();
        sendChatAction.setChatId(String.valueOf(chatid));
        sendChatAction.setAction(ActionType.TYPING);
        execute(sendChatAction);
    }
}
