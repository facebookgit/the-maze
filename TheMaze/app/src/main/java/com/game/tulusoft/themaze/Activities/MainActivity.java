package com.game.tulusoft.themaze.Activities;

import android.content.Intent;

import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Objective.MummySprite;
import com.game.tulusoft.themaze.Objective.SpriteObjective;
import com.game.tulusoft.themaze.Utilities.Common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class MainActivity extends BaseGameActivity implements SpriteObjective.SpriteObjectiveTouchListener,SpriteObjective.SpriteObjectiveFinishAnimationListener{

    private Camera mCamera;
    private Scene mScene;


    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mTextureRegion;
//    private Sprite mbtnSound,mbtnPlay,mbtnOption,mbtnScore;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

    private BitmapTextureAtlas mBitmapTextureAtlas_mummy1;
    private TextureRegion mTextureRegion_mummy1;
    private Sprite mSprite_mummy1;

    private MummySprite mummySprite;
    private ButtonSprite mbtnTextThe;
    private ButtonSprite mbtnCloud1;
    private ButtonSprite mbtnCloud2;
    private ButtonSprite mbtnSun;
    private ButtonSprite mbtnTextMaze;
    private ButtonSprite mbtnLimited;


    private ButtonSprite mbtnPlay;
    private ButtonSprite mbtnScore;
    private ButtonSprite mbtnOption;
    private ButtonSprite mbtnHelp;


    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera).setNeedsSound(true).setNeedsMusic(true));
        mummySprite = new MummySprite(50,550,"Objective/","cus_mummy_front_step.png",512,512,0,0,6,2,"mummySprite");
        this.mbtnCloud2 = new ButtonSprite(340,75,"menu/","cloud2.png",256,64,0,0,1,1,"mbtnCloud2");
        this.mbtnTextThe = new ButtonSprite(15,70,"menu/","text_the.png",512,256,0,0,1,1,"mbtnTextThe");
        this.mbtnCloud1 = new ButtonSprite(40,58,"menu/","cloud1.png",512,256,0,0,1,1,"mbtnCloud1");
        this.mbtnSun = new ButtonSprite(73,0,"menu/","sun.png",512,128,0,0,1,1,"mbtnSun");
        this.mbtnTextMaze = new ButtonSprite(100,250,"menu/","text_maze.png",512,128,0,0,1,1,"mbtnTextMaze");
        this.mbtnLimited = new ButtonSprite(100,800,"menu/","text_limited.png",512,64,0,0,1,1,"mbtnLimited");

        this.mbtnPlay = new ButtonSprite(235,400,"menu/","text_play.png",256,128,0,0,1,1,"mbtnPlay");
        this.mbtnScore = new ButtonSprite(200,500,"menu/","text_score.png",256,64,0,0,1,1,"mbtnScore");
        this.mbtnOption = new ButtonSprite(200,580,"menu/","text_option.png",256,128,0,0,1,1,"mbtnOption");
        this.mbtnHelp = new ButtonSprite(235,680,"menu/","text_help.png",128,128,0,0,1,1,"mbtnHelp");
        return engine;
    }

    @Override
    public void onLoadResources() {

        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);

        this.mummySprite.onLoadResources(mEngine, getBaseContext());
        Common.getMbtnSound().onLoadResources(mEngine, getBaseContext());
        this.mbtnCloud2.onLoadResources(mEngine, getBaseContext());
        this.mbtnTextThe.onLoadResources(mEngine, getBaseContext());
        this.mbtnCloud1.onLoadResources(mEngine, getBaseContext());
        this.mbtnSun.onLoadResources(mEngine, getBaseContext());
        this.mbtnTextMaze.onLoadResources(mEngine, getBaseContext());
        this.mbtnLimited.onLoadResources(mEngine, getBaseContext());

        this.mbtnPlay.onLoadResources(mEngine, getBaseContext());
        this.mbtnScore.onLoadResources(mEngine, getBaseContext());
        this.mbtnOption.onLoadResources(mEngine, getBaseContext());
        this.mbtnHelp.onLoadResources(mEngine, getBaseContext());

    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));
//        this.mScene.setOnSceneTouchListener(new Scene.IOnSceneTouchListener() {
//            public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
//                return true;
//            }
//        });
        this.mummySprite.onLoadScene(this.mScene);
        Common.getMbtnSound().onLoadScene(this.mScene);
        this.mbtnCloud2.onLoadScene(this.mScene);
        this.mbtnTextThe.onLoadScene(this.mScene);
        this.mbtnCloud1.onLoadScene(this.mScene);
        this.mbtnSun.onLoadScene(this.mScene);

        this.mbtnTextMaze.onLoadScene(this.mScene);
        this.mbtnLimited.onLoadScene(this.mScene);

        this.mbtnPlay.onLoadScene(this.mScene);
        this.mbtnScore.onLoadScene(this.mScene);
        this.mbtnOption.onLoadScene(this.mScene);
        this.mbtnHelp.onLoadScene(this.mScene);

        return mScene;
    }

    @Override
    public void onLoadComplete() {
        Common.getMbtnSound().mSpriteObjectiveTouchListener = this;
        this.mbtnPlay.mSpriteObjectiveFinishAnimationListener = this;
        this.mbtnOption.mSpriteObjectiveFinishAnimationListener = this;
        Common.setupSound(this.mEngine,this);
        if(Common.getIsSound()) {
            Common.getMbtnSound().getObject_AnimatedSprite().animate(new long[]{100}, new int[]{0}, 1);
        }
        else {
            Common.getMbtnSound().getObject_AnimatedSprite().animate(new long[]{100}, new int[]{1}, 1);
        }
    }

    @Override
    public void onTouchArea(SpriteObjective _sender, TouchEvent _event) {
        if(_sender.getName().equals(Common.getMbtnSound().getName())){
            if(Common.getIsSound()) Common.getMbtnSound().getObject_AnimatedSprite().animate(new long[]{100}, new int[]{1}, 0);
            else Common.getMbtnSound().getObject_AnimatedSprite().animate(new long[]{100}, new int[]{0}, 0);

            Common.setIsSound(!Common.getIsSound());
        }
    }

    @Override
    public void FinishAnimation(SpriteObjective _sender) {
        if(_sender.getName().equals(this.mbtnPlay.getName())){
            Intent i = new Intent(MainActivity.this, SelectPlayMode.class);
            MainActivity.this.startActivity(i);
        }else if(_sender.getName().equals(this.mbtnOption.getName())){
            Intent i = new Intent(MainActivity.this, OptionActivity.class);
            MainActivity.this.startActivity(i);
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
    protected void onDestroy() {
        super.onDestroy();
        Common.releaseSound();
    }
}
