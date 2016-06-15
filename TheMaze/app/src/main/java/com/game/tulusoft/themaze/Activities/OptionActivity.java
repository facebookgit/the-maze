package com.game.tulusoft.themaze.Activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.R;
import com.game.tulusoft.themaze.Utilities.Common;
import com.game.tulusoft.themaze.Utilities.CreteBitmapScoreToShare;
import com.game.tulusoft.themaze.Utilities.PlayerInfo;
import com.game.tulusoft.themaze.Utilities.SendAPI;
import com.game.tulusoft.themaze.Utilities.params_Http;

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

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shazam on 5/5/2016.
 */


public class OptionActivity extends BaseGameActivity implements SpriteObjective.SpriteObjectiveTouchListener, SpriteObjective.SpriteObjectiveFinishAnimationListener {

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private BitmapTextureAtlas mFontTexture_Black_60;
    Font mFont_Black_60;

    private ButtonSprite mbtnOption;
    ChangeableText txtWelcom;
//    ChangeableText txtMusic;

    private ButtonSprite mbtnSound;
    private ButtonSprite mbtnRope_Sound;
    private ButtonSprite mbtnKnot_Sound;

    private ButtonSprite mbtnMusic;
    private ButtonSprite mbtnRope_Music;
    private ButtonSprite mbtnKnot_Music;

    private ButtonSprite mbtnStore;
    private ButtonSprite mbtnFacebook;
    private ButtonSprite mbtnArrowBack;


    int KnotEndWidth = 30;
    float startSound_Location;
    float endSound_Location;
    float lenghSound_Location;
    float deltaSound_Location;
    float deltaMusic_Location;

    //region facebook
    LoginButton btnLoginFacebook;
    private CallbackManager callbackManager;
    Profile profile = Profile.getCurrentProfile();
    //endregion

    SendAPI sender_API;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        btnLoginFacebook = new LoginButton(this);

        sender_API = new SendAPI(getBaseContext());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0,0);
        this.addContentView(btnLoginFacebook,params);
        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profileSuccess = Profile.getCurrentProfile();
                if(profileSuccess !=null){
                    Toast.makeText(OptionActivity.this, profileSuccess.getName(), Toast.LENGTH_SHORT).show();
//                    mbtnFacebook.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{1}, 1);
//                    if(profile != null){
//                        String mess = getResources().getString(R.string.facebook_welcom);
//                        txtWelcom.setText(mess + " " + profile.getFirstName());
//                        txtWelcom.setPosition(Common.getmWIDTH() / 2 - txtWelcom.getWidth() /2,txtWelcom.getY());
//                    }


                    params_Http[] login_param = new params_Http[]{new params_Http(Common.API_Login_1, profileSuccess.getId()),
                            new params_Http(Common.API_Login_2, String.valueOf(Common.getPlayerInfo().getMax_n())),
                            new params_Http(Common.API_Login_3, String.valueOf(Common.getPlayerInfo().getMax_m())),
                            new params_Http(Common.API_Login_4, String.valueOf(Common.getPlayerInfo().getMax_h())),
                            new params_Http(Common.API_Login_5, String.valueOf(Common.getPlayerInfo().getMul_n())),
                            new params_Http(Common.API_Login_6, String.valueOf(Common.getPlayerInfo().getMul_m())),
                            new params_Http(Common.API_Login_7, String.valueOf(Common.getPlayerInfo().getMul_h())),
                            new params_Http(Common.API_Login_8, String.valueOf(Common.getPlayerInfo().getCoin())),
                            new params_Http(Common.API_Login_9, String.valueOf(Common.getPlayerInfo().getBugs())),
                            new params_Http(Common.API_Login_10, String.valueOf(Common.getPlayerInfo().getProhibits())),
                            new params_Http(Common.API_Login_11, Common.getPlayerInfo().getMap_trial()),
                            new params_Http(Common.API_Login_12, String.valueOf(Common.getPlayerInfo().getGame_speed())),
                    };
                    sender_API.HTTPConnectServer(login_param, false, "Login ...", "req_login", Request.Method.POST, Common.URL_Login);
                }
            }

            @Override
            public void onCancel() {
//                info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
//                info.setText("Login attempt failed.");
            }
        });

    }
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

        this.mbtnOption = new ButtonSprite(200,30,"menu/","text_option.png",256,128,0,0,1,1,"mbtnOption");
        this.mbtnSound = new ButtonSprite(20,120,"menu/","sound.png",128,64,0,0,2,1,"mbtnSound");
        this.mbtnRope_Sound = new ButtonSprite(100,130,"menu/","rope4.png",512,64,0,0,1,1,"mbtnRope_Sound");
        this.mbtnKnot_Sound = new ButtonSprite(150,130,"menu/","knot.png",128,64,0,0,1,1,"mbtnKnot_Sound");

        this.mbtnMusic = new ButtonSprite(20,180,"menu/","music.png",128,64,0,0,2,1,"mbtnMusic");
        this.mbtnRope_Music = new ButtonSprite(100,190,"menu/","rope5.png",512,64,0,0,1,1,"mbtnRope_Music");
        this.mbtnKnot_Music = new ButtonSprite(150,190,"menu/","knot.png",128,64,0,0,1,1,"mbtnKnot_Music");

        this.mbtnStore = new ButtonSprite(150,300,"menu/","store.png",512,128,0,0,1,1,"mbtnStore");
        this.mbtnFacebook = new ButtonSprite(150,450,"menu/","facebook.png",512,256,0,0,1,2,"mbtnFacebook");
        this.mbtnArrowBack = new ButtonSprite(20,775,"menu/","arrow_back2.png",64,64,0,0,1,1,"mbtnArrowBack");

        txtWelcom = new ChangeableText(20, 650, mFont_Black_60, "login facebook",100);
