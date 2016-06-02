package com.game.tulusoft.themaze.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.Utilities.Algorithms;
import com.game.tulusoft.themaze.Utilities.Common;
import com.game.tulusoft.themaze.Utilities.CreteBitmapScoreToShare;
import com.game.tulusoft.themaze.Utilities.GameLoader;
import com.game.tulusoft.themaze.Utilities.GameStory;
import com.game.tulusoft.themaze.Utilities.PointArray;
import com.game.tulusoft.themaze.Utilities.PointLocal;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
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
import org.anddev.andengine.util.modifier.IModifier;

import java.util.ArrayList;

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
    private ButtonSprite ConfirmPanel;
    private ButtonSprite ScorePanel;
    private ButtonSprite ProcessBar;

    private ButtonSprite mbtnArrowBack;

    //text region

    private BitmapTextureAtlas mFontTexture_Black_60;
    Font mFont_Black_60;

    private BitmapTextureAtlas mFontTexture_Red_60;
    Font mFont_Red_60;

    private BitmapTextureAtlas mFontTexture_Blue_80;
    Font mFont_Blue_80;

    private BitmapTextureAtlas mFontTexture_Yellow_40;
    Font mFont_Yellow_40;

    ChangeableText txtMap;
    ChangeableText txtMultiRoom;
    ChangeableText txtCurrentRoom;

    ChangeableText txtNumMap;
    ChangeableText txtNumMultiRoom;
    ChangeableText txtNumCurrentRoom;

    ChangeableText txtNumCoin;
    ChangeableText txtNumBugs;
    ChangeableText txtNumProhibit;

    ChangeableText txtNumScore;

    ChangeableText txt_Game_Over_Result;

    private int mMapSelected = 0;

    // game process

    private ButtonSprite mCoin;
    private ButtonSprite mBug;
    private ButtonSprite mProhibit;

    private ButtonSprite mLockDoor;

    private ButtonSprite mRec_Coin;
    private ButtonSprite mRec_Bug;
    private ButtonSprite mRec_Prohibit;

    private ButtonSprite mbtnNext;
    private ButtonSprite mbtnReplay;
    private ButtonSprite mbtnShare;

    private ButtonSprite mHeadBone_Tiny_x1;
    private ButtonSprite mHeadBone_Tiny_x2;
    private ButtonSprite mHeadBone_Tiny_x3;

    GameStory gameStory= new GameStory();
    ButtonSprite[] arrTrap;
    ButtonSprite[] arrWallH;
    ButtonSprite[] arrWallV;

    ButtonSprite mummy;

    ArrayList<Integer> allObjectOnMap = new ArrayList<>();

    GameLoader loader;
    GameLoader loader2;

    int iCurrTrap = 0;
    int paddingLeft_Map = 12;
    int paddingTop_Map = 190;
    int imultiChoice = 0;
    int iStart = -1;
    int iRandomProhibit;
    int mPointHead = 3;

    int baseSpeed = Common.baseSpeed;
    int object_width = Common.getObject_width();

    boolean isClick = true;
    boolean isShowConfirmPanel = false;
    boolean isClickProhibit = true;
    boolean isClickBugs = true;
    boolean isRunning = true;

    int[][] mummyAction = new int[4][];
    int[][] coinAction = new int[3][];
    private Handler handler = new Handler();
    private Thread ScoredThread;
    int baseTime = 5;
    double mScore = 0;
    int iOneSec = 1000;
    int iOneMinus = 60;
    double mTotalTime = 0;
    int BaseScore = 200;

    int mUsedBug = 0;
    int mUsedProhibit = 0;
    boolean isUsedBug = true;

    float HeadBone_SpriteAlpha = 0;

    CreteBitmapScoreToShare mCreateBitMapShareFacebook;

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

        this.ScorePanel = new ButtonSprite(5,10,"gameplay/","score_panel.png",1024,256,0,0,1,1,"ScorePanel");
        this.ConfirmPanel = new ButtonSprite(0,-548,"menu/","confirm_panel_rope_color.png",512,1024,0,0,1,1,"ConfirmPanel");
        this.Map = new ButtonSprite(22,200,"gameplay/","map_gray_border.png",1024,1024,0,0,1,1,"Map");
        this.ProcessBar = new ButtonSprite(22,200,"gameplay/","process_all.png",2048,1024,0,0,3,7,"ProcessBar");
        this.mbtnArrowBack = new ButtonSprite(20,775,"menu/","arrow_back2.png",64,64,0,0,1,1,"mbtnArrowBack");
        this.mummy = new ButtonSprite(-object_width,0,"Objective/","mummy_52px.png",512,256,0,0,5,4,"mummy");


        this.mCoin = new ButtonSprite(-object_width,object_width,"gameplay/","coin_glass6x6.png",512,512,0,0,6,6,"mCoin");
        this.mBug = new ButtonSprite(-object_width,object_width,"gameplay/","bug29x29.png",64,64,0,0,1,1,"mBug");
        this.mProhibit = new ButtonSprite(-object_width,object_width,"gameplay/","prohibit29x29.png",64,64,0,0,1,1,"mProhibit");

        this.mLockDoor = new ButtonSprite(-object_width,object_width,"gameplay/","lock_door.png",64,64,0,0,1,1,"mLockDoor");

        this.mRec_Coin = new ButtonSprite(340,37,"gameplay/","rec_coin.png",64,64,0,0,1,1,"mRec_Coin");
        this.mRec_Bug = new ButtonSprite(340,82,"gameplay/","rec_bug.png",64,64,0,0,1,1,"mRec_Bug");
        this.mRec_Prohibit = new ButtonSprite(340,127,"gameplay/","rec_prohibit.png",64,64,0,0,1,1,"mRec_Prohibit");

        this.mbtnNext = new ButtonSprite(60,460,"gameplay/","next_button.png",64,64,0,0,1,1,"mbtnNext");
        this.mbtnReplay = new ButtonSprite(160,460,"gameplay/","replay_button.png",128,128,0,0,1,1,"mbtnReplay");
        this.mbtnShare = new ButtonSprite(260,460,"gameplay/","share_button.png",128,64,0,0,1,1,"mbtnShare");

        this.mHeadBone_Tiny_x1 = new ButtonSprite(0,0,"gameplay/","headbone_tiny_x1.png",64,64,0,0,1,1,"mHeadBone_Tiny_x1");
        this.mHeadBone_Tiny_x2 = new ButtonSprite(100,0,"gameplay/","headbone_tiny_x2.png",64,64,0,0,1,1,"mHeadBone_Tiny_x2");
        this.mHeadBone_Tiny_x3 = new ButtonSprite(200,0,"gameplay/","headbone_tiny_x3.png",64,64,0,0,1,1,"mHeadBone_Tiny_x3");

        arrTrap = new ButtonSprite[Common.getButtonLevel() +1];
        arrWallH = new ButtonSprite[60];
        arrWallV = new ButtonSprite[60];


        for (int i = 0;i<arrTrap.length;i++) {
            arrTrap[i] = new ButtonSprite(-object_width, -object_width, "gameplay/", "trapx10.png", 1024, 256, 0, 0, 5, 2,"Trap");
        }
        for (int i = 0;i < 60;i++) {
            arrWallH[i] = new ButtonSprite(-60,0,"gameplay/","wallH.png",64,8,0,0,1,1,"wall");
            arrWallV[i] = new ButtonSprite(-10,0,"gameplay/","wallV.png",8,64,0,0,1,1,"wall");
        }

        //declare font region

        FontFactory.setAssetBasePath("fonts/");
        this.mFontTexture_Black_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Black_60 = FontFactory.createFromAsset(this.mFontTexture_Black_60, this, "youmurdererbb_reg.ttf", 60, true, Color.BLACK);

        this.mFontTexture_Red_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Red_60 = FontFactory.createFromAsset(this.mFontTexture_Red_60, this, "youmurdererbb_reg.ttf", 60, true, Color.rgb(180,102,1));

        this.mFontTexture_Blue_80 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Blue_80 = FontFactory.createFromAsset(this.mFontTexture_Blue_80, this, "youmurdererbb_reg.ttf", 60, true, Color.rgb(59,176,230));

        this.mFontTexture_Yellow_40 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Yellow_40 = FontFactory.createFromAsset(this.mFontTexture_Yellow_40, this, "youmurdererbb_reg.ttf", 50, true, Color.rgb(30,38,11));

        engine.getTextureManager().loadTexture(this.mFontTexture_Black_60);
        engine.getFontManager().loadFont(this.mFont_Black_60);

        engine.getTextureManager().loadTexture(this.mFontTexture_Red_60);
        engine.getFontManager().loadFont(this.mFont_Red_60);

        engine.getTextureManager().loadTexture(this.mFontTexture_Blue_80);
        engine.getFontManager().loadFont(this.mFont_Blue_80);

        engine.getTextureManager().loadTexture(this.mFontTexture_Yellow_40);
        engine.getFontManager().loadFont(this.mFont_Yellow_40);

        txtMap = new ChangeableText(60, 40, mFont_Black_60, "score :");
        txtMultiRoom = new ChangeableText(60, 80, mFont_Black_60, "Multi :");
        txtCurrentRoom = new ChangeableText(60, 120, mFont_Black_60, "Curent :");

        txtNumMap = new ChangeableText(155, 40, mFont_Red_60, "5",5);
        txtNumMultiRoom = new ChangeableText(180, 80, mFont_Red_60, "0",5);
        txtNumCurrentRoom = new ChangeableText(210, 120, mFont_Red_60, "1/5",5);

        txtNumCoin = new ChangeableText(390, 34, mFont_Red_60, "0",5);
        txtNumBugs = new ChangeableText(390, 79, mFont_Red_60, "0",5);
        txtNumProhibit = new ChangeableText(390, 124, mFont_Red_60, "0",5);

        txtNumScore = new ChangeableText(200, 40, mFont_Blue_80, "0231",6);

        txt_Game_Over_Result = new ChangeableText(100, 410, mFont_Yellow_40, "mission completed");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMapSelected = extras.getInt(Common.Key_Game_Select_Map_Trial,5);
        }

        //Create Bitmap share Facebook
        mCreateBitMapShareFacebook = new CreteBitmapScoreToShare(mMapSelected,0,0,0,0,getBaseContext());

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);
        this.mbtnArrowBack.onLoadResources(mEngine,getBaseContext());

        this.ScorePanel.onLoadResources(mEngine, getBaseContext());
        this.ConfirmPanel.onLoadResources(mEngine, getBaseContext());
        this.Map.onLoadResources(mEngine, getBaseContext());
        this.ProcessBar.onLoadResources(mEngine, getBaseContext());

        this.mummy.onLoadResources(mEngine, getBaseContext());

        this.mCoin.onLoadResources(mEngine, getBaseContext());
        this.mBug.onLoadResources(mEngine, getBaseContext());
        this.mProhibit.onLoadResources(mEngine, getBaseContext());

        this.mLockDoor.onLoadResources(mEngine, getBaseContext());

        this.mRec_Coin.onLoadResources(mEngine, getBaseContext());
        this.mRec_Bug.onLoadResources(mEngine, getBaseContext());
        this.mRec_Prohibit.onLoadResources(mEngine, getBaseContext());

        this.mbtnNext.onLoadResources(mEngine, getBaseContext());
        this.mbtnReplay.onLoadResources(mEngine, getBaseContext());
        this.mbtnShare.onLoadResources(mEngine, getBaseContext());

        this.mHeadBone_Tiny_x1.onLoadResources(mEngine, getBaseContext());
        this.mHeadBone_Tiny_x2.onLoadResources(mEngine, getBaseContext());
        this.mHeadBone_Tiny_x3.onLoadResources(mEngine, getBaseContext());


        for (int i = 0;i<arrTrap.length;i++) {
            arrTrap[i].onLoadResources(mEngine, getBaseContext());
        }

        for (int i = 0;i < 60;i++) {
            arrWallH[i].onLoadResources(mEngine, getBaseContext());
            arrWallV[i].onLoadResources(mEngine, getBaseContext());
        }

    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));

        this.mbtnArrowBack.onLoadScene(this.mScene);
        this.Map.onLoadScene(this.mScene);
        this.ProcessBar.onLoadScene(this.mScene);


        this.mRec_Coin.onLoadScene(this.mScene);
        this.mRec_Bug.onLoadScene(this.mScene);
        this.mRec_Prohibit.onLoadScene(this.mScene);

        for (int i = 0;i<arrTrap.length;i++) {
            arrTrap[i].onLoadScene(this.mScene);
        }
        this.ScorePanel.onLoadScene(this.mScene);

        for (int i = 0;i < 60;i++) {
            arrWallH[i].onLoadScene(this.mScene);
            arrWallV[i].onLoadScene(this.mScene);
        }

        this.mCoin.onLoadScene(this.mScene);
        this.mBug.onLoadScene(this.mScene);
        this.mProhibit.onLoadScene(this.mScene);

        this.mLockDoor.onLoadScene(this.mScene);

        this.mummy.onLoadScene(this.mScene);
        this.ConfirmPanel.onLoadScene(this.mScene);
        this.ConfirmPanel.object_AnimatedSprite.attachChild(txt_Game_Over_Result);

        mbtnNext.createScene();
        mbtnReplay.createScene();
        mbtnShare.createScene();

        this.mScene.registerTouchArea(mbtnNext.object_AnimatedSprite);
        this.mScene.registerTouchArea(mbtnReplay.object_AnimatedSprite);
        this.mScene.registerTouchArea(mbtnShare.object_AnimatedSprite);

        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        this.mbtnArrowBack.moveToHide();
        this.ScorePanel.setIsButtonFlip(false);
        this.ScorePanel.moveToHide();
        this.Map.setIsButtonFlip(false);

        this.Map.moveToHide();
        this.mRec_Coin.moveToHide();
        this.mRec_Bug.moveToHide();
        this.mRec_Prohibit.moveToHide();

        this.ProcessBar.setIsButtonFlip(false);
        this.ProcessBar.CenterScreen();
        this.ConfirmPanel.CenterScreen_Horizontal();

        mbtnNext.mSpriteObjectiveTouchListener = this;
        mbtnReplay.mSpriteObjectiveTouchListener = this;
        mbtnShare.mSpriteObjectiveTouchListener = this;

        this.mbtnNext.setIsButtonFlip(true);
        this.mbtnReplay.setIsButtonFlip(true);
        this.mbtnShare.setIsButtonFlip(true);

        txtNumCurrentRoom.setText("1/" + String.valueOf(mMapSelected));

        mUsedBug = 0;
        mUsedProhibit = 0;
        mPointHead = 3;
        mScore = 0;

        loader = new GameLoader(mMapSelected,0);
        loader.execute("");
        loader.mLoadComplete = this;
    }

    @Override
    public void LoadComplete(int _IsLoader) {
        switch(_IsLoader) {
            case 0: // Loader
            {
                if (this.ProcessBar != null) {
                    this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{20}, 1);
                }

                MoveModifier movescorepanel = new MoveModifier(0.2f, this.ScorePanel.object_AnimatedSprite.getX(), 5, this.ScorePanel.object_AnimatedSprite.getY(), 10);
                this.ScorePanel.object_AnimatedSprite.registerEntityModifier(movescorepanel);
                movescorepanel = new MoveModifier(0.2f, this.Map.object_AnimatedSprite.getX(), paddingLeft_Map, this.Map.object_AnimatedSprite.getY(), paddingTop_Map);
                this.Map.object_AnimatedSprite.registerEntityModifier(movescorepanel);
                movescorepanel = new MoveModifier(0.2f, this.Map.object_AnimatedSprite.getX(), 10, this.Map.object_AnimatedSprite.getY(), 765);
                this.mbtnArrowBack.object_AnimatedSprite.registerEntityModifier(movescorepanel);
                this.mbtnArrowBack.mSpriteObjectiveFinishAnimationListener = this;

                movescorepanel = new MoveModifier(0.2f, this.Map.object_AnimatedSprite.getX(), 340, this.Map.object_AnimatedSprite.getY(), 37);
                this.mRec_Coin.object_AnimatedSprite.registerEntityModifier(movescorepanel);
                movescorepanel = new MoveModifier(0.2f, this.Map.object_AnimatedSprite.getX(), 340, this.Map.object_AnimatedSprite.getY(), 82);
                this.mRec_Bug.object_AnimatedSprite.registerEntityModifier(movescorepanel);
                movescorepanel = new MoveModifier(0.2f, this.Map.object_AnimatedSprite.getX(), 340, this.Map.object_AnimatedSprite.getY(), 127);
                this.mRec_Prohibit.object_AnimatedSprite.registerEntityModifier(movescorepanel);

                movescorepanel = new MoveModifier(0.2f, this.ProcessBar.object_AnimatedSprite.getX(), this.ProcessBar.object_AnimatedSprite.getX() + 20,
                        this.ProcessBar.object_AnimatedSprite.getY(), 750) {
                    @Override
                    protected void onModifierFinished(IEntity pItem) {
                        super.onModifierFinished(pItem);

                        mSprite_bg.attachChild(txtMap);
                        mSprite_bg.attachChild(txtMultiRoom);
                        mSprite_bg.attachChild(txtCurrentRoom);

//                        mSprite_bg.attachChild(txtNumMap);
                        mSprite_bg.attachChild(txtNumMultiRoom);
                        mSprite_bg.attachChild(txtNumCurrentRoom);

                        mSprite_bg.attachChild(txtNumCoin);
                        mSprite_bg.attachChild(txtNumBugs);
                        mSprite_bg.attachChild(txtNumProhibit);
                        mSprite_bg.attachChild(txtNumScore);
                        performLoadComplete();
                    }
                };
                this.ProcessBar.object_AnimatedSprite.registerEntityModifier(movescorepanel);
            }
            break;
            case 1: // Counter
            {
                // you lose, end of time
                if(isRunning) {
                    isRunning = false;
                    txt_Game_Over_Result.setText("mission failed");
                    this.ConfirmPanel.object_AnimatedSprite.detachChild(mbtnNext.object_AnimatedSprite);
                    this.ConfirmPanel.object_AnimatedSprite.detachChild(mbtnReplay.object_AnimatedSprite);
                    this.ConfirmPanel.object_AnimatedSprite.detachChild(mbtnShare.object_AnimatedSprite);

                    this.ConfirmPanel.object_AnimatedSprite.attachChild(mbtnReplay.object_AnimatedSprite);
                    if (detectNextGame()) {
                        this.ConfirmPanel.object_AnimatedSprite.attachChild(mbtnNext.object_AnimatedSprite);
                    }
                    ShowPanel();
                    if (loader2 != null) {
                        loader2.stop();
                    }
                    if(ScoredThread != null) {
                        ScoredThread.interrupt();
                    }
                }
            }
            break;
            case 2: // Scored

                break;
            default:
                break;
        }
    }

    private void performLoadComplete(){
        this.mummy.setIsButtonFlip(false);
        this.mbtnArrowBack.setIsButtonFlip(true);
        this.Map.mSpriteObjectiveTouchListener = this;
        this.mRec_Bug.mSpriteObjectiveTouchListener = this;
        this.mRec_Prohibit.mSpriteObjectiveTouchListener = this;
        this.mbtnArrowBack.mSpriteObjectiveFinishAnimationListener = this;


        coinAction[0] = new int[]{0,1,2,3,4,0};
        coinAction[1] = new int[]{0,4,3,2,1,0};
        coinAction[2] = new int[]{0,0,0,0,0,0};

        mummyAction[0] = new int[]{0,1,2,3,4}; // top move
        mummyAction[1] = new int[]{5,6,7,8,9}; // right move
        mummyAction[2] = new int[]{10,11,12,13,14}; // bottom move
        mummyAction[3] = new int[]{15,16,17,18,19}; // left move
        mCoin.object_AnimatedSprite.animate(new long[]{baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed},
                0, 35, true);

        setStartRoom();

//        txtNumMap.setText(String.valueOf(mMapSelected));
        txtNumMultiRoom.setText(String.valueOf(Common.getMulGameStory()));

        txtNumCoin.setText(String.valueOf(Common.getCoin_Local()));
        txtNumBugs.setText(String.valueOf(Common.getCount_Bugs()));
        txtNumProhibit.setText(String.valueOf(Common.getCount_Prohibit()));

        this.mRec_Bug.setPlaySound(false);
        this.mRec_Prohibit.setPlaySound(false);
        mHeadBone_Tiny_x1.createScene();
        mHeadBone_Tiny_x2.createScene();
        mHeadBone_Tiny_x3.createScene();

        HeadBone_SpriteAlpha = mHeadBone_Tiny_x1.getObject_AnimatedSprite().getAlpha();

        mHeadBone_Tiny_x2.setPositionX(this.ProcessBar.object_AnimatedSprite.getWidth() / 4);
        mHeadBone_Tiny_x3.setPositionX(this.ProcessBar.object_AnimatedSprite.getWidth() / 2);

        this.ProcessBar.object_AnimatedSprite.detachChildren();

        this.ProcessBar.object_AnimatedSprite.attachChild(mHeadBone_Tiny_x1.object_AnimatedSprite);
        this.ProcessBar.object_AnimatedSprite.attachChild(mHeadBone_Tiny_x2.object_AnimatedSprite);
        this.ProcessBar.object_AnimatedSprite.attachChild(mHeadBone_Tiny_x3.object_AnimatedSprite);

        loader2 = new GameLoader(mMapSelected,1);
        loader2.execute("");
        loader2.mLoadComplete = this;


        mTotalTime = ((mMapSelected - 5) * 0.5 + baseTime) * iOneMinus;
        mScore = mTotalTime + mMapSelected * BaseScore;
        txtNumScore.setText(String.valueOf((int) mScore));

        ScoredThread=  new Thread(new Runnable() {
            public void run() {
                while (mScore > 0){
                    try {
                        Thread.sleep(iOneSec);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        public void run() {
                            txtNumScore.setText(String.valueOf((int) mScore));
                        }
                    });
                    mScore--;
                }
            }

        });
        ScoredThread.start();
    }

    private void resetGame(boolean _isnextGame){
//        gameStory = new GameStory();
//        mummy.object_AnimatedSprite.stopAnimation();
//        mummy.object_AnimatedSprite.clearEntityModifiers();
//        setMoveForMummy(4, 1);
//
//        setStartRoom();
//        txtNumCurrentRoom.setText("1/" + String.valueOf(mMapSelected));
//
//        if (this.ProcessBar != null) {
//            this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{20}, 1);
//        }
//        mHeadBone_Tiny_x1.object_AnimatedSprite.stopAnimation();
//        mHeadBone_Tiny_x2.object_AnimatedSprite.stopAnimation();
//        mHeadBone_Tiny_x3.object_AnimatedSprite.stopAnimation();
//        mHeadBone_Tiny_x1.object_AnimatedSprite.clearEntityModifiers();
//        mHeadBone_Tiny_x2.object_AnimatedSprite.clearEntityModifiers();
//        mHeadBone_Tiny_x1.object_AnimatedSprite.clearEntityModifiers();
//
//        AlphaModifier Alpha = new AlphaModifier(0.1f, 0, HeadBone_SpriteAlpha);
//        mHeadBone_Tiny_x1.object_AnimatedSprite.registerEntityModifier(Alpha);
//        mHeadBone_Tiny_x2.object_AnimatedSprite.registerEntityModifier(Alpha);
//        mHeadBone_Tiny_x3.object_AnimatedSprite.registerEntityModifier(Alpha);
//
//        this.ProcessBar.object_AnimatedSprite.detachChildren();
//        this.ProcessBar.object_AnimatedSprite.attachChild(mHeadBone_Tiny_x1.object_AnimatedSprite);
//        this.ProcessBar.object_AnimatedSprite.attachChild(mHeadBone_Tiny_x2.object_AnimatedSprite);
//        this.ProcessBar.object_AnimatedSprite.attachChild(mHeadBone_Tiny_x3.object_AnimatedSprite);
//        Common.getFoot_step().pause();
//
//
//        isClick = true;
//        isShowConfirmPanel = false;
//        isClickProhibit = true;
//        isClickBugs = true;
//
//        if(loader2 != null){
//            loader2.cancel(true);
//        }
////        ShowPanel();
//        loader2 = new GameLoader(mMapSelected,false);
//        loader2.execute("");
//        loader2.mLoadComplete = this;

        if(loader != null){
            loader.stop();
            loader.cancel(true);

        }
        if(loader2 != null){
            loader2.stop();
            loader2.cancel(true);
        }
        if(ScoredThread != null) {
            ScoredThread.interrupt();
        }

        ShowPanel();
        Intent intent = getIntent();
        if(_isnextGame) {
            mMapSelected += 1;
            intent.putExtra(Common.Key_Game_Select_Map_Trial, mMapSelected);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        }
        startActivity(intent);
        finish();
    }


    private void setMoveForMummy(int iway,int icount){
        if(iway != -1) {
            int[] iFrame = mummyAction[iway];
            long[] curAnimTime = new long[iFrame.length];
            for(int i = 0;i < curAnimTime.length; i++){
                curAnimTime[i] = Common.getSpeed_Animation_mummy();
            }
            mummy.object_AnimatedSprite.animate(curAnimTime,
                    iFrame, icount);
        }
    }

    /*
    * to get trend of mummy, 0 not move, 1 top, 2 left,3 right,4 bottom
    * */
    private int getTrend(PointLocal _p1, PointLocal _p2){
        int iResults = -1;
        for(int i = 0;i < 4; i++){
            if(_p2.getIndex() == _p1.getNextPoint()[i]){
                iResults = i;
                break;
            }
        }

        return iResults;
    }

    public boolean detectNextGame(){
        boolean bresults = false;
        if(Common.getArr_Map_Trial().length > (mMapSelected - 4)) bresults = true;
        return bresults;
    }

    public void caculationGameComplete(int _CurPointHead){
        int MapIndex = mMapSelected - 5;
        int iPointHead = Integer.valueOf(Common.getArr_Map_Trial()[MapIndex]);
        if(_CurPointHead > iPointHead) {

            String[] arr_Map_Trial = Common.getArr_Map_Trial();
            arr_Map_Trial[MapIndex] = String.valueOf(_CurPointHead);

            String valueTrial = TextUtils.join(",", arr_Map_Trial);
            Common.setMap_Trial(valueTrial);
        }
        if(MapIndex + 1 == Common.getArr_Map_Trial().length){
            Common.setMap_Trial(Common.getMap_Trial() + ",0,");
        }

        Common.getEdit(getBaseContext()).putString(Common.Key_Config_Map_Trial, Common.getMap_Trial()).commit();
    }

    private void setStartRoom(){
        resetCurrentTrap();
        setTrapIntoMap();
        setWall();
        isUsedBug = isClickBugs;

        isClickProhibit = true;
        isClickBugs = true;
        iRandomProhibit = 0;
        this.mLockDoor.moveToHide();
        allObjectOnMap.clear(); // clear all location

        // set location for Mummy
        iStart = gameStory.randomMummyLocation();
        Point mummyPoint = getLocationBynumber(iStart);
        mummy.object_AnimatedSprite.setPosition(mummyPoint.x, mummyPoint.y);

        // add location of all trap
        allObjectOnMap.addAll(gameStory.mArrTrap.get(gameStory.iRoom));
        // add location of mummy
        allObjectOnMap.add(iStart);

        int iCoin;
        // random location Coin
        if(gameStory.Coin.get(gameStory.iRoom)) {
            do {
                iCoin = Common.randomNext(0, 100);
            } while (allObjectOnMap.contains(iCoin));
            // add location of Coin
            allObjectOnMap.add(iCoin);
            // set location for Coin
            Point CoinPoint = getLocationBynumber(iCoin);
            mCoin.object_AnimatedSprite.setPosition(CoinPoint.x, CoinPoint.y);
            // play sound coin drop
            if(Common.getCoin_drop() != null){
                Common.getCoin_drop().play();
            }
        }else mCoin.moveToHide();

        // random to bugs and prohibit.
        int iShow_Pro = Common.randomNext(1,21);
        int iLocation_Pro;
        if(iShow_Pro == 10){// show Prohibit
            do {
                iLocation_Pro = Common.randomNext(0, 100);
            } while (allObjectOnMap.contains(iLocation_Pro));
            // add location of Prohibit
            allObjectOnMap.add(iLocation_Pro);
            // set location for Prohibit
            Point ProhibitPoint = getLocationBynumber(iLocation_Pro);
            mProhibit.object_AnimatedSprite.setPosition(ProhibitPoint.x, ProhibitPoint.y);
        }else mProhibit.moveToHide();

        // random to bugs .
        int iShow_Bugs = Common.randomNext(1,101);
        int iLocation_Bugs;
        if(iShow_Bugs == 50){// show Prohibit
            do {
                iLocation_Bugs = Common.randomNext(0, 100);
            } while (allObjectOnMap.contains(iLocation_Bugs));
            // add location of Prohibit
            allObjectOnMap.add(iLocation_Bugs);
            // set location for Prohibit
            Point BugsPoint = getLocationBynumber(iLocation_Bugs);
            mBug.object_AnimatedSprite.setPosition(BugsPoint.x, BugsPoint.y);
        }else mBug.moveToHide();
    }

    private Point getLocationBynumber(int _inum){
        return new Point(_inum % 10 * object_width + 22, _inum / 10 * object_width + 200);
    }

    private void setTrapIntoMap(){
        for (int i = 1;i<arrTrap.length;i++) {
            arrTrap[i].setIsButtonFlip(false);

            Point curTrapPoint = getLocationBynumber(gameStory.mArrTrap.get(gameStory.iRoom).get(i));
            arrTrap[i].object_AnimatedSprite.setPosition(curTrapPoint.x, curTrapPoint.y);
        }
    }

    private void setWall(){
        PointArray curPoint;
        clearWall();
        curPoint = gameStory.getCurRoom().Clone();

        Algorithms.setWallToWay(curPoint);

        for (int i = 0 ;i < curPoint.MapPointH.size(); i++){
            arrWallH[i].object_AnimatedSprite.setPosition(((Point)curPoint.MapPointH.get(i)).x * object_width + 19,((Point)curPoint.MapPointH.get(i)).y * object_width + 197);
        }
        for (int i = 0 ;i < curPoint.MapPointV.size(); i++){
            arrWallV[i].object_AnimatedSprite.setPosition(((Point) curPoint.MapPointV.get(i)).x * object_width + 19, ((Point) curPoint.MapPointV.get(i)).y * object_width + 197);
        }
    }

    private void clearWall() {
        for (int i = 0;i < 60;i++) {
            arrWallH[i].object_AnimatedSprite.setPosition(-60, 0);
            arrWallV[i].object_AnimatedSprite.setPosition(-10,0);
        }
    }

    private void resetCurrentTrap(){
        arrTrap[iCurrTrap].object_AnimatedSprite.animate(new long[]{1}, new int[]{0}, 1);
    }

    @Override
    public void iProcessChange(int iProcess, int _IsLoader) {
        switch(_IsLoader) {
            case 0: // Loader
            {
                if (this.ProcessBar != null) {
                    this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{
                            iProcess / 5}, 1);
                }
            }
            break;
            case 1: // Counter
            {
                if (this.ProcessBar != null) {
                    this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{
                            iProcess}, 1);

                    if (iProcess == 11) {
                        FadeSprite(mHeadBone_Tiny_x3);
                    } else if (iProcess == 10) {
                        this.ProcessBar.object_AnimatedSprite.detachChild(mHeadBone_Tiny_x3.object_AnimatedSprite);
                        mPointHead = 2;
                    } else if (iProcess == 6) {
                        FadeSprite(mHeadBone_Tiny_x2);
                    } else if (iProcess == 5) {
                        this.ProcessBar.object_AnimatedSprite.detachChild(mHeadBone_Tiny_x2.object_AnimatedSprite);
                        mPointHead = 1;
                    } else if (iProcess == 1) {
                        FadeSprite(mHeadBone_Tiny_x1);
                    } else if (iProcess == 0) {
                        this.ProcessBar.object_AnimatedSprite.detachChild(mHeadBone_Tiny_x1.object_AnimatedSprite);
                    }
                }
            }
            break;
            case 2: // Scored
            {
                txtNumScore.setText(String.valueOf(iProcess));
            }
                break;
            default:
                break;
        }
    }

    private void FadeSprite(ButtonSprite _Item){
        AlphaModifier[] arrFade = new AlphaModifier[2];
        float SpriteAlpha = _Item.object_AnimatedSprite.getAlpha();
        AlphaModifier iem1 = new AlphaModifier(0.5f, SpriteAlpha, 0);
        arrFade[0] = iem1;
        AlphaModifier iem2 = new AlphaModifier(0.5f, 0, SpriteAlpha);
        arrFade[1] = iem2;

        SequenceEntityModifier sequenceEntityModifier = new SequenceEntityModifier(arrFade){
            @Override
            protected void onModifierFinished(IEntity pItem) {
                super.reset();
            }
        };
        _Item.object_AnimatedSprite.registerEntityModifier(sequenceEntityModifier);
    }

    private void ShareToFacebook(){
        if(mCreateBitMapShareFacebook != null){
            mCreateBitMapShareFacebook.setMiBug(mUsedBug);
            mCreateBitMapShareFacebook.setMiHeadPoint(mPointHead);
            mCreateBitMapShareFacebook.setMiProhibit(mUsedProhibit);
            mCreateBitMapShareFacebook.setMlScore((int)mScore);

            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(mCreateBitMapShareFacebook.getmBitmapScore())
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            ShareDialog.show(this,content);
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
        if(_sender.getName().equals(this.Map.getName())){
            if(isShowConfirmPanel){
                if(mbtnReplay.object_AnimatedSprite.contains(_event.getX(),_event.getY())){
                    if(Common.getClick_button() != null){
                        Common.getClick_button().play();
                    }
                    resetGame(false);
                }
                else if(mbtnNext.object_AnimatedSprite.contains(_event.getX(),_event.getY())){
                    if(Common.getClick_button() != null){
                        Common.getClick_button().play();
                    }
                    resetGame(true);
                }
                else if(mbtnShare.object_AnimatedSprite.contains(_event.getX(),_event.getY())){
                    if(Common.getClick_button() != null){
                        Common.getClick_button().play();
                    }
                    ShareToFacebook();
                }
            }else {
                if (mummy.isTouch(_event)) {
                    Common.getMummy_hit().play();
//                    if (isClick) {
//                        setMoveForMummy(4, 1);
//                    }
                }
                for (int i = 1; i < arrTrap.length; i++) {
                    if (arrTrap[i].isTouch(_event)) {
                        if (isClick && iRandomProhibit != i) {
                            isClick = false;
                            Common.getFoot_step().play();
                            Common.getOpen_door().play();
                            arrTrap[i].getObject_AnimatedSprite().animate(new long[]{
                                            baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                                            baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed},
                                    new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 0);

                            int iGoal = gameStory.mArrTrap.get(gameStory.iRoom).get(i);
                            final int CurRoom = gameStory.iRoom;
                            gameStory.selecWay(i);
                            final boolean isNewMulti = gameStory.isNewMulti();
                            gameStory.setIsNewMulti(false);
                            final int NextRoom = gameStory.iRoom;
                            if (Common.getMaxGameStory() < gameStory.iRoom)
                                Common.setMaxGameStory(gameStory.iRoom);
                            if (isNewMulti && isClickBugs) {
                                imultiChoice++;
                                if (Common.getMulGameStory() < imultiChoice)
                                    Common.setMulGameStory(imultiChoice);
                            } else imultiChoice = 0;

                            iCurrTrap = i;
                            ArrayList wayResults = Algorithms.createroadFromSToG(iStart, iGoal);

                            MoveModifier[] arrMoveMummy = new MoveModifier[wayResults.size()];

                            PointLocal curLocalMummy = Algorithms.mAllPointDFS[iStart];
                            final int[] iCurTrend = {3};
                            int iRender = 0;

                            float oldX = mummy.object_AnimatedSprite.getX();
                            float oldY = mummy.object_AnimatedSprite.getY();
                            for (Object pointDFS :
                                    wayResults) {

                                Point mummyPoint = getLocationBynumber(((PointLocal) pointDFS).getIndex());
                                final PointLocal newLocalMummy = ((PointLocal) pointDFS);
                                final PointLocal oldLocalMummy = curLocalMummy;
                                MoveModifier mod = new MoveModifier(Common.getSpeed_Location(), oldX, mummyPoint.x, oldY, mummyPoint.y, new IEntityModifier.IEntityModifierListener() {
                                    @Override
                                    public void onModifierStarted(IModifier<IEntity> iModifier, IEntity iEntity) {
                                        iCurTrend[0] = getTrend(oldLocalMummy, newLocalMummy);
                                        setMoveForMummy(iCurTrend[0], 1);
                                        mScore--;
                                        txtNumScore.setText(String.valueOf((int) mScore));
                                        //detect when mummy move on tra
                                        for (Object curTrap :
                                                arrTrap) {
                                            if (((ButtonSprite) curTrap).Contains(mummy)) {
                                                Common.getClose_door().play();
                                                break;
                                            }
                                        }

                                        //detect when mummy move on coin
                                        if(mCoin.Contains(mummy)){
                                            Common.getCoin_bonus().play();
                                            Common.setCoin_Local(Common.getCoin_Local() + 1);
                                            txtNumCoin.setText(String.valueOf(Common.getCoin_Local()));
                                            Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Coin_Local, Common.getCoin_Local()).commit();
                                            gameStory.Coin.set(CurRoom, false);
                                            mCoin.moveToHide();
                                        }

                                        //detect when mummy move on prohibit
                                        if(mProhibit.Contains(mummy)){
                                            Common.getBonus_bugs_pro().play();
                                            Common.setCount_Prohibit(Common.getCount_Prohibit() + 1);
                                            txtNumProhibit.setText(String.valueOf(Common.getCount_Prohibit()));
                                            Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Prohibit, Common.getCount_Prohibit()).commit();
                                            mProhibit.moveToHide();
                                        }

                                        //detect when mummy move on bugs
                                        if(mBug.Contains(mummy)){
                                            Common.getBug_hit().play();
                                            Common.setCount_Bugs(Common.getCount_Bugs() + 1);
                                            txtNumBugs.setText(String.valueOf(Common.getCount_Bugs()));
                                            Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Bugs, Common.getCount_Bugs()).commit();
                                            mBug.moveToHide();
                                        }
                                    }

                                    @Override
                                    public void onModifierFinished(IModifier<IEntity> iModifier, IEntity iEntity) {

                                    }
                                });
                                arrMoveMummy[iRender] = mod;

                                oldX = mummyPoint.x;
                                oldY = mummyPoint.y;
                                iRender++;
                                curLocalMummy = newLocalMummy;
                            }
                            SequenceEntityModifier sequenceEntityModifier = new SequenceEntityModifier(arrMoveMummy) {
                                @Override
                                protected void onModifierFinished(IEntity pItem) {
                                    setStartRoom();
                                    txtNumCurrentRoom.setText(String.valueOf(gameStory.iRoom) + "/" + String.valueOf(mMapSelected));
//                                    txtNumMaxRoom.setText(String.valueOf(Common.getMaxGameStory()));
                                    txtNumMultiRoom.setText(String.valueOf(Common.getMulGameStory()));
                                    mummy.object_AnimatedSprite.stopAnimation();
                                    isClick = true;
//                                    if (iCurTrend[0] == 3) setMoveForMummy(4, 1);
                                    Common.getFoot_step().pause();

                                    if (CurRoom < NextRoom) {
                                        Common.getNext_door().play();
                                    } else {
                                        Common.getReturn_door().play();
                                    }

                                    if(NextRoom > mMapSelected) {
                                        if (isRunning) {
                                            isRunning = false;
                                            if (loader2 != null) {
                                                loader2.stop();
                                            }
                                            if(ScoredThread != null) {
                                                ScoredThread.interrupt();
                                            }
                                            txt_Game_Over_Result.setText("mission completed");

                                            ConfirmPanel.object_AnimatedSprite.detachChild(mbtnNext.object_AnimatedSprite);
                                            ConfirmPanel.object_AnimatedSprite.detachChild(mbtnReplay.object_AnimatedSprite);
                                            ConfirmPanel.object_AnimatedSprite.detachChild(mbtnShare.object_AnimatedSprite);

                                            ConfirmPanel.object_AnimatedSprite.attachChild(mbtnNext.object_AnimatedSprite);
                                            ConfirmPanel.object_AnimatedSprite.attachChild(mbtnReplay.object_AnimatedSprite);
                                            ConfirmPanel.object_AnimatedSprite.attachChild(mbtnShare.object_AnimatedSprite);
                                            caculationGameComplete(mPointHead);
                                            ShowPanel();
                                        }
                                    }
                                }
                            };

                            mummy.object_AnimatedSprite.registerEntityModifier(sequenceEntityModifier);
                            break;
                        } else {
                            Common.getClose_door().play();
                            break;
                        }
                    }
                }
            }
        }
        else if(_sender.getName().equals(this.mRec_Prohibit.getName()) && !isShowConfirmPanel && isClick){

            if(Common.getCount_Prohibit() > 0 && isClickProhibit && isClickBugs && gameStory.iRoom != 1){

                if(Common.getPurchase_gold() != null){
                    Common.getPurchase_gold().play();
                }
                mUsedProhibit++;

                isClickProhibit = false;
                int iNextRoom;

                do{
                    iRandomProhibit = Common.randomNext(1,Common.getButtonLevel() + 1);
                    iNextRoom = gameStory.way.get(gameStory.iRoom)[iRandomProhibit];
                }while (iNextRoom > gameStory.iRoom);

                this.mLockDoor.object_AnimatedSprite.setPosition(arrTrap[iRandomProhibit].getObject_AnimatedSprite().getX(),arrTrap[iRandomProhibit].getObject_AnimatedSprite().getY());

                Common.setCount_Prohibit(Common.getCount_Prohibit() - 1);
                txtNumProhibit.setText(String.valueOf(Common.getCount_Prohibit()));
                Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Prohibit, Common.getCount_Prohibit()).commit();
            }else {
                if(Common.getCancel_click() != null){
                    Common.getCancel_click().play();
                }
            }
        }
        else if(_sender.getName().equals(this.mRec_Bug.getName()) && !isShowConfirmPanel && isClick){

            if(Common.getCount_Bugs() > 0 && isClickBugs && gameStory.iRoom != 1 && isUsedBug){

                if(Common.getBug_hit2() != null){
                    Common.getBug_hit2().play();
                }
                mUsedBug++;

                isClickBugs = false;

                for (int i = 1; i <= gameStory.way.get(gameStory.iRoom).length ; i++){
                    if(gameStory.way.get(gameStory.iRoom)[i] > gameStory.iRoom)
                    {
                        arrTrap[i].getObject_AnimatedSprite().animate(new long[]{
                                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed},
                                new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 0);
                        Common.getOpen_door().play();
                        iCurrTrap = i;
                        break;
                    }
                }

                Common.setCount_Bugs(Common.getCount_Bugs() - 1);
                txtNumBugs.setText(String.valueOf(Common.getCount_Bugs()));
                Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Bugs, Common.getCount_Bugs()).commit();
            }else {
                if(Common.getCancel_click() != null){
                    Common.getCancel_click().play();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(loader != null){
            loader.stop();
            loader.cancel(true);

        }
        if(loader2 != null){
            loader2.stop();
            loader2.cancel(true);
        }
        if(ScoredThread != null) {
            ScoredThread.interrupt();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        Common.CommitSharedPreferent(getApplicationContext());

        if(Common.theme_Music != null){
            Common.theme_Music.pause();
        }
        if(Common.getFoot_step() != null){
            Common.getFoot_step().pause();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(Common.theme_Music != null && Common.getIsSound()){
            Common.theme_Music.play();
        }
    }


    private void ShowPanel(){
        if(isShowConfirmPanel){
            isShowConfirmPanel = false;
            MoveYModifier movey = new MoveYModifier(1,0,-548){
                @Override
                protected void onModifierFinished(IEntity pItem) {
                    super.onModifierFinished(pItem);
                    Common.getRope_slide().stop();
                }
            };
            this.ConfirmPanel.object_AnimatedSprite.registerEntityModifier(movey);
            Common.getRope_slide().play();
        }else {
            isShowConfirmPanel = true;

            txt_Game_Over_Result.setPosition(this.ConfirmPanel.getWidth() / 2 - txt_Game_Over_Result.getWidth() /2,txt_Game_Over_Result.getY());

            MoveYModifier movey = new MoveYModifier(1,-548,0){
                @Override
                protected void onModifierFinished(IEntity pItem) {
                    super.onModifierFinished(pItem);
                    Common.getRope_slide().stop();
                }
            };
            this.ConfirmPanel.object_AnimatedSprite.registerEntityModifier(movey);
            Common.getRope_slide().play();
        }
    }
}
