package com.game.tulusoft.themaze.Utilities;

import java.util.ArrayList;

/**
 * Created by Shazam on 3/13/2016.
 */
public class GameStory {
    public  ArrayList<PointArray> mRoom = new ArrayList<>();
    public  ArrayList<Integer[]> way = new ArrayList<>();
    public  ArrayList<Boolean> Coin = new ArrayList<>();
    public  ArrayList<ArrayList<Integer>> mArrTrap = new ArrayList<>();
    public CreatePoint mCreatePoint = new CreatePoint();
    public boolean isNewMulti() {
        return isNewMulti;
    }

    public void setIsNewMulti(boolean isNewMulti) {
        this.isNewMulti = isNewMulti;
    }

    private boolean isNewMulti = false;

    public int iRoom = 1;
    public GameStory() {
        //add buffer
        way.add(new Integer[1]);
        mRoom.add(mCreatePoint.MapPoint.Clone());
        Coin.add(false);
        Coin.add(true);
        mArrTrap.add(new ArrayList());
        addWay();
        createMapRoom();
        mArrTrap.add(randomTrapLocation());
    }

    public void addWay(){
        int iCurRoom = way.size();
        int iNumberOfButton = Common.getButtonLevel();
        Integer[] iCurway = new Integer[iNumberOfButton + 1];
        if(iCurRoom == 1){
            for (int i = 1;i <= iNumberOfButton; i++){
                iCurway[i] = iCurRoom + 1;
            }
        }
        else {
            for (int i = 1;i <= iNumberOfButton; i++){
                iCurway[i] = Common.randomNext(1, iCurRoom);
            }
            iCurway[Common.randomNext(1, iNumberOfButton)] = iCurRoom + 1;
        }
        way.add(iCurway);
    }


    public PointArray selecWay(int _way){
        int iNextRoom = way.get(iRoom)[_way];
        if(way.size() - 1 < iNextRoom){
            isNewMulti = true;
            Coin.add(true);
            addWay();
            createMapRoom();
            mArrTrap.add(randomTrapLocation());
        }
        iRoom = iNextRoom;

        return getCurRoom();
    }

    public void createMapRoom(){
        mCreatePoint.MapPoint.MapPointH.clear();
        mCreatePoint.MapPoint.MapPointV.clear();
        do {
            mCreatePoint.CreaateWall();
        }while (mCreatePoint.MapPoint.MapPointH.size() + mCreatePoint.MapPoint.MapPointV.size() < 60);
        mRoom.add(mCreatePoint.MapPoint.Clone());
    }

    public PointArray getCurRoom(){
        return mRoom.get(iRoom);
    }

    private ArrayList<Integer> randomTrapLocation(){
        ArrayList<Integer> arrResults = new ArrayList<>();
        arrResults.add(-1);
        int iButton = Common.getButtonLevel();
        for (int i = 0 ;i < iButton;i++){
            int iCurButton = 0;
            do {
                iCurButton = Common.randomNext(0, 100);
            }while (arrResults.contains(iCurButton));
            arrResults.add(iCurButton);
        }

        return arrResults;
    }

    public int randomMummyLocation(){
        int iCurMummy;
        do {
            iCurMummy = Common.randomNext(0, 100);
        }while (mArrTrap.get(iRoom).contains(iCurMummy));
        return iCurMummy;
    }
}