//        txtMusic = new ChangeableText(20, 170, mFont_Black_40, "Music");
        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

        this.mbtnOption.onLoadResources(mEngine, getBaseContext());
        this.mbtnSound.onLoadResources(mEngine, getBaseContext());
        this.mbtnRope_Sound.onLoadResources(mEngine, getBaseContext());
        this.mbtnKnot_Sound.onLoadResources(mEngine, getBaseContext());

        this.mbtnMusic.onLoadResources(mEngine, getBaseContext());
        this.mbtnRope_Music.onLoadResources(mEngine, getBaseContext());
        this.mbtnKnot_Music.onLoadResources(mEngine, getBaseContext());

        this.mbtnStore.onLoadResources(mEngine, getBaseContext());
        this.mbtnFacebook.onLoadResources(mEngine, getBaseContext());
        this.mbtnArrowBack.onLoadResources(mEngine,getBaseContext());
    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));
        mSprite_bg.attachChild(txtWelcom);
//        mSprite_bg.attachChild(txtMusic);
        this.mbtnOption.onLoadScene(this.mScene);
        this.mbtnSound.onLoadScene(this.mScene);
        this.mbtnRope_Sound.onLoadScene(this.mScene);
        this.mbtnKnot_Sound.onLoadScene(this.mScene);

        this.mbtnMusic.onLoadScene(this.mScene);
        this.mbtnRope_Music.onLoadScene(this.mScene);
        this.mbtnKnot_Music.onLoadScene(this.mScene);

        this.mbtnStore.onLoadScene(this.mScene);
        this.mbtnFacebook.onLoadScene(this.mScene);
        this.mbtnArrowBack.onLoadScene(this.mScene);

        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        this.mbtnOption.setIsButtonFlip(false);
        this.mbtnOption.CenterScreen_Horizontal();

        this.mbtnRope_Sound.setIsButtonFlip(false);
        this.mbtnKnot_Sound.setIsButtonFlip(false);

        this.mbtnRope_Music.setIsButtonFlip(false);
        this.mbtnKnot_Music.setIsButtonFlip(false);

        this.mbtnRope_Sound.mSpriteObjectiveTouchListener = this;
        this.mbtnRope_Music.mSpriteObjectiveTouchListener = this;
        this.mbtnFacebook.mSpriteObjectiveTouchListener = this;
        this.mbtnStore.mSpriteObjectiveFinishAnimationListener = this;
        this.mbtnFacebook.mSpriteObjectiveFinishAnimationListener = this;
        this.mbtnArrowBack.mSpriteObjectiveFinishAnimationListener = this;

        this.mbtnStore.CenterScreen_Horizontal();
        this.mbtnFacebook.CenterScreen_Horizontal();

        startSound_Location = this.mbtnRope_Sound.getPositionX() + KnotEndWidth;
        endSound_Location = this.mbtnRope_Sound.getPositionX() - KnotEndWidth * 4 + this.mbtnRope_Sound.getWidth();
        lenghSound_Location = endSound_Location - startSound_Location;
        deltaSound_Location = Common.getVolume_Sound() * lenghSound_Location + startSound_Location;
        deltaMusic_Location = Common.getVolume_Music() * lenghSound_Location + startSound_Location;

        this.mbtnKnot_Sound.getObject_AnimatedSprite().setPosition(deltaSound_Location, this.mbtnKnot_Sound.getObject_AnimatedSprite().getY());
        this.mbtnKnot_Music.getObject_AnimatedSprite().setPosition(deltaMusic_Location, this.mbtnKnot_Music.getObject_AnimatedSprite().getY());

        if(Common.isLoggedIn()){
            mbtnFacebook.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{1}, 1);
            if(profile != null){
                String mess = getResources().getString(R.string.facebook_welcom);
                txtWelcom.setText(mess + " " + profile.getFirstName());
                txtWelcom.setPosition(Common.getmWIDTH() / 2 - txtWelcom.getWidth() /2,txtWelcom.getY());
            }
        }
        else {
            mbtnFacebook.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{0}, 1);
            txtWelcom.setText(" ");
        }
    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {
        if(_sender.getName().equals(this.mbtnRope_Sound.getName())){
            float KnotX = _event.getX() - this.mbtnKnot_Sound.getWidth() / 2;
            if(KnotX < startSound_Location) KnotX = startSound_Location;
            if(KnotX > endSound_Location) KnotX = endSound_Location;
            this.mbtnKnot_Sound.getObject_AnimatedSprite().setPosition(KnotX, this.mbtnKnot_Sound.getObject_AnimatedSprite().getY());
            Common.setVolume_Sound((KnotX - startSound_Location) / lenghSound_Location );
            Common.setVolumeSound(Common.getVolume_Sound());
        }else if(_sender.getName().equals(this.mbtnRope_Music.getName())){
            float KnotX = _event.getX() - this.mbtnKnot_Music.getWidth() / 2;
            if(KnotX < startSound_Location) KnotX = startSound_Location;
            if(KnotX > endSound_Location) KnotX = endSound_Location;
            this.mbtnKnot_Music.getObject_AnimatedSprite().setPosition(KnotX, this.mbtnKnot_Music.getObject_AnimatedSprite().getY());
            Common.setVolume_Music((KnotX - startSound_Location) / lenghSound_Location );
            Common.theme_Music.setVolume(Common.getVolume_Music());
        }else if(_sender.getName().equals(this.mbtnFacebook.getName())){
            btnLoginFacebook.performClick();
            if(Common.isLoggedIn()){
                if(profile != null){
                    String mess = getResources().getString(R.string.facebook_welcom);
                    txtWelcom.setText(mess + " " + profile.getFirstName());
                    txtWelcom.setPosition(Common.getmWIDTH() / 2 - txtWelcom.getWidth() /2,txtWelcom.getY());
                }
            }
            else {
                txtWelcom.setText(" ");
            }
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

    @Override
    public void FinishAnimation(SpriteObjective _sender) {
        if(_sender.getName().equals(this.mbtnFacebook.getName())){
            if(Common.isLoggedIn()){
                mbtnFacebook.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{1}, 1);
                if(profile != null){
                    String mess = getResources().getString(R.string.facebook_welcom);
                    txtWelcom.setText(mess + " " + profile.getFirstName());
                    txtWelcom.setPosition(Common.getmWIDTH() / 2 - txtWelcom.getWidth() /2,txtWelcom.getY());
                }
            }
            else {
                mbtnFacebook.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{0}, 1);
                txtWelcom.setText(" ");
            }
        }
        else if(_sender.getName().equals(this.mbtnArrowBack.getName())){
            finish();
        }
        else if(_sender.getName().equals(this.mbtnStore.getName())){

            AssetManager assetManager = getBaseContext().getAssets();

            InputStream istr;
            Bitmap bitmap = null;
            try {
                istr = assetManager.open("gameplay/cus_Score_Share_f.png");
                bitmap = BitmapFactory.decodeStream(istr);
            } catch (IOException e) {
                // handle exception
            }

            CreteBitmapScoreToShare creteBitmapScoreToShare = new CreteBitmapScoreToShare(1,2,1,1,1,getBaseContext());

            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(creteBitmapScoreToShare.getmBitmapScore())
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
//            ShareLinkContent content = new ShareLinkContent.Builder()
//                    .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                    .build();

            ShareDialog.show(this,content);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
