package backend.challenge.modules.task.services;

import kikaha.urouting.api.Response;

public interface IDeleteTaskService {

	Response execute(Long taskId);

}
