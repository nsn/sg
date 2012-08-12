/*
 *
 */
package com.nightspawn.sg;

import pythagoras.f.AffineTransform;
import pythagoras.f.Vector;

/**
 * 
 * @author nsn
 */
public interface Node {

    void update(float deltams);

    void visit(SceneGraphVisitor visitor);

    void setParent(Node p);

    /**
     * marks this branch dirty, causing a complete geometry update the next time
     * update is called
     */
    void markAsDirty();

    boolean drawBoundary();

    void setDrawBoundary(boolean b);

    int boundaryColor();

    void setBoundaryColor(int color);

    boolean isRendered();

    void setRendered(boolean b);

    /**
     * 
     * @return the world transformation, an AffineTransform combined from all
     *         transforms from this node all the way up to the root node, places
     *         this branch relative to the origin
     */
    AffineTransform getWorldTransform();

    /**
     * 
     * @return the local transform, an AffineTransform that places this branch
     *         relative to the parent node
     */
    AffineTransform getLocalTransform();

    /**
     * 
     * @return a BoundingRectangle encompassing this branch, transformed into
     *         world coordinates
     */
    BoundingRectangle getWorldBound();

    /**
     * 
     * @return a BoundingRectangle encompassing this branch, in model
     *         coordinates
     */
    BoundingRectangle getModelBound();

    /**
     * apply a transform to this branch - multiplies t with the current
     * localTransform
     * 
     * @param t
     *            the transform to apply to this branch
     */
    void transform(AffineTransform t);

    /**
     * sets this branches local transform
     * 
     * @param t
     */
    void setTransform(AffineTransform t);

    /**
     * scale this branch
     * 
     * @param s
     *            factor to scale this branch by
     */
    void scale(float s);

    /**
     * sets the
     * 
     * @param s
     */
    void setScale(float s);

    /**
     * translate this branch - affects local transformation
     * 
     * @param t
     *            vector to translate this branch by
     */
    void translate(Vector t);

    /**
     * set this branches local translation
     * 
     * @param t
     *            translation vector
     */
    void setTranslation(Vector t);

    /**
     * rotate this branch
     * 
     * @param angle
     *            angle in radians to rotate this branch by
     */
    void rotate(float angle);

    /**
     * sets this
     * 
     * @param angle
     */
    void setRotation(float angle);
}
