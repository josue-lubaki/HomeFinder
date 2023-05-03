package ca.josue.homefinder.utils

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView

/**
 * created by Josue Lubaki
 * date : 2023-05-03
 * version : 1.0.0
 */

fun Context.buildExoPlayer(uri: Uri): ExoPlayer {
    return ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }
}

fun Context.buildPlayerView(exoPlayer: ExoPlayer): StyledPlayerView {
    return StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }
}