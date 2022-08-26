package com.kaiserdavar.androidui;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Map;

public class Video extends BaseVue<Video, VideoView> {

    public static Video create(Context context) {
        return new Video(context);
    }

    public Video(VideoView view) {
        super(view);
    }

    public Video(Context context) {
        super(context);
    }

    @Override
    protected VideoView onGetMainView(Context context) {
        return new VideoView(context);
    }

    public Video path(String path) {
        view.setVideoPath(path);
        return this;
    }
    public Video uri(Uri uri) {
        view.setVideoURI(uri);
        return this;
    }
    public Video uri(Uri uri, Map<String, String> headers) {
        view.setVideoURI(uri, headers);
        return this;
    }
    public Video start() {
        view.start();
        return this;
    }
    public Video stop() {
        view.stopPlayback();
        return this;
    }
    public Video pause() {
        view.pause();
        return this;
    }
    public Video resume() {
        view.resume();
        return this;
    }
    public Video seekTo(int milliSecond) {
        view.seekTo(milliSecond);
        return this;
    }
    public Video mediaController(MediaController mediaController) {
        view.setMediaController(mediaController);
        return this;
    }
    public Video onPrepared(MediaPlayer.OnPreparedListener listener) {
        view.setOnPreparedListener(listener);
        return this;
    }
    public Video onCompletion(MediaPlayer.OnCompletionListener listener) {
        view.setOnCompletionListener(listener);
        return this;
    }
    public Video onError(MediaPlayer.OnErrorListener listener) {
        view.setOnErrorListener(listener);
        return this;
    }
    public Video onInfo(MediaPlayer.OnInfoListener listener) {
        view.setOnInfoListener(listener);
        return this;
    }
}
