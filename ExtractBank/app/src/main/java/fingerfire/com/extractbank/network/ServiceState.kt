package fingerfire.com.extractbank.network

sealed class ServiceState<T> {
    data class Success<T>(val data: T?) : ServiceState<T>()
    data class Error<T>(val data: T? = null) : ServiceState<T>()
}