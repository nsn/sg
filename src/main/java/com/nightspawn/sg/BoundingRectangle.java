package com.nightspawn.sg;

import pythagoras.f.*;

@SuppressWarnings("serial")
public class BoundingRectangle extends Rectangle {

    public BoundingRectangle() {
        super();
    }

    public BoundingRectangle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public BoundingRectangle(IDimension d) {
        super(d);
    }

    public BoundingRectangle(IPoint p, IDimension d) {
        super(p, d);
    }

    public BoundingRectangle(IPoint p) {
        super(p);
    }

    public BoundingRectangle(IRectangle r) {
        super(r);
    }

    public BoundingRectangle translate(AffineTransform t) {
        BoundingRectangle r = clone();
        r.translate(t.tx, t.ty);
        return r;
    }

    public BoundingRectangle transform(AffineTransform t) {
        Vector[] corners = getCorners();
        float minX, maxX, minY, maxY;
        minX = maxX = minY = maxY = 0.0f;
        for (Vector v : corners) {
            t.transform(v, v);
            if (v.x < minX) {
                minX = v.x;
            }
            if (v.x > maxX) {
                maxX = v.x;
            }
            if (v.y < minY) {
                minY = v.y;
            }
            if (v.y > maxY) {
                maxY = v.y;
            }
        }
        return new BoundingRectangle(minX, minY, maxX - minX, maxY - minY);
    }

    public BoundingRectangle clone() {
        return new BoundingRectangle(super.clone());
    }

    public BoundingRectangle union(BoundingRectangle r) {
        return new BoundingRectangle(super.union(r));
    }

    public Vector[] getCorners() {
        Vector[] corners = new Vector[4];
        corners[0] = new Vector(this.x, this.y); // top left
        corners[1] = new Vector(this.x + this.width, this.y); // top right
        corners[2] = new Vector(this.x + this.width, this.y + this.height); // bottom right
        corners[3] = new Vector(this.x, this.y + this.height); // bottom left
        return corners;
    }

    public Line[] getOutlines() {
        Vector[] corners = getCorners();

        Line[] outLines = new Line[4];

        outLines[0] = new Line(corners[0].x, corners[0].y, corners[1].x, corners[1].y); // upper
        outLines[1] = new Line(corners[2].x, corners[2].y, corners[3].x, corners[3].y); // lower
        outLines[2] = new Line(corners[0].x, corners[0].y, corners[3].x, corners[3].y); // left
        outLines[3] = new Line(corners[1].x, corners[1].y, corners[2].x, corners[2].y); // right

        return outLines;
    }
}
