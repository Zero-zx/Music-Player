package com.zerox.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zerox.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var controller: NavController

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
        setupWithNavController(binding.bottomNav, controller)
    }

    @OptIn(UnstableApi::class) private fun initializePlayer(){
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
//                binding.mediaPlayer.player = exoPlayer
//                exoPlayer.setVideoSurfaceView(binding.mediaPlayer)
                val mediaItem = MediaItem.fromUri("https://firebasestorage.googleapis.com/v0/b/music-player-fefb8.appspot.com/o/songs%2FDuongToiChoEmVe.mp3?alt=media&token=7632727e-4ac3-4943-9fc9-db9613ae6e5e")
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
//                exoPlayer.play()
            }
    }

    @OptIn(UnstableApi::class) override fun onStart() {
        super.onStart()
        if(Util.SDK_INT > 23){
            initializePlayer()
        }
    }

    @OptIn(UnstableApi::class) public override fun onResume() {
        super.onResume()
//        hideSystemUi()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }
}