package com.gregorajdergmail.mymike.vm.viewObject;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.czt.mp3recorder.MP3Recorder;
import com.gregorajdergmail.mymike.App;
import com.gregorajdergmail.mymike.model.audio.MikeModel;
import com.gregorajdergmail.mymike.util.Log;
import com.gregorajdergmail.mymike.util.Util;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

public class ActivityVO extends BaseObservable {
    private MP3Recorder mRecorder;
    private File file;
    @Inject
    protected MikeModel mikeModel;

    public ActivityVO() {
        App.getComponent().inject(this);
    }

    @BindingAdapter("checkRecording")
    public static void bindSrcCompat(FloatingActionButton floatingActionButton, boolean b) {
        floatingActionButton.setSelected(b);
    }

    public void recordClick(View view) {
        if (mRecorder != null && mRecorder.isRecording()) {
            stopRecording(view);
        } else {
            startRecording(view);
        }
    }

    private void stopRecording(View view) {
        Log.d();
        view.setSelected(false);
        mRecorder.stop();
        mRecorder = null;
        mikeModel.addSoundTrack(file);
        MediaScannerConnection.scanFile(view.getContext(), new String[]{file.getPath()}, null, null);
        file=null;
    }

    private void startRecording(View view) {
        Log.d();
        view.setSelected(true);
        file = new File(view.getContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC), Util.getName(Util.MP3));
        mRecorder = new MP3Recorder(file);
        try {
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRecording() {
        return (mRecorder != null && mRecorder.isRecording());
    }
}
