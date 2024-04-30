package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	@InjectMocks
	private RetrieveTaskByIdService retrieveTaskByIdService;

	@Mock
	private ITaskRepository taskRepository;

	private final Task task = Task.create();
	private final Long taskId = 1L;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		configureTask();
	}

	private void configureTask() {
		task.setId(taskId);
		task.setTitle("Louça");
		task.setDescription("Lavar a louça");
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(new Date());
	}

	@Test
	public void testRetrieveTaskById() {
		Mockito.when(taskRepository.index(taskId)).thenReturn(task);

		TaskDTO retrievedTask = (TaskDTO) retrieveTaskByIdService.execute(taskId).entity();

		Assert.assertNotNull(retrievedTask);
	}
}
