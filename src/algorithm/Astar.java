package algorithm;
import decis.*;

/**
 * Created by Roman on 21.05.2017.
 */
public class Astar {

    public static final int UP = -4;
    public static final int DOWN = 4;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public int H;
    public int G;
    public int F;
    public int[] begArr = new int[16];
    public int[] termState1 = new int[16];
    public int[] termState2 = new int[16];
    public int[] temp = new int[16];
    public int[] tempA = new int[16];
    public int[] tempU = new int[16];
    public int[] tempD = new int[16];
    public int[] tempL = new int[16];
    public int[] tempR = new int[16];
    public int flag = 0;
    public int tempGU, tempGD, tempGL, tempGR;
    public int tempHU, tempHD, tempHL, tempHR;
    public int tempFU, tempFD, tempFL, tempFR;

    public Astar(MainIssue begState) {
        begArr = begState.getArr();
        fillTerm();
        H = getHTerm1(begArr);
        F = H + G;
    }

    public void fillTerm() {
        for (int i = 0; i < 16; i++) {
            termState1[i] = i + 1;
        }
        termState1[15] = -1;
        for (int i = 0; i < 16; i++) {
            termState2[i] = i + 1;
        }
        termState2[15] = -1;
        termState2[14] = 14;
        termState2[13] = 15;
    }

    public int getHnumber(int[] arr, int i) {
        int numInBeg = 0;
        //arr = begArr;
        if ((arr[i]) != (termState1[i])) {
            for (int j = 0; j < 16; j++) {
                if ((arr[i]) == (termState1[j])) numInBeg = j;
            }
        } else {
            numInBeg = 0;
            return numInBeg;
        }
        return Math.abs(numInBeg - i);
    }

    public int getHTerm1(int[] arr) {
        int htemp = 0;
        arr = begArr;
        for (int i = 0; i < 16; i++) {
            if ((arr[i]) == (termState1[i])) htemp++;
        }
        return 16 - htemp;
    }

    public int findEmp(int[] arr) {
        for (int i = 0; i < 16; i++) {
            if (arr[i] == -1) return i;
        }
        return -1;
    }

    public int minF(int a, int b, int c, int d) {
        int m2 = Math.min(a, b);
        if (m2 <= c & m2 <= d) return m2;
        if (c <= m2 & c <= d) return c;
        if (d <= c & d <= m2) return d;
        return m2;
    }

    public void swapCell(int dir, int tempHDir, int tempGDir, int tempFDir, int[] tempDir, int flag) {
        int tempCell = 0;
        tempGDir++;
        for (int j = 0; j < 16; j++) {
            temp[j] = begArr[j];
        }
        int empCell = findEmp(temp);
        int ttempHU = getHnumber(temp, empCell + dir);
        tempCell = temp[empCell + dir];
        temp[empCell + dir] = -1;
        temp[empCell] = tempCell;
        tempHDir = getHnumber(temp, empCell);
        flag = 0;
        if (tempHDir < ttempHU) {
            tempFDir = tempHDir + tempGDir;
            //System.out.println("dir " + dir + tempFDir);
            tempDir = temp;
            //tempA = tempDir;
            temp = new int[16];
            switch (dir) {
                case -1:
                    tempHL = tempHDir;
                    tempGL = tempGDir;
                    tempFL = tempHDir + tempGDir;
                    tempL = tempDir;
                    System.out.println("left " + tempFL);
                    break;
                case 1:
                    tempHR = tempHDir;
                    tempGR = tempGDir;
                    tempFR = tempHDir + tempGDir;
                    tempR = tempDir;
                    System.out.println("Right " + tempFR);
                    break;
                case -4:
                    tempHU = tempHDir;
                    tempGU = tempGDir;
                    tempFU = tempHDir + tempGDir;
                    tempU = tempDir;
                    System.out.println("Up " + tempFU);
                    break;
                case 4:
                    tempHD = tempHDir;
                    tempGD = tempGDir;
                    tempFD = tempHDir + tempGDir;
                    tempD = tempDir;
                    System.out.println("Down " + tempFD);
                    break;
            }
            flag = 1;
        }
    }

    public void alghor() {
        int empCell = findEmp(begArr);
        G = 0;
        tempGU = G;
        tempGD = G;
        tempGL = G;
        tempGR = G;
        tempHU = H;
        tempHD = H;
        tempHL = H;
        tempHR = H;
        tempFU = G + H;
        tempFD = G + H;
        tempFL = G + H;
        tempFR = G + H;
        while (begArr != termState1) {
            for (int i = 0; i < 4; i++) {
                empCell = findEmp(begArr);
                if (i == 0) {//UP
                    if ((empCell != 0) && (empCell != 1) && (empCell != 2) && (empCell != 3)) {
                        swapCell(UP, tempHU, tempGU, tempFU, tempU, flag);
                    } else tempFU = 1000000000;
                }
                if (i == 1) {//DOWN
                    if ((empCell != 12) && (empCell != 13) && (empCell != 14) && (empCell != 15)) {
                        swapCell(DOWN, tempHD, tempGD, tempFD, tempD, flag);
                    } else tempFD = 1000000000;
                }
                if (i == 2) {//LEFT
                    if ((empCell != 0) && (empCell != 4) && (empCell != 8) && (empCell != 12)) {
                        swapCell(LEFT, tempHL, tempGL, tempFL, tempL, flag);
                    } else tempFL = 1000000000;
                }
                if (i == 3) {//RIGHT
                    if ((empCell != 3) && (empCell != 7) && (empCell != 11) && (empCell != 15)) {
                        swapCell(RIGHT, tempHR, tempGR, tempFR, tempR, flag);
                    } else tempFR = 1000000000;
                }
            }
            F = minF(tempFU, tempFD, tempFL, tempFR);
            if ((F == tempFU)) {
                begArr = tempU;
                H = tempHU;
                G = tempGU;
            }
            if ((F == tempFD)) {
                begArr = tempD;
                H = tempHD;
                G = tempGD;
            }
            if ((F == tempFR)) {
                begArr = tempR;
                H = tempHR;
                G = tempGR;
            }
            if ((F == tempFL)) {
                begArr = tempL;
                H = tempHL;
                G = tempGL;
            }
            if (flag == 0) {

            }

            for (int i = 0; i < 16; i++) {
                System.out.print(begArr[i] + " ");
                if (i > 0)
                    if ((15 - i) % 4 == 0)
                        System.out.println();
            }
        }

    }
}
