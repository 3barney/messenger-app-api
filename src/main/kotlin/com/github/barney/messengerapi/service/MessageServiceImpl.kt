package com.github.barney.messengerapi.service

import com.github.barney.messengerapi.models.Conversation
import com.github.barney.messengerapi.models.Message
import com.github.barney.messengerapi.models.User
import com.github.barney.messengerapi.repositories.ConversationRepository
import com.github.barney.messengerapi.repositories.MessageRepository
import com.github.barney.messengerapi.repositories.UserRepository

class MessageServiceImpl(
        val repository: MessageRepository,
        val conversationRepository: ConversationRepository,
        val conversationService: ConversationService,
        val userRepository: UserRepository): MessageService {

    @Throws(MessageEmptyException::class, MessageRecipientInvalidException::class)
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)
        if (optional.isPresent) {
            val recipient = optional.get()

            if (!messageText.isEmpty()) {
                val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                    conversationService.getConversation(sender, recipient)
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message = Message(sender, recipient, messageText, conversation)
                repository.save(message)
                return message
            }
        } else {
            throw MessageRecipientInvalidException("Recipient id $recipientId is invalid")
        }
        throw MessageEmptyException()
    }

}