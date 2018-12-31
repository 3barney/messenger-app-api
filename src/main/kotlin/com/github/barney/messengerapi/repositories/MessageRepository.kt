package com.github.barney.messengerapi.repositories

import com.github.barney.messengerapi.models.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {

    fun findByConversationId(conversationId: Long): List<Message>
}