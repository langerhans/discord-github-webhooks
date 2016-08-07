package de.langerhans.discord.handlers

import de.langerhans.discord.models.IssuesEvent
import org.springframework.stereotype.Component

/**
 * Created by maxke on 07.08.2016.
 * handles new/closed issues
 */

@Component
open class IssueHandler: AbstractHandler() {

    override fun handle(payload: String) {
        val event = gson.fromJson(payload, IssuesEvent::class.java)

        if (event.action !in arrayOf("opened", "closed", "reopened")) {
            return
        }

        val message = "Issue #${event.issue.number} ${event.action} by ${event.sender.login}: ${event.issue.title}\n${event.issue.htmlUrl}"
        client.getChannelByID(config.targetChannel).sendMessage(message)
    }
}