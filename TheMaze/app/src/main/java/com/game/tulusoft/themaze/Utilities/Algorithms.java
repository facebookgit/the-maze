package com.game.tulusoft.themaze.Utilities;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Shazam on 3/16/2016.
 */
public class Algorithms {

//    static public void BFSNganNhat(ArrayList _V0, ArrayList _arrStateDuongDiNganNhat, ArrayList _S, ArrayList _Q, ArrayList _G)
//    {
//        _V0 = _S;
//
//        for (Object StateinV0 : _V0) {
//
//        }
//        foreach (clsState StateinV0 : _V0)
//        {
//            StateinV0.PREVIOUS = null;
//            // gán nhãn cho trạng thái.
//            StateinV0.LABEL = 0;
//        }
//
//        _arrStateDuongDiNganNhat.Add(_V0);
//        int ik = 0;
//        bool bStoop = false;
//
//
//        while (!bStoop)
//        {
//            ArrayList VkCong1 = new ArrayList();
//            ArrayList Vk = new ArrayList();
//            Vk.Clear();
//            VkCong1.Clear();
//
//            Vk = (ArrayList)_arrStateDuongDiNganNhat[ik];
//
//            bool bTrangThaiDichTrongVk = false;
//            for (int i_arrik = 0; i_arrik < Vk.Count; i_arrik++)
//            {
//                if (((clsState)_G[0]).NAME == ((clsState)Vk[i_arrik]).NAME)
//                {
//
//                    bTrangThaiDichTrongVk = true;
//
//                }
//            }
//
//            if (Vk.Count == 0 && !bTrangThaiDichTrongVk)
//            {
//                bStoop = true;
//            }
//            else
//            {
//                foreach (clsState StateinVk in Vk)
//                {
//                    // Tác vụ lấy tập SuccsS của vk
//                    ArrayList arrSuccsS = new ArrayList();
//                    foreach (string strStateinSuccsS in StateinVk.SUCCESS)
//                    {
//                        foreach (clsState StateinQ in _Q)
//                        {
//                            if (strStateinSuccsS == StateinQ.NAME)
//                            {
//                                arrSuccsS.Add(StateinQ);
//                            }
//                        }
//                    }
//
//                    // Đã có tập succs(s) là arrSuccs
//                    // với mỗi trạng thái s' trong succs(s)
//                    foreach (clsState StateinSuccsS in arrSuccsS)
//                    {
//                        //nếu s' chưa gán nhãn hoặc g(s) + cost(s,s')< g(s')
//                        if (StateinSuccsS.LABEL < 0)
//                        {
//
//                            //thì đặt previous(s')=s
//                            StateinSuccsS.PREVIOUS = StateinVk;
//                            //hình thức gán nhãn
//                            StateinSuccsS.LABEL = ik + 1;
//                            //thêm s' vào v k+1
//                            VkCong1.Add(StateinSuccsS);
//                        }
//                    }
//
//                }
//
//            }
//
//            _arrStateDuongDiNganNhat.Add(VkCong1);
//
//            ik++;
//        }
//    }

//    PointLocal[] mallPointDFS = new PointLocal[100];
//    public int[][] arrQ = new int[100][];


    public static PointLocal[] mAllPointDFS = new PointLocal[100];

    static void DFS(PointLocal Start, PointLocal _G)
    {
        ArrayList arrP = new ArrayList();
        Start.setIsChecked(true);

        if (Start.getIndex() == _G.getIndex())
        {
            return;
        }
        arrP.addAll(getNextPoint(Start));

        for (Object curPoint :
                arrP) {
            if (!((PointLocal) curPoint).isChecked())
            {
                ((PointLocal) curPoint).setParent(Start);
                DFS(((PointLocal) curPoint), _G);
            }
        }
    }

    public static ArrayList createroadFromSToG(int _iStart,int _iGoal)
    {
        ArrayList arrResults = new ArrayList();

        PointLocal pS,pG,pTemp;

        pS = mAllPointDFS[_iStart];
        pG = mAllPointDFS[_iGoal];

        DFS(pS,pG);
        arrResults.add(pG);
        pTemp = pG.getParent();

        do {
            arrResults.add(pTemp);
            pTemp = pTemp.getParent();
        }while (pTemp != null);
        Collections.reverse(arrResults);
        return arrResults;
    }

    private static ArrayList getNextPoint(PointLocal _p)
    {
        ArrayList results = new ArrayList();
        int i0, i1, i2, i3;

        i0 = _p.getNextPoint()[0];
        i1 = _p.getNextPoint()[1];
        i2 = _p.getNextPoint()[2];
        i3 = _p.getNextPoint()[3];

        if (i0 >= 0) results.add(mAllPointDFS[i0]);
        if (i1 >= 0) results.add(mAllPointDFS[i1]);
        if (i2 >= 0) results.add(mAllPointDFS[i2]);
        if (i3 >= 0) results.add(mAllPointDFS[i3]);
        Collections.shuffle(results);
        return results;
    }

    public static void setWallToWay(PointArray _arr){
        int[][] arrQ = new int[100][];
        for (int i = 0;i<100;i++) {
            arrQ[i] = new int[4];

            arrQ[i][0] = i / 10 == 0 ? -1 : i - 10;
            arrQ[i][1] = i % 10 == 0 ? -1 : i - 1;
            arrQ[i][2] = i % 10 == 9 ? -1 : i + 1;
            arrQ[i][3] = i / 10 == 9 ? -1 : i + 10;
        }

        for (Object curPoint :
                _arr.MapPointH) {
            int curLocation = ((Point)curPoint).y * 10 + ((Point)curPoint).x;
            int curLocationTop = curLocation - 10;
            if (curLocation <= 99)
            {
                arrQ[curLocation][0] = -1; // top
            }
            if (curLocationTop >= 0)
            {
                arrQ[curLocationTop][3] = -1; // bottom
            }
        }

        for (Object curPoint :
                _arr.MapPointV) {
            int curLocation = ((Point)curPoint).y * 10 + ((Point)curPoint).x;
            if (curLocation / 10 != 0)
            {
                int curLocationLeft = curLocation - 1;

                arrQ[curLocation][1] = -1; // left
                arrQ[curLocationLeft][2] = -1; // right
            }
        }

        for (int i = 0; i < 100; i++)
        {
            mAllPointDFS[i] = new PointLocal();
            mAllPointDFS[i].setIndex(i);
            mAllPointDFS[i].setNextPoint(arrQ[i]);
        }
    }
}
