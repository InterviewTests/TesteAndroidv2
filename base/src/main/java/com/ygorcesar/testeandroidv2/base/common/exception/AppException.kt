package com.ygorcesar.testeandroidv2.base.common.exception

import com.ygorcesar.testeandroidv2.base.common.network.ResponseError


/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific should extend [BusinessException] class.
 */
sealed class AppException(message: String = "") : RuntimeException(message)

/**
 * Object used to identify a generic http request exception
 */
object HttpError : AppException("Server http response code is not 200, 400 or 403")

/**
 * Object used to identify a network without internet connection
 */
object NetworkError : AppException()

/**
 * Object used to identify an user Unauthorized
 */
object UnauthorizedError : AppException()

/**
 * Unknown error
 * */
object UnknownException : AppException("Need check Logcat to see more information")

/**
 * Exception used when the server returns business validations
 */
class ServerError(val error: ResponseError) : AppException()

/**
 * Exception used when server returns authentication with login or password invalid
 * */
object InvalidCredentials : AppException()

/**
 * Extend this class for feature specific failures.
 *
 * */
abstract class BusinessException : AppException()

/**
 * Exception thrown when an essential parameter is missing in the
 * backend/network response.
 *
 */
class EssentialParamMissingException(
    missingParam: String,
    rawObject: Any
) : AppException("The params: $missingParam are missing in received object: $rawObject")
