package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTOFactory;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class RetrieveAllTasksService implements IRetrieveAllTasksService {

	private final ITaskRepository taskRepository;

	@Inject
	public RetrieveAllTasksService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Response execute() {
		return DefaultResponse.ok().entity(taskRepository.show().stream().map(TaskDTOFactory::build).collect(Collectors.toList()));
	}
}
