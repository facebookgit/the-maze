package com.game.tulusoft.themaze.Activities;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.Utilities.Algorithms;
import com.game.tulusoft.themaze.Utilities.Common;
import com.game.tulusoft.themaze.Utilities.GameStory;
import com.game.tulusoft.themaze.Utilities.PointArray;
import com.game.tulusoft.themaze.Utilities.PointLocal;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
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
 * Created by Shazam on 3/13/2016.
 */
public class GameStoryActivity extends BaseGameActivity implements SpriteObjective.SpriteObjectiveTouchListener,SpriteObjective.SpriteObjectiveFinishAnimationListener{

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private ButtonSprite Map;
    private ButtonSprite ConfirmPanel;
    private ButtonSprite ScorePanel;
    private ButtonSprite mbtnArrowBack;


    private ButtonSprite mCoin;
    private ButtonSprite mBug;
    private ButtonSprite mProhibit;

    private ButtonSprite mLockDoor;

    private ButtonSprite mRec_Coin;
    private ButtonSprite mRec_Bug;
    private ButtonSprite mRec_Prohibit;

    GameStory gameStory= new GameStory();
    ButtonSprite[] arrTrap;
    ButtonSprite[] arrWallH;
    ButtonSprite[] arrWallV;

    ButtonSprite mummy;

    ArrayList<Integer> allObjectOnMap = new ArrayList<>();


    private BitmapTextureAtlas mFontTexture_Black_60;
    Font mFont_Black_60;

    private BitmapTextureAtlas mFontTexture_Red_60;
    Font mFont_Red_60;

    private BitmapTextureAtlas mFontTexture_Yellow_40;
    Font mFont_Yellow_40;

    ChangeableText txtMaxRoom;
    ChangeableText txtMultiRoom;
    ChangeableText txtCurrentRoom;

    ChangeableText txtNumMaxRoom;
    ChangeableText txtNumMultiRoom;
    ChangeableText txtNumCurrentRoom;

    ChangeableText txtNumCoin;
    ChangeableText txtNumBugs;
    ChangeableText txtNumProhibit;

    ChangeableText txt_Confirm_Yes;
    ChangeableText txt_Confirm_No;
    ChangeableText txt_Confirm_Quit_Game;

    int iCurrTrap = 0;
    int paddingLeft_Map = 12;
    int paddingTop_Map = 190;
    int imultiChoice = 0;
    int iStart = -1;
    int iRandomProhibit;

    int baseSpeed = Common.baseSpeed;
    int object_width = Common.getObject_width();

    boolean isClick = true;
    boolean isShowConfirmPanel = false;
    boolean isClickProhibit = true;
    boolean isClickBugs = true;

    int[][] mummyAction = new int[4][];
    int[][] coinAction = new int[3][];

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
    }

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

