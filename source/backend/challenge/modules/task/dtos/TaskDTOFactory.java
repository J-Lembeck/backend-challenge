package backend.challenge.modules.task.dtos;

import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;

public class TaskDTOFactory {

    public static TaskDTO build(Task task) {
        TaskDTO taskDTO = TaskDTO.create();

        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());

        return taskDTO;
    }

    public static TaskDTO buildFromView(TaskView task) {
        TaskDTO taskDTO = TaskDTO.create();

        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());

        return taskDTO;
    }

    public static TaskDTO build(String title, String description) {
        TaskDTO taskDTO = TaskDTO.create();

        taskDTO.setTitle(title);
        taskDTO.setDescription(description);

        return taskDTO;
    }
}
