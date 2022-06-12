package io.github.usbharu.hayasaka.slack.event

import io.github.usbharu.hayasaka.event.ReactionEvent
import io.github.usbharu.hayasaka.event.ReactionEventType
import io.github.usbharu.hayasaka.model.Reaction

class SlackReactionEvent(source: Any, reaction: Reaction, reactionEventType: ReactionEventType) :
    ReactionEvent(source, reaction, reactionEventType) {
}
