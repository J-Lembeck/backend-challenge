package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
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

@RunWith( KikahaRunner.class )

public class DeleteTaskServiceTest {

	@InjectMocks
	private DeleteTaskService deleteTaskService;

	@Mock
	private ITaskRepository taskRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldBeAbleToDeleteTaskById() {
		final Long taskId = 1L;

		deleteTaskService.execute(taskId);

		Mockito.verify(taskRepository, Mockito.times(1)).delete(taskId);
	}
}