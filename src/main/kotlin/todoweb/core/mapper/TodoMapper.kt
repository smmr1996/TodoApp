/**
 * It's a Copyright doc 2023
 */
package todoweb.core.mapper

import todoweb.core.domain.Todo
import todoweb.utils.TodoAppException

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext

import java.sql.ResultSet

/**
 * Mapper Class to map SQL result to the properties of a [Todo].
 *
 * @author Syed Mohammad Mehdi
 */
class TodoMapper : RowMapper<Todo> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Todo {
        if (rs != null) {
            return Todo(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description")
            )
        }
        throw TodoAppException("Could Not Map result set to Domain!")
    }
}