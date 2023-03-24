/**
 * It's a Copyright doc 2023
 */
package todoweb.utils

import java.lang.RuntimeException

/**
 * Custom Application Exception class
 *
 * This class defines the custom exception which would be thrown and caught in the App.
 *
 * @author Syed Mohammad Mehdi
 */
class TodoAppException(message: String, cause: Throwable? = null): RuntimeException(message, cause)