package module;

import java.util.UUID;

/**
 * Класс IdGenerator генерирует уникальные идентификаторы с использованием UUID.
 */
public class IdGenerator {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
