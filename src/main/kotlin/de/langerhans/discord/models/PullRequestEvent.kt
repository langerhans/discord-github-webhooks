package de.langerhans.discord.models

import com.google.gson.annotations.SerializedName

/**
 * Created by maxke on 07.08.2016.
 */

data class PullRequestEvent(
        val action: String,
        val number: Long,
        @SerializedName("pull_request") val pullRequest: PullRequest,
        val repository: Repository,
        val sender: UserProfile
)