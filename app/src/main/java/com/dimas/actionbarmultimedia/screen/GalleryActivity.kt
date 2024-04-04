package com.dimas.actionbarmultimedia.screen

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.appcompat.app.AppCompatActivity
import com.dimas.actionbarmultimedia.databinding.ActivityGaleryBinding
import com.dimas.actionbarmultimedia.utils.getFile


class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGaleryBinding

    var pickMedia = registerForActivityResult<PickVisualMediaRequest, Uri>(
        PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            setTempFile(uri)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    private val mPermissionStorageRead = registerForActivityResult<String, Boolean>(
        RequestPermission()
    ) { result: Boolean ->
        if (result) {
            doImagePickFromGallery()
        } else {
            Toast.makeText(this, "Enable Permission", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doImagePickFromGallery() {
        pickMedia.launch(
            PickVisualMediaRequest.Builder()
                .setMediaType(ImageOnly)
                .build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeListener()
    }

    private fun initializeListener() {
        binding.buttonPick.setOnClickListener {
            mPermissionStorageRead.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun setTempFile(uri: Uri) {
        val file = getFile(this, uri)
        if (file?.exists() == true) {
            binding.imageDisplay.setImageURI(uri)
            Toast.makeText(this, "Ulalala", Toast.LENGTH_SHORT).show()
        }
    }
}