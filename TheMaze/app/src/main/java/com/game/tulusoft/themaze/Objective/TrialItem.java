package com.game.tulusoft.themaze.Objective;

import android.content.Context;

import com.game.tulusoft.themaze.Interface.InterfaceSprite;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

/**
 * Created by Shazam on 5/11/2016.
 */
public class TrialItem implements InterfaceSprite {

    ButtonSprite mbtnMainBackground;

    public TrialItem(){
        this.mbtnMainBackground = new ButtonSprite(0,0,"menu/","bg_trial_item.png",128,128,0,0,1,1,"mbtnMainBackground");
    }

    @Override
    public void onLoadResources(Engine mEngine, Context mContext) {
        this.mbtnMainBackground.onLoadResources(mEngine, mContext);
    }

    @Override
    public void onLoadScene(Scene mScene) {
        this.mbtnMainBackground.onLoadScene(mScene);
    }
}
