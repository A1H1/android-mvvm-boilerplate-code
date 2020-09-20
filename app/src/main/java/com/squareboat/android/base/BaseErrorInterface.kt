package com.squareboat.android.base

import com.google.gson.Gson
import com.squareboat.android.data.model.ErrorResponse
import com.squareboat.android.data.model.ValidationErrors
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

interface BaseErrorInterface {
    fun showError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                val response = throwable.response()

                val errorResponse =
                    Gson().fromJson(response?.errorBody()!!.string(), ErrorResponse::class.java)

                when (response.code()) {
                    401 -> onUnauthorizedAccess()
                    422 -> showValidationError(errorResponse.errors)
                    403 -> onForbiddenError(errorResponse)
                    404 -> onNotFound(errorResponse)
                    else -> showOtherError(errorResponse)
                }

            }
            is SocketTimeoutException -> showSocketTimeoutError()
            is IOException -> showInternetError()
        }
    }

    fun onUnauthorizedAccess() {
        showOtherError(ErrorResponse())
    }

    fun showInternetError() {
        showOtherError(ErrorResponse())
    }

    fun showSocketTimeoutError() {
        showOtherError(ErrorResponse())
    }

    fun onForbiddenError(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }

    fun onNotFound(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }

    fun showValidationError(validationErrors: ValidationErrors?) {
        showOtherError(ErrorResponse())
    }

    fun showOtherError(errorResponse: ErrorResponse)
}