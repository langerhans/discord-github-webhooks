package de.langerhans.discord.models

/**
 * Created by maxke on 07.08.2016.
 */

data class IssuesEvent(
        val action: String,
        val issue: Issue,
        val repository: Repository,
        val sender: UserProfile
)