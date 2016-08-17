package de.langerhans.discord.gitbot.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by maxke on 07.08.2016.
 */

data class PullRequest(
        val url: String,
        val id: Long,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("diff_url") val diffUrl: String,
        @SerializedName("patch_url") val patchUrl: String,
        @SerializedName("issue_url") val issueUrl: String,
        val number: Long,
        val state: String,
        val locked: Boolean,
        val title: String,
        val user: UserProfile,
        val body: String,
        @SerializedName("created_at") val createdAt: Date,
        @SerializedName("updated_at") val updatedAt: Date,
        @SerializedName("closed_at") val closedAt: Date,
        @SerializedName("merged_at") val mergedAt: Date,
        @SerializedName("merge_commit_sha") val mergeCommitSha: String,
        val assignee: UserProfile,
        val milestone: Milestone,
        @SerializedName("commits_url") val commitsUrl: String,
        @SerializedName("review_comments_url") val reviewCommentsUrl: String,
        @SerializedName("comments_url") val commentsUrl: String,
        @SerializedName("statuses_url") val statusesUrl: String,
        val head: PrCommit,
        val base: PrCommit,
        @SerializedName("_links") val links: PrLinks,
        val merged: Boolean,
        val mergable: Boolean,
        @SerializedName("mergable_state") val mergableState: String,
        @SerializedName("merged_by") val mergedBy: UserProfile, // Assumption
        val comments: Long,
        @SerializedName("review_comments") val reviewComments: Long,
        val commits: Long,
        val additions: Long,
        val deletions: Long,
        @SerializedName("changed_files") val changedFiles: Long,
        val repository: Repository,
        val sender: UserProfile
)