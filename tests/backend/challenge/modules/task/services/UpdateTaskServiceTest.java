package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;

	@Mock
	private ITaskRepository taskRepository = new TaskRepository();
	private final Task initialTask = Task.create();
	private final Task modifiedTask = Task.create();
	private final Task updatedTask = Task.create();
	private final Long taskId = 1L;
	private TaskStatus taskStatus = TaskStatus.COMPLETE;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		updateTaskService = new UpdateTaskService(taskRepository);

		initialTask.setId(taskId);
		initialTask.setTitle("Louça");
		initialTask.setDescription("Lavar a louça");

		modifiedTask.setId(1L);
		modifiedTask.setTitle("Faculdade");
		modifiedTask.setDescription("Estudar machine learning");

		updatedTask.setId(taskId);
		updatedTask.setTitle("Faculdade");
		updatedTask.setDescription("Estudar machine learning");
		updatedTask.setStatus(taskStatus);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.update(Matchers.anyLong(), Matchers.any())).thenReturn(updatedTask);
		Response response = updateTaskService.execute(taskId, modifiedTask);

		Assert.assertNotEquals(initialTask.getTitle(), updatedTask.getTitle());
		Assert.assertNotEquals(initialTask.getDescription(), updatedTask.getDescription());
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		Response response = updateTaskService.execute(1L, modifiedTask);
		Response expectedResponse = DefaultResponse.notFound().statusCode(404);

		Assert.assertEquals(expectedResponse.statusCode(), response.statusCode());
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		Mockito.when(taskRepository.index(Matchers.anyLong())).thenReturn(initialTask);
		Mockito.when(taskRepository.update(Matchers.anyLong(), Matchers.anyObject())).thenReturn(updatedTask);

		Response response = updateTaskService.execute(taskId, modifiedTask);
		Assert.assertNull(initialTask.getStatus());
	}
}