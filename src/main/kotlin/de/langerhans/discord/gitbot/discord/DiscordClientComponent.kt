package de.langerhans.discord.gitbot.discord

import de.langerhans.discord.gitbot.BotConfig
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.modules.Configuration

/**
 * Created by maxke on 07.08.2016.
 * Main client provider
 */
@Component
open class DiscordClientComponent : DisposableBean {
    private lateinit var client: IDiscordClient

    @Autowired lateinit var config: BotConfig
    @Autowired lateinit var eventHandler: EventHandler

    @Bean
    open fun discordClient(): IDiscordClient {
        Configuration.AUTOMATICALLY_ENABLE_MODULES = false
        Configuration.LOAD_EXTERNAL_MODULES = false

        client = ClientBuilder().withToken(config.discordtoken).setMaxReconnectAttempts(25).login()
        eventHandler.setup()
        client.dispatcher.registerListener(eventHandler)
        return client
    }

    override fun destroy() {
        client.logout()
    }
}