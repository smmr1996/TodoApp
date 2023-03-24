/**
 * It's a Copyright doc 2023
 */
package todoweb

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid
import javax.validation.constraints.NotNull

class TodoWebConfiguration(
    @Valid
    @NotNull
    @field:JsonProperty("database")
    val database: DataSourceFactory = DataSourceFactory()
) : Configuration()

