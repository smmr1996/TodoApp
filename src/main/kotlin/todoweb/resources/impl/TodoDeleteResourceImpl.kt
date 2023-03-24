/**
 * It's a Copyright doc 2023
 */
package todoweb.resources.impl

import todoweb.resources.TodoDeleteResource
import todoweb.service.TodoDeleteService

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.NotFoundException
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

/**
 * Implementation of [TodoDeleteResource] interface
 *
 * @author Syed Mohammad Mehdi
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/todos")
class TodoDeleteResourceImpl(
    private val todoDeleteService: TodoDeleteService
): TodoDeleteResource {

    @DELETE
    @Path("/{id}")
    override fun deleteTodoById(@PathParam("id") id: Long): Response {
        return try {
            Response.ok(todoDeleteService.deleteTodoById(id))
                .entity("Deleted Successfully")
                .build()
        } catch (e: NotFoundException) {
            Response.status(Status.NOT_FOUND).entity(e.message).build()
        }
    }
}