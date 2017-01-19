package com.gregorajdergmail.mymike.model.json;

import android.media.MediaScannerConnection;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregorajdergmail.mymike.App;
import com.gregorajdergmail.mymike.util.Log;
import com.gregorajdergmail.mymike.util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonFileSaver {
    VisualizerData visualizerData;

    public void add(float[] floats, String name) {
        if (visualizerData == null)
            visualizerData = new VisualizerData(name);
        visualizerData.addPoints(floats.clone());
    }

    public void completed(String name) {
        Log.d(name);
        if (visualizerData != null) {
           final VisualizerData tempVisualizerData = visualizerData;
            visualizerData = null;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        File file = new File(App.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                                , Util.convertName(tempVisualizerData.getFileName(), Util.JSON));
                        if (file.exists()) file.delete();

                        //TODO check if FileWriter need to be end with try-with resource block
                        Writer writer = new FileWriter(file.getPath());
                        Gson gson = new GsonBuilder().create();
                        gson.toJson(tempVisualizerData, writer);
                        writer.close();
                        MediaScannerConnection.scanFile(App.getContext(), new String[]{file.getPath()}, null, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
