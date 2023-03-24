/**
 * It's a Copyright doc 2023
 */
package todoweb.resources.impl

import todoweb.resources.TodoReadResource
import todoweb.service.TodoReadService
import todoweb.utils.TodoAppException

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

/**
 * Implementation of [TodoReadResource] interface
 *
 * @author Syed Mohammad Mehdi
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/todos")
class TodoReadResourceImpl(
    private val todoReadService: TodoReadService
) : TodoReadResource {

    @GET
    override fun getAll(): Response {
        return try {
            Response.ok(todoReadService.getAll()).build()
        } catch (e: TodoAppException) {
            return Response
                .status(Status.INTERNAL_SERVER_ERROR)
                .entity("Some internal error")
                .build()
        }
    }

    @GET
    @Path("/{id}")
    override fun getTodoById(@PathParam("id") id: Long): Response {
        return try {
            Response.ok(todoReadService.getTodoById(id)).build()
        } catch (e: TodoAppException) {
            Response.status(Status.NOT_FOUND).entity(e.message).build()
        }
    }

}