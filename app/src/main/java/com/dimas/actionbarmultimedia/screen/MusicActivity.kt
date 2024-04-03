package com.dimas.actionbarmultimedia.screen

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dimas.actionbarmultimedia.R
import com.dimas.actionbarmultimedia.databinding.ActivityMusicBinding
import com.dimas.actionbarmultimedia.utils.IntentConstant
import java.io.IOException
import java.lang.Exception

class MusicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicBinding

    private var mediaPlayer: MediaPlayer? = null
    private var musicUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        musicUrl = intent.getStringExtra(IntentConstant.INTENT_MUSIC_URL).orEmpty()
        binding.textMusic.text = musicUrl

        initMediaPlayer()
        initButton()
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        try {
            mediaPlayer?.setDataSource(musicUrl)
            mediaPlayer?.prepare()
        }catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Failed Load Media", Toast.LENGTH_SHORT).show()
            Log.e("Failed Media", e.message.orEmpty())
        }

    }

    private fun initButton() {
        with(binding) {
            buttonPlay.setOnClickListener {
                mediaPlayer?.start()
            }

            buttonPause.setOnClickListener {
                mediaPlayer?.pause()
            }

            buttonStop.setOnClickListener {
                mediaPlayer?.stop()
                mediaPlayer = null
            }
        }
    }
}