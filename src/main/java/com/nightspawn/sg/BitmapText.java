package com.nightspawn.sg;

import pythagoras.f.Vector;

import com.nightspawn.sg.BitmapFont.InvalidCharacterException;

public abstract class BitmapText<T extends Node> extends GroupNode<T> {

    protected String text;
    protected BitmapFont<?> font;

    public BitmapText(String text, BitmapFont<?> font) throws InvalidCharacterException {
        this.text = text;
        this.font = font;

        float charWidth = font.getCharDims().width;
        char[] chars = text.toCharArray();
        float x = 0.0f;
        for (int i = 0; i < chars.length; i++) {
            // SceneElement charElement = font.getCharSprite(chars[i]);
            T charElement = makeCharElement(chars[i], i);
            if (i >= 1) {
                x += font.getTracking();
                x += font.getKerning(chars[i - 1], chars[i]);
            }
            charElement.translate(new Vector(x, 0.0f));
            addChild(charElement);
            x += charWidth;
        }
    }

    protected abstract T makeCharElement(char c, int pos) throws InvalidCharacterException;

    // return font.getCharSprite(c);

    @Override
    public void visit(SceneGraphVisitor visitor) {
        super.visit(visitor);
    }
}
