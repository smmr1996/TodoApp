package todoweb.db

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlBatch
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

import todoweb.core.domain.Task
import todoweb.core.domain.Todo
import todoweb.core.mapper.TaskMapper


@RegisterRowMapper(TaskMapper::class)
interface TaskDbDao: SqlObject {

    @SqlQuery("SELECT * FROM task WHERE parent_id = :id")
    fun findById(@Bind("id") id: Long): List<Task>

    @SqlUpdate("DELETE FROM task WHERE parent_id = :id")
    fun deleteById(@Bind("id") id: Long)

    @SqlBatch("INSERT INTO task (id, name, description, parent_id) VALUES (:id, :name, :description, :parentId)")
    @GetGeneratedKeys
    fun insertTasks(@BindBean tasks: List<Task>, @Bind("parentId") parentId: Long): List<Long>
}