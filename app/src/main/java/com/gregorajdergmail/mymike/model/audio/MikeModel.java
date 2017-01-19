package com.gregorajdergmail.mymike.model.audio;

import android.os.Environment;

import com.gregorajdergmail.mymike.App;
import com.gregorajdergmail.mymike.util.rx.FileToTrackVOFunction;
import com.gregorajdergmail.mymike.viewmodel.viewObject.TrackVO;

import java.io.File;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MikeModel {
    private FileModel fileModel;

    private String tracksDirectory = App.getContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath();


    private FileModel getFileModel(){
        if (fileModel== null)
            fileModel = new FileModel();
        return fileModel;

    }
    public Observable<TrackVO> getNewTracks() {
       return getFileModel().getTracks(tracksDirectory)
               .map(new FileToTrackVOFunction())
               .subscribeOn(Schedulers.computation())
               .observeOn(AndroidSchedulers.mainThread());
    }

    public void addSoundTrack(File file){
         getFileModel().addSoundTrack(file);
    }
}
