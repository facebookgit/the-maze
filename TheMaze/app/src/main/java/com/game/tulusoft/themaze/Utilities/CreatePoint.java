package com.game.tulusoft.themaze.Utilities;

import android.graphics.Point;

/**
 * Created by Shazam on 3/11/2016.
 */
public class CreatePoint {

    public Point[] arrPointH = new Point[110];
    public Point[] arrPointV = new Point[110];

    public int[][] arrNeighborH = new int[110][];
    public int[][] arrNeighborV = new int[110][];

    PointArray MapPoint = new PointArray();


//    public PointLocal[] allPointDFS = new PointLocal[100];

    public CreatePoint(){
        int iY = -1;
        for (int i = 0; i < 110; i++)
        {
            if (i % 10 == 0) iY++;
            arrPointH[i] = new Point(i % 10, iY);
            arrPointV[i] = new Point();
            arrPointV[i].x = arrPointH[i].y;
            arrPointV[i].y = arrPointH[i].x;
            arrNeighborH[i] = createNeighbor(i, true);
            arrNeighborV[i] = createNeighbor(i,false);
        }
    }

    public int[] createNeighbor(int _numofPoint, boolean isHLine)
    {
        int[] results = new int[6];
        int tenth = _numofPoint / 10;
        int unit = _numofPoint % 10;
        if (isHLine)
        {
            results[0] = _numofPoint - 1;
            results[3] = _numofPoint + 1;
            results[5] = unit * 10 + tenth;
            results[1] = results[5] - 1;
            results[2] = results[1] + 10;
            results[4] = results[2] + 1;

            if (tenth == 0)
            {
                results[1] = results[2] = -1;
            }
            if (tenth == 10)
            {
                results[4] = results[5] = -1;
            }
            if (unit == 0)
            {
                results[0] = -1;
            }
            if (unit == 9)
            {
                results[3] = -1;
            }
        }
        else
        {
            results[1] = _numofPoint - 1;
            results[4] = _numofPoint + 1;
            results[2] = unit * 10 + tenth;
            results[0] = results[2] - 1;
            results[3] = results[2] + 10;
            results[5] = results[3] - 1;

            if (tenth == 0)
            {
                results[0] = results[5] = -1;
            }
            if (tenth == 10)
            {
                results[2] = results[3] = -1;
            }
            if (unit == 0)
            {
                results[1] = -1;
            }
            if (unit == 9)
            {
                results[4] = -1;
            }
        }

        return results;
    }

    private boolean CheckHL(int _iPoint)
    {
        if (_iPoint % 10 == 0) return false;
        int i0, i1, i5;
        i0 = this.arrNeighborH[_iPoint][0];
        if (i0 != -1)
        {
            Point P0 = this.arrPointH[i0];
            if (MapPoint.MapPointH.contains(P0)) return false;
        }
        i1 = this.arrNeighborH[_iPoint][1];
        if (i1 != -1)
        {
            Point P1 = this.arrPointV[i1];
            if (MapPoint.MapPointV.contains(P1)) return false;
        }
        i5 = this.arrNeighborH[_iPoint][5];
        if (i5 != -1)
        {
            Point P5 = this.arrPointV[i5];
            if (MapPoint.MapPointV.contains(P5)) return false;
        }
        if (i0 == -1 && i1 == -1 && i5 == -1) return false;
        return true;
    }

    private boolean CheckHR(int _iPoint)
    {
        if (_iPoint % 10 == 9) return false;
        int i2, i3, i4;

        i2 = this.arrNeighborH[_iPoint][2];
        if (i2 != -1)
        {
            Point P2 = this.arrPointV[i2];
            if (MapPoint.MapPointV.contains(P2)) return false;
        }

        i3 = this.arrNeighborH[_iPoint][3];
        if (i3 != -1)
        {
            Point P3 = this.arrPointH[i3];
            if (MapPoint.MapPointH.contains(P3)) return false;
        }

        i4 = this.arrNeighborH[_iPoint][4];
        if (i4 != -1)
        {
            Point P4 = this.arrPointV[i4];
            if (MapPoint.MapPointV.contains(P4)) return false;
        }
        if (i2 == -1 && i3 == -1 && i4 == -1) return false;
        return true;
    }

