package de.langerhans.discord.models

/**
 * Created by maxke on 07.08.2016.
 */

data class PrCommit(
        val label: String,
        val ref: String,
        val sha: String,
        val user: UserProfile,
        val repo: Repository
)