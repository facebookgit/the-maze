package com.game.tulusoft.themaze.Objective;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.game.tulusoft.themaze.Activities.GamePlayActivity;
import com.game.tulusoft.themaze.Interface.InterfaceSprite;
import com.game.tulusoft.themaze.Utilities.Common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;

import java.util.ArrayList;

/**
 * Created by Shazam on 5/11/2016.
 */
public class TrialItem implements InterfaceSprite, SpriteObjective.SpriteObjectiveFinishAnimationListener {

    ButtonSprite mbtnMainBackground;
//    ButtonSprite mbtnHead1;

    ChangeableText txtNumRoom;
    int inumroom = 0;
    Context mMainArrayContext;

    private ArrayList<ButtonSprite> arrHeadBone;
    private boolean isActive = false;


    public TrialItem(int _inumroom, int _iheadbone, int _x, int _y, Font _font1, Font _font2, boolean _active){
        Font font = _font2;
        if(_active) font = _font1;
        this.mbtnMainBackground = new ButtonSprite(_x, _y, "menu/", "bg_trip_headbone.png", 128, 256, 0, 0, 1, 1,"mbtnMainBackground");
        txtNumRoom = new ChangeableText(20, 60, font, String.valueOf(_inumroom),2);
        inumroom = _inumroom;
        isActive = _active;
        arrHeadBone = new ArrayList<>();
        for(int i = 0; i < _iheadbone ; i++){
            ButtonSprite mbtnHead = new ButtonSprite(i * 35,103,"menu/","head_bone_g.png",64,64,0,0,1,1,"mbtnHead");
            arrHeadBone.add(mbtnHead);
        }
    }

    @Override
    public void onLoadResources(Engine mEngine, Context mContext) {
        this.mbtnMainBackground.onLoadResources(mEngine, mContext);
        for(int i = 0; i < arrHeadBone.size() ; i++){
            arrHeadBone.get(i).onLoadResources(mEngine, mContext);
        }
        mMainArrayContext = mContext;
    }

    @Override
    public void onLoadScene(Scene mScene) {
        this.mbtnMainBackground.onLoadScene(mScene);
        this.mbtnMainBackground.object_AnimatedSprite.attachChild(txtNumRoom);
        txtNumRoom.setPosition(this.mbtnMainBackground.object_AnimatedSprite.getWidth() / 2  - txtNumRoom.getWidth() / 2,
                (this.mbtnMainBackground.object_AnimatedSprite.getHeight() - 40) / 2 - txtNumRoom.getHeight() / 2);
        if(isActive) {
            this.mbtnMainBackground.mSpriteObjectiveFinishAnimationListener = this;
        }
        this.mbtnMainBackground.setIsButtonFlip(isActive);

        for(int i = 0; i < arrHeadBone.size() ; i++){
            arrHeadBone.get(i).createScene();
            mbtnMainBackground.object_AnimatedSprite.attachChild(arrHeadBone.get(i).object_AnimatedSprite);
        }
    }

    @Override
    public void FinishAnimation(SpriteObjective _sender) {
        if(_sender.getName().equals(this.mbtnMainBackground.getName())){
            Intent i = new Intent(mMainArrayContext, GamePlayActivity.class);
            i.putExtra(Common.Key_Game_Select_Map_Trial,inumroom);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mMainArrayContext.startActivity(i);
        }
    }
}
