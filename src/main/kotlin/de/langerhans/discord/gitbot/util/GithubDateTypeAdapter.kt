package de.langerhans.discord.gitbot.util

import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Adapted from http://stackoverflow.com/a/37438619
 */

class GithubDateTypeAdapter : TypeAdapter<Date>() {
    private val iso8601Format = buildIso8601Format()

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Date? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        return deserializeToDate(reader.nextString())
    }

    @Synchronized private fun deserializeToDate(json: String): Date {
        try {
            return iso8601Format.parse(json)
        } catch (e: ParseException) {
            try {
                return Date(json.toLong())
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(json, e)
            }
        }
    }

    @Synchronized @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) {
            out.nullValue()
            return
        }
        val dateFormatAsString = iso8601Format.format(value)
        out.value(dateFormatAsString)
    }

    companion object {
        private fun buildIso8601Format(): DateFormat {
            val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            iso8601Format.timeZone = TimeZone.getTimeZone("UTC")
            return iso8601Format
        }
    }
}
