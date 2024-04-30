package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTOFactory;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService {

    private final ITaskRepository taskRepository;

    @Inject
    public UpdateTaskProgressService(final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Response execute(final Long taskId, final TaskProgressDTO taskProgress) {
        if(taskProgress.getProgress() > 100) taskProgress.setProgress(100);
        if(taskProgress.getProgress() < 0) taskProgress.setProgress(0);

        Task updatedTask = taskRepository.updateProgress(taskId, taskProgress);

        if(updatedTask == null) {
            return DefaultResponse.notFound().statusCode(404);
        }

        return DefaultResponse.ok().entity(TaskProgressDTOFactory.build(updatedTask));
    }
}
