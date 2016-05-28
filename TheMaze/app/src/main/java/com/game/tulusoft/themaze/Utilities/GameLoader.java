package com.game.tulusoft.themaze.Utilities;

import android.os.AsyncTask;

/**
 * Created by Shazam on 3/11/2016.
 */
public class GameLoader extends AsyncTask<String, Integer, Boolean> {

    public interface GameLoaderListener{
        public void LoadComplete(boolean _isLoader);
        public void iProcessChange(int iProcess, boolean _isLoader);
    }

    public GameLoaderListener mLoadComplete;

    int mRoom = 0;
    double mTotalTime =0;
    long mTimeSleep =0;
    boolean mIsLoader = false;
    boolean isPaused = false;
    boolean isStop = false;
    public  CreatePoint createPoint;
    int baseTime = 5;
    public GameLoader(int iRoom,boolean isLoader){
        createPoint = new CreatePoint();
        mRoom = iRoom;
        mIsLoader = isLoader;
        float addTimeLevel = Common.getiLevel() == 1 ? 2.0f : Common.getiLevel() == 2 ? 2.5f : 1;
        mTotalTime = ((iRoom - 5) * 0.5 + baseTime) * 60000;
//        mTotalTime =  5000; // test
        mTimeSleep = (long) ((mTotalTime * addTimeLevel) / 20);
    }

    public void pause()
    {
        this.isPaused = true;
    }

    public void resume()
    {
        this.isPaused = false;
    }

    public void stop(){
        this.isStop = true;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        if(mIsLoader) {
            int iTotalWall = 0;
            do {
                if(isStop) return null;
                createPoint.CreaateWall();
                iTotalWall = createPoint.MapPoint.MapPointV.size() + createPoint.MapPoint.MapPointH.size();
                publishProgress((int) ((iTotalWall / (float) 60) * 100));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (iTotalWall < 60);
        }else {
            int iprocess = 21;
            do {
                if(isStop) return null;
                while (isPaused){
                    if(isStop) return null;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                iprocess--;
                publishProgress(iprocess);
                try {
                    Thread.sleep(mTimeSleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (0 < iprocess);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (mLoadComplete != null) {
            mLoadComplete.iProcessChange(values[0],mIsLoader);
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (mLoadComplete != null) {
            mLoadComplete.LoadComplete(mIsLoader);
        }
    }
}
