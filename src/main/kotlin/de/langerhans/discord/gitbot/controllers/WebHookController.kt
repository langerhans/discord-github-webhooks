package de.langerhans.discord.gitbot.controllers

import de.langerhans.discord.gitbot.GithubConfig
import de.langerhans.discord.gitbot.handlers.IssueHandler
import de.langerhans.discord.gitbot.handlers.PrHandler
import de.langerhans.discord.gitbot.util.SignatureVerificationFailedException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SignatureException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Created by maxke on 07.08.2016.
 * Main entry for the webhooks
 */

@RestController
open class WebHookController {

    @Autowired lateinit var config: GithubConfig
    @Autowired lateinit var issueHandler: IssueHandler
    @Autowired lateinit var prHandler: PrHandler

    private val logger = LoggerFactory.getLogger(WebHookController::class.java)
    private val HMAC_SHA1_ALGORITHM = "HmacSHA1"

    @RequestMapping("/webhook", method = arrayOf(RequestMethod.POST))
    fun handle(
            @RequestHeader("X-GitHub-Event") event: String,
            @RequestHeader("X-Hub-Signature") signature: String,
            @RequestHeader("X-GitHub-Delivery") delivery: String,
            @RequestBody payload: String
    ) {

        if (signature.isNullOrEmpty()) {
            logger.error("No signature in request, dropping!")
            throw SignatureVerificationFailedException()
        }

        try {
            val expected = "sha1=${calcualteSignature(payload, config.secret)}"
            if (expected != signature) {
                logger.error("Signature in request wrong, dropping!")
                throw SignatureVerificationFailedException()
            }
        } catch (e: Exception) {
            logger.error("Signature could not be calculated, dropping!")
            throw SignatureVerificationFailedException()
        }

        logger.debug("Received webhook with event type $event")
        when (event) {
            "ping" -> logger.info("WebHook registered!")
            "issues" -> issueHandler.handle(payload)
            "pull_request" -> prHandler.handle(payload)
        }
    }

    @Throws(SignatureException::class, NoSuchAlgorithmException::class, InvalidKeyException::class)
    private fun calcualteSignature(data: String, key: String): String {
        val signingKey = SecretKeySpec(key.toByteArray(), HMAC_SHA1_ALGORITHM)
        val mac = Mac.getInstance(HMAC_SHA1_ALGORITHM)
        mac.init(signingKey)
        return toHexString(mac.doFinal(data.toByteArray()))
    }

    private fun toHexString(bytes: ByteArray): String {
        val formatter = Formatter()
        bytes.forEach {
            formatter.format("%02x", it)
        }
        return formatter.toString()
    }

}