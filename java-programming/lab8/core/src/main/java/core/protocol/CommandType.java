package core.protocol;

/**
 * Все возможные имена команд.
 */
public enum CommandType {
    REGISTER,
    LOGIN,
    HELP,
    INFO,
    SHOW,
    ADD,
    UPDATE,
    REMOVE_BY_ID,
    CLEAR,
    SAVE_SERVER,
    EXECUTE_SCRIPT,
    EXIT,
    HEAD,
    ADD_IF_MAX,
    HISTORY,
    AVERAGE_OF_MINIMAL_POINT,
    MIN_BY_ID,
    COUNT_LESS_THAN_DIFFICULTY
}
