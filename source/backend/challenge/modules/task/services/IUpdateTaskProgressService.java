package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import kikaha.urouting.api.Response;

public interface IUpdateTaskProgressService {

	Response execute(final Long taskId, final TaskProgressDTO taskProgress);

}