    private boolean CheckVT(int _iPoint)
    {
        if (_iPoint % 10 == 0) return false;
        int i0, i1, i2;
        i0 = this.arrNeighborV[_iPoint][0];
        if (i0 != -1)
        {
            Point P0 = this.arrPointH[i0];
            if (MapPoint.MapPointH.contains(P0)) return false;
        }
        i1 = this.arrNeighborV[_iPoint][1];
        if (i1 != -1)
        {
            Point P1 = this.arrPointV[i1];
            if (MapPoint.MapPointV.contains(P1)) return false;
        }
        i2 = this.arrNeighborV[_iPoint][2];
        if (i2 != -1)
        {
            Point P2 = this.arrPointH[i2];
            if (MapPoint.MapPointH.contains(P2)) return false;
        }
        if (i0 == -1 && i1 == -1 && i2 == -1) return false;
        return true;
    }

    private boolean CheckVB(int _iPoint)
    {
        if (_iPoint % 10 == 9) return false;
        int i3, i4, i5;
        i3 = this.arrNeighborV[_iPoint][3];
        if (i3 != -1)
        {
            Point P0 = this.arrPointH[i3];
            if (MapPoint.MapPointH.contains(P0)) return false;
        }
        i4 = this.arrNeighborV[_iPoint][4];
        if (i4 != -1)
        {
            Point P1 = this.arrPointV[i4];
            if (MapPoint.MapPointV.contains(P1)) return false;
        }
        i5 = this.arrNeighborV[_iPoint][5];
        if (i5 != -1)
        {
            Point P2 = this.arrPointH[i5];
            if (MapPoint.MapPointH.contains(P2)) return false;
        }
        if (i3 == -1 && i4 == -1 && i5 == -1) return false;
        return true;
    }

    private boolean CreateWallH(int _indexPoint, boolean isLeft)
    {
        int iPre = _indexPoint;
        if (isLeft)
        {
            int icurLeft = this.arrNeighborH[iPre][0];
            if (icurLeft == -1) return false;
            if (icurLeft < 10 || icurLeft > 99) return false;
            Point pcur = this.arrPointH[icurLeft];
            if (MapPoint.MapPointH.contains(pcur)) return false;
            if (CheckHL(icurLeft))
            {
                MapPoint.MapPointH.add(pcur);
                return true;
            }
            return false;
        }
        else
        {
            int icurRight = this.arrNeighborH[iPre][3];
            if (icurRight == -1) return false;
            if (icurRight < 10 || icurRight > 99) return false;
            Point pcur = this.arrPointH[icurRight];
            if (MapPoint.MapPointH.contains(pcur)) return false;
            if (CheckHR(icurRight))
            {
                MapPoint.MapPointH.add(pcur);
                return true;
            }
            return false;
        }
    }

    private boolean CreateWallV(int _indexPoint, boolean isTop)
    {
        int iPre = _indexPoint;
        if (isTop)
        {
            int icurTop = this.arrNeighborV[iPre][1];
            if (icurTop == -1) return false;
            if (icurTop < 10 || icurTop > 99) return false;
            Point pcur = this.arrPointV[icurTop];
            if (MapPoint.MapPointV.contains(pcur)) return false;
            if (CheckVT(icurTop))
            {
                MapPoint.MapPointV.add(pcur);
                return true;
            }
            return false;
        }
        else
        {
            int icurBot = this.arrNeighborV[iPre][4];
            if (icurBot == -1) return false;
            if (icurBot < 10 || icurBot > 99) return false;
            Point pcur = this.arrPointV[icurBot];
            if (MapPoint.MapPointV.contains(pcur)) return false;
            if (CheckVB(icurBot))
            {
                MapPoint.MapPointV.add(pcur);
                return true;
            }

            return false;
        }
    }

    private int CreateMultiWallH(int _indexPoint, boolean isLeft, int iCountWall)
    {
        int iPoint = _indexPoint;
        int iLeftRight = isLeft ? -1 : 1;
        for (int i = 0; i < iCountWall; i++)
        {
            if (!CreateWallH(iPoint, isLeft)) break;
            iPoint += iLeftRight;
        }
        return iPoint--;
    }

    private int CreateMultiWallV(int _indexPoint, boolean isTop,int iCountWall)
    {
        int iPoint = _indexPoint;
        int iTopBot = isTop ? -1 : 1;
        for (int i = 0; i < iCountWall; i++)
        {
            if (!CreateWallV(iPoint, isTop)) break;
            iPoint += iTopBot;
        }
        return iPoint--;
    }

