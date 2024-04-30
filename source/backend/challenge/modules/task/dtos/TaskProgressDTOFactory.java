package backend.challenge.modules.task.dtos;

import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;

public class TaskProgressDTOFactory {

    public static TaskProgressDTO build(Task task) {
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();

        taskProgressDTO.setProgress(task.getProgress());
        taskProgressDTO.setStatus(task.getStatus());

        return taskProgressDTO;
    }

    public static TaskProgressDTO buildFromView(TaskProgressView taskProgressView) {
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();

        taskProgressDTO.setProgress(taskProgressView.getProgress());

        return taskProgressDTO;
    }

    public static TaskProgressDTO buildFromProgress(int expectedProgress, TaskStatus status) {
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();

        taskProgressDTO.setProgress(expectedProgress);
        taskProgressDTO.setStatus(status);

        return taskProgressDTO;
    }
}
