package io.github.usbharu.hayasaka.slack.model

import io.github.usbharu.hayasaka.model.Channel
import io.github.usbharu.hayasaka.model.Message
import io.github.usbharu.hayasaka.model.MessageType
import io.github.usbharu.hayasaka.model.User

class SlackMessage(
    message: String,
    val timeStamp: String,
    sender: User,
    messageType: MessageType,
    channel: Channel
) :
    Message(message, sender, messageType, channel) {
}
