package com.game.tulusoft.themaze.Utilities;

/**
 * Created by Shazam on 6/13/2016.
 */
public class params_Http {
    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    private String mKey;
    private String mValue;

    public params_Http(String _Key, String _Value){
        this.mKey = _Key;
        this.mValue = _Value;
    }
    public params_Http(){
        this.mKey = "";
        this.mValue = "";
    }
}
