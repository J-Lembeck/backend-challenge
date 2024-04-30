package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskDTOFactory;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTaskService implements ICreateTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public CreateTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Response execute(TaskDTO taskDTO) {
		Task task = taskRepository.create(taskDTO);

		if (task == null) {
			return DefaultResponse.badRequest().entity("Title may not be null or empty");
		}

		return DefaultResponse.created().entity(TaskDTOFactory.build(task));
	}

}
