package com.nightspawn.sg;

public class Scene {
    private Spatial root;

    public Scene(Spatial r) {
        setRoot(r);
    }

    public void update(float deltams) {
        root.update(deltams);
    }

    public void visit(SceneGraphVisitor v) {
        root.visit(v);
    }

    public Spatial getRoot() {
        return root;
    }

    public void setRoot(Spatial root) {
        this.root = root;
    }

}
