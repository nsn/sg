package com.nightspawn.sg;

import pythagoras.f.AffineTransform;
import pythagoras.f.Transforms;
import pythagoras.f.Vector;

/**
 * Basic Object in the scene graph, describes an area in the scene
 * 
 * @author nsn
 * 
 */
public abstract class Spatial implements Node {

    private String name;
    private boolean displayed = true;
    private boolean dirty = true;
    private AffineTransform localTransform;
    private AffineTransform worldTransform;
    protected BoundingRectangle modelBound;
    protected BoundingRectangle worldBound;
    protected Node parent;
    protected boolean drawBoundary = false;
    protected int boundaryColor = 0;

    public Spatial() {
        init();
    }

    public Spatial(String name) {
        init();
        setName(name);
    }

    private void init() {
        displayed = true;
        dirty = true;
        localTransform = new AffineTransform();
        worldTransform = new AffineTransform();
        modelBound = new BoundingRectangle();
        worldBound = new BoundingRectangle();
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void update(float deltams) {
        updateGeometry();
    }

    private void updateGeometry() {
        // not dirty - nothing changed
        if (!dirty) {
            return;
        }
        // perform transform updates
        if (parent != null) {
            Transforms.multiply(parent.getWorldTransform(), localTransform, worldTransform);
            worldBound = modelBound.translate(worldTransform);
        } else {
            worldTransform = new AffineTransform(localTransform.m00, localTransform.m01, localTransform.m10,
                    localTransform.m11, localTransform.tx, localTransform.ty);
            worldBound = modelBound.translate(localTransform);
        }
    }

    @Override
    public boolean drawBoundary() {
        return drawBoundary;
    }

    @Override
    public void setDrawBoundary(boolean b) {
        drawBoundary = b;
    }

    @Override
    public int boundaryColor() {
        return boundaryColor;
    }

    @Override
    public void setBoundaryColor(int color) {
        boundaryColor = color;
    }

    /**
     * rotates this branch
     * 
     * @param angle
     *            the angle to rotate by, in radians
     */
    public void rotate(float angle) {
        localTransform.rotate(angle);
        markAsDirty();
    }

    /**
     * translates this branch, adding to current translation vector
     * 
     * @param t
     *            vector to translate by
     */
    @Override
    public void translate(Vector t) {
        localTransform.translate(t.x, t.y);
        markAsDirty();
    }

    public void setTranslation(Vector t) {
        localTransform.setTranslation(t.x, t.y);
        markAsDirty();
    }

    public void setTransform(AffineTransform trans) {
        localTransform = trans;
        markAsDirty();
    }

    @Override
    public void transform(AffineTransform t) {
        Transforms.multiply(localTransform, t, localTransform);
        markAsDirty();
    }

    public Vector getWorldPosition() {
        return worldTransform.translation();
    }

    /**
     * scales this branch in a uniform manner
     * 
     * @param s
     *            the scaling factor
     */
    @Override
    public void scale(float s) {
        localTransform.uniformScale(s);
        markAsDirty();
    }

    @Override
    public void setScale(float s) {
        localTransform.setScale(s, s);
        markAsDirty();
    }

    @Override
    public void setRotation(float angle) {
        localTransform.setRotation(angle);
        markAsDirty();
    }

    /**
     * marks the whole branch from this element on upward as dirty, so the
     * world-translation, -bound, -scale and rotation are recalculated the next
     * time the branch is rendered.
     */
    @Override
    public void markAsDirty() {
        dirty = true;
        if (parent != null) {
            parent.markAsDirty();
        }
    }

    /**
     * if a branch is marked as dirty the world-translation, -bound, -scale and
     * rotation are recalculated the next time the branch is rendered.
     * 
     * @return boolean indicating whether the branch is dirty
     */
    public boolean isDirty() {
        return dirty;
    }

    /**
     * 
     * @return the transformation matrix needed to place this branch in the
     *         scene
     */
    @Override
    public AffineTransform getWorldTransform() {
        return worldTransform;
    }

    /**
     * 
     * @return the transformation matrix needed to place this branch relative to
     *         the parent node
     */
    @Override
    public AffineTransform getLocalTransform() {
        return localTransform;
    }

    public void setModelBound(BoundingRectangle b) {
        modelBound = b;
    }

    @Override
    public BoundingRectangle getWorldBound() {
        return worldBound;
    }

    @Override
    public BoundingRectangle getModelBound() {
        return modelBound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isRendered() {
        return displayed;
    }

    @Override
    public void setRendered(boolean displayed) {
        this.displayed = displayed;
    }
}
