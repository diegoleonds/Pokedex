package com.example.pokedex.data.error.http

import com.example.pokedex.R
import com.example.pokedex.data.error.ErrorMessage

sealed class HttpErrorMessage(messageResource: Int): ErrorMessage(messageResource) {
    object Network : HttpErrorMessage(R.string.network_error)

    object NotFound : HttpErrorMessage(R.string.not_found_error)

    object AccessDenied : HttpErrorMessage(R.string.access_denied_error)

    object ServiceUnavailable : HttpErrorMessage(R.string.service_unavailable_error)

    object Unknown : HttpErrorMessage(R.string.unknown_error)
}