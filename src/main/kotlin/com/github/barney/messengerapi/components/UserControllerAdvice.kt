package com.github.barney.messengerapi.components

import com.github.barney.messengerapi.constants.ErrorResponse
import com.github.barney.messengerapi.constants.ResponseConstants
import com.github.barney.messengerapi.exceptions.InvalidUserIdException
import com.github.barney.messengerapi.exceptions.UserStatusEmptyException
import com.github.barney.messengerapi.exceptions.UsernameUnavailableException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

// Controller Advice Handle errors that are thrown within a spring application
// Handle All users controller Exceptions
@ControllerAdvice
class UserControllerAdvice {

    @ExceptionHandler(UsernameUnavailableException::class)
    fun usernameUnavailable(usernameUnavailableException: UsernameUnavailableException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.USERNAME_UNAVAILABLE.value, usernameUnavailableException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }

    @ExceptionHandler(InvalidUserIdException::class)
    fun invalidId(invalidUserIdException: InvalidUserIdException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.INVALID_USER_ID.value, invalidUserIdException.message)
        return ResponseEntity.badRequest().body(res)
    }

    @ExceptionHandler(UserStatusEmptyException::class)
    fun statusEmpty(userStatusEmptyException: UserStatusEmptyException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse(ResponseConstants.EMPTY_STATUS.value, userStatusEmptyException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}
