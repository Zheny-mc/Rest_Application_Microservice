package ru.university.services.processors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.university.clients.currency_rate_client.CustomerClient;
import ru.university.model.MessageTextProcessorResult;


@Slf4j
@AllArgsConstructor
@Service("messageTextProcessorCustomer")
public class MessageTextProcessorCustomer implements MessageTextProcessor {
    private final CustomerClient customerClient;

    @Override
    public MessageTextProcessorResult process(String msgText) {
        log.info("msgText:{}", msgText);

        var textParts = msgText.split(" ");

        if (textParts.length != 1) {
            return new MessageTextProcessorResult(null, Messages.EXPECTED_FORMAT_MESSAGE.getText());
        }

        Integer number = null;

        if (textParts.length == 1) {
            try {
                number = Integer.parseInt(textParts[0]);
            } catch (Exception ex) {
                System.out.println("ex = " + ex);
            }
        }

        var rate = customerClient.getCustomer(number);
        return new MessageTextProcessorResult(rate.getName(), null);
    }

}
