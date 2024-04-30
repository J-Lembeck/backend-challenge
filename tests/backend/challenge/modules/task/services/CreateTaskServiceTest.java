package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskDTOFactory;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.DefaultResponse;
import kikaha.urouting.api.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void setUp() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		final String expectedTitle = "Louça";
		final String expectedDescription = "Lavar a louça";
		TaskDTO taskDTO = TaskDTOFactory.build(expectedTitle, expectedDescription);

		Response response = createTaskService.execute(taskDTO);
		TaskDTO task = (TaskDTO) response.entity();

		Assert.assertEquals(expectedTitle, task.getTitle());
		Assert.assertEquals(expectedDescription, task.getDescription());
	}

	@Test
	public void ShouldNotBeAbleToCreateNewTaskWithEmptyTitle() {
		final String expectedTitle = "";
		final String expectedDescription = "Lavar o carro";
		TaskDTO taskDTO = TaskDTOFactory.build(expectedTitle, expectedDescription);

		Response response = createTaskService.execute(taskDTO);

		Assert.assertTrue(response.entity().toString().contains("Title may not be null or empty"));
	}

}