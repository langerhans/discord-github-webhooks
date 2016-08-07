package de.langerhans.discord.gitbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class GitbotApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(GitbotApplication::class.java, *args)
        }
    }

}


