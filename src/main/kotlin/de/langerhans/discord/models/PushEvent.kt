package de.langerhans.discord.models

import com.google.gson.annotations.SerializedName

/**
 * Created by maxke on 07.08.2016.
 */

data class PushEvent(
        val ref: String,
        val before: String,
        val after: String,
        val created: Boolean,
        val forced: Boolean,
        @SerializedName("base_ref") val baseRef: String,
        val compare: String,
        val commits: List<Commit>,
        @SerializedName("head_commit") val headCommit: Commit,
        val repository: Repository,
        val pusher: User,
        val sender: UserProfile
)