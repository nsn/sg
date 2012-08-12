package com.nightspawn.sg;

import pythagoras.f.MathUtil;

import com.nightspawn.sg.animation.Interpolator;
import com.nightspawn.sg.animation.LinearInterpolator;

public class SpriteAnimator {
    private int animation;
    private long duration;
    private int numFrames;
    private long elapsed;
    private Sprite sprite;
    private boolean loop;
    private boolean stopped;
    private Interpolator interpolator;

    public SpriteAnimator(int animation, long duration, int numFrames, boolean loop) {
        super();
        this.animation = animation;
        this.duration = duration;
        this.numFrames = numFrames;
        this.loop = loop;
        this.stopped = true;
        interpolator = new LinearInterpolator(0, numFrames);
    }

    public void setSprite(Sprite s) {
        this.sprite = s;
    }

    public void update(float deltams) {
        elapsed += deltams;

        if (stopped()) {
            return;
        }

        float progress = ((float) (elapsed % duration)) / duration;
        // PlayN.log().debug("f6012 " + interpolator.interpolate(progress));
        int frame = MathUtil.ifloor(interpolator.interpolate(progress));
        sprite.setFrame(frame);
        sprite.setAnimation(animation);
    }

    public void stop() {
        stopped = true;
    }

    public void start() {
        if (!stopped)
            return;
        restart();
    }

    public void restart() {
        reset();
        stopped = false;
    }

    public void reset() {
        elapsed = 0;
        sprite.setFrame(0);
    }

    public boolean stopped() {
        return stopped || (!loop && elapsed >= duration);
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

}
