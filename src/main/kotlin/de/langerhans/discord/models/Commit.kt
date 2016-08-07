package de.langerhans.discord.models

import com.google.gson.annotations.SerializedName

/**
 * Created by maxke on 07.08.2016.
 */

data class Commit(
        val id: String,
        @SerializedName("tree_id") val treeId: String,
        val distinct: Boolean,
        val message: String,
        val timestamp: String,
        val url: String,
        val author: User,
        val commiter: User,
        val added: List<String>,
        val removed: List<String>,
        val modified: List<String>
)