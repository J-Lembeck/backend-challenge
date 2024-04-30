package backend.challenge.modules.task.services;

import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteTaskService implements IDeleteTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Response execute(Long taskId) {
		Boolean deleted = taskRepository.delete(taskId);

		if(deleted) {
			return DefaultResponse.ok().entity("Task deleted");
		};

		return DefaultResponse.notFound().entity("Task not found");
	}

}
