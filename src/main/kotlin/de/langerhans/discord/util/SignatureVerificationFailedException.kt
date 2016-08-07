package de.langerhans.discord.util

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by maxke on 07.08.2016.
 * Marker exception for wrong signatures
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
class SignatureVerificationFailedException : Exception()