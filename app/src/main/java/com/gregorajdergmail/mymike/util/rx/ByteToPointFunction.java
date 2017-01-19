package com.gregorajdergmail.mymike.util.rx;

public class ByteToPointFunction implements rx.functions.Func1<byte[], float[]> {
    private int mSpectrumNum = 48;
    private float[] mPoints = new float[mSpectrumNum*4+4];

    @Override
    public float[] call(byte[] bytes) {

        byte[] model = new byte[bytes.length / 2 + 1];

        model[0] = (byte) Math.abs(bytes[0]);
        for (int i = 2, j = 1; j < mSpectrumNum; ) {
            model[j] = (byte) Math.hypot(bytes[i], bytes[i + 1]);
            i += 2;
            j++;
        }

        for (int i = 0; i < mSpectrumNum; i++) {
            if (model[i] < 0) {
                model[i] = 127;
            }
            mPoints[i * 4] = i;
            mPoints[i * 4 + 1] = 0;
            mPoints[i * 4 + 2] = i;
            mPoints[i * 4 + 3] = model[i];
        }
        return mPoints;
    }
}
