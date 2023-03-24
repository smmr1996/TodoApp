package todoweb.core.mapper

import todoweb.core.domain.Todo
import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.ResultSet
import java.sql.SQLException

class TodoMapper : RowMapper<Todo> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): Todo {
        if (rs != null) {
            return Todo(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                tasks = null
            )
        }
        throw SQLException("Could Not Map!")
    }
}