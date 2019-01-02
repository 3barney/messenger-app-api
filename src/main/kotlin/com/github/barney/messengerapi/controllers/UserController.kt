package com.github.barney.messengerapi.controllers

import com.github.barney.messengerapi.models.User
import com.github.barney.messengerapi.repositories.UserRepository
import com.github.barney.messengerapi.service.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userServiceImpl: UserServiceImpl, val userRepository: UserRepository) {

    @PostMapping
    @RequestMapping("/registrations")
    fun create(@Validated @RequestBody userDetails: User): ResponseEntity<User> {
        val user = userServiceImpl.attemptRegistration(userDetails)
        return ResponseEntity.ok(user)
    }
}