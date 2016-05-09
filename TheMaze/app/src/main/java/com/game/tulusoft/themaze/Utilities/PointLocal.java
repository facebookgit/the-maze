package com.game.tulusoft.themaze.Utilities;

/**
 * Created by Shazam on 3/20/2016.
 */
public class PointLocal {
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int[] getNextPoint() {
        return nextPoint;
    }

    public void setNextPoint(int[] nextPoint) {
        this.nextPoint = nextPoint;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public PointLocal getParent() {
        return parent;
    }

    public void setParent(PointLocal parent) {
        this.parent = parent;
    }

    private int index;
    private int[] nextPoint;
    private boolean isChecked;

    private PointLocal parent;

    public PointLocal()
    {
        this.index = -1;
        this.nextPoint = new int[4];
        this.isChecked = false;
        parent = null;
    }
}
