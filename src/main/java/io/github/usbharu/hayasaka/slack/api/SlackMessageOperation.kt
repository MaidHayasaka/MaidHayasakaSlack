package io.github.usbharu.hayasaka.slack.api

import io.github.usbharu.hayasaka.api.MessageOperation
import io.github.usbharu.hayasaka.api.PostMessage
import io.github.usbharu.hayasaka.api.PostMessageResponse
import io.github.usbharu.hayasaka.model.Channel
import io.github.usbharu.hayasaka.model.Message
import io.github.usbharu.hayasaka.model.MessageType
import io.github.usbharu.hayasaka.slack.model.SlackMessage
import io.github.usbharu.hayasaka.slack.model.SlackUser
import io.github.usbharu.hayasaka.util.SlackUtil

class SlackMessageOperation : MessageOperation {
    override fun postMessage(postMessage: PostMessage): PostMessageResponse {
        return postMessage(
            postMessage.message,
            postMessage.messageType,
            postMessage.channel,
            postMessage.replyTo
        )
    }

    override fun postMessage(
        message: String, messageType: MessageType, channel: Channel,
        replyTo: Message?
    ): PostMessageResponse {
        if (replyTo is SlackMessage) {
            SlackUtil.chatPostMessage(message, channel.toString(), replyTo.message)
        } else {
            SlackUtil.chatPostMessage(message, channel.toString())
        }
        return PostMessageResponse(
            SlackMessage(
                message,
                SlackUser("Hayasaka"),
                messageType,
                channel
            )
        )
    }
}