//        this.trap = new ButtonSprite(0,0,"gameplay/","trapall.png",256,256,0,0,4,4);
        this.ScorePanel = new ButtonSprite(5,10,"gameplay/","score_panel.png",1024,256,0,0,1,1,"ScorePanel");
        this.ConfirmPanel = new ButtonSprite(0,-548,"menu/","confirm_panel_rope_color.png",512,1024,0,0,1,1,"ConfirmPanel");
        this.Map = new ButtonSprite(paddingLeft_Map,paddingTop_Map,"gameplay/","map_gray_border.png",1024,1024,0,0,1,1,"Map");
        this.mbtnArrowBack = new ButtonSprite(20,760,"menu/","arrow_back2.png",64,64,0,0,1,1,"mbtnArrowBack");
        this.mummy = new ButtonSprite(-object_width,0,"Objective/","mummy_52px.png",512,256,0,0,5,4,"mummy");


        this.mCoin = new ButtonSprite(-object_width,object_width,"gameplay/","coin_glass6x6.png",512,512,0,0,6,6,"mCoin");
        this.mBug = new ButtonSprite(-object_width,object_width,"gameplay/","bug29x29.png",64,64,0,0,1,1,"mBug");
        this.mProhibit = new ButtonSprite(-object_width,object_width,"gameplay/","prohibit29x29.png",64,64,0,0,1,1,"mProhibit");

        this.mLockDoor = new ButtonSprite(-object_width,object_width,"gameplay/","lock_door.png",64,64,0,0,1,1,"mLockDoor");

        this.mRec_Coin = new ButtonSprite(290,37,"gameplay/","rec_coin.png",64,64,0,0,1,1,"mRec_Coin");
        this.mRec_Bug = new ButtonSprite(290,82,"gameplay/","rec_bug.png",64,64,0,0,1,1,"mRec_Bug");
        this.mRec_Prohibit = new ButtonSprite(290,127,"gameplay/","rec_prohibit.png",64,64,0,0,1,1,"mRec_Prohibit");

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

        FontFactory.setAssetBasePath("fonts/");
        this.mFontTexture_Black_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Black_60 = FontFactory.createFromAsset(this.mFontTexture_Black_60, this, "youmurdererbb_reg.ttf", 60, true, Color.BLACK);

        this.mFontTexture_Red_60 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Red_60 = FontFactory.createFromAsset(this.mFontTexture_Red_60, this, "youmurdererbb_reg.ttf", 60, true, Color.rgb(180,102,1));

        this.mFontTexture_Yellow_40 = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont_Yellow_40 = FontFactory.createFromAsset(this.mFontTexture_Yellow_40, this, "youmurdererbb_reg.ttf", 60, true, Color.rgb(30,38,11));

        engine.getTextureManager().loadTexture(this.mFontTexture_Black_60);
        engine.getFontManager().loadFont(this.mFont_Black_60);

        engine.getTextureManager().loadTexture(this.mFontTexture_Red_60);
        engine.getFontManager().loadFont(this.mFont_Red_60);

        engine.getTextureManager().loadTexture(this.mFontTexture_Yellow_40);
        engine.getFontManager().loadFont(this.mFont_Yellow_40);

        txtMaxRoom = new ChangeableText(60, 40, mFont_Black_60, "Max :");
        txtMultiRoom = new ChangeableText(60, 80, mFont_Black_60, "Multi :");
        txtCurrentRoom = new ChangeableText(60, 120, mFont_Black_60, "Curent :");

        txtNumMaxRoom = new ChangeableText(155, 40, mFont_Red_60, "1",5);
        txtNumMultiRoom = new ChangeableText(180, 80, mFont_Red_60, "0",5);
        txtNumCurrentRoom = new ChangeableText(210, 120, mFont_Red_60, "1",5);

        txtNumCoin = new ChangeableText(340, 34, mFont_Red_60, "0",5);
        txtNumBugs = new ChangeableText(340, 79, mFont_Red_60, "0",5);
        txtNumProhibit = new ChangeableText(340, 124, mFont_Red_60, "0",5);

        txt_Confirm_Yes = new ChangeableText(110, 480, mFont_Yellow_40, "yes");
        txt_Confirm_No = new ChangeableText(220, 480, mFont_Yellow_40, "no");
        txt_Confirm_Quit_Game = new ChangeableText(100, 410, mFont_Yellow_40, "quit game");

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

        this.ScorePanel.onLoadResources(mEngine, getBaseContext());
        this.ConfirmPanel.onLoadResources(mEngine, getBaseContext());
        this.Map.onLoadResources(mEngine, getBaseContext());
        this.mbtnArrowBack.onLoadResources(mEngine, getBaseContext());
        this.mummy.onLoadResources(mEngine, getBaseContext());

        this.mCoin.onLoadResources(mEngine, getBaseContext());
        this.mBug.onLoadResources(mEngine, getBaseContext());
        this.mProhibit.onLoadResources(mEngine, getBaseContext());

        this.mLockDoor.onLoadResources(mEngine, getBaseContext());

        this.mRec_Coin.onLoadResources(mEngine, getBaseContext());
        this.mRec_Bug.onLoadResources(mEngine, getBaseContext());
        this.mRec_Prohibit.onLoadResources(mEngine, getBaseContext());


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
        mSprite_bg.attachChild(txtMaxRoom);
        mSprite_bg.attachChild(txtMultiRoom);
        mSprite_bg.attachChild(txtCurrentRoom);

        mSprite_bg.attachChild(txtNumMaxRoom);
        mSprite_bg.attachChild(txtNumMultiRoom);
        mSprite_bg.attachChild(txtNumCurrentRoom);

        mSprite_bg.attachChild(txtNumCoin);
        mSprite_bg.attachChild(txtNumBugs);
        mSprite_bg.attachChild(txtNumProhibit);

        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));
        this.Map.onLoadScene(this.mScene);

        this.mRec_Coin.onLoadScene(this.mScene);
        this.mRec_Bug.onLoadScene(this.mScene);
        this.mRec_Prohibit.onLoadScene(this.mScene);

        for (int i = 0;i<arrTrap.length;i++) {
            arrTrap[i].onLoadScene(this.mScene);
        }
        this.ScorePanel.onLoadScene(this.mScene);
        this.mbtnArrowBack.onLoadScene(this.mScene);

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

        this.ConfirmPanel.object_AnimatedSprite.attachChild(txt_Confirm_Yes);
        this.ConfirmPanel.object_AnimatedSprite.attachChild(txt_Confirm_No);
        this.ConfirmPanel.object_AnimatedSprite.attachChild(txt_Confirm_Quit_Game);
        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        this.ScorePanel.setIsButtonFlip(false);
        this.Map.setIsButtonFlip(false);
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
//        mummyAction[4] = new int[]{22,23,24,25,26,27,28,29,
//                30,31,32,33,34,35,36,37,38,39,
//                40,41,42,43,44,45,46,47,48,49,
//                50,51,52,53,54,55,56,57,58,59,}; // dance

        mCoin.object_AnimatedSprite.animate(new long[]{baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed,
                        baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed, baseSpeed},
                0, 35, true);

