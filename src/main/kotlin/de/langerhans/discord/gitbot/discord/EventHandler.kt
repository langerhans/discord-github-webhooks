@file:Suppress("unused")

package de.langerhans.discord.gitbot.discord

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Component
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent
import sx.blah.discord.handle.impl.events.ReadyEvent
import sx.blah.discord.handle.obj.Status
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer

/**
 * Created by maxke on 07.08.2016.
 * Discord event handler
 */

@Component
open class EventHandler: DisposableBean {

    private val log = LoggerFactory.getLogger(EventHandler::class.java)

    fun setup() {
        // stub for now until we have commands
    }

    @EventSubscriber fun onReadyEvent(e: ReadyEvent) {
        log.info("Bot is ready")
        if (reconnectTimer != null) {
            (reconnectTimer as Timer).cancel()
        }
        e.client.changePresence(false)
        e.client.changeStatus(Status.game("Pokemon GO"))
    }

    private val RECONNECT_DELAY = TimeUnit.SECONDS.toMillis(15)
    private var reconnectTimer: Timer? = null

    @EventSubscriber fun onDiscordDisconnectedEvent(e: DiscordDisconnectedEvent) {
        log.info("Got disconnected with reason ${e.reason.name}")
        reconnectTimer = timer("reconnect", false, RECONNECT_DELAY, RECONNECT_DELAY, {
            if (!e.client.isReady) {
                e.client.login()
            } else {
                reconnectTimer?.cancel()
            }
        })
    }

    override fun destroy() {
        reconnectTimer?.cancel()
    }

}