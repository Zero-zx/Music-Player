package com.zerox.musicplayer.services

import android.app.PendingIntent
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession
import com.zerox.musicplayer.data.MusicSource

class MusicService : MediaLibraryService() {

    private lateinit var mediaSession: MediaLibrarySession
    private var currentMediaItemIndex: Int = 0

    private lateinit var musicSource: MusicSource

    private val audioAttributes = AudioAttributes.Builder()
        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()



    private val exoPlayer: Player by lazy{
        val player = ExoPlayer.Builder(this)
            .build()
            .apply {
                setAudioAttributes(audioAttributes, true)
                setHandleAudioBecomingNoisy(true)
//                addListener() do later
            }
        player
    }

//    open fun getCallback(): MediaLibrarySession.Callback{
//        return MusicServiceCallback()
//    }

    @OptIn(UnstableApi::class) override fun onCreate() {
        super.onCreate()

//        mediaSession = with(MediaLibrarySession
//            .Builder(this, exoPlayer, getCallback())){
//            packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
//                setSessionActivity(
//                    PendingIntent.getActivity(
//                        this@MusicService,
//                        0,
//                        sessionIntent,
//                        if(Util.SDK_INT >= 23) PendingIntent.FLAG_IMMUTABLE
//                        else PendingIntent.FLAG_UPDATE_CURRENT
//                    )
//                )
//            }
//            build()
//        }
    }
    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaLibrarySession? {
        TODO("Not yet implemented")
    }
}