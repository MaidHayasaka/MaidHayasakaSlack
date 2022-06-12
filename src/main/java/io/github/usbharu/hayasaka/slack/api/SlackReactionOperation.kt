package io.github.usbharu.hayasaka.slack.api

import io.github.usbharu.hayasaka.api.AddReaction
import io.github.usbharu.hayasaka.api.ReactionAddedResponse
import io.github.usbharu.hayasaka.api.ReactionOperation
import io.github.usbharu.hayasaka.model.Message
import io.github.usbharu.hayasaka.model.Reaction

class SlackReactionOperation : ReactionOperation {
    override fun addReaction(addReaction: AddReaction): ReactionAddedResponse {
        TODO()
    }

    override fun addReaction(reaction: Reaction, message: Message): ReactionAddedResponse {
        TODO()
    }
}
