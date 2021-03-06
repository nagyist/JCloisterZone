package com.jcloisterzone.board;

/**
 * Enumerates all possible edge types. An edge can contain a farm, a river, a city or a road. An unknown type is also
 * provided.
 */
public enum EdgeType {
    ROAD(0b0001, 'R'),
    CITY(0b0010, 'C'),
    FARM(0b0100, 'F'),
    RIVER(0b1000, 'I'),
    UNKNOWN(0b1111, '?');

    private int mask;
    private char ch;

    /**
     * Constructs a new instance.
     *
     * @param mask the mask for the new instance
     * @param ch an id for the new instance
     */
    EdgeType(int mask, char ch) {
        this.mask = mask;
        this.ch = ch;
    }

    /**
     * Returns the {@code mask} of the instance.
     * @return the {@code mask} of the instance
     */
    public int getMask() {
        return mask;
    }

    /**
     * Returns the {@code ch} (identifier) of the instance.
     * @return the {@code ch} (identifier) of the instance
     */
    public char asChar() {
        return ch;
    }

    /**
     * Returns the instance with the given {@code mask}.
     * @param mask the mask to search
     * @return the instance with the given {@code mask}
     * @throws IllegalArgumentException if {@code mask} does not match any instance
     */
    static EdgeType forMask(int mask) {
        for (EdgeType e : values()) {
            if (e.mask == mask) return e;
        }
        throw new IllegalArgumentException("Invalid Edge mask " + mask);
    }

    /**
     * Returns the instance with the given {@code ch} (identifier).
     * @param ch the ch to search
     * @return the instance with the given {@code ch} (identifier)
     * @throws IllegalArgumentException if {@code ch} does not match any instance
     */
    static EdgeType forChar(int ch) {
        for (EdgeType e : values()) {
            if (e.ch == ch) return e;
        }
        throw new IllegalArgumentException("Unknown edge " + ch);
    }
}