package com.example.pokedex.data.error

import com.example.pokedex.data.error.http.HttpErrorMessage

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorMessage
}