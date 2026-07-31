package app.commands;

import core.dao.LabWorkDao;
import core.objects.LabWork;
import core.protocol.CommandResponse;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Команда min_by_id – выводит элемент с минимальным id.
 */
public class MinByIdCommand implements Command {
    private final LabWorkDao labWorkDao;

    public MinByIdCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        // Получаем все элементы
        List<LabWork> list = labWorkDao.fetchAll();

        if (list.isEmpty()) {
            return new CommandResponse(false,
                    "Коллекция пуста.",
                    null);
        }

        // Ищем минимальный по id
        Optional<LabWork> minOpt = list.stream()
                .min(Comparator.comparingLong(LabWork::getId));

        if (!minOpt.isPresent()) {
            return new CommandResponse(false,
                    "Не удалось найти элемент с минимальным id.",
                    null);
        }

        LabWork min = minOpt.get();
        return new CommandResponse(true,
                "Минимальный элемент: " + min,
                min);
    }

    @Override
    public String getDescription() {
        return "min_by_id – вывести элемент с минимальным id";
    }
}
