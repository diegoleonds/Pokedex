package com.example.pokedex.data.error.http

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class HttpErrorHandlerTest {
    val errorHandler = HttpErrorHandler()
    val mockkHttpException = mockk<HttpException>()
    val mockkIOException = mockk<IOException>()

    @Test
    fun shouldReturnNotFoundWhenReceiveSameError() {
        val expectedError = HttpErrorMessage.NotFound
        every { mockkHttpException.code() } returns HttpURLConnection.HTTP_NOT_FOUND

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldNotReturnNotFoundWhenReceiveDiferentError() {
        val expectedError = HttpErrorMessage.NotFound
        every { mockkHttpException.code() } returns -1

        assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldReturnForbiddenWhenReceiveSameError() {
        val expectedError = HttpErrorMessage.AccessDenied
        every { mockkHttpException.code() } returns HttpURLConnection.HTTP_FORBIDDEN

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldNotReturnForbiddenWhenReceiveDiferentError() {
        val expectedError = HttpErrorMessage.AccessDenied
        every { mockkHttpException.code() } returns -1

        assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldReturnUnavailableWhenReceiveSameError() {
        val expectedError = HttpErrorMessage.ServiceUnavailable
        every { mockkHttpException.code() } returns HttpURLConnection.HTTP_UNAVAILABLE

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldNotReturnUnavailableWhenReceiveDiferentError() {
        val expectedError = HttpErrorMessage.ServiceUnavailable
        every { mockkHttpException.code() } returns -1

        assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldReturnUnknownWhenErrorIsNotDefined() {
        val expectedError = HttpErrorMessage.Unknown
        every { mockkHttpException.code() } returns -1

        assertEquals(expectedError, errorHandler.getError(mockkHttpException))
    }

    @Test
    fun shouldReturnNetworkErrorWhenReceiveIOException() {
        val expectedError = HttpErrorMessage.Network
        assertEquals(expectedError, errorHandler.getError(mockkIOException))
    }

    @Test
    fun shouldNotReturnNetworkErrorWhenNotReceiveIOException() {
        val expectedError = HttpErrorMessage.Network
        every { mockkHttpException.code() } returns -1

        assertNotEquals(expectedError, errorHandler.getError(mockkHttpException))
    }
}