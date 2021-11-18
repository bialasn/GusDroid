package com.nbprog.gusdroid.model

import okio.IOException
import retrofit2.HttpException

sealed class GusResult<out T> {
    data class Success<out T>(val value: T): GusResult<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): GusResult<Nothing>()
    data class ParsingError(val message : String): GusResult<Nothing>()
    object NetworkError: GusResult<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): GusResult<T> {
        return try {
            GusResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> GusResult.NetworkError
                is HttpException -> {
                    val code = throwable.code()
//                    val errorResponse = convertErrorBody(throwable)
                    GusResult.GenericError(code, "some error")
                }
                is XMLParseException -> GusResult.ParsingError(throwable.message)
                else -> {
                    GusResult.GenericError(null, null)
                }
            }
        }
    }
