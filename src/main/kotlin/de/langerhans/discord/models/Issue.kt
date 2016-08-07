package de.langerhans.discord.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by maxke on 07.08.2016.
 */

data class Issue(
        val url: String,
        @SerializedName("labels_url") val labelsUrl: String,
        @SerializedName("comments_url") val commentsUrl: String,
        @SerializedName("events_url") val eventsUrl: String,
        @SerializedName("html_url") val htmlUrl: String,
        val id: Long,
        val number: Long,
        val title: String,
        val user: UserProfile,
        val labels: List<Label>,
        val state: String,
        val locked: Boolean,
        val assignee: UserProfile,
        val milestone: String,
        val comments: Long,
        @SerializedName("created_at") val createdAt: Date,
        @SerializedName("updated_at") val updatedAt: Date,
        @SerializedName("closed_at") val closedAt: Date,
        val body: String
)