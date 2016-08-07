package de.langerhans.discord.gitbot.models

import com.google.gson.annotations.SerializedName

/**
 * Created by maxke on 07.08.2016.
 */

data class PrLinks(
        val self: PrLink,
        val html: PrLink,
        val issue: PrLink,
        val comments: PrLink,
        @SerializedName("review_comments") val reviewComments: PrLink,
        @SerializedName("review_comment") val reviewComment: PrLink,
        val commits: PrLink,
        val statuses: PrLink
)