package de.langerhans.discord.gitbot

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by maxke on 07.08.2016.
 * Main config
 */

@Component
@ConfigurationProperties(prefix = "bot")
open class BotConfig() {
    lateinit var discordtoken: String
    lateinit var targetChannel: String
}

@Component
@ConfigurationProperties(prefix = "github")
open class GithubConfig() {
    lateinit var secret: String
}