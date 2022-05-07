package ru.university.clients.telegram_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.university.clients.base.HttpClient;
import ru.university.config.TelegramClientConfig;
import ru.university.model.GetUpdatesRequest;
import ru.university.model.GetUpdatesResponse;
import ru.university.model.SendMessageRequest;
import ru.university.services.TelegramException;


@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramClientImpl implements TelegramClient {

    private final HttpClient httpClientJdk;
    private final ObjectMapper objectMapper;
    private final TelegramClientConfig clientConfig;

    @Override
    public GetUpdatesResponse getUpdates(GetUpdatesRequest request) {
        try {
            var params = objectMapper.writeValueAsString(request);
            log.info("getUpdates params:{}", params);

            var updatesAsString = httpClientJdk.performRequest(makeUrl("getUpdates"), params);
            log.info("updatesAsString:{}", updatesAsString);
            var updates = objectMapper.readValue(updatesAsString, GetUpdatesResponse.class);
            log.info("updates:{}", updates);

            return updates;
        } catch (JsonProcessingException ex) {
            log.error("request:{}", request, ex);
            throw new TelegramException(ex);
        }
    }

    @Override
    public void sendMessage(SendMessageRequest request) {
        try {
            var params = objectMapper.writeValueAsString(request);
            log.info("params:{}", params);

            var responseAsString = httpClientJdk.performRequest(makeUrl("sendMessage"), params);
            log.info("responseAsString:{}", responseAsString);
        } catch (JsonProcessingException ex) {
            log.error("request:{}", request, ex);
            throw new TelegramException(ex);
        }
    }

    private String makeUrl(String apiRequest) {
        return String.format("%s/bot%s/%s", clientConfig.getUrl(), clientConfig.getToken(), apiRequest);
    }
}
