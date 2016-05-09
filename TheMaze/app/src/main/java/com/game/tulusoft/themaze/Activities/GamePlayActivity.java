package com.game.tulusoft.themaze.Activities;

import com.game.tulusoft.themaze.Objective.ButtonSprite;
import com.game.tulusoft.themaze.Utilities.Common;
import com.game.tulusoft.themaze.Utilities.GameLoader;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

/**
 * Created by Shazam on 3/9/2016.
 */
public class GamePlayActivity extends BaseGameActivity implements GameLoader.GameLoaderListener {

    private Camera mCamera;
    private Scene mScene;

    private BitmapTextureAtlas mBitmapTextureAtlas_bg;
    private TextureRegion mTextureRegion_bg;
    private Sprite mSprite_bg;

//    private Sprite ScorePanel;
//    private TextureRegion textureRegion_ScorePanel;
//    private BitmapTextureAtlas bitmapTextureAtlas_ScorePanel;

//    private Sprite Map;
//    private TextureRegion textureRegion_Map;
//    private BitmapTextureAtlas bitmapTextureAtlas_Map;




    private ButtonSprite Map;
//    private ButtonSprite trap;
    private ButtonSprite ScorePanel;
    private ButtonSprite ProcessBar;

    @Override
    public Engine onLoadEngine() {
        this.mCamera = new Camera(0, 0, Common.getmWIDTH(), Common.getmHEIGHT());
        Engine engine = new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(Common.getmWIDTH(),Common.getmHEIGHT()),this.mCamera));

//        this.trap = new ButtonSprite(0,0,"gameplay/","trapall.png",256,256,0,0,4,4);
//        this.ScorePanel = new ButtonSprite(5,10,"gameplay/","score_panel.png",1024,256,0,0,1,1);
//        this.Map = new ButtonSprite(22,200,"gameplay/","map.png",1024,1024,0,0,1,1);

//        this.trap = new ButtonSprite(0,0,"gameplay/","trapall.png",256,256,0,0,4,4);
        this.ScorePanel = new ButtonSprite(5,10,"gameplay/","score_panel.png",1024,256,0,0,1,1,"ScorePanel");
        this.Map = new ButtonSprite(22,200,"gameplay/","map.png",1024,1024,0,0,1,1,"Map");
        this.ProcessBar = new ButtonSprite(22,200,"gameplay/","process_all.png",2048,1024,0,0,3,7,"ProcessBar");

        return engine;
    }

    @Override
    public void onLoadResources() {
        this.mBitmapTextureAtlas_bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mTextureRegion_bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas_bg, this, "bg_white.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas_bg);


//        this.trap.onLoadResources(mEngine, getBaseContext());
        this.ScorePanel.onLoadResources(mEngine, getBaseContext());
        this.Map.onLoadResources(mEngine, getBaseContext());
        this.ProcessBar.onLoadResources(mEngine, getBaseContext());


    }

    @Override
    public Scene onLoadScene() {
        this.mScene = new Scene();
        this.mSprite_bg = new Sprite(0, 0, this.mTextureRegion_bg);
        this.mScene.setBackground(new SpriteBackground(this.mSprite_bg));

//        this.trap.onLoadScene(this.mScene);
        this.ScorePanel.onLoadScene(this.mScene);
        this.Map.onLoadScene(this.mScene);
        this.ProcessBar.onLoadScene(this.mScene);

        return this.mScene;
    }

    @Override
    public void onLoadComplete() {
        this.ScorePanel.setIsButtonFlip(false);
        this.ScorePanel.moveToHide();
//        this.trap.setIsButtonFlip(false);
        this.Map.setIsButtonFlip(false);
        this.Map.moveToHide();
//        this.ProcessBar.setIsButtonFlip(false);
//        this.trap.getObject_AnimatedSprite().animate(new long[]{100,100,100,100,100,100,100,100,100,100}, new int[]{0,1,2,3,4,5,6,7,8,9}, 100);
        this.ProcessBar.CenterScreen();
//        CreatePoint createPoint = new CreatePoint();
//        createPoint.CreaateWall();
//
//        ArrayList MapPointH = createPoint.MapPointH;
//        ArrayList MapPointV = createPoint.MapPointH;
//
//        GameLoader loader = new GameLoader(10);
//        loader.execute("");
//        loader.mLoadComplete = this;
//
//        GameStory Story = new GameStory();
    }

    @Override
    public void LoadComplete() {
        MoveModifier movescorepanel = new MoveModifier(0.2f,this.ScorePanel.object_AnimatedSprite.getX(),5,this.ScorePanel.object_AnimatedSprite.getY(),10);
        this.ScorePanel.object_AnimatedSprite.registerEntityModifier(movescorepanel);
        movescorepanel = new MoveModifier(0.2f,this.Map.object_AnimatedSprite.getX(),22,this.Map.object_AnimatedSprite.getY(),200);
        this.Map.object_AnimatedSprite.registerEntityModifier(movescorepanel);

        MoveYModifier moveY = new MoveYModifier(0.2f,this.ProcessBar.object_AnimatedSprite.getY(),730);
        this.ProcessBar.object_AnimatedSprite.registerEntityModifier(moveY);
    }

    @Override
    public void iProcessChange(int iProcess) {
        if(this.ProcessBar!= null){
            this.ProcessBar.getObject_AnimatedSprite().animate(new long[]{100}, new int[]{
                    iProcess}, 1);
        }
    }

}
