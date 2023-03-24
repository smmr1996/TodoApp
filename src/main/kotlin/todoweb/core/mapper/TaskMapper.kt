package todoweb.core.mapper

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import todoweb.core.domain.Task
import java.sql.ResultSet
import java.sql.SQLException

class TaskMapper : RowMapper<Task> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Task {
        if (rs != null) {
            return Task(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description")
            )
        }
        throw SQLException("Could Not Map!")
    }
}