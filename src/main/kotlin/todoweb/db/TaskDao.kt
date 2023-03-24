/**
 * It's a Copyright doc 2023
 */
package todoweb.db

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlBatch
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

import todoweb.core.domain.Task
import todoweb.core.mapper.TaskMapper


/**
 * DAO interface to map perform CRUD operations on Task Table.
 *
 * @author Syed Mohammad Mehdi
 */
@RegisterRowMapper(TaskMapper::class)
interface TaskDao: SqlObject {

    @SqlQuery("SELECT * FROM task WHERE parent_id = :id")
    fun findById(@Bind("id") id: Long): List<Task>

    @SqlUpdate("DELETE FROM task WHERE parent_id = :id")
    fun deleteById(@Bind("id") id: Long): Int

    @SqlBatch("INSERT INTO task (name, description, parent_id) VALUES (:name, :description, :parentId)")
    fun insertTasks(@BindBean tasks: List<Task>, @Bind("parentId") parentId: Long)
}