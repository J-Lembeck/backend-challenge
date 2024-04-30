package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskDTOFactory;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskService implements IUpdateTaskService {

    private final ITaskRepository taskRepository;

    @Inject
    public UpdateTaskService(final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Response execute(final Long taskId, final Task task) {
        Task updatedTask = taskRepository.update(taskId, task);

        if(updatedTask == null) {
            return DefaultResponse.notFound().statusCode(404);
        }

        return DefaultResponse.ok().entity(TaskDTOFactory.build(updatedTask));
    }
}
