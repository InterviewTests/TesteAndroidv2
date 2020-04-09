package pt.felipegouveia.bankapp.presentation.entity

/**
 * A generic wrapper class around data request
 */
data class Data<RequestData>(var responseType: Status, var data: RequestData? = null, var error: Error? = null)

enum class Status { SUCCESSFUL, ERROR }