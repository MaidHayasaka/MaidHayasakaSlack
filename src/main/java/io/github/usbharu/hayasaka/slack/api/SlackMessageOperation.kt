package io.github.usbharu.hayasaka.slack.api

import io.github.usbharu.hayasaka.api.MessageOperation
import io.github.usbharu.hayasaka.api.PostMessage
import io.github.usbharu.hayasaka.api.PostMessageResponse
import io.github.usbharu.hayasaka.model.Channel
import io.github.usbharu.hayasaka.model.Message
import io.github.usbharu.hayasaka.model.MessageType

class SlackMessageOperation : MessageOperation {
    override fun postMessage(postMessage: PostMessage): PostMessageResponse {
        TODO()
    }

    override fun postMessage(
        s: String, messageType: MessageType, channel: Channel,
        message: Message?
    ): PostMessageResponse {
        TODO()
    }
}
