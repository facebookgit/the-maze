package com.game.tulusoft.themaze.Activities;

import android.graphics.Color;

import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.Objective.TrialItem;
import com.game.tulusoft.themaze.Utilities.Common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

/**
 * Created by Shazam on 5/11/2016.
 */
public class ArrayTrialItemActivity extends BaseGameActivity implements SpriteObjective.SpriteObjectiveTouchListener, SpriteObjective.SpriteObjectiveFinishAnimationListener {

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private BitmapTextureAtlas mFontTexture_Black_60;
    Font mFont_Black_60;

    private TrialItem item;
    ButtonSprite mbtnMainBackground;

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

        FontFactory.setAssetBasePath("fonts/");

        this.mFontTexture_Black_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Black_60 = FontFactory.createFromAsset(this.mFontTexture_Black_60, this, "utmfacebook.ttf", 40, true, Color.BLACK);
        engine.getTextureManager().loadTexture(this.mFontTexture_Black_60);
        engine.getFontManager().loadFont(this.mFont_Black_60);

        item = new TrialItem();
        this.mbtnMainBackground = new ButtonSprite(0,0,"menu/","bg_trial_item.png",128,128,0,0,1,1,"mbtnMainBackground");

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

        item.onLoadResources(mEngine,getBaseContext());
        this.mbtnMainBackground.onLoadResources(mEngine, getBaseContext());
    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));
        item.onLoadScene(this.mScene);
        this.mbtnMainBackground.onLoadScene(mScene);
        return this.mScene;
    }

    @Override
    public void onLoadComplete() {

    }

    @Override
    public void FinishAnimation(SpriteObjective _sender) {

    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {

    }
}
