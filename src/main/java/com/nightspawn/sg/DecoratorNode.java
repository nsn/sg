package com.nightspawn.sg;

import pythagoras.f.AffineTransform;
import pythagoras.f.Vector;

/**
 * abstract decorator class for nodes, allows extending a node's functionality without extending that node
 *
 * @author nsn
 */
public class DecoratorNode<T extends Node> implements Node {

    protected Node parent;
    protected T child;

    public DecoratorNode(T child) {
        this.child = child;
    }

    @Override
    public void update(float deltams) {
        child.update(deltams);
    }

    @Override
    public void visit(SceneGraphVisitor visitor) {
        child.visit(visitor);
    }

    @Override
    public void setParent(Node p) {
        parent = p;
        child.setParent(p);
    }

    @Override
    public void markAsDirty() {
        if (parent != null) {
            parent.markAsDirty();
        }
    }

    @Override
    public AffineTransform getWorldTransform() {
        return child.getWorldTransform();
    }

    @Override
    public AffineTransform getLocalTransform() {
        return child.getLocalTransform();
    }

    @Override
    public BoundingRectangle getWorldBound() {
        return child.getWorldBound();
    }

    @Override
    public BoundingRectangle getModelBound() {
        return child.getModelBound();
    }

    @Override
    public void transform(AffineTransform t) {
        child.transform(t);
    }

    @Override
    public void setTransform(AffineTransform t) {
        child.setTransform(t);
    }

    @Override
    public void scale(float s) {
        child.scale(s);
    }

    @Override
    public void setScale(float s) {
        child.setScale(s);
    }

    @Override
    public void translate(Vector t) {
        child.translate(t);
    }

    @Override
    public void setTranslation(Vector t) {
        child.setTranslation(t);
    }

    @Override
    public void rotate(float angle) {
        child.rotate(angle);
    }

    @Override
    public void setRotation(float angle) {
        child.setRotation(angle);
    }

    @Override
    public boolean drawBoundary() {
        return child.drawBoundary();
    }

    @Override
    public void setDrawBoundary(boolean b) {
        child.setDrawBoundary(b);
    }

    @Override
    public int boundaryColor() {
        return child.boundaryColor();
    }

    @Override
    public void setBoundaryColor(int color) {
        child.setBoundaryColor(color);
    }

    @Override
    public boolean isRendered() {
        return child.isRendered();
    }

    @Override
    public void setRendered(boolean b) {
        child.setRendered(b);
    }
}
