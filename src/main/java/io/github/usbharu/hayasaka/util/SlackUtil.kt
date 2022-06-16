package io.github.usbharu.hayasaka.util

import com.slack.api.Slack
import com.slack.api.methods.request.auth.AuthTestRequest
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.request.conversations.ConversationsHistoryRequest
import com.slack.api.methods.request.conversations.ConversationsMembersRequest
import com.slack.api.methods.request.conversations.ConversationsRepliesRequest
import com.slack.api.methods.response.chat.ChatPostMessageResponse
import com.slack.api.methods.response.conversations.ConversationsMembersResponse
import com.slack.api.model.Message

object SlackUtil {
    val slack: Slack = Slack.getInstance()
    val token: String = System.getenv("SLACK_BOT_TOKEN")
    fun isChannelMembers(channelId: String): Boolean {
        val conversationsMembers: ConversationsMembersResponse =
            slack.methods(token).conversationsMembers(
                ConversationsMembersRequest.builder().channel(channelId).build()
            )
        return conversationsMembers.members.contains(getBotUserId())
    }

    fun getBotUserId(): String {
        return slack.methods(token).authTest(AuthTestRequest.builder().build()).userId
    }

    fun getMessage(channelId: String, timeStamp: String): String {
        return slack.methods(token).conversationsHistory(
            ConversationsHistoryRequest.builder().channel(channelId).latest(timeStamp)
                .inclusive(true).limit(1).build()
        ).messages[0].text
    }

    fun getMessageWithReplies(channelId: String, timeStamp: String): String {
        val conversationsHistory =
            slack.methods(token).conversationsHistory(ConversationsHistoryRequest.builder().build())
        val messages = ArrayList<Message>()
        for (message in conversationsHistory.messages) {
            if (message.threadTs != null) {
                messages.add(message)
            } else if (message.ts.equals(timeStamp)) {
                return message.text
            }
        }
        for (message in messages) {
            val replies = slack.methods(token).conversationsReplies(
                ConversationsRepliesRequest.builder().ts(message.threadTs).channel(channelId)
                    .build()
            ).messages
            for (reply in replies) {
                if (reply.ts.equals(timeStamp)) {
                    return reply.text
                }
            }

        }
        throw java.lang.IllegalArgumentException("Message not found channel : $channelId timeStamp : $timeStamp")
    }

    fun chatPostMessage(
        text: String,
        channelId: String,
        timeStamp: String? = null
    ): ChatPostMessageResponse? {
        if (timeStamp == null) {
            return slack.methods(token).chatPostMessage(
                ChatPostMessageRequest.builder().channel(channelId).text(text).build()
            )
        }
        return slack.methods(token).chatPostMessage(
            ChatPostMessageRequest.builder().channel(channelId).text(text).threadTs(timeStamp)
                .build()
        )
    }


}
