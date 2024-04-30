package backend.challenge.modules.task.services;

import kikaha.urouting.api.Response;

public interface IRetrieveTaskByIdService {

	Response execute(Long taskId);

}
