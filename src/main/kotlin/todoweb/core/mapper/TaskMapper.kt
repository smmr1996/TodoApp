/**
 * It's a Copyright doc 2023
 */
package todoweb.core.mapper

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext

import todoweb.core.domain.Task

import java.sql.ResultSet
import java.sql.SQLException

/**
 * Mapper Class to map SQL result to the properties of a [Task].
 *
 * @author Syed Mohammad Mehdi
 */
class TaskMapper : RowMapper<Task> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Task {
        if (rs != null) {
            return Task(
                rs.getString("name"),
                rs.getString("description")
            )
        }
        throw SQLException("Could Not Map!")
    }
}