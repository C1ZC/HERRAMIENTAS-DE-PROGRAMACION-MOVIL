package com.cizc.camilo_zavala_herpm13051_s5

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView

class CameraActivity : AppCompatActivity() {

    private lateinit var imageCaptureUri: Uri
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var showPictureButton: Button
    private lateinit var capturedImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        capturedImageView = findViewById(R.id.capturedImageView)
        showPictureButton = findViewById(R.id.showPictureButton)

        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                // Hacer visible el botón de mostrar foto
                showPictureButton.visibility = Button.VISIBLE
            } else {
                // Manejar error o cancelación
            }
        }

        val takePictureButton: Button = findViewById(R.id.takePictureButton)
        takePictureButton.setOnClickListener {
            imageCaptureUri = createImageUri() ?: return@setOnClickListener
            takePictureLauncher.launch(imageCaptureUri)
        }

        showPictureButton.setOnClickListener {
            capturedImageView.setImageURI(imageCaptureUri)
            // Opcional: Puedes agregar una lógica para ajustar la visibilidad o el layout del ImageView según necesites
        }
    }

    private fun createImageUri(): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "captured_image_${System.currentTimeMillis()}")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            // Añadir más metadatos si es necesario
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
}
