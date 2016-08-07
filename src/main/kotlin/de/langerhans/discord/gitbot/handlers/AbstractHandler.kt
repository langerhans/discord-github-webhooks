package de.langerhans.discord.gitbot.handlers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import de.langerhans.discord.gitbot.BotConfig
import de.langerhans.discord.gitbot.util.GithubDateTypeAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import sx.blah.discord.api.IDiscordClient
import java.util.*

/**
 * Created by maxke on 07.08.2016.
 * Base handler class
 */

@Component
abstract class AbstractHandler {
    @Autowired protected lateinit var client: IDiscordClient
    @Autowired protected lateinit var config: BotConfig
    protected val gson: Gson = GsonBuilder().registerTypeAdapter(Date::class.java, GithubDateTypeAdapter()).create()

    abstract fun handle(payload: String)
}