package com.dimas.actionbarmultimedia.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimas.actionbarmultimedia.databinding.ActivityVideoBinding
import com.dimas.actionbarmultimedia.utils.videoUrl
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class VideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    private var exoplayer: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeExoPlayer()
    }

    private fun initializeExoPlayer() {
        exoplayer = ExoPlayer.Builder(this).build()
        binding.exoPlayerView.player = exoplayer
        val mediaItem = MediaItem.fromUri(videoUrl)
        exoplayer?.apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    override fun onStop() {
        super.onStop()
        exoplayer?.apply {
            playWhenReady = false
            release()
        }
        exoplayer = null
    }
}