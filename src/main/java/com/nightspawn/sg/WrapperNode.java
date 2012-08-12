/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightspawn.sg;

/**
 * wraps a single Node, essentially a GroupNode with only a single child node
 *
 * @author nsn
 */
public class WrapperNode<C extends Node> extends Spatial {

    private C child;

    public WrapperNode(C child) {
        this.child = child;
        child.setParent(this);
    }

    @Override
    public void visit(SceneGraphVisitor visitor) {
        if (visitor.visitNode(this)) {
            child.visit(visitor);
        }
    }

    @Override
    public void update(float deltams) {
        super.update(deltams);
        child.update(deltams);
        modelBound = child.getModelBound().clone();
        worldBound = modelBound.translate(getWorldTransform());
    }
}
