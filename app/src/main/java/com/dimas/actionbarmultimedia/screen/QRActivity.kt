package com.dimas.actionbarmultimedia.screen

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dimas.actionbarmultimedia.databinding.ActivityQrBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder


class QRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeListener()
    }

    private fun initializeListener() {
        binding.buttonSubmit.setOnClickListener {
            val text = binding.edtQr.text.toString()
            if (text.isNotEmpty()) generateQR(text)
        }
    }

    private fun generateQR(text: String) {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.encodeBitmap(text, BarcodeFormat.QR_CODE, 400, 400)
            binding.imageDisplay.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}