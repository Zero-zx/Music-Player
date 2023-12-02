package com.zerox.musicplayer.data

import android.media.browse.MediaBrowser.MediaItem
import com.zerox.musicplayer.data.State.STATE_CREATED
import com.zerox.musicplayer.data.State.STATE_ERROR
import com.zerox.musicplayer.data.State.STATE_INITIALIZED
import com.zerox.musicplayer.data.State.STATE_INITIALIZING

interface MusicSource : Iterable<MediaItem> {

    suspend fun load()
    fun whenReady(performAction: (Boolean) -> Unit): Boolean

}

enum class State {
    //Create but not initialize yet
    STATE_CREATED,

    //initialization is in progress
    STATE_INITIALIZING,

    //Ready to be used
    STATE_INITIALIZED,

    //Error
    STATE_ERROR
}

abstract class AbstractionMusicSource : MusicSource {

    private val onReadyListener = mutableListOf<(Boolean) -> Unit>()
    var state: State = STATE_CREATED
        set(value) {
            if (value == STATE_INITIALIZED || value == STATE_ERROR) {
                synchronized(onReadyListener) {
                    field = value
                    onReadyListener.forEach { listener ->
                        listener(state == STATE_INITIALIZED)
                    }
                }
            } else {
                field = value
            }
        }

    override fun whenReady(performAction: (Boolean) -> Unit): Boolean {
        if(state == STATE_CREATED || state == STATE_INITIALIZING){
            onReadyListener += performAction
            return false
        }else{
            performAction(state == STATE_INITIALIZED)
            return true
        }
    }
}