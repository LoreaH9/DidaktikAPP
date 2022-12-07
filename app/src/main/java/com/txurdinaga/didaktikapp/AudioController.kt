package com.txurdinaga.didaktikapp

import android.content.Context
import android.media.MediaPlayer
import android.provider.MediaStore

interface AudioController{
    fun MediaPlayer.play(){
        if(this.currentPosition!=0 && this.currentPosition!= this.duration){
            this.seekTo(this.currentPosition)
        }
        this.start()
    }

    fun MediaPlayer.rewind(){
        this.seekTo(0)
        this.play()
    }
}

/*class AudioController(audiores: Int, context: Context) {
    private var mediaplayer: MediaPlayer? = MediaPlayer.create(context, audiores)
    var paused: Boolean = false
    var volumeLevel: Float = 0.6f

    fun play(){
        if(mediaplayer!!.currentPosition!=0 && mediaplayer!!.currentPosition!= mediaplayer!!.duration){
            mediaplayer!!.seekTo(mediaplayer!!.currentPosition)
        }
        mediaplayer!!.start()
        paused = false
    }

    fun pause(){
        mediaplayer!!.pause()
        paused = true
    }

    fun rewind(){
        mediaplayer!!.reset()
        paused = false
    }

    fun setVolume(vol:Float){
        mediaplayer!!.setVolume(vol, vol)
        volumeLevel = vol
    }
}*/