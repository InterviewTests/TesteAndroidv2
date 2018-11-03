package com.ygorcesar.testeandroidv2.base.data

import com.ygorcesar.testeandroidv2.base.common.exception.EssentialParamMissingException
import com.ygorcesar.testeandroidv2.base.common.network.BaseResponse
import io.reactivex.functions.Function

/**
 * Base mapper to all Mappers extend
 *
 * @param Raw The result from server
 * @param Model The object to create from Raw
 */
abstract class BaseMapper<Raw : BaseResponse, Model> : Function<Raw, Model> {

    private var missingParams = StringBuilder("[")

    abstract val trackException: (Throwable) -> Unit

    fun addMissingParam(param: String) {
        missingParams.append("$param,")
    }

    fun getMissingParams() = missingParams.toString()

    private fun closeMissingParams() = "${getMissingParams()}]"

    fun isMissingParams() = closeMissingParams() != "[]"

    fun throwException(raw: Any) {
        missingParams = StringBuilder("[")
        val essentialParamException =
            EssentialParamMissingException(
                getMissingParams(),
                rawObject = raw
            )
        trackException(essentialParamException)
        throw essentialParamException
    }

    @Throws(EssentialParamMissingException::class)
    override fun apply(raw: Raw): Model {
        raw.validateError()
        assertEssentialParams(raw)
        return copyValues(raw)
    }

    /**
     * Check if the required parameters were return from server
     *
     * @param raw The result from server
     * @throws EssentialParamMissingException When a required parameter is missing
     */
    abstract fun assertEssentialParams(raw: Raw)

    /**
     * Create a [Model] using the values in [Raw]
     *
     * @param raw The result from server
     * @return A model with the raw's values
     */
    abstract fun copyValues(raw: Raw): Model
}