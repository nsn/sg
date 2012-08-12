package com.nightspawn.sg.animation;

public abstract class Interpolator {
    protected float start;
    protected float end;

    public Interpolator(float start, float end) {
        super();
        this.start = start;
        this.end = end;
    }

    public abstract float interpolate(float progress);

}
