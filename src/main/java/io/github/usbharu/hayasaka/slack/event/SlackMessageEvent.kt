package io.github.usbharu.hayasaka.slack.event

import io.github.usbharu.hayasaka.event.MessageEvent
import io.github.usbharu.hayasaka.event.MessageEventType
import io.github.usbharu.hayasaka.model.Message

class SlackMessageEvent(
    source: Any,
    messageEventType: MessageEventType,
    message: Message,
    oldMessage: Message?
) : MessageEvent(source, messageEventType, message, oldMessage) {
}
