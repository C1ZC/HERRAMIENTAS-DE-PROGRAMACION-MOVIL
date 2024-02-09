package com.cizc.camilo_zavala_herpm13051_s5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_DURATION = 5000L // 5 segundos
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encuentra el VideoView por ID
        val videoView = findViewById<VideoView>(R.id.videoView)

        // Establece la ubicación del archivo de video
        val videoPath = "android.resource://$packageName/${R.raw.animacion_retail1}"
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        // Inicia el video
        videoView.start()

        // Inicializa el Handler y el Runnable
        handler = Handler()
        runnable = Runnable {
            // Intent para iniciar MenuActivity después de la animación
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Finaliza esta actividad para que no se pueda volver a ella
        }

        // Programa el Runnable para ejecutarse después del SPLASH_SCREEN_DURATION
        handler.postDelayed(runnable, SPLASH_SCREEN_DURATION)

        // Encuentra el botón por ID y configura el OnClickListener
        val buttonMenu = findViewById<Button>(R.id.btnMenu) // Asegúrate de que el ID sea correcto
        buttonMenu.setOnClickListener {
            // Cancela el Runnable para que no se ejecute después de presionar el botón
            handler.removeCallbacks(runnable)

            // Navega directamente a MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Finaliza esta actividad inmediatamente
        }
    }
}
