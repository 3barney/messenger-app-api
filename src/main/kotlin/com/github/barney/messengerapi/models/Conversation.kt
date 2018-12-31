package com.github.barney.messengerapi.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
class Conversation(

        @ManyToOne(optional = false)
        @JoinColumn(name = "sender_id", referencedColumnName = "id")
        var sender: User? = null,

        @JoinColumn(name = "recipient_id", referencedColumnName = "id")
        var recipient: User? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @DateTimeFormat
        val createdAt: Date = Date.from(Instant.now())
) {
    // Many messages belong to a conversation
    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    private var messages: Collection<Message>? = null
}