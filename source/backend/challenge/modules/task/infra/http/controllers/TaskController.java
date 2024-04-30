package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTOFactory;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService,
		final IRetrieveTaskByIdService retrieveTaskByIdService,
		final IUpdateTaskService updateTaskService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
	}

	@GET
	public Response show() {
		return retrieveAllTasksService.execute();
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		return retrieveTaskByIdService.execute(taskId);
	}

	@POST
	public Response create(TaskView task) {
		return createTaskService.execute(TaskDTOFactory.buildFromView(task));
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		return updateTaskService.execute(taskId, task);
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		return deleteTaskService.execute(taskId);
	}

}
