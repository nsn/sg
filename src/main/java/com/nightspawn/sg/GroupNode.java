package com.nightspawn.sg;

import java.util.ArrayList;

import pythagoras.f.Vector;

public class GroupNode<C extends Node> extends Spatial {

    protected ArrayList<C> children = new ArrayList<C>();

    public GroupNode() {
    }

    public GroupNode(String name) {
        super(name);
    }

    @Override
    public void visit(SceneGraphVisitor visitor) {
        if (visitor.visitNode(this)) {
            for (Node child : children) {
                child.visit(visitor);
            }
        }
    }

    public void addChild(C e) {
        children.add(e);
        e.setParent(this);
        markAsDirty();
    }

    public void clearChildren() {
        children = new ArrayList<C>();
        markAsDirty();
    }

    public ArrayList<C> getChildren() {
        return children;
    }

    /**
     * aligns the children centered
     */
    public void alignCentered() {
        // we need to first update the subtree
        update(0.0f);
        float maxWidth = modelBound.width;
        // translate children accordingly
        for (C c : children) {
            float w = c.getModelBound().width;
            float x = (maxWidth - w) / 2;
            c.translate(new Vector(x, 0.0f));
        }
    }

    @Override
    public void update(float deltams) {
        super.update(deltams);

        modelBound = new BoundingRectangle();
        for (Node child : children) {
            child.update(deltams);
            //modelBound = modelBound.union(child.modelBound.transform(child.getLocalTransform()));
            modelBound = modelBound.union(child.getModelBound().transform(child.getLocalTransform()).translate(child.getLocalTransform()));
        }
        worldBound = modelBound.translate(getWorldTransform());
    }
}
