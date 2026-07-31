package app.commands;

import core.dao.LabWorkDao;
import core.enums.Difficulty;
import core.protocol.CommandResponse;

public class CountLessThanDifficultyCommand implements Command {
    private final LabWorkDao labWorkDao;

    public CountLessThanDifficultyCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        if (!(argument instanceof String)) {
            return new CommandResponse(false,
                    "Для count_less_than_difficulty ожидается строка с именем Difficulty.",
                    null);
        }
        String diffName = ((String) argument).toUpperCase();
        try {
            Difficulty threshold = Difficulty.valueOf(diffName);
            long cnt = labWorkDao.fetchAll().stream()
                    .filter(lw -> lw.getDifficulty() != null
                            && lw.getDifficulty().ordinal() < threshold.ordinal())
                    .count();
            return new CommandResponse(true,
                    "Найдено элементов с difficulty<" + threshold + ": " + cnt,
                    null);
        } catch (IllegalArgumentException e) {
            return new CommandResponse(false,
                    "Неверное значение Difficulty.",
                    null);
        }
    }

    @Override
    public String getDescription() {
        return "count_less_than_difficulty – подсчитать элементы с difficulty меньше заданного";
    }
}