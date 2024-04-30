package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskDTOFactory;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.models.TaskFactory;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;

	@Mock
	final ITaskRepository taskRepository = new TaskRepository();

	List<TaskDTO> taskList = new ArrayList<>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);

		taskList = IntStream.range(0, 10)
				.mapToObj(i -> {
					return TaskDTOFactory.build("Tarefa - " + i, "Descrição tarefa - " + i);
				})
				.collect(Collectors.toList());
	}

	@Test
	public void shouldListTasks() {
		Mockito.when(retrieveAllTasksService.execute().entity()).thenReturn(taskList);
	}
}