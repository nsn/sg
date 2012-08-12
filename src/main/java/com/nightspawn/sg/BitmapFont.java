package com.nightspawn.sg;

import java.util.HashMap;

import pythagoras.f.Dimension;

public class BitmapFont<T> {
    public static final int DEFAULT_CHARS_PER_ROW = 6;
    public static final String DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,- !?";
    private T texture;
    private Dimension charDims;
    private int charsPerRow;
    private float tracking = 0.0f; // pixels
    private HashMap<Character, Integer> charIndices;
    private HashMap<LigTableKey, Float> ligTable;

    public BitmapFont(T texture, Dimension charDims) {
        this(texture, charDims, DEFAULT_CHARS_PER_ROW, DEFAULT_CHARS);
    }

    public BitmapFont(T texture, Dimension charDims, int charsPerRow, String validChars) {
        this(texture, charDims, charsPerRow, generateIndexMap(validChars));
    }

    public BitmapFont(T texture, Dimension charDims, int charsPerRow, HashMap<Character, Integer> charIndices) {
        super();
        this.texture = texture;
        this.charDims = charDims;
        this.charsPerRow = charsPerRow;
        this.charIndices = charIndices;
        ligTable = new HashMap<BitmapFont<T>.LigTableKey, Float>();
    }

    public Sprite<T> getCharSprite(char c) throws InvalidCharacterException {
        Sprite<T> rv = new Sprite<T>(texture, charDims);
        if (!charIndices.containsKey(c)) {
            throw new InvalidCharacterException("invalid char %s");
        }
        int idx = charIndices.get(c);
        int frame = idx % charsPerRow;
        int anim = (idx - frame) / charsPerRow;
        rv.setAnimation(anim);
        rv.setFrame(frame);
        return rv;
    }

    public static HashMap<Character, Integer> generateIndexMap(String validChars) {
        HashMap<Character, Integer> rv = new HashMap<Character, Integer>();
        char[] chars = validChars.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            rv.put(chars[i], i);
        }
        return rv;
    }

    public Dimension getCharDims() {
        return charDims;
    }

    /**
     * sets the kerning in pixels, default is 0.0f
     * 
     * @param left
     *            left character
     * @param right
     *            right character
     * @param kerning
     */
    public void setKerning(char left, char right, float kerning) {
        LigTableKey k = new LigTableKey(left, right);
        ligTable.put(k, kerning);
    }

    public float getKerning(char left, char right) {
        LigTableKey key = new LigTableKey(left, right);
        if (ligTable.containsKey(key)) {
            return ligTable.get(key);
        }
        return 0.0f;
    }

    public float getTracking() {
        return tracking;
    }

    /**
     * sets the letter spacing in pixels
     * 
     * @param tracking
     *            amount of pixels the letters are spaced apart, negative values
     *            mean closer together
     */
    public void setTracking(float tracking) {
        this.tracking = tracking;
    }

    @SuppressWarnings("serial")
    public static class InvalidCharacterException extends Exception {

        public InvalidCharacterException(String message) {
            super(message);
        }

    }

    private class LigTableKey {
        private char left;
        private char right;

        public LigTableKey(char left, char right) {
            super();
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof BitmapFont.LigTableKey) {
                LigTableKey k = (BitmapFont.LigTableKey) o;
                return k.left == left && k.right == right;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return left + right * 31;
        }
    }

}
