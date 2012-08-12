package com.nightspawn.sg.animation;

public class LinearInterpolator extends Interpolator {

    public LinearInterpolator(float start, float end) {
        super(start, end);
    }

    @Override
    public float interpolate(float progress) {
        return start + (end - start) * progress;
    }
}
