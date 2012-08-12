package com.nightspawn.sg.animation;

import com.nightspawn.sg.Node;
import com.nightspawn.sg.Transforms;

import pythagoras.f.AffineTransform;
import pythagoras.f.FloatMath;

public class Jiggle<T extends Node> extends AnimationNode<T> {

    public Jiggle(T child, float angle, long duration) {
        super(child, duration, new LinearInterpolator(FloatMath.toRadians(-angle), FloatMath.toRadians(angle)), true, true);
    }

    @Override
    public void animate(float absolute, float delta) {
        transform(Transforms.rotateAroundCenter(child, delta));
    }

    @Override
    public void reset() {
        super.reset();
        AffineTransform trans = Transforms.rotateAroundCenter(child, getLocalTransform().rotation());
        transform(trans);
    }
}
