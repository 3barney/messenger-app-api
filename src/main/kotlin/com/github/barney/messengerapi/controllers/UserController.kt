package com.github.barney.messengerapi.controllers

import com.github.barney.messengerapi.models.User
import com.github.barney.messengerapi.repositories.UserRepository
import com.github.barney.messengerapi.service.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userServiceImpl: UserServiceImpl, val userRepository: UserRepository) {

    @PostMapping
    @RequestMapping("/registrations")
    fun create(@Validated @RequestBody userDetails: User): ResponseEntity<User> {
        val user = userServiceImpl.attemptRegistration(userDetails)
        return ResponseEntity.ok(user)
    }

    @GetMapping
    fun returnItems(): String {
        return "Hello World"
    }
}