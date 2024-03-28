package com.abcstudios.camilo_zavala_herpm13051_s7

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.Button
import android.content.Intent

class HolidayActivity : AppCompatActivity() {

    private lateinit var holidayListView: ListView
    private lateinit var welcomeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday)

        holidayListView = findViewById(R.id.holidayListView)
        welcomeTextView = findViewById(R.id.welcomeTextView)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        welcomeTextView.text = "Bienvenido: ${AppPreferences.username}"

        FetchHolidaysTask().execute("2022") // Puedes cambiar el año dinámicamente según sea necesario

        logoutButton.setOnClickListener {
            // Limpiar las preferencias de usuario y cerrar sesión
            AppPreferences.isUserLoggedIn = false
            AppPreferences.username = ""

            // Redirigir al usuario a LoginActivity
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }

    private inner class FetchHolidaysTask : AsyncTask<String, Void, List<Holiday>>() {
        override fun doInBackground(vararg params: String): List<Holiday> {
            val year = params[0]
            val json = NetworkUtils.fetchHolidays(year)
            val type = object : TypeToken<List<Holiday>>() {}.type
            return Gson().fromJson(json, type)
        }

        override fun onPostExecute(result: List<Holiday>) {
            super.onPostExecute(result)
            // Set the adapter with the result
            holidayListView.adapter = HolidayAdapter(this@HolidayActivity, result)
        }
    }
}