package es.dmoral.toasty;

import android.graphics.Color;

import androidx.annotation.CheckResult;

/**
 * Provide a simple way to configure shadows for toast texts
 */
public class ShadowOptions {

    /**
     * No shadow at all
     */
    public static ShadowOptions NONE = new ShadowOptions(0,0,0,0);

    /**
     * A drop shadow suitable far from the text to aid legibility
     */
    public static ShadowOptions DROP_SHADOW = new ShadowOptions(8,5,5,Color.BLACK);

    /**
     * An outer 'glow'
     */
    public static ShadowOptions OUTER_GLOW = new ShadowOptions(3,0,0,Color.WHITE);

    private int radius, dx, dy, color;

    public ShadowOptions(int radius, int dx, int dy, int color) {
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    @CheckResult
    public int getColor() {
        return color;
    }

    @CheckResult
    public int getDx() {
        return dx;
    }

    @CheckResult
    public int getDy() {
        return dy;
    }

    @CheckResult
    public int getRadius() {
        return radius;
    }

}
