package com.gregorajdergmail.mymike.model.json;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VisualizerData {
    private String fileName;
    private ArrayList <float[]> points = new ArrayList<>();

    public VisualizerData(String name) {
        fileName= name;
    }

    @NotNull
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<float[]> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<float[]> points) {
        this.points = points;
    }

    public void addPoints(float[] floats) {
        points.add(floats);
    }
}
