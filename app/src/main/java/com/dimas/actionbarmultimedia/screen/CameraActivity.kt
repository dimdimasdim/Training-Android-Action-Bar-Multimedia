package com.dimas.actionbarmultimedia.screen

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dimas.actionbarmultimedia.databinding.ActivityCameraBinding
import com.dimas.actionbarmultimedia.utils.getFile
import com.dimas.actionbarmultimedia.utils.getTempFileUri


class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    private val mPermissionResult = registerForActivityResult<String, Boolean>(
        ActivityResultContracts.RequestPermission()
    ) { result: Boolean ->
        if (result) {
            doImagePickFromCamera()
        } else {
            Log.d("Camera Picker", "No media selected")
        }
    }

    var takeImage = registerForActivityResult<Uri, Boolean>(
        ActivityResultContracts.TakePicture(), object : ActivityResultCallback<Boolean?> {
            override fun onActivityResult(result: Boolean?) {
                if (result == true) {
                    tempCaptureImageUri?.let { setTempFile(it) }
                }
            }
        })

    private var tempCaptureImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeListener()
    }

    private fun initializeListener() {
        binding.buttonPick.setOnClickListener {
            mPermissionResult.launch(Manifest.permission.CAMERA)
        }
    }

    private fun doImagePickFromCamera() {
        val tempFileName = System.currentTimeMillis().toString()
        tempCaptureImageUri = getTempFileUri(this, tempFileName)
        takeImage.launch(tempCaptureImageUri)
    }

    private fun setTempFile(uri: Uri) {
        val file = getFile(this, uri)
        if (file?.exists() == true) {
            binding.imageDisplay.setImageURI(uri)
        }
    }


}