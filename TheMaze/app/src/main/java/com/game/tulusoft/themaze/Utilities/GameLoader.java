package com.game.tulusoft.themaze.Utilities;

import android.os.AsyncTask;

/**
 * Created by Shazam on 3/11/2016.
 */
public class GameLoader extends AsyncTask<String, Integer, Boolean> {

    public interface GameLoaderListener{
        public void LoadComplete();
        public void iProcessChange(int iProcess);
    }

    public GameLoaderListener mLoadComplete;

    int mRoom = 0;
    public  CreatePoint createPoint;
    public GameLoader(int iRoom){
        createPoint = new CreatePoint();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        int iTotalWall = 0;
        do
        {
            createPoint.CreaateWall();
            iTotalWall = createPoint.MapPoint.MapPointV.size() + createPoint.MapPoint.MapPointH.size();
            publishProgress((int) ((iTotalWall / (float) 60) * 100));
        } while (iTotalWall < 60);

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (mLoadComplete != null) {
            mLoadComplete.iProcessChange(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (mLoadComplete != null) {
            mLoadComplete.LoadComplete();
        }
    }
}
