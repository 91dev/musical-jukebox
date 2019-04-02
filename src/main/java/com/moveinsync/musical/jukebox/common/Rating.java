package com.moveinsync.musical.jukebox.common;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    public final int rating;

    Rating(int r) {
        this.rating = r;
    }

    public static Rating getRating(final int num) {
        switch (num) {
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            default:
                return null;
        }
    }

    public int getRating() {
        return this.rating;
    }
}

