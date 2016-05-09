package com.game.tulusoft.themaze.Objective;

import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;

/**
 * Created by Shazam on 2/27/2016.
 */
public class MummySprite extends SpriteObjective{

    public MummySprite(float positionX, float positionY, String assetsubfolder, String _imgbutton, int widthBitmapTextureAtlas, int heightBitmapTextureAtlas, int fromxTiledTextureRegion, int fromyTiledTextureRegion, int colTiledTextureRegion, int rowTiledTextureRegion, String name) {
        super(positionX, positionY, assetsubfolder, _imgbutton, widthBitmapTextureAtlas, heightBitmapTextureAtlas, fromxTiledTextureRegion, fromyTiledTextureRegion, colTiledTextureRegion, rowTiledTextureRegion, name);
    }

//    //Tạo hiệu ứng khi di chuyển cho player
//    public AnimatedSprite object_AnimatedSprite;
//
//    //Các biến lưu và load ảnh
//    private TiledTextureRegion object_TiledTextureRegion;
//    private BitmapTextureAtlas object_BitmapTextureAtlas;
//
//    //Vị trí của Player
//    private float positionX = 0;
//    private float positionY = 0;




//    public MummySprite() {
//    }

//    @Override
//    public void onLoadResources(Engine mEngine, Context mContext) {
//        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("Objective/");
//        this.object_BitmapTextureAtlas = new BitmapTextureAtlas(2048, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//        this.object_TiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.object_BitmapTextureAtlas, mContext, "mummy1_front.png", 0, 0, 8, 1);
//        mEngine.getTextureManager().loadTexture(this.object_BitmapTextureAtlas);
//
//    }

    @Override
    public void onLoadScene(Scene mScene) {
        //Đặt vị trí ban đầu của player
        object_AnimatedSprite = new AnimatedSprite(this.positionX, this.positionY, this.object_TiledTextureRegion){

        //Lắng nghe sự kiện khi chạm vào sprite
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN){
                    System.out.println("Sprite onAreaTouched: chạm vào sprite");
                    System.out.println("Tọa độ X = "+pTouchAreaLocalX);
                    System.out.println("Tọa độ Y = "+pTouchAreaLocalY);

//                    object_AnimatedSprite.setScale(0.7f);
//                    object_AnimatedSprite.setScale(1.3f);
//                    object_AnimatedSprite.setScale(0.8f);
//                    object_AnimatedSprite.setScale(1.2f);
//                    object_AnimatedSprite.setScale(0.9f);
//                    object_AnimatedSprite.setScale(1.1f);
//                    object_AnimatedSprite.animate(new long[]{5}, new int[]{0}, 1);
//
//        object_AnimatedSprite.animate(new long[]{100,100,100,100,100,
//                                                100,100,100,100,100,
//                                                100,100,100,100,100,
//                                                100,100,100,100}, new int[]{
//                                                1,2,3,4,5,
//                                                6,7,8,9,10,
//                                                11,1,2,3,4,
//                                                5,6,7,0}, 0);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.1f, 0.05f, 0.1f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.2f, 0.1f, 0.15f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.3f, 0.15f, 0.2f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.4f, 0.2f, 0.25f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.5f, 0.25f, 0.3f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.6f, 0.3f, 0.35f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.7f, 0.35f, 0.4f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.8f, 0.4f, 0.45f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.9f, 0.45f, 0.5f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.0f, 0.5f, 0.55f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.1f, 0.55f, 0.6f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.2f, 0.6f, 0.65f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.3f, 0.65f, 0.7f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.4f, 0.7f, 0.75f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.5f, 0.75f, 0.8f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.6f, 0.8f, 0.85f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.7f, 0.85f, 0.9f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.8f, 0.9f, 0.95f));
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.9f, 0.95f, 1f));

//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.1f, 0.3f, 0.4f));
////                    object_AnimatedSprite.animate(new long[]{5}, new int[]{1}, 1);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.15f, 0.4f, 0.5f));
////                    object_AnimatedSprite.animate(new long[]{5}, new int[]{2}, 1);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.2f, 0.5f, 0.6f));
////                    object_AnimatedSprite.animate(new long[]{5}, new int[]{3}, 1);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.25f, 0.6f, 0.7f));
////                    object_AnimatedSprite.animate(new long[]{5}, new int[]{4}, 1);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.3f, 0.7f, 0.8f));
////                    object_AnimatedSprite.animate(new long[]{5}, new int[]{5}, 1);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.35f, 0.8f, 0.9f));
////                    object_AnimatedSprite.animate(new long[]{5}, new int[]{6}, 1);
//                    object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.4f, 0.9f, 1f));
//                    object_AnimatedSprite.animate(new long[]{5}, new int[]{7}, 1);
                }
                return true;
            }};
        //Phương thức này cho biết player sẽ hiện thị lần đầu tiên với trạng thái gì
//        showPlayerStatus();

//        object_AnimatedSprite.setScale(0.5f);
//        RotationModifier rotate = new RotationModifier(20f, 0, 360);
//        object_AnimatedSprite.registerEntityModifier(rotate);
//        MoveXModifier mod1=new MoveXModifier(1f,0f,100f);
//
//        object_AnimatedSprite.registerEntityModifier(mod1);
////        object_AnimatedSprite.setFlippedHorizontal(true);


        object_AnimatedSprite.setScale(0.05f);
        object_AnimatedSprite.animate(new long[]{100,100,100,100,100,
                100,100,100,100,100,
                100,100,100,100,100,
                100,100,100,100}, new int[]{
                1,2,3,4,5,
                6,7,8,9,10,
                11,1,2,3,4,
                5,6,7,0}, 0);
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.1f, 0.05f, 0.1f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.2f, 0.1f, 0.15f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.3f, 0.15f, 0.2f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.4f, 0.2f, 0.25f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.5f, 0.25f, 0.3f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.6f, 0.3f, 0.35f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.7f, 0.35f, 0.4f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.8f, 0.4f, 0.45f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(0.9f, 0.45f, 0.5f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.0f, 0.5f, 0.55f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.1f, 0.55f, 0.6f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.2f, 0.6f, 0.65f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.3f, 0.65f, 0.7f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.4f, 0.7f, 0.75f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.5f, 0.75f, 0.8f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.6f, 0.8f, 0.85f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.7f, 0.85f, 0.9f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.8f, 0.9f, 0.95f));
        object_AnimatedSprite.registerEntityModifier(new ScaleModifier(1.9f, 0.95f, 1f));

        mScene.attachChild(object_AnimatedSprite);
        mScene.registerTouchArea(object_AnimatedSprite);
    }
}
