package com.nightspawn.sg;

import pythagoras.f.Dimension;
import pythagoras.f.Rectangle;
import pythagoras.f.Vector;

public class Sprite<T> extends Spatial {
	protected T texture;
	protected Dimension dim;
	protected Vector offset;
	protected int animation, frame;
	private SpriteAnimator animator;

	public Sprite(T texture, Dimension d, Vector offset) {
		super();
		init(texture, d, offset);
	}

	public Sprite(T texture, Dimension d) {
		init(texture, d, new Vector());
	}

	public void init(T texture, Dimension d, Vector offset) {
		dim = d;
		this.offset = offset;
		animation = frame = 0;
		this.texture = texture;
		setModelBound(new BoundingRectangle(new Rectangle(dim)));
	}

	public void setAnimator(SpriteAnimator a) {
		animator = a;
		animator.setSprite(this);
	}

	public void stopAnimation() {
		animator = null;
	}

	public T getTexture() {
		return texture;
	}

	public Dimension getDim() {
		return dim;
	}

	public int getAnimation() {
		return animation;
	}

	public int getFrame() {
		return frame;
	}

	@Override
	public void visit(SceneGraphVisitor visitor) {
		visitor.visitSprite(this);
	}

	@Override
	public void update(float deltams) {
		if (animator != null) {
			animator.update(deltams);
		}
		super.update(deltams);
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public Vector getOffset() {
		return offset;
	}

	public void setOffset(Vector offset) {
		this.offset = offset;
	}

}
