package com.game.tulusoft.themaze.Activities;

import android.content.Intent;

import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.Utilities.Common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

/**
 * Created by Shazam on 2/29/2016.
 */
public class SelectPlayMode extends BaseGameActivity implements SpriteObjective.SpriteObjectiveTouchListener,SpriteObjective.SpriteObjectiveFinishAnimationListener {

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private ButtonSprite mbtnTextLevel;
    private ButtonSprite mbtnTextHard;
    private ButtonSprite mbtnTextMedium;
    private ButtonSprite mbtnTextNormal;
    private ButtonSprite mbtnTextMode;
    private ButtonSprite mbtnTextStory;
    private ButtonSprite mbtnTextTrial;
    private ButtonSprite mbtnArrowBack;

    private BitmapTextureAtlas mFontTexture;

    Font mFont;

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

        this.mbtnTextLevel = new ButtonSprite(140,15,"menu/","text_level.png",512,128,0,0,1,1,"mbtnTextLevel");
        this.mbtnTextHard = new ButtonSprite(140,120,"menu/","text_hard.png",1024,128,0,0,2,1,"mbtnTextHard");
        this.mbtnTextMedium = new ButtonSprite(100,225,"menu/","text_medium.png",1024,128,0,0,2,1,"mbtnTextMedium");
        this.mbtnTextNormal = new ButtonSprite(100,330,"menu/","text_normal.png",1024,128,0,0,2,1,"mbtnTextNormal");
        this.mbtnTextMode = new ButtonSprite(140,400,"menu/","text_mode.png",512,128,0,0,1,1,"mbtnTextMode");
        this.mbtnTextStory = new ButtonSprite(170,500,"menu/","text_story.png",256,256,0,0,1,1,"mbtnTextStory");
        this.mbtnTextTrial = new ButtonSprite(170,650,"menu/","text_trial.png",256,128,0,0,1,1,"mbtnTextTrial");
        this.mbtnArrowBack = new ButtonSprite(10,775,"menu/","arrow_back.png",512,128,0,0,1,1,"mbtnArrowBack");

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

//        FontFactory.setAssetBasePath("fonts/");
//        this.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//        this.mFont_Black_40 = FontFactory.createFromAsset(this.mFontTexture, this, "youmurdererbb_reg.ttf", 100, true, Color.GREEN);
//
//        this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
//        this.mEngine.getFontManager().loadFont(this.mFont_Black_40);

        this.mbtnTextLevel.onLoadResources(mEngine,getBaseContext());
        this.mbtnTextHard.onLoadResources(mEngine,getBaseContext());
        this.mbtnTextMedium.onLoadResources(mEngine,getBaseContext());
        this.mbtnTextNormal.onLoadResources(mEngine,getBaseContext());
        this.mbtnTextMode.onLoadResources(mEngine,getBaseContext());
        this.mbtnTextStory.onLoadResources(mEngine,getBaseContext());
        this.mbtnTextTrial.onLoadResources(mEngine,getBaseContext());
        this.mbtnArrowBack.onLoadResources(mEngine,getBaseContext());

    }

    @Override
    public Scene onLoadScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);

        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));



//        Line line = new Line(Common.getmWIDTH()/2,0,Common.getmWIDTH()/2,Common.getmHEIGHT(),2);
//        line.setColor(255,0,255);
//        mSprite_bg.attachChild(line);

//        Text abc = new Text(100, 100, mFont_Black_40, "tuyen", HorizontalAlign.CENTER);
//        mSprite_bg.attachChild(abc);


        this.mbtnTextLevel.onLoadScene(this.mScene);

        this.mbtnTextHard.onLoadScene(this.mScene);
        this.mbtnTextMedium.onLoadScene(this.mScene);
        this.mbtnTextNormal.onLoadScene(this.mScene);

        switch (Common.getiLevel()){
            case Common.LevelHard:
                this.mbtnTextHard.object_AnimatedSprite.animate(new long[]{1}, new int[]{1}, 1);
                break;
            case Common.LevelMedium:
                this.mbtnTextMedium.object_AnimatedSprite.animate(new long[]{1}, new int[]{1}, 1);
                break;
            case Common.LevelNormal:
                this.mbtnTextNormal.object_AnimatedSprite.animate(new long[]{1}, new int[]{1}, 1);
                break;
        }

        this.mbtnTextMode.onLoadScene(this.mScene);
        this.mbtnTextStory.onLoadScene(this.mScene);
        this.mbtnTextTrial.onLoadScene(this.mScene);
        this.mbtnArrowBack.onLoadScene(this.mScene);

        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        this.mbtnTextStory.mSpriteObjectiveFinishAnimationListener = this;
        this.mbtnTextTrial.mSpriteObjectiveFinishAnimationListener = this;
        this.mbtnTextHard.mSpriteObjectiveTouchListener = this ;
        this.mbtnTextMedium.mSpriteObjectiveTouchListener = this ;
        this.mbtnTextNormal.mSpriteObjectiveTouchListener = this;
        this.mbtnArrowBack.mSpriteObjectiveFinishAnimationListener = this;
    }


    @Override
    public void FinishAnimation(SpriteObjective _sender) {
        if(_sender.getName().equals(this.mbtnTextStory.getName())){
            Intent i = new Intent(SelectPlayMode.this, GameStoryActivity.class);
            SelectPlayMode.this.startActivity(i);
        }
        else if(_sender.getName().equals(this.mbtnArrowBack.getName())){
            finish();
        }else if(_sender.getName().equals(this.mbtnTextTrial.getName())){
            Intent i = new Intent(SelectPlayMode.this, ArrayTrialItemActivity.class);
            SelectPlayMode.this.startActivity(i);
        }
    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {
        if(_sender.getName().equals(this.mbtnTextHard.getName())){
            mbtnTextHard.object_AnimatedSprite.animate(new long[]{1}, new int[]{1}, 1);
            mbtnTextMedium.object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
            mbtnTextNormal.object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
            Common.setiLevel(Common.LevelHard);
        }
        else if(_sender.getName().equals(this.mbtnTextMedium.getName())){
            mbtnTextHard.object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
            mbtnTextMedium.object_AnimatedSprite.animate(new long[]{1}, new int[]{1}, 1);
            mbtnTextNormal.object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
            Common.setiLevel(Common.LevelMedium);
        }else if(_sender.getName().equals(this.mbtnTextNormal.getName())){
            mbtnTextHard.object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
            mbtnTextMedium.object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
            mbtnTextNormal.object_AnimatedSprite.animate(new long[]{1}, new int[]{1}, 1);
            Common.setiLevel(Common.LevelNormal);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Common.CommitSharedPreferent(getApplicationContext());
        if(Common.theme_Music != null){
            Common.theme_Music.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Common.theme_Music != null && Common.getIsSound()){
            Common.theme_Music.play();
        }
    }
}