    public void CreaateWall() {
        int iQueo = Common.randomNext(0, 9);

        int _indexPoint = -1;

        do {
            _indexPoint = Common.randomNext(10, 100);
        } while (_indexPoint < 10 || _indexPoint > 99);


        if (_indexPoint % 10 == 0) {
            if (!CheckHR(_indexPoint)) return;
        }

        if (_indexPoint % 10 == 9) {
            if (!CheckHL(_indexPoint)) return;
        }
        if (!CheckHL(_indexPoint) && !CheckHR(_indexPoint)) return;

        Point CurPoint = this.arrPointH[_indexPoint];
        if (MapPoint.MapPointH.contains(CurPoint)) return;

        MapPoint.MapPointH.add(CurPoint);

        int iaddLR = Common.randomNext(1, 3);

        boolean isLeftTopHV = iaddLR == 1;
        int iveroi = CreateMultiWallH(_indexPoint, isLeftTopHV, Common.randomNext(1, 9));

        boolean isHorizontal = true;

        for (int i = 0; i < iQueo; i++) {
            int iLR = Common.randomNext(1, 4);
            int icountwall = Common.randomNext(1, 18);

            if (iLR == 1) {
                if (isHorizontal) {
                    int iLeftV = this.arrNeighborH[iveroi][isLeftTopHV ? 5 : 2];
                    if (isLeftTopHV) iLeftV -= 1;
                    else iLeftV += 1;
                    if (iLeftV < 0) break;

                    Point PLeftV = this.arrPointV[iLeftV];

                    if (!MapPoint.MapPointV.contains(PLeftV)) {
                        iveroi = CreateMultiWallV(iLeftV, !isLeftTopHV, icountwall);
                        isLeftTopHV = !isLeftTopHV;
                        if (iveroi == iLeftV) return;

                    } else break;
                } else {
                    int iLeftH = this.arrNeighborV[iveroi][isLeftTopHV ? 0 : 3];
                    if (isLeftTopHV) iLeftH += 1;
                    else iLeftH -= 1;
                    if (iLeftH < 0) break;

                    Point PLeftH = this.arrPointH[iLeftH];

                    if (!MapPoint.MapPointH.contains(PLeftH)) {
                        iveroi = CreateMultiWallH(iLeftH, isLeftTopHV, icountwall);
                        if (iveroi == iLeftH) return;
                    } else break;
                }

                isHorizontal = !isHorizontal;

            } else if (iLR == 2) {
                if (isHorizontal) {
                    int iRightV = this.arrNeighborH[iveroi][isLeftTopHV ? 1 : 4];
                    if (isLeftTopHV) iRightV += 1;
                    else iRightV -= 1;
                    if (iRightV < 0) break;

                    Point PRightV = this.arrPointV[iRightV];


                    if (!MapPoint.MapPointV.contains(PRightV)) {
                        iveroi = CreateMultiWallV(iRightV, isLeftTopHV, icountwall);
                        if (iveroi == iRightV) return;
                    } else break;
                } else {
                    int iRightH = this.arrNeighborV[iveroi][isLeftTopHV ? 2 : 5];
                    if (isLeftTopHV) iRightH -= 1;
                    else iRightH += 1;
                    if (iRightH < 0) break;


                    Point PRightH = this.arrPointH[iRightH];

                    if (!MapPoint.MapPointH.contains(PRightH)) {
                        iveroi = CreateMultiWallH(iRightH, !isLeftTopHV, icountwall);
                        isLeftTopHV = !isLeftTopHV;
                        if (iveroi == iRightH) return;
                    } else break;
                }


                isHorizontal = !isHorizontal;
            } else {
                if (isHorizontal) {
                    int iGoOnH = this.arrNeighborH[iveroi][isLeftTopHV ? 0 : 3];
                    if (isLeftTopHV) iGoOnH += 1;
                    else iGoOnH -= 1;
                    if (iGoOnH < 0) break;

                    Point PGoOnH = this.arrPointH[iGoOnH];

                    if (!MapPoint.MapPointH.contains(PGoOnH)) {
                        iveroi = CreateMultiWallH(iGoOnH, isLeftTopHV, icountwall);
                        if (iveroi == iGoOnH) return;
                    } else break;
                } else {
                    int iGoOnV = this.arrNeighborV[iveroi][isLeftTopHV ? 1 : 4];
                    if (isLeftTopHV) iGoOnV += 1;
                    else iGoOnV -= 1;
                    if (iGoOnV < 0) break;

                    Point PGoOnV = this.arrPointV[iGoOnV];

                    if (!MapPoint.MapPointV.contains(PGoOnV)) {
                        iveroi = CreateMultiWallV(iGoOnV, !isLeftTopHV, icountwall);
                        if (iveroi == iGoOnV) return;
                    } else break;
                }
            }
        }
    }


}
