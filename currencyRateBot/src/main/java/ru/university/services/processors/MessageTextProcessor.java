package ru.university.services.processors;

import ru.university.model.MessageTextProcessorResult;

public interface MessageTextProcessor {
    MessageTextProcessorResult process(String msgText);
}
