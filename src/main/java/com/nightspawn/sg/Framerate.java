package com.nightspawn.sg;

import java.util.Arrays;

public class Framerate {
    public static final int NUM_SAMPLES = 100;
    private double[] samples;
    private int pointer = 0;
    private double sum = 0.0d;
    private double lastTick;

    public Framerate(double now) {
        this(NUM_SAMPLES, now);
    }

    public Framerate(int numSamples, double now) {
        init(numSamples, now);
    }

    public void init(int numSamples, double now) {
        lastTick = now;
        samples = new double[numSamples];
        Arrays.fill(samples, 0.0d);
        pointer = 0;
        sum = 0.0d;
    }

    public void onPaint(double now) {
        sum -= samples[pointer];
        samples[pointer] = now - lastTick;
        sum += samples[pointer];
        pointer = ++pointer % samples.length;
        lastTick = now;
    }

    public double average() {
        return sum / samples.length;
    }

    public double framerate() {
        return 1000 / average();
    }

}
