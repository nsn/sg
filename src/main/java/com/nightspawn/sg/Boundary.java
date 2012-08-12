package com.nightspawn.sg;

import pythagoras.f.AffineTransform;
import pythagoras.f.Dimension;
import pythagoras.f.ILine;
import pythagoras.f.Vector;

public interface Boundary {

    public boolean intersects(Boundary b);

    public boolean intersects(ILine l);

    public boolean intersects(BoundingRectangle r);

    public Boundary transform(AffineTransform t);

    public Boundary union(Boundary b);

    public Boundary union(BoundingRectangle r);

    public Vector location();

    public Dimension size();
}
