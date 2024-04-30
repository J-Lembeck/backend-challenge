package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import kikaha.urouting.api.Response;

public interface IUpdateTaskService {

	Response execute(Long taskId, Task task);

}
