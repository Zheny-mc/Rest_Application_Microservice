package ru.university.services.processors;

public enum Messages {
    NUMBER_FORMAT_MESSAGE("Ожидаемый формат числа: " + "Пример: 10"),
    EXPECTED_FORMAT_MESSAGE("""
                    Возможные команды ("/" не нужен):
                    получить информацию о работнику: <целое число>
                    """);

    private final String text;

    Messages(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
