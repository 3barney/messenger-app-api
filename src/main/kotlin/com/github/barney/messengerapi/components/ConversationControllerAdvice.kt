package com.github.barney.messengerapi.components

import com.github.barney.messengerapi.constants.ErrorResponse
import com.github.barney.messengerapi.exceptions.ConversationIdInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {

    @ExceptionHandler(ConversationIdInvalidException::class)
    fun conversationIdInvalidException(conversationIdInvalidException: ConversationIdInvalidException): ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationIdInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}
