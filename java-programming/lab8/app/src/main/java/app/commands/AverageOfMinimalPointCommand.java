package app.commands;

import core.dao.LabWorkDao;
import core.objects.LabWork;
import core.protocol.CommandResponse;

import java.util.List;

/**
 * average_of_minimal_point – вычисляет среднее значение поля minimalPoint
 */
public class AverageOfMinimalPointCommand implements Command {
    private final LabWorkDao labWorkDao;

    public AverageOfMinimalPointCommand(LabWorkDao labWorkDao) {
        this.labWorkDao = labWorkDao;
    }

    @Override
    public CommandResponse executeCommand(Object argument) {
        List<LabWork> list = labWorkDao.fetchAll();
        if (list.isEmpty()) {
            return new CommandResponse(false, "Коллекция пуста.", null);
        }
        double avg = list.stream()
                .mapToLong(lw -> lw.getMinimalPoint())
                .average()
                .orElse(0.0);
        return new CommandResponse(true,
                "Среднее minimalPoint = " + avg,
                null);
    }

    @Override
    public String getDescription() {
        return "average_of_minimal_point – среднее значение minimalPoint";
    }
}