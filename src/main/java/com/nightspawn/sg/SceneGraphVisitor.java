package com.nightspawn.sg;

/**
 * Describes a visitor to each node in the scene graph.
 * 
 * 
 * @param T
 *            texture type (used for sprites)
 * 
 * @see SceneElement
 * @author nsn
 * 
 */
public interface SceneGraphVisitor<T> {

    /**
     * called when visiting a GroupNode
     * 
     * @param n
     *            the current node
     * @return a boolean value indicating whether to continue traversal down the
     *         current branch
     */
    boolean visitNode(com.nightspawn.sg.Node n);

    /**
     * called upon visiting a Sprite
     * 
     * @param s
     *            Sprite being visited
     */
    void visitSprite(Sprite<T> s);

    /**
     * called upon visiting an Area
     * 
     * @param a
     *            Area being visited
     */
    void visitArea(Area a);
}
