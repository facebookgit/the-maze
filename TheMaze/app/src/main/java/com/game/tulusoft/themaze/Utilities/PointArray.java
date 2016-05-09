package com.game.tulusoft.themaze.Utilities;

import java.util.ArrayList;

/**
 * Created by Shazam on 3/14/2016.
 */
public class PointArray {
    public ArrayList MapPointH = new ArrayList();
    public ArrayList MapPointV = new ArrayList();

    public PointArray Clone(){
        PointArray PAResults = new PointArray();
        PAResults.MapPointH = (ArrayList) this.MapPointH.clone();
        PAResults.MapPointV = (ArrayList) this.MapPointV.clone();

        return PAResults;
    }
}
