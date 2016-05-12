package com.game.tulusoft.themaze.Activities;

import android.graphics.Color;
import android.os.Bundle;

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
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import java.util.ArrayList;

/**
 * Created by Shazam on 5/11/2016.
 */
public class ArrayTrialItemActivity extends BaseGameActivity implements SpriteObjective.SpriteObjectiveTouchListener, SpriteObjective.SpriteObjectiveFinishAnimationListener {

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private BitmapTextureAtlas mFontTexture_Black_40;
    Font mFont_Black_40;

    private BitmapTextureAtlas mFontTexture_Black_100_blood;
    Font mFont_Black_100_blood;

    private BitmapTextureAtlas mFontTexture_Gray_100_blood;
    Font mFont_Gray_100_blood;

    private ArrayList<TrialItem> listTrial_Item;
    ChangeableText txtTitle;

    int[] mMap_Trial;

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

        FontFactory.setAssetBasePath("fonts/");

        this.mFontTexture_Black_40 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Black_40 = FontFactory.createFromAsset(this.mFontTexture_Black_40, this, "youmurdererbb_reg.ttf", 100, true, Color.BLACK);
        engine.getTextureManager().loadTexture(this.mFontTexture_Black_40);
        engine.getFontManager().loadFont(this.mFont_Black_40);

        this.mFontTexture_Black_100_blood = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Black_100_blood = FontFactory.createFromAsset(this.mFontTexture_Black_100_blood, this, "youmurdererbb_reg.ttf", 100, true, Color.BLACK);
        engine.getTextureManager().loadTexture(this.mFontTexture_Black_100_blood);
        engine.getFontManager().loadFont(this.mFont_Black_100_blood);

        this.mFontTexture_Gray_100_blood = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Gray_100_blood = FontFactory.createFromAsset(this.mFontTexture_Gray_100_blood, this, "youmurdererbb_reg.ttf", 100, true, Color.GRAY);
        engine.getTextureManager().loadTexture(this.mFontTexture_Gray_100_blood);
        engine.getFontManager().loadFont(this.mFont_Gray_100_blood);


        txtTitle = new ChangeableText(20, 10, mFont_Black_40, "select map");

        String[] strMap_Trial = Common.getMap_Trial().split(",");
        int countMapActive = strMap_Trial.length;
        mMap_Trial = new int[countMapActive];

        for(int i = 0; i < countMapActive ; i++){
            mMap_Trial[i] =  Integer.valueOf( strMap_Trial[i]);
        }

        listTrial_Item = new ArrayList<>();
        for(int i = 0 ; i < 25 ; i++){
            TrialItem item;
            item = new TrialItem(i + 5, i < countMapActive ? mMap_Trial[i] : 0, (i % 5) * 105 + 20, i / 5 * 145 + 105,this.mFont_Black_100_blood,this.mFont_Gray_100_blood ,i < countMapActive);
            listTrial_Item.add(item);
        }

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

        for(int i = 0 ; i < 25 ; i++){
            listTrial_Item.get(i).onLoadResources(mEngine,getBaseContext());
        }
    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));
        this.mScene.attachChild(txtTitle);
        for(int i = 0 ; i < 25 ; i++){
            listTrial_Item.get(i).onLoadScene(this.mScene);
        }
        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        txtTitle.setPosition(Common.getmWIDTH() / 2 - txtTitle.getWidth() / 2, 10);
    }

    @Override
    public void FinishAnimation(SpriteObjective _sender) {

    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {

    }
}
