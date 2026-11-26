package com.nyp.scaffoldingapp.data
import android.util.Log
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
        Log.d("DEBUG", "Opening connection")
        val connection = (URL(ENDPOINT).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
        }
        try {
            Log.d("DEBUG", "Reading response")
            val body = connection.inputStream.bufferedReader().use { it.readText() }
            Log.d("DEBUG", "Response received")
            parse(body)
        } finally {
            connection.disconnect()
        }
    }

    private fun parse(body: String): User {
        val json = JSONObject(body)
        return User(
            id = json.getInt("id"),
            name = json.getString("name"),
            email = json.getString("email"),
            city = json.getJSONObject("address").getString("city")
        )
    }
}