package com.github.barney.messengerapi.models

import com.github.barney.messengerapi.listeners.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name="`user`", schema = "")
@EntityListeners(UserListener::class)
class User(

    @Column(unique = true)
    @Size(min = 2)
    var username: String = "",

    @Size(min = 8, max = 15)
    var phoneNumber: String = "",

    @Size(min = 60, max = 60)
    var password: String = "",

    var status: String = "available",

    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    var accountStatus: String = "activated",

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
) {
    // Collection of Sent Messages
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessages: Collection<Message>? = null

    // Collection of Recieved Messages
    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null

}