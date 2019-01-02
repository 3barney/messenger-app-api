package com.github.barney.messengerapi.service

import com.github.barney.messengerapi.models.Message
import com.github.barney.messengerapi.models.User

interface MessageService {

    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}