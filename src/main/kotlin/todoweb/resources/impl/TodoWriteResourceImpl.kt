/**
 * It's a Copyright doc 2023
 */
package todoweb.resources.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

import todoweb.core.domain.Todo
import todoweb.db.TodoDao
import todoweb.resources.TodoWriteResource
import todoweb.service.TodoWriteService

import javax.ws.rs.Consumes
import javax.ws.rs.NotFoundException
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
        val mapper = jacksonObjectMapper()
        val todo: Todo = mapper.readValue(todoJson, Todo::class.java)
        return try {
            Response.ok(todoWriteService.updateTodoById(id, todo)).build()
        } catch (e: NotFoundException) {
            Response.status(Status.NOT_FOUND).entity(e.message).build()
        }
    }

    @POST
    override fun addTodo(@QueryParam("todo") todoJson: String): Response {
        val mapper = jacksonObjectMapper()
        val todo: Todo = mapper.readValue(todoJson, Todo::class.java)
        return Response.ok(todoWriteService.addTodo(todo)).build()
//        return try {
//            Response.ok(todoWriteService.addTodo(todo)).build()
//        } catch (e: Exception) {
//            Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.message).build()
//        }
    }
}