package de.langerhans.discord.gitbot.handlers

import de.langerhans.discord.gitbot.models.PullRequestEvent
import org.springframework.stereotype.Component

/**
 * Created by maxke on 07.08.2016.
 * handles new/closed PRs
 */

@Component
open class PrHandler: AbstractHandler() {

    override fun handle(payload: String) {
        val event = gson.fromJson(payload, PullRequestEvent::class.java)

        if (event.action !in arrayOf("opened", "closed", "reopened")) {
            return
        }

        val message = "PR #${event.pullRequest.number} ${event.action} by ${event.sender.login}: ${event.pullRequest.title}\n${event.pullRequest.htmlUrl}"
        client.getChannelByID(config.targetChannel).sendMessage(message)
    }
}