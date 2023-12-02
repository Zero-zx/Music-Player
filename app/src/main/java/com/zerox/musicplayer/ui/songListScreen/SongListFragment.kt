package com.zerox.musicplayer.ui.songListScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zerox.musicplayer.R
import com.zerox.musicplayer.databinding.FragmentSongListBinding


class SongListFragment : Fragment() {
    private val binding by lazy {
        FragmentSongListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}