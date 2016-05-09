package com.game.tulusoft.themaze.Interface;

import android.content.Context;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

/**
 * Created by Shazam on 2/27/2016.
 */
public interface InterfaceSprite {
    public void onLoadResources(Engine mEngine, Context mContext);
    public void onLoadScene(Scene mScene);
}