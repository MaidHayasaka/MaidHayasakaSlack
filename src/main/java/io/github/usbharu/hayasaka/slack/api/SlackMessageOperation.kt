package io.github.usbharu.hayasaka.slack.api

import com.slack.api.methods.response.chat.ChatPostMessageResponse
import io.github.usbharu.hayasaka.api.MessageOperation
import io.github.usbharu.hayasaka.api.PostMessage
import io.github.usbharu.hayasaka.api.PostMessageResponse
import io.github.usbharu.hayasaka.model.Channel
import io.github.usbharu.hayasaka.model.ChannelType
import io.github.usbharu.hayasaka.model.Message
import io.github.usbharu.hayasaka.model.MessageType
import io.github.usbharu.hayasaka.slack.model.SlackChannel
import io.github.usbharu.hayasaka.slack.model.SlackMessage
import io.github.usbharu.hayasaka.slack.model.SlackUser
import io.github.usbharu.hayasaka.util.SlackUtil

class SlackMessageOperation : MessageOperation {
    override fun postMessage(postMessage: PostMessage): PostMessageResponse {
        return postMessage(
            postMessage.message, postMessage.messageType, postMessage.channel, postMessage.replyTo
        )
    }

    override fun postMessage(
        message: String, messageType: MessageType, channel: Channel, replyTo: Message?
    ): PostMessageResponse {
        var response: ChatPostMessageResponse
        if (replyTo is SlackMessage) {
            response = SlackUtil.chatPostMessage(message, channel.toString(), replyTo.timeStamp)
        } else {
            response = SlackUtil.chatPostMessage(message, channel.toString())
        }
        return PostMessageResponse(
            SlackMessage(
                response.message.text,
                response.message.ts,
                SlackUser(response.message.username),
                messageType,
                SlackChannel(response.message.channel, ChannelType.PUBLIC)
            )
        )
    }
}
