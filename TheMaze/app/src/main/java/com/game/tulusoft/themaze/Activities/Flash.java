package com.game.tulusoft.themaze.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.Profile;
import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Utilities.Common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

/**
 * Created by Shazam on 3/29/2016.
 */
public class Flash extends BaseGameActivity {

    private Camera mCamera;
    private Scene mScene;


    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;


    private ButtonSprite mLoGo;
    private ButtonSprite mLoading;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        Profile profile = Profile.getCurrentProfile();
        if(profile != null) {
            Toast.makeText(Flash.this, getWelcome(profile), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera).setNeedsSound(true).setNeedsMusic(true));

        this.mLoGo = new ButtonSprite(100,250,"menu/","logo.png",512,256,0,0,1,1,"mLoGo");
        this.mLoading = new ButtonSprite(100,800,"menu/","loading.png",128,32,0,0,1,1,"mLoading");

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

        this.mLoGo.onLoadResources(mEngine, getBaseContext());
        this.mLoading.onLoadResources(mEngine, getBaseContext());
    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));


        this.mLoGo.onLoadScene(this.mScene);
        this.mLoading.onLoadScene(this.mScene);
        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        float value_Volume_Sound = Common.getPref_config(getApplicationContext()).getFloat(Common.Key_Config_Volume_Sound, 0.5f);
        float value_Volume_music = Common.getPref_config(getApplicationContext()).getFloat(Common.Key_Config_Volume_Music, 0.5f);
        Boolean value_Sound = Common.getPref_config(getApplicationContext()).getBoolean(Common.Key_Config_Sound, true);
        int value_Max_Room_N = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Max_N, 1);
        int value_Mul_Room_N = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Multi_N, 0);
        int value_Max_Room_M = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Max_M, 1);
        int value_Mul_Room_M = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Multi_M, 0);
        int value_Max_Room_H = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Max_H, 1);
        int value_Mul_Room_H = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Multi_H, 0);
        int value_Level = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Level, 0);

        int value_Count_Prohibit = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Count_Prohibit, 0);
        int value_Count_Bugs = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Count_Bugs, 0);
        int value_Coin_Local = Common.getPref_config(getApplicationContext()).getInt(Common.Key_Config_Coin_Local, 0);

        String value_Map_Trial = Common.getPref_config(getApplicationContext()).getString(Common.Key_Config_Map_Trial, "0,");

        Common.setMax_Room_N(value_Max_Room_N);
        Common.setMulti_Room_N(value_Mul_Room_N);
        Common.setMax_Room_M(value_Max_Room_M);
        Common.setMulti_Room_M(value_Mul_Room_M);
        Common.setMax_Room_H(value_Max_Room_H);
        Common.setMulti_Room_H(value_Mul_Room_H);

        Common.setCount_Prohibit(value_Count_Prohibit);
        Common.setCount_Bugs(value_Count_Bugs);
        Common.setCoin_Local(value_Coin_Local);

        Common.setMap_Trial(value_Map_Trial);

        Common.setiLevel(value_Level);

        Common.setIsSound(value_Sound);

        Common.setVolume_Sound(value_Volume_Sound);
        Common.setVolume_Music(value_Volume_music);

        this.mLoGo.CenterScreen();
        this.mLoading.CenterScreen();
        this.mLoading.object_AnimatedSprite.setPosition(this.mLoading.object_AnimatedSprite.getX(), this.mLoading.object_AnimatedSprite.getY() + 150);

        Intent i = new Intent(Flash.this, MainActivity.class);
        Flash.this.startActivity(i);
        finish();
    }

    private String getWelcome(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("Welcome ").append(profile.getName());
        }
        return stringBuffer.toString();
    }
}