//        setMoveForMummy(4, 1);

        setStartRoom();

        txtNumMaxRoom.setText(String.valueOf(Common.getMaxGameStory()));
        txtNumMultiRoom.setText(String.valueOf(Common.getMulGameStory()));

        txtNumCoin.setText(String.valueOf(Common.getPlayerInfo().getCoin()));
        txtNumBugs.setText(String.valueOf(Common.getPlayerInfo().getBugs()));
        txtNumProhibit.setText(String.valueOf(Common.getPlayerInfo().getProhibits()));
        this.ConfirmPanel.CenterScreen_Horizontal();

        this.mRec_Bug.setPlaySound(false);
        this.mRec_Prohibit.setPlaySound(false);
    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {
        if(_sender.getName().equals(this.Map.getName())){
            if(isShowConfirmPanel){
                if(txt_Confirm_Yes.contains(_event.getX(),_event.getY())){
                    finish();
                }
                else if(txt_Confirm_No.contains(_event.getX(),_event.getY())){
                    PressBackButton();
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
                                            Common.getPlayerInfo().setCoin(Common.getPlayerInfo().getCoin() + 1);
                                            txtNumCoin.setText(String.valueOf(Common.getPlayerInfo().getCoin()));
                                            Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Coin_Local, Common.getPlayerInfo().getCoin()).commit();
                                            gameStory.Coin.set(CurRoom, false);
                                            mCoin.moveToHide();
                                        }

                                        //detect when mummy move on prohibit
                                        if(mProhibit.Contains(mummy)){
                                            Common.getBonus_bugs_pro().play();
                                            Common.getPlayerInfo().setProhibits(Common.getPlayerInfo().getProhibits() + 1);
                                            txtNumProhibit.setText(String.valueOf(Common.getPlayerInfo().getProhibits()));
                                            Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Prohibit, Common.getPlayerInfo().getProhibits()).commit();
                                            mProhibit.moveToHide();
                                        }

                                        //detect when mummy move on bugs
                                        if(mBug.Contains(mummy)){
                                            Common.getBug_hit().play();
                                            Common.getPlayerInfo().setBugs(Common.getPlayerInfo().getBugs() + 1);
                                            txtNumBugs.setText(String.valueOf(Common.getPlayerInfo().getBugs()));
                                            Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Bugs, Common.getPlayerInfo().getBugs()).commit();
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
                                    txtNumCurrentRoom.setText(String.valueOf(gameStory.iRoom));
                                    txtNumMaxRoom.setText(String.valueOf(Common.getMaxGameStory()));
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

            if(Common.getPlayerInfo().getProhibits() > 0 && isClickProhibit && isClickBugs && gameStory.iRoom != 1){

                if(Common.getPurchase_gold() != null){
                    Common.getPurchase_gold().play();
                }

                isClickProhibit = false;
                int iNextRoom;

                do{
                    iRandomProhibit = Common.randomNext(1,Common.getButtonLevel() + 1);
                    iNextRoom = gameStory.way.get(gameStory.iRoom)[iRandomProhibit];
                }while (iNextRoom > gameStory.iRoom);

                this.mLockDoor.object_AnimatedSprite.setPosition(arrTrap[iRandomProhibit].getObject_AnimatedSprite().getX(),arrTrap[iRandomProhibit].getObject_AnimatedSprite().getY());

                Common.getPlayerInfo().setProhibits(Common.getPlayerInfo().getProhibits() - 1);
                txtNumProhibit.setText(String.valueOf(Common.getPlayerInfo().getProhibits()));
                Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Prohibit, Common.getPlayerInfo().getProhibits()).commit();
            }else {
                if(Common.getCancel_click() != null){
                    Common.getCancel_click().play();
                }
            }
        }
        else if(_sender.getName().equals(this.mRec_Bug.getName()) && !isShowConfirmPanel && isClick){

            if(Common.getPlayerInfo().getBugs() > 0 && isClickBugs && gameStory.iRoom != 1){

                if(Common.getBug_hit2() != null){
                    Common.getBug_hit2().play();
                }

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

                Common.getPlayerInfo().setBugs(Common.getPlayerInfo().getBugs() - 1);
                txtNumBugs.setText(String.valueOf(Common.getPlayerInfo().getBugs()));
                Common.getEdit(getApplicationContext()).putInt(Common.Key_Config_Count_Bugs, Common.getPlayerInfo().getBugs()).commit();
            }else {
                if(Common.getCancel_click() != null){
                    Common.getCancel_click().play();
                }
            }
        }
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
    private int getTrend(PointLocal _p1,PointLocal _p2){
        int iResults = -1;
        for(int i = 0;i < 4; i++){
            if(_p2.getIndex() == _p1.getNextPoint()[i]){
                iResults = i;
                break;
            }
        }

        return iResults;
    }

    private void setStartRoom(){
        resetCurrentTrap();
        setTrapIntoMap();
        setWall();
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
    public void FinishAnimation(SpriteObjective _sender) {
        if(_sender.getName().equals(this.mbtnArrowBack.getName())){
            PressBackButton();
        }

    }

    private void PressBackButton(){
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

    @Override
    public void onBackPressed() {
        PressBackButton();
    }
}