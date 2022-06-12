package io.github.usbharu.hayasaka.slack.model

import io.github.usbharu.hayasaka.model.Message
import io.github.usbharu.hayasaka.model.Reaction
import io.github.usbharu.hayasaka.model.User

class SlackReaction(name: String, sender: User, target: Message) : Reaction(name, sender, target) {
}
