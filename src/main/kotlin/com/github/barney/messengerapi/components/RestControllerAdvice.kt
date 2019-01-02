package com.github.barney.messengerapi.components

import com.github.barney.messengerapi.constants.ErrorResponse
import com.github.barney.messengerapi.constants.ResponseConstants
import com.github.barney.messengerapi.exceptions.UserDeactivatedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

// define exception handlers for errors that can happen anywhere within the server over the course of runtime
// More Like our catch All errors
@ControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(UserDeactivatedException::class)
    fun userDeativated(userDeactivatedException: UserDeactivatedException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.value, userDeactivatedException.message)

        // Return an Http 403 unauthorized error
        return ResponseEntity(res, HttpStatus.UNAUTHORIZED)
    }
}