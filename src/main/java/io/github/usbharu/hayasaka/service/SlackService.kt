package io.github.usbharu.hayasaka.service

import com.slack.api.bolt.App
import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.model.event.MessageEvent
import com.slack.api.model.event.ReactionAddedEvent
import io.github.usbharu.hayasaka.api.MessageOperation
import io.github.usbharu.hayasaka.api.ReactionOperation
import io.github.usbharu.hayasaka.core.EventManager
import io.github.usbharu.hayasaka.core.service.Service
import io.github.usbharu.hayasaka.event.Event
import io.github.usbharu.hayasaka.event.EventType
import io.github.usbharu.hayasaka.model.ChannelType
import io.github.usbharu.hayasaka.model.MessageType
import io.github.usbharu.hayasaka.slack.api.SlackMessageOperation
import io.github.usbharu.hayasaka.slack.api.SlackReactionOperation
import io.github.usbharu.hayasaka.slack.model.SlackChannel
import io.github.usbharu.hayasaka.slack.model.SlackMessage
import io.github.usbharu.hayasaka.slack.model.SlackReaction
import io.github.usbharu.hayasaka.slack.model.SlackUser
import io.github.usbharu.hayasaka.util.SlackUtil

class SlackService : Service() {
    private lateinit var app: App;
    private lateinit var socketModeApp: SocketModeApp;
    override val messageOperation: MessageOperation
        get() {
            return SlackMessageOperation()
        }
    override val reactionOperation: ReactionOperation
        get() {
            return SlackReactionOperation()
        }

    override fun init() {
        app = App()
        app.event(MessageEvent::class.java) { req, context ->
            EventManager.event(
                Event(
                    EventType.MESSAGE_ADD,
                    SlackMessage(
                        req.event.text,
                        SlackUser(req.event.user),
                        MessageType.PUBLIC,
                        SlackChannel(req.event.channel, ChannelType.PUBLIC)
                    )
                )
            )
            context.ack()
        }
        app.event(ReactionAddedEvent::class.java) { req, context ->
            EventManager.event(
                Event(
                    EventType.REACTION_ADD,
                    SlackReaction(
                        req.event.reaction,
                        SlackUser(req.event.user),
                        SlackMessage(
                            SlackUtil.getMessageWithReplies(
                                req.event.item.channel,
                                req.event.eventTs
                            ),
                            SlackUser(req.event.user),
                            MessageType.PUBLIC,
                            SlackChannel(req.event.item.channel, ChannelType.PUBLIC)
                        )
                    )
                )
            )
            context.ack()
        }
    }

    override fun start() {
        socketModeApp = SocketModeApp(app)
        socketModeApp.start()
    }

    override fun stop() {
        socketModeApp.stop()
    }
}
