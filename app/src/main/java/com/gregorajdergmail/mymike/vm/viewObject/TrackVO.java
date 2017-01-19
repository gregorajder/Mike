package com.gregorajdergmail.mymike.vm.viewObject;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.gregorajdergmail.mymike.BR;
import com.gregorajdergmail.mymike.util.Log;

import java.io.File;

public class TrackVO extends BaseObservable {
    private File file;
    private String name;

    private boolean playing = false;
    private TrackClickListener clickListener;


    public TrackVO(File file) {
        this.file = file;
        name = file.getName();
    }

    public static TrackVO create(File file) {
        return new TrackVO(file);
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public boolean isPlaying() {
        return playing;
    }

    @Bindable
    public void setPlaying(boolean playing) {
        this.playing = playing;
        notifyPropertyChanged(BR.playing);
    }

    public void playClick(View view) {
        Log.d();
        if (clickListener != null) {
            if (playing) {
                clickListener.stopPlay(TrackVO.this);
            } else {
                clickListener.startPlay(TrackVO.this);
            }
        }
    }

    public void setClickListener(TrackClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public interface TrackClickListener {
        void startPlay(TrackVO trackVO);
        void stopPlay(TrackVO trackVO);
    }
}
