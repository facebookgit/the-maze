package com.game.tulusoft.themaze.Objective;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;

/**
 * Created by Shazam on 2/27/2016.
 */
public class ButtonSprite extends SpriteObjective{


    public ButtonSprite(float positionX, float positionY, String assetsubfolder, String _imgbutton, int widthBitmapTextureAtlas, int heightBitmapTextureAtlas, int fromxTiledTextureRegion, int fromyTiledTextureRegion, int colTiledTextureRegion, int rowTiledTextureRegion, String name) {
        super(positionX, positionY, assetsubfolder, _imgbutton, widthBitmapTextureAtlas, heightBitmapTextureAtlas, fromxTiledTextureRegion, fromyTiledTextureRegion, colTiledTextureRegion, rowTiledTextureRegion, name);
    }

    @Override
    public void onLoadScene(Scene mScene) {
        //Đặt vị trí ban đầu của player
        object_AnimatedSprite = new AnimatedSprite(this.positionX, this.positionY, this.object_TiledTextureRegion){
            //Lắng nghe sự kiện khi chạm vào sprite
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN || pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE){


                    if(isButtonFlip() && pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN ) {
                        TouchAnimation();
                    }

                    if(!isButtonActivity) {
                        if (mSpriteObjectiveTouchListener != null) {
                            mSpriteObjectiveTouchListener.onTouchArea(ButtonSprite.this,pSceneTouchEvent);
                        }
                    }
                }
                return true;
            }};

        //region test

//        //Phương thức này cho biết player sẽ hiện thị lần đầu tiên với trạng thái gì
////        showPlayerStatus();
//
//        object_AnimatedSprite.setScale(0.5f);
//        RotationModifier rotate = new RotationModifier(20f, 0, 360);
//        object_AnimatedSprite.registerEntityModifier(rotate);
//        MoveXModifier mod1=new MoveXModifier(1f,0f,100f);
//
//        object_AnimatedSprite.registerEntityModifier(mod1);
////        object_AnimatedSprite.setFlippedHorizontal(true);
//        object_AnimatedSprite.animate(new long[]{1000,100,100,100,100,1000,100,100,100,100}, new int[]{4,0,1,2,3,4,3,2,1,0}, 1000);

        //endregion

        mScene.attachChild(object_AnimatedSprite);
        mScene.registerTouchArea(object_AnimatedSprite);
    }

    public boolean isTouch(TouchEvent _event){
        if(object_AnimatedSprite.contains(_event.getX(),_event.getY())) return true;
        return false;
    }


}
