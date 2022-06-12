package io.github.usbharu.hayasaka.slack

import io.github.usbharu.hayasaka.api.MessageOperation
import io.github.usbharu.hayasaka.api.ReactionOperation
import io.github.usbharu.hayasaka.core.service.Service

class SlackService : Service() {
    override val messageOperation: MessageOperation
        get() {
            TODO()
        }
    override val reactionOperation: ReactionOperation
        get() {
            TODO()
        }

    override fun init() {}
    override fun start() {}
    override fun stop() {}
}
