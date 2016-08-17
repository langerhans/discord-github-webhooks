package de.langerhans.discord.gitbot.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by maxke on 17.08.2016.
 * Milestone model
 */
data class Milestone(
        val url: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("labels_url") val labelsUrl: String,
        val id: Long,
        val number: Long,
        val state: String,
        val title: String,
        val description: String,
        val creator: UserProfile,
        @SerializedName("open_issues") val openIssues: Long,
        @SerializedName("close_issues") val closedIssues: Long,
        @SerializedName("created_at") val createdAt: Date,
        @SerializedName("updated_at") val updatedAt: Date,
        @SerializedName("closed_at") val closedAt: Date,
        @SerializedName("due_on") val dueOn: Date
)