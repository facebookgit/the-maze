package com.game.tulusoft.themaze.Objective;

import android.content.Context;

import com.game.tulusoft.themaze.Interface.InterfaceSprite;
import com.game.tulusoft.themaze.Utilities.Common;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.modifier.IModifier;

/**
 * Created by Shazam on 2/27/2016.
 */
public class SpriteObjective implements InterfaceSprite {



    public interface SpriteObjectiveTouchListener{
        public void onTouchArea(SpriteObjective _sender,TouchEvent _event);
    }

    public interface SpriteObjectiveFinishAnimationListener{
        public void FinishAnimation(SpriteObjective _sender);
    }

    public SpriteObjectiveTouchListener mSpriteObjectiveTouchListener;
    public SpriteObjectiveFinishAnimationListener mSpriteObjectiveFinishAnimationListener;
    //Tạo hiệu ứng khi di chuyển cho button
    public AnimatedSprite object_AnimatedSprite;

    //Các biến lưu và load ảnh
    protected TiledTextureRegion object_TiledTextureRegion;
    protected BitmapTextureAtlas object_BitmapTextureAtlas;

    //Vị trí của Player
    protected float positionX = 0;
    protected float positionY = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name = "";

    public boolean isPlaySound() {
        return playSound;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    private boolean playSound = true;

    public float getWidth() {
        return object_AnimatedSprite.getWidth();
    }

    public void setWidth(float width) {
        this.object_AnimatedSprite.setWidth(width);
    }

    public float getHeight() {
        return object_AnimatedSprite.getHeight();
    }

    public void setHeight(float height) {
        this.object_AnimatedSprite.setHeight(height);
    }

    public boolean isButtonActivity() {
        return isButtonActivity;
    }

    public void setIsButtonActivity(boolean isButtonActivity) {
        this.isButtonActivity = isButtonActivity;
    }

    protected boolean isButtonActivity = false;

    public boolean isButtonFlip() {
        return isButtonFlip;
    }

    public void setIsButtonFlip(boolean isButtonFlip) {
        this.isButtonFlip = isButtonFlip;
    }

    protected boolean isButtonFlip = true;

    protected String assetsubfolder, _imgbutton;
    protected int widthBitmapTextureAtlas,heightBitmapTextureAtlas,
    fromxTiledTextureRegion,fromyTiledTextureRegion,
    colTiledTextureRegion,rowTiledTextureRegion;



    public AnimatedSprite getObject_AnimatedSprite() {
        return object_AnimatedSprite;
    }

    public void setObject_AnimatedSprite(AnimatedSprite object_AnimatedSprite) {
        this.object_AnimatedSprite = object_AnimatedSprite;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public SpriteObjective(float positionX, float positionY, String assetsubfolder, String _imgbutton,
                           int widthBitmapTextureAtlas, int heightBitmapTextureAtlas,
                           int fromxTiledTextureRegion, int fromyTiledTextureRegion,
                           int colTiledTextureRegion, int rowTiledTextureRegion,String name) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.assetsubfolder = assetsubfolder;
        this._imgbutton = _imgbutton;
        this.widthBitmapTextureAtlas = widthBitmapTextureAtlas;
        this.heightBitmapTextureAtlas = heightBitmapTextureAtlas;
        this.fromxTiledTextureRegion = fromxTiledTextureRegion;
        this.fromyTiledTextureRegion = fromyTiledTextureRegion;
        this.colTiledTextureRegion = colTiledTextureRegion;
        this.rowTiledTextureRegion = rowTiledTextureRegion;
        this.name = name;
    }
    public SpriteObjective(){

    }
    @Override
    public void onLoadResources(Engine mEngine, Context mContext) {
        if(!this.assetsubfolder.equals("")){
            BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(this.assetsubfolder);
        }
        this.object_BitmapTextureAtlas = new BitmapTextureAtlas(this.widthBitmapTextureAtlas, this.heightBitmapTextureAtlas, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.object_TiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.object_BitmapTextureAtlas, mContext,
                this._imgbutton, this.fromxTiledTextureRegion, this.fromyTiledTextureRegion, this.colTiledTextureRegion, this.rowTiledTextureRegion);
        mEngine.getTextureManager().loadTexture(this.object_BitmapTextureAtlas);
    }

    @Override
    public void onLoadScene(Scene mScene) {

    }

    public void moveToHide(){
        this.object_AnimatedSprite.setPosition(-this.getWidth(), -this.getHeight());
    }

    public void CenterScreen(){
        this.object_AnimatedSprite.setPosition(Common.getmWIDTH() / 2 - this.getWidth() / 2, Common.getmHEIGHT() / 2 - this.getHeight() / 2);
    }


    public void CenterScreen_Vertical(){
        this.object_AnimatedSprite.setPosition(this.object_AnimatedSprite.getX(), Common.getmHEIGHT() / 2 - this.getHeight() / 2);
    }

    public void CenterScreen_Horizontal(){
        this.object_AnimatedSprite.setPosition(Common.getmWIDTH() / 2 - this.getWidth() / 2, this.object_AnimatedSprite.getY());
    }

    public void detachSelf(){
        if(object_AnimatedSprite != null)object_AnimatedSprite.detachSelf();
    }

    public boolean Contains(SpriteObjective _object){

        SpriteObjective tempObject = null;

        try{

            tempObject = _object.Clone();
            tempObject.object_AnimatedSprite.setWidth(_object.object_AnimatedSprite.getWidth() - 2);
            tempObject.object_AnimatedSprite.setHeight(_object.object_AnimatedSprite.getHeight() - 2);
            tempObject.object_AnimatedSprite.setPosition(_object.object_AnimatedSprite.getX() + 1,_object.object_AnimatedSprite.getY() + 1);
        }catch (Exception e){

            System.out.println(e.toString());
        };

        if(tempObject == null) return false;
        return this.object_AnimatedSprite.collidesWith(tempObject.object_AnimatedSprite);
    }

    public SpriteObjective Clone(){
        SpriteObjective tempObject = new SpriteObjective();
        tempObject.object_AnimatedSprite = new AnimatedSprite(this.positionX,this.positionY,this.object_TiledTextureRegion.clone());
        return tempObject;
    }

    public void TouchAnimation(){
        if(Common.getClick_button() != null && playSound){
            Common.getClick_button().play();
        }
        if(object_AnimatedSprite != null){
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.05f, 1, 0.7f));
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.1f, 0.7f, 1.3f));
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.15f, 1.3f, 0.8f));
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.2f, 0.8f, 1.2f));
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.25f, 1.2f, 0.9f));
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.3f, 0.9f, 1.1f));
            object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.35f, 1.1f, 1f, new IEntityModifier.IEntityModifierListener() {
                @Override
                public void onModifierStarted(IModifier<IEntity> iModifier, IEntity iEntity) {

                }

                @Override
                public void onModifierFinished(IModifier<IEntity> iModifier, IEntity iEntity) {
//                    if(isButtonActivity) {
//                        if (mSpriteObjectiveTouchListener != null) {
//                            mSpriteObjectiveTouchListener.onTouchArea(SpriteObjective.this,null);
//                        }
//                    }
                        if (mSpriteObjectiveFinishAnimationListener != null) {
                            mSpriteObjectiveFinishAnimationListener.FinishAnimation(SpriteObjective.this);
                        }
                }
            }));
        }
    }
}
