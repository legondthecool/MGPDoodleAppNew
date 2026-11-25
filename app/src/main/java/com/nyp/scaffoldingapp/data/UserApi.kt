package com.nyp.scaffoldingapp.data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * The data source: the only code in the app that talks to the network.
 * The ViewModel calls fetchUser(); it never sees a URL or a connection.
 */
object UserApi {

    // Replace with your own endpoint later, e.g. one you made on mockapi.dog
    private const val ENDPOINT = "https://jsonplaceholder.typicode.com/users/1"
    private const val TIMEOUT_MS = 10_000

    // Main-safe: switches to the IO thread itself, so it can be called from anywhere.
    suspend fun fetchUser(): User = withContext(Dispatchers.IO) {
        val connection = (URL(ENDPOINT).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
        }
        try {
            val body = connection.inputStream.bufferedReader().use { it.readText() }
            parse(body)
        } finally {
            connection.disconnect()
        }
    }

    private fun parse(body: String): User {
        // TODO 4: turn the text into a User with JSONObject(body).
        //         Read "id", "name", "email", and "city" from inside "address".
        error("TODO 4: parse() is not written yet")
    }
}