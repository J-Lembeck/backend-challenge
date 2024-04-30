package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import kikaha.urouting.api.Response;

public interface ICreateTaskService {

	Response execute(TaskDTO taskDTO);

}
