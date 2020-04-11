package pt.felipegouveia.bankapp.presentation.entity

/**
 * A generic wrapper class around API requests
 */
data class Response<RequestData>(var status: Status, var data: RequestData? = null, var error: Error? = null)

enum class Status { SUCCESSFUL, ERROR, BAD_USER, BAD_PASSWORD, NO_NETWORK }