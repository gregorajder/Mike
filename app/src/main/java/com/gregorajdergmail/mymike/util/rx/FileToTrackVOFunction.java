package com.gregorajdergmail.mymike.util.rx;

import com.gregorajdergmail.mymike.viewmodel.viewObject.TrackVO;

import java.io.File;

public class FileToTrackVOFunction implements rx.functions.Func1<File, TrackVO> {
    @Override
    public TrackVO call(File file) {

        return new TrackVO(file);
    }
}
