package com.abcstudios.camilo_zavala_herpm13051_s7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Simula la validación de las credenciales. En un caso real, deberías validar contra tus registros de usuario.
            if (validateCredentials(username, password)) {
                AppPreferences.username = username
                AppPreferences.isUserLoggedIn = true

                startActivity(Intent(this, HolidayActivity::class.java))
                finish() // Finalizar LoginActivity para sacarla de la pila de actividades
            } else {
                // Aquí puedes manejar un error de inicio de sesión, por ejemplo, mostrando un Toast o un Snackbar
                // Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        // Implementa tu lógica de validación aquí. Este es solo un ejemplo básico.
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
