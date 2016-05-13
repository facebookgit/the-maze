package com.game.tulusoft.themaze.Activities;

import android.graphics.Color;

import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.Utilities.Common;
import com.game.tulusoft.themaze.Utilities.GameLoader;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
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

/**
 * Created by Shazam on 3/9/2016.
 */
public class GamePlayActivity extends BaseGameActivity implements GameLoader.GameLoaderListener,SpriteObjective.SpriteObjectiveTouchListener, SpriteObjective.SpriteObjectiveFinishAnimationListener {

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private ButtonSprite Map;
    private ButtonSprite ScorePanel;
    private ButtonSprite ProcessBar;

    private ButtonSprite mbtnArrowBack;

    //text region

    private BitmapTextureAtlas mFontTexture_Black_60;
    Font mFont_Black_60;

    private BitmapTextureAtlas mFontTexture_Red_60;
    Font mFont_Red_60;

    private BitmapTextureAtlas mFontTexture_Yellow_40;
    Font mFont_Yellow_40;

    ChangeableText txtMultiRoom;
    ChangeableText txtCurrentRoom;

    ChangeableText txtNumMultiRoom;
    ChangeableText txtNumCurrentRoom;

    ChangeableText txtNumCoin;
    ChangeableText txtNumBugs;
    ChangeableText txtNumProhibit;

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

        //declare font region

        FontFactory.setAssetBasePath("fonts/");
        this.mFontTexture_Black_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Black_60 = FontFactory.createFromAsset(this.mFontTexture_Black_60, this, "youmurdererbb_reg.ttf", 60, true, Color.BLACK);

        this.mFontTexture_Red_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Red_60 = FontFactory.createFromAsset(this.mFontTexture_Red_60, this, "youmurdererbb_reg.ttf", 60, true, Color.rgb(180,102,1));

        this.mFontTexture_Yellow_40 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Yellow_40 = FontFactory.createFromAsset(this.mFontTexture_Yellow_40, this, "youmurdererbb_reg.ttf", 40, true, Color.rgb(231,189,21));

        engine.getTextureManager().loadTexture(this.mFontTexture_Black_60);
        engine.getFontManager().loadFont(this.mFont_Black_60);

        engine.getTextureManager().loadTexture(this.mFontTexture_Red_60);
        engine.getFontManager().loadFont(this.mFont_Red_60);

        engine.getTextureManager().loadTexture(this.mFontTexture_Yellow_40);
        engine.getFontManager().loadFont(this.mFont_Yellow_40);

        txtMultiRoom = new ChangeableText(60, 80, mFont_Black_60, "Multi :");
        txtCurrentRoom = new ChangeableText(60, 120, mFont_Black_60, "Curent :");

        txtNumMultiRoom = new ChangeableText(180, 80, mFont_Red_60, "0",5);
        txtNumCurrentRoom = new ChangeableText(210, 120, mFont_Red_60, "1",5);

        txtNumCoin = new ChangeableText(340, 34, mFont_Red_60, "0",5);
        txtNumBugs = new ChangeableText(340, 79, mFont_Red_60, "0",5);
        txtNumProhibit = new ChangeableText(340, 124, mFont_Red_60, "0",5);

        this.ScorePanel = new ButtonSprite(5,10,"gameplay/","score_panel.png",1024,256,0,0,1,1,"ScorePanel");
        this.Map = new ButtonSprite(22,200,"gameplay/","map_gray_border.png",1024,1024,0,0,1,1,"Map");
        this.ProcessBar = new ButtonSprite(22,200,"gameplay/","process_all.png",2048,1024,0,0,3,7,"ProcessBar");
        this.mbtnArrowBack = new ButtonSprite(70,35,"menu/","arrow_back2.png",64,64,0,0,1,1,"mbtnArrowBack");

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);
        this.mbtnArrowBack.onLoadResources(mEngine,getBaseContext());

        this.ScorePanel.onLoadResources(mEngine, getBaseContext());
        this.Map.onLoadResources(mEngine, getBaseContext());
        this.ProcessBar.onLoadResources(mEngine, getBaseContext());


    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));

        this.mbtnArrowBack.onLoadScene(this.mScene);
        this.ScorePanel.onLoadScene(this.mScene);
        this.Map.onLoadScene(this.mScene);
        this.ProcessBar.onLoadScene(this.mScene);

        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        this.mbtnArrowBack.moveToHide();
        this.ScorePanel.setIsButtonFlip(false);
        this.ScorePanel.moveToHide();
        this.Map.setIsButtonFlip(false);
        this.Map.moveToHide();
        this.ProcessBar.setIsButtonFlip(false);
        this.ProcessBar.CenterScreen();

        GameLoader loader = new GameLoader(10);
        loader.execute("");
        loader.mLoadComplete = this;
    }

    @Override
    public void LoadComplete() {

        if(this.ProcessBar!= null){
            this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{20}, 1);
        }
        MoveModifier movescorepanel = new MoveModifier(0.2f,this.ScorePanel.object_AnimatedSprite.getX(),5,this.ScorePanel.object_AnimatedSprite.getY(),10);
        this.ScorePanel.object_AnimatedSprite.registerEntityModifier(movescorepanel);
        movescorepanel = new MoveModifier(0.2f,this.Map.object_AnimatedSprite.getX(),22,this.Map.object_AnimatedSprite.getY(),200);
        this.Map.object_AnimatedSprite.registerEntityModifier(movescorepanel);
        movescorepanel = new MoveModifier(0.2f,this.Map.object_AnimatedSprite.getX(),70,this.Map.object_AnimatedSprite.getY(),35);
        this.mbtnArrowBack.object_AnimatedSprite.registerEntityModifier(movescorepanel);
        this.mbtnArrowBack.mSpriteObjectiveFinishAnimationListener = this;

        MoveYModifier moveY = new MoveYModifier(0.2f,this.ProcessBar.object_AnimatedSprite.getY(),750){
            @Override
            protected void onModifierFinished(IEntity pItem) {
                super.onModifierFinished(pItem);

                mSprite_bg.attachChild(txtMultiRoom);
                mSprite_bg.attachChild(txtCurrentRoom);

                mSprite_bg.attachChild(txtNumMultiRoom);
                mSprite_bg.attachChild(txtNumCurrentRoom);

                mSprite_bg.attachChild(txtNumCoin);
                mSprite_bg.attachChild(txtNumBugs);
                mSprite_bg.attachChild(txtNumProhibit);

                txtNumMultiRoom.setText(String.valueOf(Common.getMulGameStory()));

                txtNumCoin.setText(String.valueOf(Common.getCoin_Local()));
                txtNumBugs.setText(String.valueOf(Common.getCount_Bugs()));
                txtNumProhibit.setText(String.valueOf(Common.getCount_Prohibit()));
            }
        };
        this.ProcessBar.object_AnimatedSprite.registerEntityModifier(moveY);

    }

    @Override
    public void iProcessChange(int iProcess) {
        if(this.ProcessBar!= null){
            this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{
                    iProcess / 5}, 1);

            System.out.println("Sprite Process : " + String.valueOf(iProcess));
        }
    }

    @Override
    public void FinishAnimation(SpriteObjective _sender) {
        if(_sender.getName().equals(this.mbtnArrowBack.getName())){
            finish();
        }
    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {

    }
}
