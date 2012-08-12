package com.nightspawn.sg;

import pythagoras.f.AffineTransform;
import pythagoras.f.FloatMath;

/**
 * Helper class that performs common transforms on {@link SceneElement} instances
 *
 * @author nsn
 */
public class Transforms {

    public static AffineTransform rotateAroundCenter(Node e, float angle) {
        float w = e.getModelBound().width;
        float x = w / 2;
        float h = e.getModelBound().height;
        float y = h / 2;

        return Transforms.rotateAroundPoint(e, angle, x, y);
    }

    public static AffineTransform rotateAroundPoint(Node e, float angle, float x, float y) {
        float cosa = FloatMath.cos(angle);
        float sina = FloatMath.sin(angle);

        float tx = x - cosa * x - sina * y;
        float ty = y + sina * x - cosa * y;

        AffineTransform trans = new AffineTransform(cosa, -sina, sina, cosa, tx, ty);

        return trans;
    }
}
