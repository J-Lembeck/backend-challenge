package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTOFactory;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private IUpdateTaskProgressService updateTaskProgressService;

	@Mock
	private ITaskRepository taskRepository = new TaskRepository();

	private final Task initialTask = Task.create();
	private final Task updatedTask = Task.create();
	private final Long taskId = 1L;
	private final int oldProgress = 10;
	private final TaskStatus taskStatus = TaskStatus.PROGRESS;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);

		initialTask.setId(taskId);
		initialTask.setProgress(oldProgress);
		initialTask.setStatus(taskStatus);

		updatedTask.setId(taskId);
		updatedTask.setProgress(50);
		updatedTask.setStatus(taskStatus);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		final int expectedProgress = 50;

		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.updateProgress(Matchers.anyLong(), Matchers.anyObject())).thenReturn(updatedTask);

		TaskProgressDTO task = (TaskProgressDTO) updateTaskProgressService
				.execute(taskId, TaskProgressDTOFactory.buildFromProgress(expectedProgress, updatedTask.getStatus())).entity();

		Assert.assertNotEquals(task.getProgress(), oldProgress);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		final Task completedTask = Task.create();
		completedTask.setId(taskId);
		completedTask.setProgress(100);
		completedTask.setStatus(TaskStatus.COMPLETE);

		final int progress = 100;
		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.updateProgress(Matchers.anyLong(), Matchers.anyObject())).thenReturn(completedTask);

		TaskProgressDTO task = (TaskProgressDTO) updateTaskProgressService
				.execute(taskId, TaskProgressDTOFactory.buildFromProgress(progress, completedTask.getStatus())).entity();

		Assert.assertEquals(task.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanOneHundred() {
		final int progress = 99;

		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.updateProgress(Matchers.anyLong(), Matchers.anyObject())).thenReturn(updatedTask);

		TaskProgressDTO task = (TaskProgressDTO) updateTaskProgressService
				.execute(taskId, TaskProgressDTOFactory.buildFromProgress(progress, updatedTask.getStatus())).entity();

		Assert.assertNotEquals(task.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanZero() {
		final Task updatedTaskWithZeroProgress = Task.create();
		updatedTaskWithZeroProgress.setId(taskId);
		updatedTaskWithZeroProgress.setProgress(0);
		updatedTaskWithZeroProgress.setStatus(taskStatus);

		final int progress = -3;
		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.updateProgress(Matchers.anyLong(), Matchers.anyObject())).thenReturn(updatedTaskWithZeroProgress);

		TaskProgressDTO task = (TaskProgressDTO) updateTaskProgressService
				.execute(taskId, TaskProgressDTOFactory.buildFromProgress(progress, updatedTaskWithZeroProgress.getStatus())).entity();

		Assert.assertEquals(task.getProgress(), 0);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred() {
		final Task updatedTaskWithMaxProgress = Task.create();
		updatedTaskWithMaxProgress.setId(taskId);
		updatedTaskWithMaxProgress.setProgress(100);
		updatedTaskWithMaxProgress.setStatus(taskStatus);

		final int progress = 200;
		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.updateProgress(Matchers.anyLong(), Matchers.anyObject())).thenReturn(updatedTaskWithMaxProgress);

		TaskProgressDTO task = (TaskProgressDTO) updateTaskProgressService
				.execute(taskId, TaskProgressDTOFactory.buildFromProgress(progress, updatedTaskWithMaxProgress.getStatus())).entity();

		Assert.assertEquals(task.getProgress(), 100);
	}
}
