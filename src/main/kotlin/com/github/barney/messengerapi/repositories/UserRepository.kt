package com.github.barney.messengerapi.repositories

import com.github.barney.messengerapi.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun findByPhoneNumber(phoneNumber: String): User?
}