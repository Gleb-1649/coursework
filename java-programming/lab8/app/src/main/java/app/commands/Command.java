package app.commands;

import core.protocol.CommandResponse;

import java.io.Serializable;

/**
 * Интерфейс для команд, реализуемых в серверном модуле.
 * Команда принимает аргумент (Object) и возвращает CommandResponse.
 */
public interface Command extends Serializable {
    CommandResponse executeCommand(Object argument);
    String getDescription();
}
