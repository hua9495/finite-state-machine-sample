package com.alexchan.finite_state_machine.core.network

import com.alexchan.finite_state_machine.core.network.AppError.Code
import com.alexchan.finite_state_machine.core.state.Result
import com.alexchan.finite_state_machine.util.guard
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.ResponseBody
import retrofit2.Response

class NetworkRequestManager {
    suspend inline fun <reified T> apiRequest(crossinline apiCall: suspend () -> Response<T>): Result<T> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body().guard {
                    // Some requests might return empty response body even though the request
                    // was succeeded, the check below verifies whether the callers care about the
                    // nullability of the response body.
                    return if (
                        ResponseBody::class.java.isAssignableFrom(T::class.java) ||
                        Any::class.java.isAssignableFrom(T::class.java)
                    ) {
                        // TODO: Improve nullability check.
                        Result.Success(Any() as T)
                    } else {
                        val error = AppError(Code.InvalidData)
                        Result.Failure(error)
                    }
                }

                return Result.Success(body)
            } else {
                handleFailureInResponse(response)
            }
        } catch (exception: Exception) {
            handleFailureInRequest(exception)
        }
    }

    inline fun <reified T> handleFailureInRequest(throwable: Throwable): Result<T> {
        return if (throwable is JsonParseException) {
            val error = AppError(Code.DataSerialization, throwable)
            Result.Failure(error)
        } else {
            val error = AppError(Code.Network, throwable)
            Result.Failure(error)
        }
    }

    inline fun <reified T> handleFailureInResponse(response: Response<T>): Result<T> {
        return Result.Failure(getAppError(response))
    }

    fun <T> getAppError(response: Response<T>): AppError {
        // Placeholder error that will be used unless replaced by a more specific error.
        var error = AppError(Code.BadRequest)
        val responseError = Gson().fromJson(
            response.errorBody()?.charStream(),
            ApiError::class.java
        )
        when (response.code()) {
            400 -> {
                return error
            }

            500 -> {
                error = AppError(Code.ServerError)
                return error
            }

            else -> {
                try {
                    when (responseError.code) {
                    }

                    return error
                } catch (exception: java.lang.Exception) {
                    return error
                }
            }
        }
    }
}
