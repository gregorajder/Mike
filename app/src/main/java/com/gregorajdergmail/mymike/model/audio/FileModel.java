package com.gregorajdergmail.mymike.model.audio;

import com.gregorajdergmail.mymike.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FileModel {
    private PublishSubject<File> subject ;

    private PublishSubject<File> getFileSubject(){
        if (subject==null){
            subject= PublishSubject.create();
        }
        return subject;
    }

    private List<File> checkDirectory(String path) {
        File[] files = new File(path).listFiles();
        Log.d("Files", "Size: "+ files.length);
        return Arrays.asList(new File(path).listFiles());
    }

    public void addSoundTrack(File file) {
        getFileSubject().onNext(file);

    }

    public Observable<File> getTracks(String path) {
        return getFileSubject().startWith(checkDirectory(path));
    }
}
