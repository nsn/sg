package com.nightspawn.sg.animation;

import com.nightspawn.sg.DecoratorNode;
import com.nightspawn.sg.Node;

import pythagoras.f.FloatMath;

public abstract class AnimationNode<T extends Node> extends DecoratorNode<T> {

    private Interpolator interpolator;
    private long duration;
    private long elapsed = 0;
    private boolean stopped = false;
    private boolean loop = true;
    private boolean jojo = false;
    private float lastValue = 0.0f;

    public AnimationNode(T child, long duration, Interpolator interpolator, boolean loop, boolean jojo) {
        super(child);
        this.interpolator = interpolator;
        this.duration = duration;
        this.loop = loop;
        this.jojo = jojo;
    }

    public abstract void animate(float absolute, float delta);

    @Override
    public void update(float deltams) {
        if (!stopped) {
            elapsed += deltams;
            float progress = 0.0f;
            if (jojo) {
                progress = triangle(elapsed);
            } else {
                progress = sawtooth(elapsed);
            }

            progress = interpolator.interpolate(progress);
            float diff = lastValue - progress;
            lastValue = progress;

            animate(progress, diff);
        }

        child.update(deltams);
    }

    private float triangle(long t) {
        float h = (float) t / duration;
        return Math.abs(2 * (h - FloatMath.floor(h + .5f)));
    }

    private float sawtooth(long t) {
        return (float) (t % duration) / duration;
    }

    public void stop() {
        stopped = true;
    }

    public void start() {
        stopped = false;
    }

    public void reset() {
        elapsed = 0;
        lastValue = 0.0f;
    }

    public void restart() {
        reset();
        start();
    }
}
