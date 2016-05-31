package com.game.tulusoft.themaze.Utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import org.anddev.andengine.opengl.font.FontFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Shazam on 5/31/2016.
 */
public class CreteBitmapScoreToShare {

    Bitmap mBitmapScore;
    Bitmap mBitmapHeadbone;
    boolean isLoadComplete = false;

    public void setMiMap(int miMap) {
        this.miMap = miMap;
    }

    public void setMiHeadPoint(int miHeadPoint) {
        this.miHeadPoint = miHeadPoint;
    }

    public void setMlScore(long mlScore) {
        this.mlScore = mlScore;
    }

    public void setMiBug(int miBug) {
        this.miBug = miBug;
    }

    public void setMiProhibit(int miProhibit) {
        this.miProhibit = miProhibit;
    }

    int miMap;
    int miHeadPoint;
    long mlScore;
    int miBug;
    int miProhibit;
    Context mContext;

    public CreteBitmapScoreToShare(int _imap, int _iHeadPoint, long _lScore, int _iBug, int _iProhibit, Context _baseContext){

        AssetManager assetManager = _baseContext.getAssets();
        isLoadComplete = false;
        InputStream istr_Score;
        InputStream istr_HeadBone;

        miMap = _imap;
        miHeadPoint = _iHeadPoint;
        mlScore = _lScore;
        miBug = _iBug;
        miProhibit = _iProhibit;
        mContext = _baseContext;

        try {
            istr_Score = assetManager.open("gameplay/cus_Score_Share_f.png");
            istr_HeadBone = assetManager.open("gameplay/head_bone_g.png");
            mBitmapScore = BitmapFactory.decodeStream(istr_Score);
            mBitmapHeadbone = BitmapFactory.decodeStream(istr_HeadBone);
            isLoadComplete = true;
        } catch (IOException e) {
            // handle exception
            isLoadComplete = false;
        }
    }

    public Bitmap getmBitmapScore(){
        if(isLoadComplete){
            Canvas can = new Canvas();
            Bitmap overlayBitmap = Bitmap.createBitmap(mBitmapScore.getWidth(), mBitmapScore.getHeight(), mBitmapScore.getConfig());
            can.setBitmap(overlayBitmap);

            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
            can.drawBitmap(mBitmapScore,0,0,paint);
            for(int i = 0; i < miHeadPoint; i++) {
                can.drawBitmap(mBitmapHeadbone, 24 + i * 35, 11, paint);
            }
            Typeface plain = Typeface.createFromAsset(mContext.getAssets(), "fonts/youmurdererbb_reg.ttf");

            Paint painttext = new Paint(Paint.ANTI_ALIAS_FLAG);
            // text color - #3D3D3D
            painttext.setColor(Color.rgb(223, 159, 34));
            // text size in pixels
            painttext.setTextSize((50));
            // text shadow
//            paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

            painttext.setTypeface(plain);
            // draw map
            String gText = String.valueOf(miMap);
            Rect bounds = new Rect();
            painttext.getTextBounds(gText, 0, gText.length(), bounds);
            can.drawText(gText, 100, 370, painttext);
            // draw score
            gText = String.valueOf(mlScore);
            painttext.getTextBounds(gText, 0, gText.length(), bounds);
            can.drawText(gText, 300, 370, painttext);
            // draw bug
            gText = String.valueOf(miBug);
            painttext.getTextBounds(gText, 0, gText.length(), bounds);
            can.drawText(gText, 170, 420, painttext);
            //draw prohibit
            gText = String.valueOf(miProhibit);
            painttext.getTextBounds(gText, 0, gText.length(), bounds);
            can.drawText(gText, 290, 420, painttext);
            return overlayBitmap;
        }

        return mBitmapScore;
    }
}
