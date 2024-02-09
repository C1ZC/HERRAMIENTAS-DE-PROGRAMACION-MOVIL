package com.cizc.camilo_zavala_herpm13051_s5

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AudioActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        // Inicializa el MediaPlayer con un archivo de audio de tus recursos
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_destacado)

        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start() // Inicia la reproducción del audio
            }
        }

        val stopButton: Button = findViewById(R.id.stopButton)
        stopButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause() // Pausa la reproducción del audio
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Libera los recursos del MediaPlayer
    }
}
