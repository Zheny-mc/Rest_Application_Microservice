package ru.university.clients.telegram_client;

import ru.university.model.GetUpdatesRequest;
import ru.university.model.GetUpdatesResponse;
import ru.university.model.SendMessageRequest;


public interface TelegramClient {

    GetUpdatesResponse getUpdates(GetUpdatesRequest request);

    void sendMessage(SendMessageRequest request);
}
