package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.TaskFactory;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TaskRepository implements ITaskRepository {

	List<Task> taskList = new ArrayList<>();

	@Override
	public Task index(final Long taskId) {
		return taskList.stream().filter(task -> task.getId().equals(taskId))
				.findFirst().orElse(null);
	}

	@Override
	public List<Task> show() {
		return taskList;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		if(taskDTO.getTitle().isBlank()) {
			return null;
		}

		Task task = new TaskFactory().build(taskDTO);
		taskList.add(task);

		return task;
	}

	@Override
	public Task update(Long taskId, final Task task) {
		Task taskToUpdate = this.index(taskId);

		if(taskToUpdate == null) {
			return null;
		}

		taskToUpdate.setTitle(task.getTitle());
		taskToUpdate.setDescription(task.getDescription());

		taskList.add(taskToUpdate);

		return taskToUpdate;
	}

	@Override
	public Boolean delete(final Long taskId) {
		List<Task> tasksToRemove = taskList.stream().filter(task -> task.getId().equals(taskId)).collect(Collectors.toList());

		if(tasksToRemove.isEmpty()) {
			return Boolean.FALSE;
		}

		taskList.removeAll(tasksToRemove);
		return Boolean.TRUE;
	}

	public Task updateProgress(Long taskId, final TaskProgressDTO taskProgress) {
		Task taskToUpdate = taskList.stream().filter(t -> t.getId().equals(taskId))
				.findFirst().orElse(null);

		if(taskToUpdate == null) {
			return null;
		}

		taskList.remove(taskToUpdate);

		taskToUpdate.setProgress(taskProgress.getProgress());
		if(taskProgress.getProgress() == 100) taskToUpdate.setStatus(TaskStatus.COMPLETE);

		taskList.add(taskToUpdate);

		return taskToUpdate;
	}

}
