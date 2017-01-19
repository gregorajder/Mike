package com.gregorajdergmail.mymike.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Binder;
import android.os.IBinder;

import com.gregorajdergmail.mymike.util.Log;
import com.gregorajdergmail.mymike.vm.viewObject.TrackVO;

import java.io.IOException;

import rx.Observable;
import rx.subjects.PublishSubject;

public class PlayerService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private TrackVO trackVO;
    private MediaPlayer mediaPlayer;
    private Visualizer mVisualizer;
    private PublishSubject<byte[]> subject;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d();
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void startPlay(TrackVO trackVO) {
        stopPlay();
        subject = PublishSubject.create();
        this.trackVO = trackVO;
        Log.d();
        if (mediaPlayer == null) mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(trackVO.getFile().getPath());
            mediaPlayer.prepare();
            initVisualizer(mediaPlayer);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MyCompletionListener());
            trackVO.setPlaying(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopPlay() {
        Log.d();
        if (subject != null) subject.onCompleted();
        if (mediaPlayer != null) mediaPlayer.stop();
        if (mVisualizer != null) mVisualizer.setEnabled(false);
        if (trackVO != null) {
            trackVO.setPlaying(false);
            trackVO = null;
        }
    }

    public TrackVO getTrackVO() {
        return trackVO;
    }

    private void initVisualizer(MediaPlayer mediaPlayer) {
        mVisualizer = new Visualizer(mediaPlayer.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate) {
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                         int samplingRate) {
                subject.onNext(bytes);
            }
        };

        mVisualizer.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate() / 2, false, true);
        mVisualizer.setEnabled(true);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d();
        stopPlay();
        if (mediaPlayer != null) mediaPlayer.release();
        if (mVisualizer != null) mVisualizer.release();
    }

    public class LocalBinder extends Binder {


        public PlayerService getService() {
            return PlayerService.this;
        }

        public Observable<byte[]> getVisualizerObservable() {
            return subject;
        }
    }

    private class MyCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            stopPlay();
        }
    }
}
