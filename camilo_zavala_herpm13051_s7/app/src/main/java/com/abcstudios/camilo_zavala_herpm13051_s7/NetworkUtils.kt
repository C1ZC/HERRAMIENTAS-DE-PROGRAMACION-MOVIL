package com.abcstudios.camilo_zavala_herpm13051_s7

import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {
    fun fetchHolidays(year: String): String {
        val url = URL("https://apis.digital.gob.cl/fl/feriados/$year")
        val connection = url.openConnection() as HttpURLConnection
        return try {
            connection.connect()
            connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
        } finally {
            connection.disconnect()
        }
    }
}