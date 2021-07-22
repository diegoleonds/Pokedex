package com.example.pokedex.data.error.http

import com.example.pokedex.data.error.ErrorHandler
import com.example.pokedex.data.error.ErrorMessage
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class HttpErrorHandler(): ErrorHandler {
    override fun getError(throwable: Throwable): ErrorMessage {
        return when(throwable) {
            is IOException -> HttpErrorMessage.Network
            is HttpException -> {
                when(throwable.code()) {
                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> HttpErrorMessage.NotFound

                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> HttpErrorMessage.AccessDenied

                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> HttpErrorMessage.ServiceUnavailable

                    // all the others will be treated as unknown error
                    else -> HttpErrorMessage.Unknown
                }
            }
            else -> HttpErrorMessage.Unknown
        }
    }
}