package todoweb.db

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import todoweb.core.domain.Todo
import todoweb.core.mapper.TodoMapper


@RegisterRowMapper(TodoMapper::class)
interface TodoDbDao: SqlObject {

    @SqlQuery("SELECT * FROM todo WHERE id = :id")
    fun findById(@Bind("id") id: Long): Todo

    @SqlQuery("SELECT * FROM todo")
    fun findAll(): List<Todo>

    @SqlUpdate("DELETE FROM todo WHERE id = :id")
    fun deleteById(@Bind("id") id: Long)

    @SqlUpdate("INSERT INTO todo (id, name, description) VALUES (:id, :name, :description)")
    @GetGeneratedKeys
    fun insertTodo(@BindBean todo: Todo): Long
}