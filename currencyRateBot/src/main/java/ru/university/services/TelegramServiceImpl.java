package ru.university.services;

import lombok.extern.slf4j.Slf4j;
import ru.university.clients.telegram_client.TelegramClient;
import ru.university.model.GetUpdatesRequest;
import ru.university.model.GetUpdatesResponse;
import ru.university.services.processors.MessageTextProcessor;


@Slf4j
public class TelegramServiceImpl implements TelegramService {

    private final TelegramClient telegramClient;
    private final MessageTextProcessor processorGeneral;
    private final LastUpdateIdKeeper lastUpdateIdKeeper;

    public TelegramServiceImpl(TelegramClient telegramClient,
                               MessageTextProcessor messageTextProcessor,
                               LastUpdateIdKeeper lastUpdateIdKeeper) {
        this.telegramClient = telegramClient;
        this.processorGeneral = messageTextProcessor;
        this.lastUpdateIdKeeper = lastUpdateIdKeeper;
    }

    //main method
    @Override
    public void getUpdates() {
        try {
            log.info("getUpdates begin");
            var offset = lastUpdateIdKeeper.get();
            var request = new GetUpdatesRequest(offset);
            var response = telegramClient.getUpdates(request);
            var lastUpdateId = processResponse(response); //рекакция на запрос
            lastUpdateId = lastUpdateId == 0 ? offset : lastUpdateId + 1;
            lastUpdateIdKeeper.set(lastUpdateId);
            log.info("getUpdates end, lastUpdateId:{}", lastUpdateId);
        } catch (Exception ex) {
            log.error("unhandled exception", ex);
        }
    }

    private long processResponse(GetUpdatesResponse response) {
        log.info("response.getResult().size:{}", response.getResult().size());
        long lastUpdateId = 0;
        for (var responseMsg : response.getResult()) {
            lastUpdateId = Math.max(lastUpdateId, responseMsg.getUpdateId());
            processMessage(responseMsg.getMessage());
        }
        log.info("lastUpdateId:{}", lastUpdateId);
        return lastUpdateId;
    }

    private void processMessage(GetUpdatesResponse.Message message) {
        log.info("message:{}", message);

        var chatId = message.getChat().getId();
        var messageId = message.getMessageId();

        var result  = processorGeneral.process(message.getText());
        var replay = result.getFailReply() == null ? result.getOkReply() : result.getFailReply();
        ru.university.model.SendMessageRequest sendMessageRequest = new ru.university.model.SendMessageRequest(chatId, replay, messageId);
        telegramClient.sendMessage(sendMessageRequest);
    }
}

















