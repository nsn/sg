package com.nightspawn.sg;

import pythagoras.f.Dimension;

public class Area extends Spatial {

    public Area(Dimension dims) {
        super();
        modelBound = new BoundingRectangle(dims);
    }

    @Override
    public void visit(SceneGraphVisitor visitor) {
        visitor.visitArea(this);
    }
}
