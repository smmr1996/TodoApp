/**
 * It's a Copyright doc 2023
 */
package todoweb.resources.impl

import todoweb.core.builder.TodoBuilder
import todoweb.core.domain.Todo
import todoweb.resources.TodoWriteResource
import todoweb.service.TodoWriteService
import todoweb.utils.TodoAppException

import java.lang.RuntimeException

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

/**
 * Implementation of [TodoWriteResource] interface
 *
 * @author Syed Mohammad Mehdi
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/todos")
class TodoWriteResourceImpl(
    private val todoWriteService: TodoWriteService
): TodoWriteResource {

    @PUT
    @Path("/{id}")
    override fun updateTodoById(@PathParam("id") id: Long, @QueryParam("todo") todoJson: String): Response {
        val todo: Todo = TodoBuilder.buildTodoFromJson(todoJson)
        return try {
            Response.ok(todoWriteService.updateTodoById(id, todo))
                .entity("Successfully updated")
                .build()
        } catch (e: TodoAppException) {
            Response.status(Status.NOT_FOUND).entity(e.message).build()
        }
    }

    @POST
    override fun addTodo(@QueryParam("todo") todoJson: String): Response {
        val todo: Todo = TodoBuilder.buildTodoFromJson(todoJson)
        return try {
            Response.ok(todoWriteService.addTodo(todo)).build()
        } catch (e: RuntimeException) {
            Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.message).build()
        }
    }
}